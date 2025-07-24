package at.trinkl.Omimon.Values;

import at.trinkl.Omimon.Omimon.Omimon;

/**
 * Represents the defence stat of an {@link Omimon}.
 * <p>
 * Defence influences how much damage an Omimon receives when attacked. The higher the value,
 * the more damage can be mitigated depending on the damage formula.
 * </p>
 * <p>
 * Values are automatically clamped to the valid range of {@code [0, 255]} as defined by {@link BaseValue}.
 * </p>
 */
public class Defence extends BaseValue {

  /**
   * Constructs a new {@code Defence} instance with the given initial value.
   *
   * @param value The raw defence value (automatically clamped between 0 and 255).
   */
  public Defence(int value) {
    super.setValue(value);
  }
}
