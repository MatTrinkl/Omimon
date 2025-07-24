package at.trinkl.Omimon.Values;

import at.trinkl.Omimon.Omimon.Omimon;

/**
 * Represents the health stat of an {@link Omimon}, indicating how much damage it can take before
 * fainting.
 * <p>
 * Health values are clamped between {@code 0} and {@code 255} as defined by {@link BaseValue}. This
 * class also implements {@link ValueUpdatable}, allowing it to take damage and return whether the
 * Omimon is still alive after an update.
 * </p>
 */
public class Health extends BaseValue implements ValueUpdatable {

  /**
   * Constructs a new {@code Health} instance with an initial value.
   *
   * @param value The initial health value (automatically clamped to {@code [0, 255]}).
   */
  public Health(int value) {
    super.setValue(value);
  }

  /**
   * Applies damage to the health value.
   * <p>
   * If the resulting health would fall to 0 or below, it is set to 0 and the method returns
   * {@code false}, indicating that the Omimon has fainted.
   * </p>
   *
   * @param value The amount of damage to apply.
   * @return {@code true} if the Omimon is still alive after taking damage; {@code false} if the
   * health dropped to 0 or below.
   */
  @Override
  public boolean updateValue(int value) {
    if (this.value - value <= 0) {
      this.value = 0;
      return false;
    }
    super.value = BaseValue.CastValue(this.value - value);
    return true;
  }

  /**
   * Fully restores the health to its maximum value of {@code 255}.
   */
  public void Heal() {
    super.setValue(255);
  }
}
