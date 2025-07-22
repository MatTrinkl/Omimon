package Omidex.Battle.Strategy.BattleStrategy;

import Omidex.Battle.Attack;
import Omidex.Omimon.Omimon;

public class RandomBattleStrategy implements BattleStrategy {

  @Override
  public Attack selectAttackViaStrategy(Omimon attacker, Omimon defender) {
   return attacker.getAttacks().get((int) (Math.random() * attacker.getAttacks().size()));
  }
}
