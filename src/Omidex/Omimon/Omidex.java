package Omidex.Omimon;

import Omidex.Battle.Attack;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class Omidex {

  Map<String, OmimonBlueprint> blueprintsByName;
  Map<String, Attack> attacksByName;
  private static Omidex Instance; //Singelton

  private Omidex() {
    blueprintsByName = Omidex.createBluePrints();
    attacksByName= Omidex.createAttacks();
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
    return blueprints;
  }
  private static Map<String, Attack> createAttacks() {
    Map<String, Attack> attacks = new HashMap<String, Attack>();

    return attacks;
  }
}
