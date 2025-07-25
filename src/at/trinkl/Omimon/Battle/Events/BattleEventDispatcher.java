package at.trinkl.Omimon.Battle.Events;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code BattleEventDispatcher} is responsible for managing and notifying
 * {@link BattleEventListener}s about {@link BattleEvent}s that occur during a battle.
 *
 * <p>This class implements a basic event dispatcher mechanism:
 * listeners can be registered or removed, and all registered listeners will be notified when a new
 * event is dispatched.</p>
 */
public class BattleEventDispatcher {

  /**
   * The list of registered {@link BattleEventListener}s.
   */
  private final List<BattleEventListener> listeners = new ArrayList<>();

  /**
   * Registers a new listener to receive battle events.
   *
   * @param listener the listener to add
   */
  public void addListener(BattleEventListener listener) {
    listeners.add(listener);
  }

  /**
   * Unregisters a listener so it no longer receives battle events.
   *
   * @param listener the listener to remove
   */
  public void removeListener(BattleEventListener listener) {
    listeners.remove(listener);
  }

  /**
   * Dispatches a {@link BattleEvent} to all registered listeners.
   *
   * @param event the event to dispatch
   */
  public void dispatch(BattleEvent event) {
    for (BattleEventListener listener : listeners) {
      listener.onBattleEvent(event);
    }
  }
}

