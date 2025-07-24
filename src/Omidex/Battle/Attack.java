package Omidex.Battle;

import Omidex.Battle.AttackEffects.AttackEffects;
import Omidex.Omimon.OmiType;
import Omidex.Omimon.Omimon;
import Omidex.Values.Strength;
import java.util.List;

public class Attack {

  private Strength strength;
  private String name;
  private OmiType type;
  private List<AttackEffects> possibleEffects;

  public Attack(String name, Strength strength, OmiType type, List<AttackEffects> possibleEffects) {
    this.strength = strength;
    this.name = name;
    this.type = type;
    this.possibleEffects = possibleEffects;
  }

  public int getStrength() {
    return strength.getValue();
  }

  public int getStrengthComparedWithType(Omimon defender) {
    return calculateBaseDmg(defender);
  }

  public String getName() {
    return name;
  }

  public OmiType getType() {
    return type;
  }

  public List<AttackEffects> getPossibleEffects() {
    return possibleEffects;
  }

  public void OnAttack(Omimon attacker, Omimon defender) {
    int currentDamage = calculateBaseDmg(defender);

    if(possibleEffects!=null) {
      int currentBaseDmg = currentDamage;
      for (AttackEffects e : possibleEffects) {
        currentDamage += e.ApplyEffect(this, attacker, defender, currentBaseDmg);
      }
    }
    defender.takeDamage(currentDamage);
  }

  private int calculateBaseDmg(Omimon defender) {
    double multiplier = type.getEffectivenessAgainst(defender.getBlueprint().getMainType());
    OmiType defenderType2 = defender.getBlueprint().getSecoundaryType();
    if (defenderType2 != null) {
      multiplier *= type.getEffectivenessAgainst(defenderType2);
    }
    return (int) Math.round(strength.getValue() * multiplier);
  }
}
