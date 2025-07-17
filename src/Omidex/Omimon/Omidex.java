package Omidex.Omimon;

import java.util.HashMap;
import java.util.Map;

public class Omidex {

  Map<String, OmimonBlueprint> blueprintsByName;
  private static Omidex Instance; //Singelton

  private Omidex() {
    blueprintsByName = Omidex.createBluePrints();
  }


  public static Omidex getInstance() {
    if (Instance == null) {
      Instance = new Omidex();
    }
    return Instance;
  }

  public OmimonBlueprint getBluePrint(String blueprintName) {
    if (blueprintsByName.containsKey(blueprintName)) {
      return blueprintsByName.get(blueprintName);
    }else{
      throw new IllegalArgumentException("No such blueprint " + blueprintName);
    }
  }

  private static Map<String, OmimonBlueprint> createBluePrints() {
    Map<String, OmimonBlueprint> blueprints = new HashMap<String, OmimonBlueprint>();
    //blueprints.put()
    return blueprints;
  }
}
