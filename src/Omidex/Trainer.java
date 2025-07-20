package Omidex;

import Omidex.Omimon.Omimon;
import Omidex.Omimon.OmimonBlueprint;
import java.util.List;

public class Trainer {

  private List<Omimon> omimons;
  private String name;
  private String gender;
  private int id;

  private Strategy actionStrategy;
  private Strategy battleStrategy;

  public boolean CaptureOmmimon(OmimonBlueprint blueprint, String name, int level) {
    if (omimons.size() < 6) {
      blueprint.createInstance(name, level);
      return true;
    } else {
      throw new ArrayIndexOutOfBoundsException("There can be only 6 Omimons");
    }
  }

}
