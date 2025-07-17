package Omidex;

import java.util.ArrayList;
import java.util.List;

public class Omidex {
  List<OmimonBlueprint> blueprints;
  private static Omidex Instance; //Singelton

  private Omidex() {
    blueprints = Omidex.createBluePrints();
  }


  public static Omidex getInstance() {
    if (Instance == null) {
      Instance = new Omidex();
    }
    return Instance;
  }


  private static List<OmimonBlueprint> createBluePrints() {
    List<OmimonBlueprint> blueprints = new ArrayList<>();
    blueprints.add(new OmimonBlueprint());
    return blueprints;
  }
}
