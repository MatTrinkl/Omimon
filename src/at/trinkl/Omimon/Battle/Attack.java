package at.trinkl.Omimon.Battle;

import at.trinkl.Omimon.Battle.AttackEffects.AttackEffects;
import at.trinkl.Omimon.Omimon.OmiType;
import at.trinkl.Omimon.Omimon.Omimon;
import at.trinkl.Omimon.Values.Strength;
import java.util.List;

/**
 * Represents an attack that an {@link Omimon} can perform during battle.
 * <p>
 * An {@code Attack} has a name, base strength, type, and optional special effects that may modify
 * its behavior when used.
 * </p>
 */
public class Attack {

  private final Strength strength;
  private final String name;
  private final OmiType type;
  private final List<AttackEffects> possibleEffects;

  /**
   * Creates a new {@code Attack} instance.
   *
   * @param name            The name of the attack.
   * @param strength        The base {@link Strength} of the attack.
   * @param type            The {@link OmiType} (element/type) of the attack.
   * @param possibleEffects A list of optional {@link AttackEffects} that can influence the outcome
   *                        of the attack. Can be {@code null}.
   */
  public Attack(String name, Strength strength, OmiType type, List<AttackEffects> possibleEffects) {
    this.strength = strength;
    this.name = name;
    this.type = type;
    this.possibleEffects = possibleEffects;
  }

  /**
   * Returns the base strength value of the attack.
   *
   * @return The integer strength value.
   */
  public int getStrength() {
    return strength.getValue();
  }

  /**
   * Calculates the attack's strength adjusted for type effectiveness against the given defender.
   *
   * @param defender The {@link Omimon} that would receive the attack.
   * @return The type-adjusted base damage.
   */
  public int getStrengthComparedWithType(Omimon defender) {
    return calculateBaseDmg(defender);
  }

  /**
   * Returns the name of the attack.
   *
   * @return The attack's name.
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the {@link OmiType} of the attack.
   *
   * @return The {@link OmiType} of the attack.
   */
  public OmiType getType() {
    return type;
  }

  /**
   * Returns the list of possible effects this attack can trigger.
   *
   * @return A list of {@link AttackEffects}, or {@code null} if none exist.
   */
  public List<AttackEffects> getPossibleEffects() {
    return possibleEffects;
  }

  /**
   * Executes this attack against the given defender.
   * <p>
   * The base damage is calculated based on type matchups. Then, each effect in
   * {@code possibleEffects} (if any) is applied to potentially modify the damage. Then the
   * {@link at.trinkl.Omimon.Values.Defence} from the defender is subtracted. Finally, the resulting
   * damage is inflicted on the defender.
   * </p>
   *
   * @param attacker The {@link Omimon} performing the attack.
   * @param defender The {@link Omimon} receiving the attack.
   */
  public void OnAttack(Omimon attacker, Omimon defender) {
    int currentDamage = calculateBaseDmg(defender);

    if (possibleEffects != null) {
      int currentBaseDmg = currentDamage;
      for (AttackEffects e : possibleEffects) {
        currentDamage += e.ApplyEffect(this, attacker, currentBaseDmg);
      }
    }
    currentDamage -= defender.getCurrentDefence();
    if (currentDamage > 0) {
      defender.takeDamage(currentDamage);
    }

  }

  /**
   * Calculates the base damage of the attack, factoring in the effectiveness of this attack's type
   * against the defender's type(s).
   *
   * @param defender The {@link Omimon} receiving the attack.
   * @return The calculated base damage, before effects.
   */
  private int calculateBaseDmg(Omimon defender) {
    double multiplier = type.getEffectivenessAgainst(defender.getBlueprint().getMainType());
    OmiType defenderType2 = defender.getBlueprint().getSecoundaryType();
    if (defenderType2 != null) {
      multiplier *= type.getEffectivenessAgainst(defenderType2);
    }
    return (int) Math.round(strength.getValue() * multiplier);
  }
}
