package Omidex.Omimon;

import Omidex.Battle.Attack;
import Omidex.Values.*;
import java.util.List;

public class OmimonBlueprint {
  private String name;
  private OmiType mainType;
  private OmiType secoundaryType;
  private int baseDefence;
  private int baseHealth;
  private int baseSpeed;
  private OmimonBlueprint evolution;
  private int levelToEvolve;
  private List<Attack> attacks;

  public OmimonBlueprint(String name, OmiType mainType, OmiType secoundaryType, int baseDefence,
      int baseHealth, int baseSpeed, OmimonBlueprint evolution, int levelToEvolve,
      List<Attack> attacks) {
    this.name = name;
    this.mainType = mainType;
    this.secoundaryType = secoundaryType;
    setBaseDefence(baseDefence);
    setBaseHealth(baseHealth);
    setBaseSpeed(baseSpeed);
    this.evolution = evolution;
    this.levelToEvolve = levelToEvolve;
    this.attacks = attacks;
  }
  public Omimon createInstance(String nickname,int initLevel){
    return new Omimon(this,nickname,initLevel);
  }

  private void setBaseDefence(int baseDefence) {
    this.baseDefence = BaseValue.CastValue(baseDefence);
  }

  private void setBaseHealth(int baseHealth) {
    this.baseHealth = BaseValue.CastValue(baseHealth);
  }

  private void setBaseSpeed(int baseSpeed) {
    this.baseSpeed = BaseValue.CastValue(baseSpeed);
  }

  public String getName() {
    return name;
  }

  public OmiType getMainType() {
    return mainType;
  }

  public OmiType getSecoundaryType() {
    return secoundaryType;
  }

  public int getBaseDefence() {
    return baseDefence;
  }

  public int getBaseHealth() {
    return baseHealth;
  }

  public int getBaseSpeed() {
    return baseSpeed;
  }

  public OmimonBlueprint getEvolution() {
    return evolution;
  }

  public int getLevelToEvolve() {
    return levelToEvolve;
  }

  public List<Attack> getAttacks() {
    return attacks;
  }
}
