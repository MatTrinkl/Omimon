package Omidex.Battle.AttackEffects;

import Omidex.Battle.Attack;
import Omidex.Omimon.Omimon;

public interface AttackEffects {
public int ApplyEffect(Attack attack, Omimon attacker,Omimon defender, int baseDmg);
}
