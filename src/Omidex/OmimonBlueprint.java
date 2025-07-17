package Omidex;

import Omidex.Battle.Attack;
import Omidex.Values.Valueable;
import java.util.List;

public class OmimonBlueprint {
  private String Name;
  private OmiType mainType;
  private OmiType secoundaryType;
  private List<Valueable> values;
  private OmimonBlueprint evolution;
  private int levelToEvolve;
  private List<Attack> attacks;


  public String getName() {
    return Name;
  }

  public OmiType getMainType() {
    return mainType;
  }

  public OmiType getSecoundaryType() {
    return secoundaryType;
  }

  public List<Valueable> getValues() {
    return values;
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
