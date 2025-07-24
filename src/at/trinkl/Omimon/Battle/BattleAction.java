package at.trinkl.Omimon.Battle;

import at.trinkl.Omimon.Battle.Strategy.ActionStrategy.ActionStrategy;
import at.trinkl.Omimon.Battle.Strategy.BattleStrategy.BattleStrategy;
import at.trinkl.Omimon.Omimon.Omimon;

/**
 * Represents the possible actions an {@link Omimon} can take during a battle turn.
 * <p>
 * These actions are used by {@link ActionStrategy} implementations to determine the combat behavior
 * of an Omimon.
 * </p>
 */
public enum BattleAction {
  /**
   * Perform an attack on the opposing {@link Omimon}. The specific {@link Attack} is selected using
   * the {@link BattleStrategy}.
   */
  ATTACK,

  /**
   * Switch out the current {@link Omimon} for another one from the trainer's team. If the battle is
   * against a wild Omimon, this may also result in an escape attempt.
   */
  SWITCH
}
