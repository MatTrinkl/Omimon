package at.trinkl.Omimon.Battle;

import at.trinkl.Omimon.Battle.BattleCommand.AttackBattleCommand;
import at.trinkl.Omimon.Battle.BattleCommand.BattleCommand;
import at.trinkl.Omimon.Battle.BattleCommand.BattleCommandFactory;
import at.trinkl.Omimon.Battle.BattleCommand.EscapeBattleCommand;
import at.trinkl.Omimon.Battle.BattleCommand.SwitchBattleCommand;
import at.trinkl.Omimon.Battle.Events.BattleEvent;
import at.trinkl.Omimon.Battle.Events.BattleEventDispatcher;
import at.trinkl.Omimon.Battle.Events.BattleEventListener;
import at.trinkl.Omimon.Battle.Events.BattleEventType;
import at.trinkl.Omimon.Battle.Strategy.ActionStrategy.ActionStrategy;
import at.trinkl.Omimon.Battle.Strategy.ActionStrategy.CautiousActionStrategy;
import at.trinkl.Omimon.Battle.Strategy.BattleStrategy.BattleStrategy;
import at.trinkl.Omimon.Omimon.Omimon;
import at.trinkl.Omimon.Trainer;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Represents the full turn-based battle system between two {@link Trainer}s or between a trainer
 * and a wild {@link Omimon}.
 * <p>
 * Handles turn preparation, action execution, health checks, switching, escaping, and victory
 * conditions. This class implements {@link BattleContext} to expose only essential control methods
 * to dependent components like {@link Omimon} and {@link BattleCommand}.
 * </p>
 */
public class Battle implements BattleContext {

  private final Queue<BattleCommand> commandQueue;

  private final Trainer trainerA;
  private final ActionStrategy actionStrategyTrainerA;

  private Trainer trainerB;
  private ActionStrategy actionStrategyTrainerBOrWildOmimon;

  private Omimon fighterA, fighterB;
  private boolean victoryIsTriggered;
  private final BattleCommandFactory commandFactory = new BattleCommandFactory(this);
  private final BattleEventDispatcher eventDispatcher = new BattleEventDispatcher();

  /**
   * Private base constructor. Sets up fighterA and initializes the queue.
   */
  private Battle(Trainer trainerA) {
    this.trainerA = trainerA;
    this.fighterA = this.trainerA.getRandomOmimonWithCanFight();
    this.fighterA.registerToBattle(this);
    this.commandQueue = new LinkedList<>();
    actionStrategyTrainerA = trainerA.getActionStrategy();
    victoryIsTriggered = false;
  }

  /**
   * Creates a battle between a trainer and a wild {@link Omimon}.
   *
   * @param trainerA   The player's trainer.
   * @param wildOmimon The wild opponent.
   */
  public Battle(Trainer trainerA, Omimon wildOmimon) {
    this(trainerA);
    this.trainerB = null;
    this.fighterB = wildOmimon;
    this.fighterB.registerToBattle(this);
    actionStrategyTrainerBOrWildOmimon = new CautiousActionStrategy();
  }

  /**
   * Creates a battle between two trainers.
   *
   * @param trainerA The first trainer.
   * @param trainerB The opposing trainer.
   */
  public Battle(Trainer trainerA, Trainer trainerB) {
    this(trainerA);
    this.trainerB = trainerB;
    this.fighterB = this.trainerB.getRandomOmimonWithCanFight();
    this.fighterB.registerToBattle(this);
    actionStrategyTrainerBOrWildOmimon = trainerB.getActionStrategy();
  }

  /**
   * Starts and executes the full battle loop until one side wins or loses.
   */
  public void executeBattle() {
    if (trainerB == null) {
      while (trainerA.hasBattleReadyOmimons() && fighterB.isAlive() && !victoryIsTriggered) {
        prepareRound();
        executeRound();
      }
    } else {
      while (trainerA.hasBattleReadyOmimons() && trainerB.hasBattleReadyOmimons()
          && !victoryIsTriggered) {
        prepareRound();
        executeRound();
      }
    }
  }

  public void addBattleEventListener(BattleEventListener listener) {
    eventDispatcher.addListener(listener);
  }

  /**
   * Replaces a fainted or switched {@link Omimon} with a new one in the battle.
   *
   * @param omimonToSwitch The Omimon currently in battle to be removed.
   * @param newOmimon      The new Omimon to bring in.
   * @throws IllegalArgumentException if {@code omimonToSwitch} is not currently fighting.
   */
  public void switchOmimon(Omimon omimonToSwitch, Omimon newOmimon) {
    omimonToSwitch.deRegisterFromBattle();
    newOmimon.registerToBattle(this);
    if (fighterA == omimonToSwitch) {
      fighterA = newOmimon;
    } else if (fighterB == omimonToSwitch) {
      fighterB = newOmimon;
    } else {
      throw new IllegalArgumentException(
          omimonToSwitch.getName() + " does not belong to the fighter");
    }
    for (BattleCommand b : commandQueue) {
      b.updateOmimons(omimonToSwitch, newOmimon);
    }
  }

