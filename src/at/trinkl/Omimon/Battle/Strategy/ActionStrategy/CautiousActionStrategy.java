package at.trinkl.Omimon.Battle.Strategy.ActionStrategy;

import at.trinkl.Omimon.Battle.BattleAction;
import at.trinkl.Omimon.Omimon.Omimon;

/**
 * An implementation of {@link ActionStrategy} that chooses actions cautiously based on the
 * attacker's current health.
 * <p>
 * This strategy favors switching out the active {@link Omimon} when its health is low, and
 * otherwise proceeds with a standard attack. It is intended to simulate a more defensive or
 * survival-oriented decision-making style.
 * </p>
 */
public class CautiousActionStrategy implements ActionStrategy {

  /**
   * Chooses the next action based on the attacker's current health.
   * <p>
   * If the attacker's health is below 125 (50%), the strategy opts to switch out. Otherwise, it returns
   * an attack action.
   * </p>
   *
   * @param attacker The {@link Omimon} which is attacking.
   * @param defender The opposing {@link Omimon}.
   * @return {@code BattleAction.SWITCH} if health < 50%, else {@code BattleAction.ATTACK}.
   */
  @Override
  public BattleAction getNextActionByStrategy(Omimon attacker, Omimon defender) {
    if (attacker.isHealthBelowPercentage(0.5)) {
      return BattleAction.SWITCH;
    }
    return BattleAction.ATTACK;
  }
}
