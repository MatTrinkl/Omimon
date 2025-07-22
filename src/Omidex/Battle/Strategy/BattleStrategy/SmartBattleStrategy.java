package Omidex.Battle.Strategy.BattleStrategy;

import Omidex.Battle.Attack;
import Omidex.Omimon.Omimon;
import java.util.Comparator;
import java.util.Optional;

public class SmartBattleStrategy implements BattleStrategy {

  @Override
  public Attack selectAttackViaStrategy(Omimon attacker, Omimon defender) {
    Optional<Attack> strongestAttack = attacker.getAttacks().stream()
        .max(Comparator.comparingInt(s -> s.getStrengthComparedWithType(defender)));
    return strongestAttack.get();
  }
}
