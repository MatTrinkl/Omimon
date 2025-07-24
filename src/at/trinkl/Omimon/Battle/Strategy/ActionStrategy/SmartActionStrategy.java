package at.trinkl.Omimon.Battle.Strategy.ActionStrategy;

import at.trinkl.Omimon.Battle.BattleAction;
import at.trinkl.Omimon.Omimon.OmiType;
import at.trinkl.Omimon.Omimon.Omimon;

/**
 * An implementation of {@link ActionStrategy} that selects actions based on type effectiveness.
 * <p>
 * This strategy analyzes the {@link OmiType} advantages between the attacker and defender. If the
 * combined effectiveness is below 1.0 (i.e., the attacker is at a disadvantage), it opts to switch.
 * Otherwise, it proceeds with an attack.
 * </p>
 */
public class SmartActionStrategy implements ActionStrategy {

  /**
   * Determines the next action by comparing the attacker's types against the defender's types.
   * <p>
   * Calculates a "type effectiveness score" by multiplying the effectiveness of:
   * <ul>
   *   <li>Main vs. Main</li>
   *   <li>Secondary vs. Secondary (if present)</li>
   *   <li>Main vs. Secondary (if present)</li>
   *   <li>Secondary vs. Main (if present)</li>
   * </ul>
   * If the final score is less than 1.0, the strategy chooses to switch. Otherwise, it attacks.
   * </p>
   *
   * @param attacker The {@link Omimon} which is attacking.
   * @param defender The opposing {@link Omimon}.
   * @return {@link BattleAction#SWITCH} if type advantage is poor; otherwise
   * {@link BattleAction#ATTACK}.
   */
  @Override
  public BattleAction getNextActionByStrategy(Omimon attacker, Omimon defender) {
    double typeDifference = 1;

    OmiType attMain = attacker.getBlueprint().getMainType();
    OmiType defMain = defender.getBlueprint().getMainType();
    OmiType attSec = attacker.getBlueprint().getSecoundaryType();
    OmiType defSec = defender.getBlueprint().getSecoundaryType();

    typeDifference *= attMain.getEffectivenessAgainst(defMain);

    typeDifference *= attSec != null ? attSec.getEffectivenessAgainst(defSec) : 1;
    typeDifference *= defSec != null ? attMain.getEffectivenessAgainst(defSec) : 1;
    typeDifference *= attSec != null ? attSec.getEffectivenessAgainst(defMain) : 1;

    if (typeDifference < 1) {
      return BattleAction.SWITCH;
    }
    return BattleAction.ATTACK;
  }
}
