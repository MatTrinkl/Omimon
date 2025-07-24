package at.trinkl.Omimon.Battle.Strategy.ActionStrategy;

import at.trinkl.Omimon.Battle.BattleAction;
import at.trinkl.Omimon.Omimon.Omimon;

/**
 * An implementation of {@link ActionStrategy} that always chooses to attack.
 * <p>
 * This strategy represents a confident or aggressive behavior style, where
 * the {@link Omimon} never considers switching and always attempts to deal damage.
 * </p>
 */
public class ConfidentActionStrategy implements ActionStrategy {

  /**
   * Always returns {@link BattleAction#ATTACK}, regardless of the current battle state.
   *
   * @param attacker  The {@link Omimon} which is attacking.
   * @param defender  The opposing {@link Omimon}.
   * @return          Always {@link BattleAction#ATTACK}.
   */
  @Override
  public BattleAction getNextActionByStrategy(Omimon attacker, Omimon defender) {
    return BattleAction.ATTACK;
  }
}
