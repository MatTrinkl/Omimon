package Omidex.Battle.Strategy.BattleStrategy;

import Omidex.Battle.Attack;
import Omidex.Omimon.Omimon;
import java.util.Comparator;
import java.util.Optional;

public class StrengthBattleStrategy implements BattleStrategy {

  @Override
  public Attack selectAttackFromStrategy(Omimon attacker, Omimon defender) {
    Optional<Attack> strongestAttack = attacker.getAttacks().stream()
        .max(Comparator.comparingInt(Attack::getStrength));
    return strongestAttack.get();
  }
}
