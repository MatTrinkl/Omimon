package at.trinkl.Omimon.Battle.BattleCommand;

import at.trinkl.Omimon.Battle.Battle;
import at.trinkl.Omimon.Omimon.Omimon;

/**
 * A concrete implementation of {@link BattleCommand} that handles an escape action of a wild
 * {@link Omimon} during a battle.
 * <p>
 * When executed, this command signals the {@link Battle} instance that the wild {@link Omimon}
 * has fled the battle.
 * </p>
 */
public class EscapeBattleCommand implements BattleCommand {

  private Battle battle;
  private Omimon omimonEscaped;

  /**
   * Creates a new {@code EscapeBattleCommand} with the given battle context and fleeing Omimon.
   *
   * @param battle     The {@link Battle} in which the escape occurs.
   * @param omimonFlee The {@link Omimon} attempting to flee the battle.
   */
  public EscapeBattleCommand(Battle battle, Omimon omimonFlee) {
    this.battle = battle;
    this.omimonEscaped = omimonFlee;
  }

  /**
   * Executes the escape command, notifying the battle system that the specified Omimon has fled.
   *
   * @param attacker Unused in this command, included for interface compatibility.
   * @param defender Unused in this command, included for interface compatibility.
   */
  @Override
  public void execute(Omimon attacker, Omimon defender) {
    battle.OmimonEscaped(omimonEscaped);
  }

  /**
   * Returns the {@link Omimon} that is escaping the battle.
   *
   * @return The fleeing {@link Omimon}.
   */
  @Override
  public Omimon getExecuter() {
    return omimonEscaped;
  }
}
