package at.trinkl.Omimon.Omimon;


import at.trinkl.Omimon.Battle.Attack;
import at.trinkl.Omimon.Battle.Battle;
import at.trinkl.Omimon.Battle.BattleContext;
import at.trinkl.Omimon.Battle.Events.BattleEvent;
import at.trinkl.Omimon.Battle.Events.BattleEventType;
import at.trinkl.Omimon.Values.Defence;
import at.trinkl.Omimon.Values.Health;
import at.trinkl.Omimon.Values.Speed;
import java.util.*;
import at.trinkl.Omimon.Trainer;

/**
 * Represents a living {@link Omimon} instance in the game, based on a predefined
 * {@link OmimonBlueprint}.
 * <p>
 * Each Omimon has its own health, stats, trainer (can be {@code null}), and active state during a
 * battle. The class encapsulates battle behavior such as taking damage, dying, healing, and
 * registering to and from battles.
 * </p>
 */
public class Omimon {

  private OmimonBlueprint blueprint;
  private String name;
  private Health currentHealth;
  private Speed currentSpeed;
  private Defence currentDefence;
  private int level;
  private List<Attack> attacks;
  private Trainer trainer;
  private BattleContext currentBattle;

  /**
   * Returns the {@link Trainer} who owns this Omimon.
   *
   * @return The owner trainer. Can be {@code null} if the {@link Omimon} is wild.
   */
  public Trainer getTrainer() {
    return trainer;
  }

  /**
   * Returns the current level of the Omimon.
   *
   * @return The level.
   */
  public int getLevel() {
    return level;
  }

  /**
   * Returns the current {@link Battle} this Omimon is participating in.
   *
   * @return The current battle, or {@code null} if none.
   */
  public BattleContext getCurrentBattle() {
    return currentBattle;
  }

  /**
   * Constructs a new {@code Omimon} with full stats based on its blueprint.
   *
   * @param blueprint The {@link OmimonBlueprint} to use.
   * @param name      Optional nickname. If {@code null} or empty, blueprint name is used.
   * @param initLevel Initial level.
   * @param trainer   The owning trainer. Can be {@code null} if the {@link Omimon} is wild.
   */
  public Omimon(OmimonBlueprint blueprint, String name, int initLevel, Trainer trainer) {
    this.blueprint = blueprint;
    if (name == null || name.isEmpty()) {
      this.name = blueprint.getName();
    } else {
      this.name = name;
    }
    this.currentHealth = new Health(blueprint.getBaseHealth());
    this.currentSpeed = new Speed(blueprint.getBaseSpeed());
    this.currentDefence = new Defence(blueprint.getBaseDefence());
    this.level = initLevel;
    this.attacks = blueprint.getAttacks();
    this.trainer = trainer;
  }

  /**
   * Constructs a level 1 Omimon with the blueprint's default name.
   *
   * @param blueprint The {@link OmimonBlueprint}.
   * @param trainer   The owning trainer.
   */
  public Omimon(OmimonBlueprint blueprint, Trainer trainer) {
    this(blueprint, null, 1, trainer);
  }

  /**
   * Constructs a level 1 Omimon with a custom name.
   *
   * @param blueprint The {@link OmimonBlueprint}.
   * @param name      The custom name.
   * @param trainer   The owning trainer.
   */
  public Omimon(OmimonBlueprint blueprint, String name, Trainer trainer) {
    this(blueprint, name, 1, trainer);
  }

  /**
   * Returns the original blueprint this Omimon was created from.
   *
   * @return The {@link OmimonBlueprint}.
   */
  public OmimonBlueprint getBlueprint() {
    return blueprint;
  }

  /**
   * Returns the name of this Omimon.
   *
   * @return The name.
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the current health value.
   *
   * @return Health value.
   */
  public int getCurrentHealth() {
    return currentHealth.getValue();
  }

  /**
   * Returns the current speed value.
   *
   * @return Speed value.
   */
  public int getCurrentSpeed() {
    return currentSpeed.getValue();
  }

  /**
   * Returns the current defence value.
   *
   * @return Defence value.
   */
  public int getCurrentDefence() {
    return currentDefence.getValue();
  }

  /**
   * Returns the current level of the Omimon.
   *
   * @return Level.
   */
  public int getCurrentLevel() {
    return level;
  }

  /**
   * Returns the list of available {@link Attack}s this Omimon can use.
   *
   * @return A list of attacks.
   */
  public List<Attack> getAttacks() {
    return attacks;
  }

  /**
   * Applies damage to this Omimon. If health reaches zero, triggers {@link #onDeath()}.
   *
   * @param currentDamage The amount of damage to apply.
   */
  public void takeDamage(int currentDamage) {
    if (!currentHealth.updateValue(currentDamage)) {
      onDeath();
    }
  }

  /**
   * Fully heals this {@link Omimon}'s health back to maximum.
   */
  public void Heal() {
    currentHealth.Heal();
  }

  /**
   * Returns whether the Omimon is currently alive (health > 0).
   *
   * @return {@code true} if alive, {@code false} otherwise.
   */
  public boolean isAlive() {
    return currentHealth.getValue() > 0;
  }

  /**
   * Registers this Omimon to a given {@link Battle}.
   *
   * @param battle The battle to join.
   */
  public void registerToBattle(BattleContext battle) {
    currentBattle = battle;
    currentBattle.dispatchEvent(new BattleEvent(BattleEventType.REGISTER_SUCCESSFUL,getName()));
  }

  /**
   * Deregisters this Omimon from the current battle.
   */
  public void deRegisterFromBattle() {
    currentBattle.dispatchEvent(new BattleEvent(BattleEventType.DEREGISTER_SUCCESSFUL,getName()));
    currentBattle = null;
  }

  /**
   * Returns whether the {@link Omimon}'s current {@link Health} is below a given percentage of its
   * maximum health.
   *
   * @param percentage The threshold (e.g., 0.25 for 25%).
   * @return {@code true} if current health is at or below that percentage.
   */
  public boolean isHealthBelowPercentage(double percentage) {
    return ((double) currentHealth.getValue() / blueprint.getBaseHealth()) <= percentage;
  }

  /**
   * Internal death handling. Notifies the trainer and battle system that this Omimon has fainted.
   */
  private void onDeath() {
    trainer.onOmimonDeath(this);
    currentBattle.notifyOmimonFainted(this);

  }
}
