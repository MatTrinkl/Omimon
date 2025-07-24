package at.trinkl.Omimon.Battle.BattleCommand;

import at.trinkl.Omimon.Battle.Battle;
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

  private Battle battle;
  private Trainer trainer;
  private Omimon omimonToSwitch;

  /**
   * Creates a new {@code SwitchBattleCommand} with the given battle, trainer, and Omimon to switch
   * out.
   *
   * @param battle         The current {@link Battle} context.
   * @param trainer        The {@link Trainer} who owns the Omimon.
   * @param omimonToSwitch The {@link Omimon} to be switched out.
   */
  public SwitchBattleCommand(Battle battle, Trainer trainer, Omimon omimonToSwitch) {
    this.battle = battle;
    this.trainer = trainer;
    this.omimonToSwitch = omimonToSwitch;
  }

  /**
   * Executes the switch command, replacing the specified Omimon with a random one
   * from the trainer's team that is still able to fight.
   * <p>
   * Outputs a message to the console indicating the switch.
   * </p>
   *
   * @param attacker  Unused in this command, included for interface compatibility.
   * @param defender  Unused in this command, included for interface compatibility.
   */
  @Override
  public void execute(Omimon attacker, Omimon defender) {
    System.out.println(trainer.getName() + " switched out " + omimonToSwitch.getName());
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
}
