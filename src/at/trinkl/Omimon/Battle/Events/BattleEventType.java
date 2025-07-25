package at.trinkl.Omimon.Battle.Events;

/**
 * The {@code BattleEventType} enum defines the types of events
 * that can occur during a battle and be communicated via {@link BattleEvent}.
 *
 * <p>Each value represents a specific kind of in-game event, such as a
 * fainted Omimon, a successful attack, or the end of a battle.</p>
 */
public enum BattleEventType {

  /**
   * Indicates that an Omimon has fainted.
   */
  OMIMON_FAINTED,

  /**
   * Indicates that a battle has ended and the victory is triggered.
   */
  VICTORY,

  /**
   * Indicates that an attack was successfully executed.
   */
  ATTACK_EXECUTED,

  /**
   * Indicates that an Omimon switch was performed.
   */
  SWITCH_PERFORMED,

  /**
   * Indicates that an attempt to escape the battle was successful.
   */
  ESCAPE_SUCCESSFUL,

  /**
   * Indicates that a listener was successfully registered.
   */
  REGISTER_SUCCESSFUL,

  /**
   * Indicates that a listener was successfully deregistered.
   */
  DEREGISTER_SUCCESSFUL
}
