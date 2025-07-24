package at.trinkl.Omimon.Battle.AttackEffects;

import at.trinkl.Omimon.Battle.Attack;
import at.trinkl.Omimon.Omimon.Omimon;
import java.util.Random;

/**
 * Represents a follow-up attack effect. The attack can hit up to 5 times.
 */
public class FollowAttack implements AttackEffects {

  /**
   * Applies the follow-up effect.
   * <p>
   * The attack can hit up to 5 times.
   * </p>
   *
   * @param attack   The {@link Attack} being performed.
   * @param attacker The {@link Omimon} initiating the attack.
   * @param baseDmg  The base damage calculated before applying the effect.
   * @return The damage after applying the follow-up effect: {@code baseDmg} up to 5 times, minimum
   * 1 time. This damage needs to be added to the damage previously calculated.
   */
  @Override
  public int ApplyEffect(Attack attack, Omimon attacker, int baseDmg) {
    Random r = new Random();
    int times = r.nextInt(5) + 1;
    return baseDmg * times;
  }
}
