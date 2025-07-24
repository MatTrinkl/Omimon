package at.trinkl.Omimon.Values;

import at.trinkl.Omimon.Battle.Attack;

/**
 * Represents the strength or power value of an {@link Attack}.
 * <p>
 * Strength influences how much base damage an attack can deal before applying type effectiveness
 * and other modifiers. Like all base stats, this value is clamped to the range {@code [0, 255]} as
 * defined by {@link BaseValue}.
 * </p>
 */
public class Strength extends BaseValue {

  /**
   * Constructs a new {@code Strength} instance with the specified value.
   *
   * @param value The base strength (automatically clamped to {@code [0, 255]}).
   */
  public Strength(int value) {
    super.setValue(value);
  }
}
