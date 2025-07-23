package Omidex.Battle;

import Omidex.Battle.BattleCommand.BattleCommand;
import Omidex.Omimon.Omimon;
import Omidex.Trainer;
import java.util.LinkedList;
import java.util.Queue;

public class Battle {

  private Trainer trainerA, trainerB;
  private Omimon fighterA, fighterB;
  private Queue<BattleCommand> commandQueue;

  public Battle(Trainer trainerA, Omimon wildOmimon) {
    this.trainerA = trainerA;
    this.trainerB = null;
    this.fighterA = this.trainerA.getRandomOmimonWithCanFight();
    this.fighterB = wildOmimon;
    this.fighterA.registerToBattle(this);
    this.fighterB.registerToBattle(this);
    this.commandQueue = new LinkedList<>();
  }

  public Battle(Trainer trainerA, Trainer trainerB) {
    this.trainerA = trainerA;
    this.trainerB = trainerB;
    this.fighterA = this.trainerA.getRandomOmimonWithCanFight();
    this.fighterB = this.trainerB.getRandomOmimonWithCanFight();
    this.fighterA.registerToBattle(this);
    this.fighterB.registerToBattle(this);
    this.commandQueue = new LinkedList<>();
  }

  public void executeBattle() {
    if (trainerB != null) {
      while (trainerA.hasBattleReadyOmimons() && fighterB.isAlive()){
        prepareRound();
        executeRound();
      }
    }else{
      while (trainerA.hasBattleReadyOmimons()&& trainerB.hasBattleReadyOmimons()) {
        prepareRound();
        executeRound();
      }
    }
  }
  public void cancleCurrentRoundAndSendNewOmimonOut(Omimon deadOmimon) {
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

  }

  private void executeRound() {
    while (!commandQueue.isEmpty()) {
      commandQueue.poll().execute();
    }
  }



  public void switchOmimon(Omimon omimonToSwitch, Omimon newOmimon) {
    if (fighterA == omimonToSwitch) {
      fighterA.deRegisterFromBattle();
      fighterA = newOmimon;
    } else if (fighterB == omimonToSwitch) {
      fighterB = newOmimon;
    } else {
      throw new IllegalArgumentException(
          omimonToSwitch.getName() + " does not belong to the fighter");
    }
  }

  public void OmimonEscaped(Omimon omimonEscaped) {
    if (fighterB == omimonEscaped && trainerB == null) {
      Victory(trainerA);
    } else {
      throw new IllegalArgumentException("Trainer B is not null. You cannot escape from a Trainer Battle. Only from Battle with wild Omimons.)";
    }
  }

  public void Victory(Trainer trainer) {
    //deregister all Omimon from battle

    if(trainer == null){
      // wild Omimon has won
    }
    else{
      // Trainer has won
    }
  }


  //Commandpattern nutzen

  //observer mit omimon sagen wann sie dran sind


}
