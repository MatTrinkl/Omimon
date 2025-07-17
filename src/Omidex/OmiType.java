package Omidex;

public enum OmiType {
Water,Fire,Plant,Normal;

  public double getEffectivenessAgainst(OmiType defenderType) {
    if (this == Fire && defenderType == Plant) return 2.0;
    if (this == Plant && defenderType == Water) return 2.0;
    if (this == Water && defenderType == Fire) return 2.0;
    if (this == Plant && defenderType == Fire) return 0.5;
    if (this == Water && defenderType == Plant) return 0.5;
    if (this == Fire && defenderType == Water) return 0.5;
    return 1.0;
  }
}
