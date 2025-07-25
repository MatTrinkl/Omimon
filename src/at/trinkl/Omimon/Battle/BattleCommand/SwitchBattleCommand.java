package at.trinkl.Omimon.Battle.BattleCommand;

import at.trinkl.Omimon.Battle.Battle;
import at.trinkl.Omimon.Battle.BattleContext;
import at.trinkl.Omimon.Battle.Events.BattleEvent;
import at.trinkl.Omimon.Battle.Events.BattleEventType;
import at.trinkl.Omimon.Omimon.Omimon;
import at.trinkl.Omimon.Trainer;

/**
 * A concrete implementation of {@link BattleCommand} that switches the active {@link Omimon} with
 * another available one from the same {@link Trainer}.
 * <p>
 * This command simulates a tactical switch during battle, replacing the current Omimon with another
 * that is able to continue fighting.
 * </p>
 */
public class SwitchBattleCommand implements BattleCommand {

  private final BattleContext battle;
  private final Trainer trainer;
  private Omimon omimonToSwitch;

  /**
   * Creates a new {@code SwitchBattleCommand} with the given battle, trainer, and Omimon to switch
   * out.
   *
   * @param battle         The current {@link BattleContext}.
   * @param trainer        The {@link Trainer} who owns the Omimon.
   * @param omimonToSwitch The {@link Omimon} to be switched out.
   */
  public SwitchBattleCommand(BattleContext battle, Trainer trainer, Omimon omimonToSwitch) {
    this.battle = battle;
    this.trainer = trainer;
    this.omimonToSwitch = omimonToSwitch;
  }

  /**
   * Executes the switch command, replacing the specified Omimon with a random one from the
   * trainer's team that is still able to fight.
   * <p>
   * Outputs a message to the console indicating the switch.
   * </p>
   */
  @Override
  public void execute() {
    battle.dispatchEvent(new BattleEvent(BattleEventType.SWITCH_PERFORMED,
        trainer.getName() + " switched out " + omimonToSwitch.getName()));
    battle.switchOmimon(omimonToSwitch, trainer.getRandomOmimonWithCanFight());
  }

  /**
   * Returns the {@link Omimon} that is being switched out.
   *
   * @return The {@link Omimon} initiating the switch.
   */
  @Override
  public Omimon getExecuter() {
    return omimonToSwitch;
  }

  /**
   * Updates the references of a {@link Omimon} in a command.
   *
   * @param oldOne The {@link Omimon} to update.
   * @param newOne The new {@link Omimon}.
   */
  @Override
  public void updateOmimons(Omimon oldOne, Omimon newOne) {
    if (oldOne == omimonToSwitch) {
      omimonToSwitch = newOne;
    }
  }
}
