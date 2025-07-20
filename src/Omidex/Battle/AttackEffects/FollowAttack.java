package Omidex.Battle.AttackEffects;

import Omidex.Battle.Attack;
import Omidex.Omimon.Omimon;
import java.util.Random;

public class FollowAttack implements AttackEffects{

  @Override
  public int ApplyEffect(Attack attack, Omimon attacker, Omimon defender, int baseDmg) {
    Random r = new Random();
    int times= r.nextInt(5)+1;
    return baseDmg *times;
  }
}
