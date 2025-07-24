package at.trinkl.Omimon.Values;

/**
 * Abstract base class for value-based attributes such as {@code Health}, {@code Speed}, or
 * {@code Defence}.
 * <p>
 * Provides common functionality for setting and clamping values to the range {@code [0, 255]}. This
 * ensures consistency across all stat-related classes in the Omimon system.
 * </p>
 */
public abstract class BaseValue {

  /**
   * The internal value, clamped between 0 and 255.
   */
  protected int value;

  /**
   * Sets the internal value, automatically clamped between {@code 0} and {@code 255}.
   *
   * @param value The raw value to assign.
   */
  protected void setValue(int value) {
    this.value = CastValue(value);
  }

  /**
   * Returns the current (already clamped) value.
   *
   * @return The value between {@code 0} and {@code 255}.
   */
  public int getValue() {
    return value;
  }

  /**
   * Utility method for clamping a raw value to the legal range {@code [0, 255]}.
   *
   * @param value The input value.
   * @return The clamped value.
   */
  public static int CastValue(int value) {
    return Math.max(0, Math.min(value, 255));
  }
}
