package at.trinkl.Omimon.Battle.AttackEffects;

import at.trinkl.Omimon.Battle.Attack;
import at.trinkl.Omimon.Omimon.Omimon;

/**
 * Represents a special effect that can be applied during an attack.
 * <p>
 * Implementations of this interface define how an attack effect modifies the outcome of an attack,
 * potentially altering damage or triggering additional behavior.
 */
public interface AttackEffects {

  /**
   * Applies an effect.
   *
   * @param attack   The {@link Attack} being effected.
   * @param attacker The {@link Omimon} performing the attack.
   * @param baseDmg  The base damage calculated before any effects are applied.
   * @return The final damage after applying this effect. This damage needs to be added to the
   * damage previously calculated.
   */
  int ApplyEffect(Attack attack, Omimon attacker, int baseDmg);
}
