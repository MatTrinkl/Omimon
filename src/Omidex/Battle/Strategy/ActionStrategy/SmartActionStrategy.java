package Omidex.Battle.Strategy.ActionStrategy;

import Omidex.Battle.BattleAction;
import Omidex.Omimon.*;

public class SmartActionStrategy implements ActionStrategy {
  OmiType attMain;
  OmiType defMain;
  OmiType attSec;
  OmiType defSec;
  public SmartActionStrategy(Omimon attacker, Omimon defender) {
    attMain= attacker.getBlueprint().getMainType();
    defMain= defender.getBlueprint().getMainType();
    attSec = attacker.getBlueprint().getSecoundaryType();
    defSec = defender.getBlueprint().getSecoundaryType();
  }
  @Override
  public BattleAction getNextActionByStrategy() {
    double typeDifference = 1;

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
