package Omidex.Battle.Strategy.ActionStrategy;

import Omidex.Battle.BattleAction;
import Omidex.Omimon.*;

public class SmartActionStrategy implements ActionStrategy {

  @Override
  public BattleAction getNextActionByStrategy(Omimon attacker, Omimon defender) {
    double typeDifference = 1;
    OmiType attMain = attacker.getBlueprint().getMainType();
    OmiType defMain = defender.getBlueprint().getMainType();
    OmiType attSec = attacker.getBlueprint().getSecoundaryType();
    OmiType defSec = defender.getBlueprint().getSecoundaryType();
    typeDifference *= attMain.compareTo(defMain);

    typeDifference *= attSec != null ? attSec.compareTo(defSec) : 1;
    typeDifference *= defSec != null ? attMain.compareTo(defSec) : 1;
    typeDifference *= attSec != null ? attSec.compareTo(defMain): 1;

    if (typeDifference < 1) {
      return BattleAction.SWITCH;
    }
    return BattleAction.ATTACK;
  }
}
