package at.trinkl.Omimon.Omimon;

import at.trinkl.Omimon.Battle.Attack;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;


/**
 * Represents an elemental type used by {@link Omimon} and {@link Attack}.
 *
 * <p>Each {@code OmiType} has defined strengths and weaknesses against other types,
 * managed through a static effectiveness chart. This chart determines how effective an attack of
 * one type is against a defender of another type.</p>
 *
 * <p>Examples:
 * <ul>
 *   <li>{@code Fire} is strong against {@code Plant} (2.0x damage)</li>
 *   <li>{@code Water} is weak against {@code Plant} (0.5x damage)</li>
 *   <li>{@code Normal} is neutral against all types (1.0x damage)</li>
 * </ul>
 * </p>
 */
public enum OmiType {
  /**
   * Water-type, strong against Fire, weak against Plant.
   */
  Water,

  /**
   * Fire-type, strong against Plant, weak against Water.
   */
  Fire,

  /**
   * Plant-type, strong against Water, weak against Fire.
   */
  Plant,

  /**
   * Normal-type, neutral against all types.
   */
  Normal;

  // Effectiveness chart: attacker -> (defender -> multiplier)
  private static final Map<OmiType, Map<OmiType, Double>> effectivenessChart = new EnumMap<>(
      OmiType.class);

  static {
    for (OmiType type : OmiType.values()) {
      effectivenessChart.put(type, new EnumMap<>(OmiType.class));
    }

    // Set all combinations to neutral by default (1.0x)
    for (OmiType attacker : OmiType.values()) {
      for (OmiType defender : OmiType.values()) {
        effectivenessChart.get(attacker).put(defender, 1.0);
      }
    }

    // Define specific strengths and weaknesses
    effectivenessChart.get(Fire).put(Plant, 2.0);
    effectivenessChart.get(Plant).put(Water, 2.0);
    effectivenessChart.get(Water).put(Fire, 2.0);

    effectivenessChart.get(Plant).put(Fire, 0.5);
    effectivenessChart.get(Water).put(Plant, 0.5);
    effectivenessChart.get(Fire).put(Water, 0.5);
  }

  /**
   * Returns the effectiveness multiplier when this type is used to attack the given defender type.
   *
   * @param defenderType the defending {@code OmiType}
   * @return the damage multiplier:
   * <ul>
   *   <li>{@code 2.0} for super effective</li>
   *   <li>{@code 0.5} for not very effective</li>
   *   <li>{@code 1.0} for neutral</li>
   * </ul>
   */
  public double getEffectivenessAgainst(OmiType defenderType) {
    return effectivenessChart
        .getOrDefault(this, Collections.emptyMap())
        .getOrDefault(defenderType, 1.0);
  }
}

