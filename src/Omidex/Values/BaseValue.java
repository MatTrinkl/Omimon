package Omidex.Values;

public abstract class BaseValue {

  protected int value;

  protected void setValue(int value) {
    this.value = CastValue(value);
  }
  public int getValue() {
    return value;
  }

  public static int CastValue(int value) {
    return Math.max(0, Math.min(value, 255));
  }
}
