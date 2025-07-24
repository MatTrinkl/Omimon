package Omidex.Omimon;

import Omidex.Battle.Attack;
import Omidex.Battle.AttackEffects.AttackEffects;
import Omidex.Battle.AttackEffects.CriticalAttack;
import Omidex.Battle.AttackEffects.FollowAttack;
import Omidex.Battle.AttackEffects.HealAttack;
import Omidex.Battle.Strategy.BattleStrategy.RandomBattleStrategy;
import Omidex.Battle.Strategy.BattleStrategy.SmartBattleStrategy;
import Omidex.Battle.Strategy.BattleStrategy.StrengthBattleStrategy;
import Omidex.Values.Strength;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class Omidex {

  private Map<String, OmimonBlueprint> blueprintsByName;
  private Map<String, Attack> attacksByName;
  private static Omidex Instance; //Singelton

  private Omidex() {

  }

  public static Omidex getInstance() {
    if (Instance == null) {
      Instance = new Omidex();
      Instance.attacksByName = Omidex.createAttacks();
      Instance.blueprintsByName = Omidex.createBluePrints();
    }
    return Instance;
  }

  public OmimonBlueprint getBluePrint(String blueprintName) {
    if (blueprintsByName.containsKey(blueprintName)) {
      return blueprintsByName.get(blueprintName);
    } else {
      throw new IllegalArgumentException("No such blueprint " + blueprintName);
    }
  }

  public Attack getAttack(String attackName) {
    if (attacksByName.containsKey(attackName)) {
      return attacksByName.get(attackName);
    } else {
      throw new IllegalArgumentException("No such Attack " + attackName);
    }
  }

  private static Map<String, OmimonBlueprint> createBluePrints() {
    Map<String, OmimonBlueprint> blueprints = new HashMap<String, OmimonBlueprint>();
    OmimonBlueprintBuilder builder = new OmimonBlueprintBuilder();
    OmimonBlueprint blue = builder.setName("Corgimon").setBaseDefence(10).setBaseHealth(100)
        .setBaseSpeed(50)
        .setEvolution("Queenmon").setMainType(OmiType.Fire).setSecoundaryType(OmiType.Water)
        .setBattleStrategy(new SmartBattleStrategy()).setLevelToEvolve(15).build();
    blue.addAttack(getInstance().getAttack("Cuddle"));
    blue.addAttack(getInstance().getAttack("FireDoublePunch"));
    blue.addAttack(getInstance().getAttack("ULTIMATE"));
    blueprints.put(blue.getName(), blue);

    blue = builder.setName("Queenmon").setBaseDefence(50).setBaseHealth(300)
        .setBaseSpeed(70).setMainType(OmiType.Fire).setSecoundaryType(OmiType.Water)
        .setBattleStrategy(new SmartBattleStrategy()).build();
    blue.addAttack(getInstance().getAttack("Cuddle"));
    blue.addAttack(getInstance().getAttack("FireDoublePunch"));
    blue.addAttack(getInstance().getAttack("ULTIMATE"));
    blueprints.put(blue.getName(), blue);

    blue = builder.setName("Picka").setBaseDefence(10).setBaseHealth(50)
        .setBaseSpeed(100).setMainType(OmiType.Normal)
        .setBattleStrategy(new RandomBattleStrategy()).build();
    blue.addAttack(getInstance().getAttack("Cuddle"));
    blue.addAttack(getInstance().getAttack("FireDoublePunch"));
    blue.addAttack(getInstance().getAttack("ULTIMATE"));
    blueprints.put(blue.getName(), blue);

    blue = builder.setName("Glumandi").setBaseDefence(30).setBaseHealth(77)
        .setBaseSpeed(20).setMainType(OmiType.Fire)
        .setBattleStrategy(new StrengthBattleStrategy()).build();
    blue.addAttack(getInstance().getAttack("Cuddle"));
    blue.addAttack(getInstance().getAttack("FireDoublePunch"));
    blue.addAttack(getInstance().getAttack("ULTIMATE"));
    blueprints.put(blue.getName(), blue);
    return blueprints;
  }

  private static Map<String, Attack> createAttacks() {
    Map<String, Attack> attacks = new HashMap<String, Attack>();
    Attack a = new Attack("Cuddle", new Strength(15), OmiType.Normal,
        Arrays.asList(new AttackEffects[]{new CriticalAttack()}));
    attacks.put(a.getName(), a);
    a = new Attack("FireDoublePunch", new Strength(20), OmiType.Fire,
        Arrays.asList(new AttackEffects[]{new FollowAttack()}));
    attacks.put(a.getName(), a);
    a = new Attack("Aqua", new Strength(30), OmiType.Water,
        null);
    attacks.put(a.getName(), a);
    a = new Attack("PlantAttack", new Strength(20), OmiType.Plant,
        Arrays.asList(new AttackEffects[]{new HealAttack()}));
    attacks.put(a.getName(), a);
    a = new Attack("NormalAttack", new Strength(12), OmiType.Normal,
        Arrays.asList(new AttackEffects[]{new FollowAttack(),new CriticalAttack()}));
    attacks.put(a.getName(), a);
    a = new Attack("ULTIMATE", new Strength(10), OmiType.Fire,
        Arrays.asList(new AttackEffects[]{new HealAttack(),new FollowAttack(),new CriticalAttack()}));
    attacks.put(a.getName(), a);
    return attacks;
  }
}
