package Omidex.Omimon;

import Omidex.Battle.Attack;
import java.util.ArrayList;
import java.util.List;

public class OmimonBlueprintBuilder {

  private String name;
  private OmiType mainType;
  private OmiType secoundaryType;
  private int baseDefence;
  private int baseHealth;
  private int baseSpeed;
  private OmimonBlueprint evolution;
  private int levelToEvolve;
  private List<Attack> attacks;


  public OmimonBlueprintBuilder() {
  }

  public OmimonBlueprintBuilder setName(String name) {
    this.name = name;
    return this;
  }

  public OmimonBlueprintBuilder setMainType(OmiType mainType) {
    this.mainType = mainType;
    return this;
  }

  public OmimonBlueprintBuilder setSecoundaryType(OmiType secoundaryType) {
    this.secoundaryType = secoundaryType;
    return this;
  }

  public OmimonBlueprintBuilder setBaseDefence(int baseDefence) {
    this.baseDefence = baseDefence;
    return this;
  }

  public OmimonBlueprintBuilder setBaseHealth(int baseHealth) {
    this.baseHealth = baseHealth;
    return this;
  }

  public OmimonBlueprintBuilder setBaseSpeed(int baseSpeed) {
    this.baseSpeed = baseSpeed;
    return this;
  }

  public OmimonBlueprintBuilder setEvolution(OmimonBlueprint e) {
    this.evolution = e;
    return this;
  }

  public OmimonBlueprintBuilder setLevelToEvolve(int level) {
    this.levelToEvolve = level;
    return this;
  }

  public OmimonBlueprintBuilder addAttack(Attack attack) {
    if (this.attacks == null) {
      this.attacks = new ArrayList<>();
    }
    this.attacks.add(attack);
    return this;
  }

  public OmimonBlueprint build() {
    return new OmimonBlueprint(this.name, this.mainType, this.secoundaryType, this.baseDefence,
        this.baseHealth, this.baseSpeed, this.evolution, this.levelToEvolve, this.attacks);
  }

}
