package at.trinkl.Omimon.Omimon;

import at.trinkl.Omimon.Battle.Attack;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

/**
 * Represents an elemental type used by {@link Omimon} and {@link Attack}.
 * <p>
 * Each type has strengths and weaknesses against others, defined in a static effectiveness map.
 * </p>
 */
public enum OmiType {
  Water,
  Fire,
  Plant,
  Normal;

  // Effectiveness chart: attacker -> (defender -> multiplier)
  private static final Map<OmiType, Map<OmiType, Double>> effectivenessChart = new EnumMap<>(
      OmiType.class);

  static {
    for (OmiType type : OmiType.values()) {
      effectivenessChart.put(type, new EnumMap<>(OmiType.class));
    }

    // Default all to 1.0
    for (OmiType attacker : OmiType.values()) {
      for (OmiType defender : OmiType.values()) {
        effectivenessChart.get(attacker).put(defender, 1.0);
      }
    }

    // Custom effectiveness
    effectivenessChart.get(Fire).put(Plant, 2.0);
    effectivenessChart.get(Plant).put(Water, 2.0);
    effectivenessChart.get(Water).put(Fire, 2.0);

    effectivenessChart.get(Plant).put(Fire, 0.5);
    effectivenessChart.get(Water).put(Plant, 0.5);
    effectivenessChart.get(Fire).put(Water, 0.5);
  }

  /**
   * Returns the effectiveness multiplier when this type attacks the given defender type.
   *
   * @param defenderType The defending type.
   * @return Effectiveness multiplier (e.g. 2.0 = super effective, 0.5 = not effective, 1.0 =
   * neutral)
   */
  public double getEffectivenessAgainst(OmiType defenderType) {
    return effectivenessChart
        .getOrDefault(this, Collections.emptyMap())
        .getOrDefault(defenderType, 1.0);
  }
}

