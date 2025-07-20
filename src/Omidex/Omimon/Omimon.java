package Omidex.Omimon;

import Omidex.Values.*;
import Omidex.Battle.*;
import java.util.*;

public class Omimon {

  private OmimonBlueprint blueprint;
  private String name;
  private Health currentHealth;
  private Speed currentSpeed;
  private Defence currentDefence;
  private int level;
  private List<Attack> attacks;

  public Omimon(OmimonBlueprint blueprint, String name, int initLevel) {
    this.blueprint = blueprint;
    if (name == null || name.isEmpty()) {
      this.name = blueprint.getName();
    } else {
      this.name = name;
    }
    this.currentHealth = new Health(blueprint.getBaseHealth());
    this.currentSpeed = new Speed(blueprint.getBaseSpeed());
    this.currentDefence = new Defence(blueprint.getBaseDefence());
    this.level = initLevel;
    this.attacks = blueprint.getAttacks();
  }

  public Omimon(OmimonBlueprint blueprint) {
    this(blueprint, null, 1);
  }

  public Omimon(OmimonBlueprint blueprint, String name) {
    this(blueprint, name, 1);
  }

  public OmimonBlueprint getBlueprint() {
    return blueprint;
  }

  public String getName() {
    return name;
  }

  public int getCurrentHealth() {
    return currentHealth.getValue();
  }

  public int getCurrentSpeed() {
    return currentSpeed.getValue();
  }

  public int getCurrentDefence() {
    return currentDefence.getValue();
  }

  public int getCurrentLevel() {
    return level;
  }

  public List<Attack> getAttacks() {
    return attacks;
  }

  public void takeDamage(int currentDamage) {
    if (!currentHealth.updateValue(currentDamage)) {
      onDeath();
    }
  }

  public void Heal() {
    currentHealth.Heal();
  }

  private void onDeath() {
  }
}
