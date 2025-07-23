package Omidex.Battle.BattleCommand;

import Omidex.Battle.Battle;
import Omidex.Omimon.Omimon;

public class EscapeBattleCommand implements BattleCommand {
  private Battle battle;
  private Omimon omimonEscaped;
  public EscapeBattleCommand(Battle battle, Omimon omimonFlee) {
    this.battle = battle;
    this.omimonEscaped = omimonFlee;
  }


  @Override
  public void execute() {
    battle.OmimonEscaped(omimonEscaped);
  }
}
