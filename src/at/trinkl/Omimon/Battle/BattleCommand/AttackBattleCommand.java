package at.trinkl.Omimon.Battle.BattleCommand;

import at.trinkl.Omimon.Battle.Attack;
import at.trinkl.Omimon.Battle.BattleContext;
import at.trinkl.Omimon.Battle.Events.BattleEvent;
import at.trinkl.Omimon.Battle.Events.BattleEventType;
import at.trinkl.Omimon.Omimon.Omimon;

/**
 * A concrete implementation of {@link BattleCommand} that performs an {@link Attack} during a
 * battle.
 * <p>
 * This command encapsulates an attack action initiated by an {@link Omimon}, including the specific
 * attack and its execution logic.
 * </p>
 */
public class AttackBattleCommand implements BattleCommand {

  private final Attack attack;
  private Omimon executer;
  private final BattleContext battleContext;
  private Omimon defender;

  /**
   * Constructs a new {@code AttackBattleCommand} with the given attack and executing Omimon.
   *
   * @param attack   The {@link Attack} to be executed.
   * @param executer The {@link Omimon} who will perform the attack.
   * @param defender The {@link Omimon} being attacked.
   * @param context  The current battle.
   */
  public AttackBattleCommand(BattleContext context, Attack attack, Omimon executer,
      Omimon defender) {
    this.battleContext = context;
    this.attack = attack;
    this.executer = executer;
    this.defender = defender;
  }

  /**
   * Executes the attack command.
   * <p>
   * Logs a message indicating the attack and calls the attack's {@code OnAttack} method, applying
   * the attack logic between attacker and defender.
   * </p>
   */
  @Override
  public void execute() {
    battleContext.dispatchEvent(new BattleEvent(BattleEventType.ATTACK_EXECUTED,
        executer.getName() + " has attacked " + defender.getName() + " with " + attack.getName()));
    attack.OnAttack(executer, defender);
  }

  /**
   * Returns the {@link Omimon} that is assigned to execute this command.
   *
   * @return The executing {@link Omimon}.
   */
  public Omimon getExecuter() {
    return executer;
  }

  /**
   * Updates the references of a {@link Omimon} in a command.
   *
   * @param oldOne The {@link Omimon} to update.
   * @param newOne The new {@link Omimon}.
   */
  @Override
  public void updateOmimons(Omimon oldOne, Omimon newOne) {
    if (oldOne == executer) {
      executer = newOne;
    } else if (oldOne == defender) {
      defender = newOne;
    }
  }
}
