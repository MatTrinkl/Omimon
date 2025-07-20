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

  public void OnAttack(Omimon attacker,Omimon defender){
    double multiplier = type.getEffectivenessAgainst(defender.getBlueprint().getMainType());
    OmiType defenderType2 = defender.getBlueprint().getSecoundaryType();
    if(defenderType2!=null){
      multiplier *= type.getEffectivenessAgainst(defenderType2);
    }
    int currentDamage = (int) Math.round(strength.getValue()*multiplier);
    int currentBaseDmg = currentDamage;
    for(AttackEffects e : possibleEffects){
      currentDamage+=e.ApplyEffect(this,attacker, defender,currentBaseDmg);
    }
    defender.takeDamage(currentDamage);
  }
}
