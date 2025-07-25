package at.trinkl.Omimon.Values;

import at.trinkl.Omimon.Omimon.Omimon;

/**
 * Represents the speed stat of an {@link Omimon}.
 * <p>
 * Speed determines turn order in battles — higher speed values allow an Omimon to act before slower
 * ones. This value is clamped to the range {@code [0, 255]} as defined in {@link BaseValue}.
 * </p>
 */
public class Speed extends BaseValue {

  /**
   * Constructs a new {@code Speed} instance with the given initial value.
   *
   * @param value The speed value (automatically clamped to {@code [0, 255]}).
   */
  public Speed(int value) {
    super.setValue(value);
  }
}
