package at.trinkl.Omimon.Battle.AttackEffects;

import at.trinkl.Omimon.Battle.Attack;
import at.trinkl.Omimon.Omimon.Omimon;

/**
 * Represents a heal effect of an attack. While attacking the {@link Omimon} which attack gets
 * healed.
 */
public class HealAttack implements AttackEffects {

  /**
   * Applies the heal effect.
   * <p>
   * Heals the {@param attacker} fully.
   * </p>
   *
   * @param attack   The {@link Attack} being performed.
   * @param attacker The {@link Omimon} initiating the attack.
   * @param baseDmg  The base damage calculated before applying the effect.
   * @return Returns zero, because the does not affect the damage dealt. This damage needs to be
   * added to the damage previously calculated.
   */
  @Override
  public int ApplyEffect(Attack attack, Omimon attacker, int baseDmg) {
    attacker.Heal();
    return 0;
  }
}
