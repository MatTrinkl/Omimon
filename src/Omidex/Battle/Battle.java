package Omidex.Battle;

import Omidex.Battle.BattleCommand.AttackBattleCommand;
import Omidex.Battle.BattleCommand.BattleCommand;
import Omidex.Battle.BattleCommand.EscapeBattleCommand;
import Omidex.Battle.BattleCommand.SwitchBattleCommand;
import Omidex.Battle.Strategy.ActionStrategy.ActionStrategy;
import Omidex.Battle.Strategy.ActionStrategy.CautiousActionStrategy;
import Omidex.Battle.Strategy.BattleStrategy.BattleStrategy;
import Omidex.Omimon.Omimon;
import Omidex.Trainer;
import java.util.LinkedList;
import java.util.Queue;

public class Battle {

  private final Queue<BattleCommand> commandQueue;

  private final Trainer trainerA;
  private final ActionStrategy actionStrategyTrainerA;

  private Trainer trainerB;
  private ActionStrategy actionStrategyTrainerBOrWildOmimon;

  private Omimon fighterA, fighterB;
  private boolean victoryIsTriggered;


  private Battle(Trainer trainerA) {
    this.trainerA = trainerA;
    this.fighterA = this.trainerA.getRandomOmimonWithCanFight();
    this.fighterA.registerToBattle(this);
    this.commandQueue = new LinkedList<>();
    actionStrategyTrainerA = trainerA.getActionStrategy();
    victoryIsTriggered = false;
  }

  public Battle(Trainer trainerA, Omimon wildOmimon) {
    this(trainerA);
    this.trainerB = null;
    this.fighterB = wildOmimon;
    this.fighterB.registerToBattle(this);
    actionStrategyTrainerBOrWildOmimon = new CautiousActionStrategy();
  }

  public Battle(Trainer trainerA, Trainer trainerB) {
    this(trainerA);
    this.trainerB = trainerB;
    this.fighterB = this.trainerB.getRandomOmimonWithCanFight();
    this.fighterB.registerToBattle(this);
    actionStrategyTrainerBOrWildOmimon = trainerB.getActionStrategy();
  }

  public void executeBattle() {
    if (trainerB == null) {
      while (trainerA.hasBattleReadyOmimons() && fighterB.isAlive()) {
        prepareRound();
        executeRound();
      }
    } else {
      while (trainerA.hasBattleReadyOmimons() && trainerB.hasBattleReadyOmimons()) {
        prepareRound();
        executeRound();
      }
    }
  }

  public void cancelCurrentRoundAndSendNewOmimonOut(Omimon deadOmimon) {
    commandQueue.clear();

    if (deadOmimon.equals(fighterA)) {
      checkVictoryOrSwitch(trainerA, deadOmimon, trainerB);
    } else if (deadOmimon.equals(fighterB)) {
      if (trainerB == null) {
        Victory(trainerA);
      } else {
        checkVictoryOrSwitch(trainerB, deadOmimon, trainerA);
      }
    } else {
      throw new IllegalArgumentException("Dead Omimon not part of the fight.");
    }
  }

  private void checkVictoryOrSwitch(Trainer deadOmimonTrainer, Omimon deadOmimon, Trainer other) {
    if (deadOmimonTrainer.hasBattleReadyOmimons()) {
      switchOmimon(deadOmimon, deadOmimonTrainer.getRandomOmimonWithCanFight());
    } else {
      Victory(other);
    }
  }

  private void prepareRound() {
    BattleAction battleActionFighterA = actionStrategyTrainerA.getNextActionByStrategy(fighterA,fighterB);
    BattleAction battleActionFighterB = actionStrategyTrainerBOrWildOmimon.getNextActionByStrategy(fighterB,fighterA);

    int comparison = battleActionFighterA.compareTo(battleActionFighterB);
    if (comparison == 0) {
      if (fighterA.getCurrentSpeed() >= fighterB.getCurrentSpeed()) {
        executeAction(battleActionFighterA, fighterA, fighterB);
        executeAction(battleActionFighterB, fighterB, fighterA);
      } else {
        executeAction(battleActionFighterB, fighterB, fighterA);
        executeAction(battleActionFighterA, fighterA, fighterB);
      }
    } else if (comparison > 0) {
      executeAction(battleActionFighterA, fighterA, fighterB);
      executeAction(battleActionFighterB, fighterB, fighterA);
    } else {
      executeAction(battleActionFighterB, fighterB, fighterA);
      executeAction(battleActionFighterA, fighterA, fighterB);
    }

  }

  private void executeAction(BattleAction actionToExecute, Omimon attacker, Omimon defender) {
    switch (actionToExecute) {
      case SWITCH:
        if (attacker == fighterB && trainerB == null) {
          commandQueue.add(new EscapeBattleCommand(this, attacker));
        }
        commandQueue.add(new SwitchBattleCommand(this, attacker.getTrainer(), attacker));
        break;
      case ATTACK:
        BattleStrategy battleStrategy = attacker.getBlueprint().getBattleStrategy();
        Attack attackFromStrategy = battleStrategy.selectAttackFromStrategy(attacker, defender);
        commandQueue.add(new AttackBattleCommand(attackFromStrategy, attacker));
    }
  }

  private void executeRound() {

    while (!commandQueue.isEmpty()) {

      BattleCommand battleCommand= commandQueue.poll();
      if(battleCommand.getExecuter()==fighterA){
        battleCommand.execute(fighterA,fighterB);
      }else{
        battleCommand.execute(fighterB,fighterA);
      }
    }
  }


  public void switchOmimon(Omimon omimonToSwitch, Omimon newOmimon) {

    if (fighterA == omimonToSwitch) {
      fighterA.deRegisterFromBattle();
      fighterA = newOmimon;
      fighterA.registerToBattle(this);
    } else if (fighterB == omimonToSwitch) {
      fighterB.deRegisterFromBattle();
      fighterB = newOmimon;
      fighterB.registerToBattle(this);
    } else {
      throw new IllegalArgumentException(
          omimonToSwitch.getName() + " does not belong to the fighter");
    }

  }

  public void OmimonEscaped(Omimon omimonEscaped) {
    if (fighterB == omimonEscaped && trainerB == null) {
      Victory(trainerA);
    } else {
      throw new IllegalArgumentException(
          "Trainer B is not null. You cannot escape from a Trainer Battle. Only from Battle with wild Omimons.");
    }
  }

  public void Victory(Trainer trainer) {
    victoryIsTriggered = true;
    fighterA.deRegisterFromBattle();
    fighterB.deRegisterFromBattle();

    if (trainer == null) {
      System.out.println(
          "The wild Omimon has defeted you. You will respawn at the next OmiCenter.");
    } else {
      System.out.println(trainer.getName() + " ID:" + trainer.getId()
          + " has won the battle. He defeated all of his opponents Omimons.");
    }
  }
}
