package Omidex.Battle.Strategy.ActionStrategy;

import Omidex.Battle.BattleAction;
import Omidex.Omimon.Omimon;

public class ConfidentActionStrategy implements ActionStrategy {

  @Override
  public BattleAction getNextActionByStrategy(Omimon attacker, Omimon defender) {
    return BattleAction.ATTACK;
  }
}
