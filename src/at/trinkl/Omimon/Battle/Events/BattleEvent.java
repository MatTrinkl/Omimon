package at.trinkl.Omimon.Battle.Events;

import at.trinkl.Omimon.Omimon.Omimon;
import at.trinkl.Omimon.Trainer;

public class BattleEvent {
  private final BattleEventType type;
  private final String message;

  public BattleEvent(BattleEventType type, String message) {
    this.type = type;
    this.message = message;
  }

  public BattleEventType getType() {
    return type;
  }

  public String getMessage() {
    return message;
  }
}

