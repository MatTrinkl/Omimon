package Omidex.Battle.Strategy.ActionStrategy;

import Omidex.Battle.BattleAction;
import Omidex.Omimon.Omimon;

public class CautiousActionStrategy implements ActionStrategy {

  private Omimon attacker;

  public CautiousActionStrategy() {
    this.attacker = attacker;
  }
  @Override
  public BattleAction getNextActionByStrategy(Omimon attacker, Omimon defender) {
    if(attacker.getCurrentHealth()<125)
      return BattleAction.SWITCH;
    return BattleAction.ATTACK;
  }
}
