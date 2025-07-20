package Omidex.Battle.AttackEffects;

import Omidex.Battle.Attack;
import Omidex.Omimon.Omimon;

public class HealAttack implements AttackEffects{

  @Override
  public int ApplyEffect(Attack attack,Omimon attacker, Omimon defender, int baseDmg) {
    attacker.Heal();
    return 0;
  }
}
