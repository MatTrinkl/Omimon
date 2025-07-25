package at.trinkl.Omimon.Battle.Strategy.BattleStrategy;

import at.trinkl.Omimon.Battle.Attack;
import at.trinkl.Omimon.Omimon.Omimon;

/**
 * Defines a strategy for selecting a specific {@link Attack} to be used in battle.
 * <p>
 * Implementations of this interface determine which {@link Attack} an {@link Omimon} should use
 * based on the current battle state, allowing for different tactical behaviors such as selecting
 * the strongest, most effective, or a random attack.
 * </p>
 */
public interface BattleStrategy {

  /**
   * Selects an {@link Attack} to be executed by the attacker against the defender.
   *
   * @param attacker The {@link Omimon} performing the attack.
   * @param defender The {@link Omimon} being targeted.
   * @return The {@link Attack} chosen according to this strategy.
   */
  Attack selectAttackFromStrategy(Omimon attacker, Omimon defender);
}
