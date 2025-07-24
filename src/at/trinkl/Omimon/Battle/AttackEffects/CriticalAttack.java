package at.trinkl.Omimon.Battle.AttackEffects;

import at.trinkl.Omimon.Battle.Attack;
import at.trinkl.Omimon.Omimon.Omimon;
import java.util.Random;

/**
 * Represents a critical attack effect that has a chance to deal full damage again. This effect has
 * a 10% chance of landing a critical hit.
 */
public class CriticalAttack implements AttackEffects {

  /**
   * Applies the critical hit effect to an attack.
   * <p>
   * With a 10% probability, the base damage is returned as-is. Otherwise, the method returns 0.
   * </p>
   *
   * @param attack   The {@link Attack} being performed.
   * @param attacker The {@link Omimon} initiating the attack.
   * @param baseDmg  The base damage calculated before applying the effect.
   * @return The damage after applying the critical effect: {@code baseDmg} with 10% chance, or
   * {@code 0} otherwise. This damage needs to be added to the damage previously calculated.
   */
  @Override
  public int ApplyEffect(Attack attack, Omimon attacker, int baseDmg) {
    Random r = new Random();
    if (r.nextFloat() <= 0.1f) {
      return baseDmg;
    } else {
      return 0;
    }
  }
}
