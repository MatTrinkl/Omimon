package at.trinkl.Omimon.Battle.Events;

/**
 * The {@code BattleEvent} class represents a single event that occurs during a battle in the game.
 * Each event has a specific type and an associated message that can be displayed in the UI or
 * recorded in a battle log.
 *
 * <p>This class is immutable, as all its fields are declared {@code final}.</p>
 */
public class BattleEvent {

  /**
   * The type of the event, e.g., ATTACK, DAMAGE, STATUS_CHANGE, etc.
   */
  private final BattleEventType type;

  /**
   * The message describing the event, e.g., "Omimon used Fire Blast!".
   */
  private final String message;

  /**
   * Constructs a new {@code BattleEvent} with the specified type and message.
   *
   * @param type    the type of the event
   * @param message the descriptive message for the event
   */
  public BattleEvent(BattleEventType type, String message) {
    this.type = type;
    this.message = message;
  }

  /**
   * Returns the type of this event.
   *
   * @return the event type
   */
  public BattleEventType getType() {
    return type;
  }

  /**
   * Returns the descriptive message of this event.
   *
   * @return the event message
   */
  public String getMessage() {
    return message;
  }
}

