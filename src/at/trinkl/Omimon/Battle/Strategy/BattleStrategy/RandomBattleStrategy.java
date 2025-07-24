package at.trinkl.Omimon.Battle.Strategy.BattleStrategy;

import at.trinkl.Omimon.Battle.Attack;
import at.trinkl.Omimon.Omimon.Omimon;

/**
 * An implementation of {@link BattleStrategy} that selects a random {@link Attack} from the
 * attacker's available attack list.
 * <p>
 * This strategy introduces unpredictability into battle behavior.
 * </p>
 */
public class RandomBattleStrategy implements BattleStrategy {

  /**
   * Selects a random {@link Attack} from the attacker's list of available attacks.
   *
   * @param attacker The {@link Omimon} performing the attack.
   * @param defender The {@link Omimon} being targeted (not used in this strategy).
   * @return A randomly selected {@link Attack} from the attacker.
   */
  @Override
  public Attack selectAttackFromStrategy(Omimon attacker, Omimon defender) {
    return attacker.getAttacks().get((int) (Math.random() * attacker.getAttacks().size()));
  }
}
