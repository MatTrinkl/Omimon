package at.trinkl.Omimon.Battle.Events;

public class ConsoleBattleLogger implements BattleEventListener {

  @Override
  public void onBattleEvent(BattleEvent event) {
    System.out.println("[BattleEvent] " + event.getType() + ": " + event.getMessage());
  }
}

