package Omidex;

import Omidex.Battle.Strategy.ActionStrategy.ActionStrategy;
import Omidex.Battle.Strategy.BattleStrategy.BattleStrategy;
import Omidex.Omimon.Omimon;
import Omidex.Omimon.OmimonBlueprint;
import java.util.List;

public class Trainer {

  private List<Omimon> lifingOmimons;
  private List<Omimon> deadOmimons;
  private String name;
  private String gender;
  private int id;

  private ActionStrategy actionStrategy;
  private BattleStrategy battleStrategy;

  public boolean CaptureOmmimon(OmimonBlueprint blueprint, String name, int level) {
    if (lifingOmimons.size() < 6) {
      blueprint.createInstance(name, level);
      return true;
    } else {
      throw new ArrayIndexOutOfBoundsException("There can be only 6 Omimons");
    }
  }

  public Omimon getRandomOmimonWithCanFight() {
    return lifingOmimons.get((int) (Math.random() * lifingOmimons.size()));
  }
  public void OnOmimonDeath(Omimon omimon) {
    deadOmimons.add(omimon);
    lifingOmimons.remove(omimon);
  }

  public boolean hasBattleReadyOmimons() {
    return !lifingOmimons.isEmpty();
  }
}
