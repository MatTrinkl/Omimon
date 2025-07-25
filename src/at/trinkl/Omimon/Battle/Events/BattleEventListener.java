package at.trinkl.Omimon.Battle.Events;

/**
 * The {@code BattleEventListener} interface should be implemented by any class that wants to be
 * notified of {@link BattleEvent}s during a battle.
 *
 * <p>Listeners can be registered with a {@link BattleEventDispatcher}, which
 * will call {@link #onBattleEvent(BattleEvent)} whenever a new event occurs.</p>
 */
public interface BattleEventListener {

  /**
   * Called when a {@link BattleEvent} is dispatched.
   *
   * @param event the event that occurred
   */
  void onBattleEvent(BattleEvent event);
}

