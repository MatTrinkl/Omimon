package Omidex;

import Omidex.Battle.Strategy.ActionStrategy.ActionStrategy;
import Omidex.Battle.Strategy.BattleStrategy.BattleStrategy;
import Omidex.Omimon.Omimon;
import Omidex.Omimon.OmimonBlueprint;
import java.util.List;

public class Trainer {

  private List<Omimon> omimons;
  private String name;
  private String gender;
  private int id;

  private ActionStrategy actionStrategy;
  private BattleStrategy battleStrategy;

  public boolean CaptureOmmimon(OmimonBlueprint blueprint, String name, int level) {
    if (omimons.size() < 6) {
      blueprint.createInstance(name, level);
      return true;
    } else {
      throw new ArrayIndexOutOfBoundsException("There can be only 6 Omimons");
    }
  }

}
