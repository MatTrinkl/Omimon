package at.trinkl.Omimon.Omimon;

import at.trinkl.Omimon.Battle.Attack;
import at.trinkl.Omimon.Battle.Strategy.BattleStrategy.BattleStrategy;

import at.trinkl.Omimon.Trainer;
import at.trinkl.Omimon.Values.BaseValue;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a blueprint for creating {@link Omimon} instances.
 * <p>
 * An {@code OmimonBlueprint} defines the base stats, types, available attacks, evolution path, and
 * default battle strategy for a species of Omimon.
 * </p>
 */
public class OmimonBlueprint {

  private final String name;
  private final OmiType mainType;
  private final OmiType secoundaryType;
  private int baseDefence;
  private int baseHealth;
  private int baseSpeed;
  private final String evolution;
  private final int levelToEvolve;
  private final List<Attack> attacks;
  private final BattleStrategy battleStrategy;

  /**
   * Constructs a new {@code OmimonBlueprint} with the given parameters.
   *
   * @param name           The name of the Omimon species.
   * @param mainType       The primary {@link OmiType}.
   * @param secoundaryType The secondary {@link OmiType}, or {@code null} if none.
   * @param baseDefence    The base defence value.
   * @param baseHealth     The base health value.
   * @param baseSpeed      The base speed value.
   * @param evolution      The name of the evolved form (looked up in {@link Omidex}).
   * @param levelToEvolve  The level at which evolution should occur.
   * @param attacks        The list of {@link Attack}s this species knows by default.
   * @param battleStrategy The {@link BattleStrategy} this species uses in battle.
   */
  public OmimonBlueprint(String name, OmiType mainType, OmiType secoundaryType, int baseDefence,
      int baseHealth, int baseSpeed, String evolution, int levelToEvolve,
      List<Attack> attacks, BattleStrategy battleStrategy) {
    this.name = name;
    this.mainType = mainType;
    this.secoundaryType = secoundaryType;
    setBaseDefence(baseDefence);
    setBaseHealth(baseHealth);
    setBaseSpeed(baseSpeed);
    this.evolution = evolution;
    this.levelToEvolve = levelToEvolve;
    this.attacks = Objects.requireNonNullElseGet(attacks, ArrayList::new);
    this.battleStrategy = battleStrategy;
  }

  /**
   * Creates a new {@link Omimon} instance based on this blueprint.
   *
   * @param nickname  The nickname to assign to the Omimon.
   * @param initLevel The initial level.
   * @param trainer   The owning {@link Trainer}. Can be {@code null}.
   * @return A new Omimon instance.
   */
  public Omimon createInstance(String nickname, int initLevel, Trainer trainer) {
    return new Omimon(this, nickname, initLevel, trainer);
  }

  /**
   * Returns the default {@link BattleStrategy} used by this blueprint in battles.
   *
   * @return The battle strategy instance.
   */
  public BattleStrategy getBattleStrategy() {
    return battleStrategy;
  }


  /**
   * Returns the name of the Omimon species.
   *
   * @return The species name.
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the primary type of the Omimon.
   *
   * @return The main {@link OmiType}.
   */
  public OmiType getMainType() {
    return mainType;
  }

  /**
   * Returns the secondary type of the Omimon, or {@code null} if none.
   *
   * @return The secondary {@link OmiType}.
   */
  public OmiType getSecoundaryType() {
    return secoundaryType;
  }

  /**
   * Returns the base defence value.
   *
   * @return Defence stat.
   */
  public int getBaseDefence() {
    return baseDefence;
  }

  /**
   * Returns the base health value.
   *
   * @return Health stat.
   */
  public int getBaseHealth() {
    return baseHealth;
  }

  /**
   * Returns the base speed value.
   *
   * @return Speed stat.
   */
  public int getBaseSpeed() {
    return baseSpeed;
  }

  /**
   * Returns the blueprint of the evolved form of this Omimon, if it has one.
   *
   * @return The evolved {@link OmimonBlueprint}.
   */
  public OmimonBlueprint getEvolution() {
    return Omidex.getInstance().getBluePrint(this.evolution);
  }

  /**
   * Returns the level required for evolution.
   *
   * @return Evolution level threshold.
   */
  public int getLevelToEvolve() {
    return levelToEvolve;
  }

  /**
   * Returns the list of known attacks for this blueprint.
   *
   * @return A list of {@link Attack}s.
   */
  public List<Attack> getAttacks() {
    return attacks;
  }

  /**
   * Adds a new attack to the Omimon's moveset.
   *
   * @param attack The {@link Attack} to add.
   */
  public void addAttack(Attack attack) {
    attacks.add(attack);
  }

  /**
   * Sets and validates the base defence value.
   *
   * @param baseDefence The raw base value.
   */
  private void setBaseDefence(int baseDefence) {
    this.baseDefence = BaseValue.CastValue(baseDefence);
  }

  /**
   * Sets and validates the base health value.
   *
   * @param baseHealth The raw base value.
   */
  private void setBaseHealth(int baseHealth) {
    this.baseHealth = BaseValue.CastValue(baseHealth);
  }

  /**
   * Sets and validates the base speed value.
   *
   * @param baseSpeed The raw base value.
   */
  private void setBaseSpeed(int baseSpeed) {
    this.baseSpeed = BaseValue.CastValue(baseSpeed);
  }

}
