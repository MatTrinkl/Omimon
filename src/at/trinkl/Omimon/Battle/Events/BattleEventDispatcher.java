package at.trinkl.Omimon.Battle.Events;

import java.util.ArrayList;
import java.util.List;

public class BattleEventDispatcher {

  private final List<BattleEventListener> listeners = new ArrayList<>();

  public void addListener(BattleEventListener listener) {
    listeners.add(listener);
  }

  public void removeListener(BattleEventListener listener) {
    listeners.remove(listener);
  }

  public void dispatch(BattleEvent event) {
    for (BattleEventListener listener : listeners) {
      listener.onBattleEvent(event);
    }
  }
}

