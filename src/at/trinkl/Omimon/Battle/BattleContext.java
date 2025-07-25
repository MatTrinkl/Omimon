package at.trinkl.Omimon.Battle;

import at.trinkl.Omimon.Battle.BattleCommand.BattleCommand;
import at.trinkl.Omimon.Battle.Events.BattleEvent;
import at.trinkl.Omimon.Battle.Events.BattleEventDispatcher;
import at.trinkl.Omimon.Omimon.Omimon;
import at.trinkl.Omimon.Trainer;

/**
 * Provides a limited and decoupled interface for classes that need to interact with battle flow,
 * such as {@link Omimon} and {@link BattleCommand} implementations.
 * <p>
 * This abstraction helps reduce tight coupling to the full {@link Battle} class and allows easier
 * testing and extensibility.
 * </p>
 */
public interface BattleContext {

  /**
   * Notifies the battle system that an {@link Omimon} has fainted and must be replaced or
   * resolved.
   *
   * @param omimon The fainted Omimon.
   */
  void notifyOmimonFainted(Omimon omimon);

  /**
   * Triggers victory for the specified trainer, ending the battle.
   *
   * @param winner The winning trainer. May be {@code null} if the wild Omimon wins.
   */
  void triggerVictory(Trainer winner);

  /**
   * Replaces an Omimon with another in the current battle.
   *
   * @param oldOne The Omimon being switched out.
   * @param newOne The Omimon entering the battle.
   */
  void switchOmimon(Omimon oldOne, Omimon newOne);

  /**
   * Signals that an Omimon has successfully escaped the battle (e.g., wild battle flee).
   *
   * @param omimon The escaping Omimon.
   */
  void omimonEscaped(Omimon omimon);

  /**
   * Dispatches the {@link BattleEvent} to the {@link BattleEventDispatcher
  }.
      *
      * @param battleEvent The Event to dispatch.
   */
  void dispatchEvent(BattleEvent battleEvent);

}
