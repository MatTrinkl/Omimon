package Omidex.Battle.BattleCommand;

import Omidex.Battle.Battle;
import Omidex.Omimon.Omimon;
import Omidex.Trainer;

public class SwitchBattleCommand implements BattleCommand {
private Battle battle;
private Trainer trainer;
private Omimon omimonToSwitch;
public SwitchBattleCommand(Battle battle, Trainer trainer, Omimon omimonToSwitch) {
  this.battle = battle;
  this.trainer = trainer;
  this.omimonToSwitch = omimonToSwitch;
}

  @Override
  public void execute() {
    battle.switchOmimon(omimonToSwitch, trainer.getRandomOmimonWithCanFight());
  }
}
