package at.trinkl.Omimon.Omimon;

import at.trinkl.Omimon.Battle.Attack;
import at.trinkl.Omimon.Battle.Strategy.BattleStrategy.BattleStrategy;
import java.util.ArrayList;
import java.util.List;

/**
 * A builder class for creating {@link OmimonBlueprint} instances using a fluent API.
 * <p>
 * This builder simplifies the construction of complex Omimon templates by allowing chained setter
 * methods and optional fields such as evolution, secondary type, and battle strategy.
 * </p>
 *
 * <pre>{@code
 * OmimonBlueprint blueprint = new OmimonBlueprintBuilder()
 *     .setName("Corgimon")
 *     .setBaseHealth(100)
 *     .setMainType(OmiType.Fire)
 *     .setBaseSpeed(50)
 *     .addAttack(cuddleAttack)
 *     .setBattleStrategy(new SmartBattleStrategy())
 *     .build();
 * }</pre>
 */
public class OmimonBlueprintBuilder {

  private String name;
  private OmiType mainType;
  private OmiType secoundaryType;
  private int baseDefence;
  private int baseHealth;
  private int baseSpeed;
  private String evolution;
  private int levelToEvolve;
  private List<Attack> attacks;
  private BattleStrategy battleStrategy;

  /**
   * Creates a new empty {@code OmimonBlueprintBuilder}.
   */
  public OmimonBlueprintBuilder() {
  }

  /**
   * Sets the name of the Omimon species.
   *
   * @param name The species name.
   * @return This builder instance.
   */
  public OmimonBlueprintBuilder setName(String name) {
    this.name = name;
    return this;
  }

  /**
   * Sets the main type of the Omimon.
   *
   * @param mainType The main {@link OmiType}.
   * @return This builder instance.
   */
  public OmimonBlueprintBuilder setMainType(OmiType mainType) {
    this.mainType = mainType;
    return this;
  }

  /**
   * Sets the secondary type of the Omimon (optional).
   *
   * @param secoundaryType The secondary {@link OmiType}.
   * @return This builder instance.
   */
  public OmimonBlueprintBuilder setSecoundaryType(OmiType secoundaryType) {
    this.secoundaryType = secoundaryType;
    return this;
  }

  /**
   * Sets the base defence value.
   *
   * @param baseDefence The defence stat.
   * @return This builder instance.
   */
  public OmimonBlueprintBuilder setBaseDefence(int baseDefence) {
    this.baseDefence = baseDefence;
    return this;
  }

  /**
   * Sets the base health value.
   *
   * @param baseHealth The health stat.
   * @return This builder instance.
   */
  public OmimonBlueprintBuilder setBaseHealth(int baseHealth) {
    this.baseHealth = baseHealth;
    return this;
  }

  /**
   * Sets the base speed value.
   *
   * @param baseSpeed The speed stat.
   * @return This builder instance.
   */
  public OmimonBlueprintBuilder setBaseSpeed(int baseSpeed) {
    this.baseSpeed = baseSpeed;
    return this;
  }

  /**
   * Sets the evolution name for this Omimon species.
   *
   * @param evolution The name of the Omimon evolved too (optional).
   * @return This builder instance.
   */
  public OmimonBlueprintBuilder setEvolution(String evolution) {
    this.evolution = evolution;
    return this;
  }

  /**
   * Sets the level at which this Omimon evolves.
   *
   * @param level The evolution level.
   * @return This builder instance.
   */
  public OmimonBlueprintBuilder setLevelToEvolve(int level) {
    this.levelToEvolve = level;
    return this;
  }

  /**
   * Adds an {@link Attack} to the Omimon's default moveset.
   *
   * @param attack The attack to add.
   * @return This builder instance.
   */
  public OmimonBlueprintBuilder addAttack(Attack attack) {
    if (this.attacks == null) {
      this.attacks = new ArrayList<>();
    }
    this.attacks.add(attack);
    return this;
  }

  /**
   * Sets the default {@link BattleStrategy} this Omimon uses in combat.
   *
   * @param battleStrategy The battle strategy instance.
   * @return This builder instance.
   */
  public OmimonBlueprintBuilder setBattleStrategy(BattleStrategy battleStrategy) {
    this.battleStrategy = battleStrategy;
    return this;
  }
  /**
   * Builds and returns a new {@link OmimonBlueprint} using the provided parameters.
   *
   * @return A fully constructed {@link OmimonBlueprint}.
   */
  public OmimonBlueprint build() {
    return new OmimonBlueprint(this.name, this.mainType, this.secoundaryType, this.baseDefence,
        this.baseHealth, this.baseSpeed, this.evolution, this.levelToEvolve, this.attacks,
        this.battleStrategy);
  }

}
