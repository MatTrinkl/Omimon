package Omidex.Battle.Strategy.ActionStrategy;

import Omidex.Battle.BattleAction;
import Omidex.Omimon.Omimon;

public class CautiousActionStrategy implements ActionStrategy {

  @Override
  public BattleAction getNextActionByStrategy(Omimon Attacker, Omimon defender) {
    if(Attacker.getCurrentHealth()<125)
      return BattleAction.SWITCH;
    return BattleAction.ATTACK;
  }
}
