package at.trinkl.Omimon.Battle.BattleCommand;

import at.trinkl.Omimon.Battle.Attack;
import at.trinkl.Omimon.Battle.Battle;
import at.trinkl.Omimon.Battle.BattleContext;
import at.trinkl.Omimon.Omimon.Omimon;
import at.trinkl.Omimon.Trainer;

/**
 * Factory for creating various {@link BattleCommand} instances based on the current battle context
 * and combat scenario.
 * <p>
 * This factory helps decouple the creation logic of command objects from the main {@link Battle}
 * class, following the Factory design pattern.
 * </p>
 */
public class BattleCommandFactory {

  private final BattleContext battleContext;

  /**
   * Constructs a new factory that uses the given battle context for command creation.
   *
   * @param battleContext The current battle context (e.g., an instance of {@link Battle}).
   */
  public BattleCommandFactory(BattleContext battleContext) {
    this.battleContext = battleContext;
  }

  /**
   * Creates a command representing a standard attack from an {@link Omimon}.
   *
   * @param attack   The attack to execute.
   * @param attacker The Omimon performing the attack.
   * @return A new {@link AttackBattleCommand}.
   */
  public BattleCommand createAttackCommand(BattleContext battleContext,Attack attack, Omimon attacker,Omimon defender) {
    return new AttackBattleCommand(battleContext,attack, attacker,defender);
  }

  /**
   * Creates a command to switch the specified Omimon out of battle.
   *
   * @param omimonToSwitch The Omimon to be switched out.
   * @return A new {@link SwitchBattleCommand}.
   */
  public BattleCommand createSwitchCommand(Omimon omimonToSwitch) {
    Trainer trainer = omimonToSwitch.getTrainer();
    return new SwitchBattleCommand(battleContext, trainer, omimonToSwitch);
  }

  /**
   * Creates a command for an Omimon attempting to escape a battle (wild encounter).
   *
   * @param omimonEscaping The Omimon trying to escape.
   * @return A new {@link EscapeBattleCommand}.
   */
  public BattleCommand createEscapeCommand(Omimon omimonEscaping) {
    return new EscapeBattleCommand(battleContext, omimonEscaping);
  }
}