  /**
   * Handles escape attempts by wild {@link Omimon}s.
   *
   * @param omimonEscaped The escaping Omimon.
   * @throws IllegalArgumentException if {@code TrainerB} is not null and this is a trainer battle.
   */
  @Override
  public void omimonEscaped(Omimon omimonEscaped) {
    if (fighterB == omimonEscaped && trainerB == null) {
      triggerVictory(trainerA);
    } else {
      throw new IllegalArgumentException(
          "Trainer B is not null. You cannot escape from a Trainer Battle. Only from Battle with wild Omimons.");
    }
  }

  @Override
  public void dispatchEvent(BattleEvent battleEvent) {
    eventDispatcher.dispatch(battleEvent);
  }

  /**
   * Declares the victory of a {@link Trainer} and ends the battle.
   *
   * @param trainer The winning trainer, or {@code null} if the player has lost to a wild Omimon.
   */
  @Override
  public void triggerVictory(Trainer trainer) {
    victoryIsTriggered = true;
    fighterA.deRegisterFromBattle();
    fighterB.deRegisterFromBattle();

    eventDispatcher.dispatch(new BattleEvent(
        BattleEventType.VICTORY,
        trainer != null
            ? trainer.getName() + " has won the battle."
            : "The wild Omimon has defeated you."
    ));

  }

  /**
   * Decides whether a {@link Trainer} should switch to another {@link Omimon} or if the battle is
   * over (i.e., no Omimons left).
   *
   * @param deadOmimonTrainer The trainer whose Omimon has fainted.
   * @param deadOmimon        The defeated Omimon.
   * @param other             The opposing trainer (may be {@code null} if wild).
   */
  private void checkVictoryOrSwitch(Trainer deadOmimonTrainer, Omimon deadOmimon, Trainer other) {
    if (deadOmimonTrainer.hasBattleReadyOmimons()) {
      switchOmimon(deadOmimon, deadOmimonTrainer.getRandomOmimonWithCanFight());
    } else {
      triggerVictory(other);
    }
  }

  /**
   * Prepares a round by determining each fighter’s intended {@link BattleAction} using their
   * respective {@link ActionStrategy}.
   * <p>
   * The order of execution is based on action priority and speed. Commands for each action are
   * added to the internal {@code commandQueue}.
   * </p>
   */
  private void prepareRound() {
    BattleAction battleActionFighterA = actionStrategyTrainerA.getNextActionByStrategy(fighterA,
        fighterB);
    BattleAction battleActionFighterB = actionStrategyTrainerBOrWildOmimon.getNextActionByStrategy(
        fighterB, fighterA);

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

  /**
   * Converts a {@link BattleAction} into a concrete {@link BattleCommand} and adds it to the
   * {@code commandQueue}.
   * <p>
   * SWITCH adds a {@link SwitchBattleCommand} or {@link EscapeBattleCommand} (for wild Omimon).
   * ATTACK adds an {@link AttackBattleCommand} based on the attacker's {@link BattleStrategy}.
   * </p>
   *
   * @param actionToExecute The selected action to execute.
   * @param attacker        The {@link Omimon} performing the action.
   * @param defender        The target {@link Omimon}.
   */
  private void executeAction(BattleAction actionToExecute, Omimon attacker, Omimon defender) {
    switch (actionToExecute) {
      case SWITCH:
        if (attacker == fighterB && trainerB == null) {
          commandQueue.add(commandFactory.createEscapeCommand(attacker));
        }
        commandQueue.add(commandFactory.createSwitchCommand(attacker));
        break;
      case ATTACK:
        BattleStrategy battleStrategy = attacker.getBlueprint().getBattleStrategy();
        Attack attackFromStrategy = battleStrategy.selectAttackFromStrategy(attacker, defender);
        commandQueue.add(
            commandFactory.createAttackCommand(this, attackFromStrategy, attacker, defender));
    }
  }

  /**
   * Executes all {@link BattleCommand}s in the queue for the current round.
   * <p>
   * Commands are resolved in the order they were added during {@link #prepareRound()}.
   * </p>
   */
  private void executeRound() {

    while (!commandQueue.isEmpty()) {

      BattleCommand battleCommand = commandQueue.poll();
      if (battleCommand.getExecuter() == fighterA) {
        battleCommand.execute();
      } else {
        battleCommand.execute();
      }
    }
  }

  /**
   * Handles a situation where an {@link Omimon} has fainted mid-round. Cancels all pending actions
   * and attempts to switch in a new fighter or declares victory if no options remain.
   *
   * @param deadOmimon The Omimon that fainted.
   */
  public void notifyOmimonFainted(Omimon deadOmimon) {
    commandQueue.clear();
    dispatchEvent(new BattleEvent(
        BattleEventType.OMIMON_FAINTED,
        deadOmimon.getName() + " has fainted!"
    ));
    if (deadOmimon.equals(fighterA)) {
      checkVictoryOrSwitch(trainerA, deadOmimon, trainerB);
    } else if (deadOmimon.equals(fighterB)) {
      if (trainerB == null) {
        triggerVictory(trainerA);
      } else {
        checkVictoryOrSwitch(trainerB, deadOmimon, trainerA);
      }
    } else {
      throw new IllegalArgumentException("Dead Omimon not part of the fight.");
    }

  }
}
