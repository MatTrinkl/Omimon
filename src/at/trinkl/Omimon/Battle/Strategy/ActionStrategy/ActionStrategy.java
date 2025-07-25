package at.trinkl.Omimon.Battle.Strategy.ActionStrategy;

import at.trinkl.Omimon.Battle.BattleAction;
import at.trinkl.Omimon.Omimon.Omimon;

/**
 * Defines a strategy for selecting the next {@link BattleAction} in a battle context.
 * <p>
 * Implementations of this interface encapsulate decision-making logic for choosing what action an
 * {@link Omimon} should take during its turn. This allows for different behavior patterns such as
 * confident, smart and cautious.
 * </p>
 */
public interface ActionStrategy {

  /**
   * Determines the next {@link BattleAction} to be executed based on the current battle state.
   *
   * @param attacker The {@link Omimon} whose turn it is to act.
   * @param defender The {@link Omimon} currently being targeted or opposed.
   * @return The {@link BattleAction} selected according to this strategy.
   */
  BattleAction getNextActionByStrategy(Omimon attacker, Omimon defender);
}
