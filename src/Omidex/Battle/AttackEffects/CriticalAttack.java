package Omidex.Battle.AttackEffects;

import Omidex.Battle.Attack;
import Omidex.Omimon.Omimon;
import java.util.Random;

public class CriticalAttack implements AttackEffects {

  @Override
  public int ApplyEffect(Attack attack, Omimon attacker, Omimon defender, int baseDmg) {
    Random r = new Random();
    if (r.nextFloat() <= 0.1f) {
      return baseDmg;
    } else {
      return 0;
    }
  }
}
