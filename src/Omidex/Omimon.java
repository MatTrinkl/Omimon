package Omidex;

public class Omimon {
  private OmimonBlueprint blueprint;
  private String name;

  public Omimon(OmimonBlueprint blueprint, String name) {
    this.blueprint = blueprint;
    if(name ==null || name.isEmpty()) {
      this.name = this.blueprint.getName();
    }

  }
  public OmimonBlueprint getBlueprint() {
    return blueprint;
  }
}
