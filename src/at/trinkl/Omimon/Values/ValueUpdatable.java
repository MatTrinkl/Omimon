package at.trinkl.Omimon.Values;

/**
 * Represents a generic interface for objects whose internal value can be updated.
 * <p>
 * Implementations typically apply logic to reduce or change a stat (e.g., health, energy), and
 * indicate whether the value is still in a valid or "alive" state after the update.
 * </p>
 */
public interface ValueUpdatable {

  /**
   * Applies a change to the internal value of the object.
   * <p>
   * The interpretation of the value (e.g., damage, healing) depends on the implementation. The
   * method returns whether the value is still considered "active" (e.g., not zero or negative).
   * </p>
   *
   * @param value The amount to apply (e.g., damage to subtract).
   * @return {@code true} if the value is still above zero or valid after the update; {@code false}
   * if it has reached a failure or death threshold.
   */
  boolean updateValue(int value);
}
