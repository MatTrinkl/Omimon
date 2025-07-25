package at.trinkl.Omimon.Battle.Events;

/**
 * The {@code ConsoleBattleLogger} is a simple implementation of the
 * {@link BattleEventListener} interface that logs battle events to the console.
 *
 * <p>This class can be useful for debugging or development purposes,
 * as it provides real-time feedback on battle events in a readable format.</p>
 */
public class ConsoleBattleLogger implements BattleEventListener {

  /**
   * Called when a {@link BattleEvent} occurs. Logs the event type and message
   * to the standard output (console).
   *
   * @param event the battle event to log
   */
  @Override
  public void onBattleEvent(BattleEvent event) {
    System.out.println("[BattleEvent] " + event.getType() + ": " + event.getMessage());
  }
}


