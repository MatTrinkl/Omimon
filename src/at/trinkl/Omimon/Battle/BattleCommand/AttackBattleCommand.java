package at.trinkl.Omimon.Battle.BattleCommand;

import at.trinkl.Omimon.Battle.Attack;
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

  /**
   *
   */
  private Attack attack;
  /**
   *
   */
  private Omimon executer;

  /**
   * Constructs a new {@code AttackBattleCommand} with the given attack and executing Omimon.
   *
   * @param attack   The {@link Attack} to be executed.
   * @param executer The {@link Omimon} who will perform the attack.
   */
  public AttackBattleCommand(Attack attack, Omimon executer) {
    this.attack = attack;
    this.executer = executer;
  }

  /**
   * Executes the attack command.
   * <p>
   * Logs a message indicating the attack and calls the attack's {@code OnAttack} method, applying
   * the attack logic between attacker and defender.
   * </p>
   *
   * @param attacker The {@link Omimon} initiating the attack.
   * @param defender The {@link Omimon} being attacked.
   */
  @Override
  public void execute(Omimon attacker, Omimon defender) {
    System.out.println(
        attacker.getName() + " has attacked " + defender.getName() + " with " + attack.getName());
    attack.OnAttack(attacker, defender);
  }

  /**
   * Returns the {@link Omimon} that is assigned to execute this command.
   *
   * @return The executing {@link Omimon}.
   */
  public Omimon getExecuter() {
    return executer;
  }
}
