package at.trinkl.Omimon.Battle.BattleCommand;

import at.trinkl.Omimon.Omimon.Omimon;

/**
 * Represents a battle command that will be executed during a
 * {@link at.trinkl.Omimon.Battle.Battle}.
 * <p>
 * Implementations define specific actions performed in battle, such as attacking, switching
 * {@link Omimon}, or escaping a fight. The command pattern is used to encapsulate the execution
 * logic.
 * </p>
 */
public interface BattleCommand {

  /**
   * Executes the battle command using the specified attacker and defender.
   *
   * @param attacker The {@link Omimon} executing the command.
   * @param defender The {@link Omimon} targeted by the command.
   */
  public void execute(Omimon attacker, Omimon defender);

  /**
   * Returns the {@link Omimon} associated with this command instance.
   * <p>
   * This is typically the entity that is effected or owns the command, and may be used for display or
   * tracking purposes.
   * </p>
   *
   * @return The {@link Omimon} that will execute or has executed this command.
   */
  public Omimon getExecuter();
}
