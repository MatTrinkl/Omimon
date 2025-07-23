package Omidex;

import Omidex.Battle.Strategy.ActionStrategy.ActionStrategy;
import Omidex.Omimon.Omimon;
import Omidex.Omimon.OmimonBlueprint;
import java.util.ArrayList;
import java.util.List;

public class Trainer {

  private List<Omimon> livingOmimons;
  private List<Omimon> deadOmimons;
  private String name;
  private String gender;
  private int id;

  private ActionStrategy actionStrategy;

  public Trainer(String name, String gender, int id, ActionStrategy actionStrategy) {
    this.name = name;
    this.gender = gender;
    this.id = id;
    this.actionStrategy = actionStrategy;
    livingOmimons = new ArrayList<>();
    deadOmimons = new ArrayList<>();
  }

  public String getName() {
    return name;
  }

  public String getGender() {
    return gender;
  }

  public int getId() {
    return id;
  }

  public boolean CaptureOmmimon(OmimonBlueprint blueprint, String name, int level) {
    if (livingOmimons.size() < 6) {
      blueprint.createInstance(name, level,this);
      return true;
    } else {
      throw new ArrayIndexOutOfBoundsException("There can be only 6 Omimons");
    }
  }

  public Omimon getRandomOmimonWithCanFight() {
    return livingOmimons.get((int) (Math.random() * livingOmimons.size()));
  }
  public void onOmimonDeath(Omimon omimon) {
    deadOmimons.add(omimon);
    livingOmimons.remove(omimon);
  }

  public boolean hasBattleReadyOmimons() {
    return !livingOmimons.isEmpty();
  }

  public ActionStrategy getActionStrategy() {
    return actionStrategy;
  }
}
