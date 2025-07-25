package at.trinkl.Omimon;

import at.trinkl.Omimon.Battle.Strategy.ActionStrategy.ActionStrategy;
import at.trinkl.Omimon.Omimon.Omimon;
import at.trinkl.Omimon.Omimon.OmimonBlueprint;
import at.trinkl.Omimon.Omimon.Omidex;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Trainer who owns and manages a team of {@link Omimon}s.
 * <p>
 * A Trainer can capture Omimons (currently create an instance from a blueprint), use a defined
 * {@link ActionStrategy} in battle, and keeps track of living and defeated Omimons. Each Trainer
 * has a name, gender, unique ID, and a list of active and fallen Omimons.
 * </p>
 */
public class Trainer {

  private final List<Omimon> livingOmimons;
  private final List<Omimon> deadOmimons;
  private final String name;
  private final String gender;
  private final int id;
  private final ActionStrategy actionStrategy;

  /**
   * Constructs a new {@code Trainer} with identity information and a combat strategy.
   *
   * @param name           The trainer's name.
   * @param gender         The trainer's gender.
   * @param id             A unique numeric identifier.
   * @param actionStrategy The {@link ActionStrategy} used for decision-making during battles.
   */
  public Trainer(String name, String gender, int id, ActionStrategy actionStrategy) {
    this.name = name;
    this.gender = gender;
    this.id = id;
    this.actionStrategy = actionStrategy;
    livingOmimons = new ArrayList<>();
    deadOmimons = new ArrayList<>();
  }

  /**
   * Returns the trainer's name.
   *
   * @return The name string.
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the trainer's gender.
   *
   * @return The gender string.
   */
  public String getGender() {
    return gender;
  }

  /**
   * Returns the trainer's unique identifier.
   *
   * @return The trainer ID.
   */
  public int getId() {
    return id;
  }

  /**
   * Captures a new {@link Omimon} using the given {@link OmimonBlueprint}. The new Omimon is added
   * to the team if the team has fewer than 6 members.
   *
   * @param blueprint The blueprint to create the Omimon from.
   * @param name      The nickname of the Omimon.
   * @param level     The initial level of the Omimon.
   * @return {@code true} if the Omimon was successfully added; {@code false} if the team is full.
   */
  public boolean captureOmmimon(OmimonBlueprint blueprint, String name, int level) {
    if (livingOmimons.size() < 6) {
      livingOmimons.add(blueprint.createInstance(name, level, this));
      return true;
    } else {
      return false;
    }
  }

  /**
   * Captures a new {@link Omimon} using a blueprint name resolved via {@link Omidex}.
   *
   * @param blueprint The name of the blueprint in the {@link Omidex}.
   * @param name      The nickname of the Omimon.
   * @param level     The initial level of the Omimon.
   * @return {@code true} if the Omimon was successfully added; {@code false} if the team is full.
   */
  public boolean captureOmmimon(String blueprint, String name, int level) {
    return captureOmmimon(Omidex.getInstance().getBluePrint(blueprint), name, level);
  }

  /**
   * Returns a random living {@link Omimon} from the trainer's team.
   *
   * @return A randomly selected Omimon that is still alive.
   * @throws IndexOutOfBoundsException if no Omimons are alive.
   */
  public Omimon getRandomOmimonWithCanFight() {
    return livingOmimons.get((int) (Math.random() * livingOmimons.size()));
  }

  /**
   * Moves an Omimon from the living list to the dead list after it faints.
   *
   * @param omimon The Omimon that has died.
   */
  public void onOmimonDeath(Omimon omimon) {
    deadOmimons.add(omimon);
    livingOmimons.remove(omimon);
  }

  /**
   * Returns whether the trainer still has at least one living Omimon ready to fight.
   *
   * @return {@code true} if at least one Omimon is battle-ready.
   */
  public boolean hasBattleReadyOmimons() {
    return !livingOmimons.isEmpty();
  }

  /**
   * Returns the action strategy used by this trainer in battle.
   *
   * @return The {@link ActionStrategy}.
   */
  public ActionStrategy getActionStrategy() {
    return actionStrategy;
  }

  public void healAllOmimon(){
    livingOmimons.addAll(deadOmimons);
    deadOmimons.clear();
    for (Omimon omimon : livingOmimons) {
      omimon.heal();
    }
  }
}
