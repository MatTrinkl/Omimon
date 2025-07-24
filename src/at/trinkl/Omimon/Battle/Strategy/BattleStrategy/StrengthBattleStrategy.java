package at.trinkl.Omimon.Battle.Strategy.BattleStrategy;

import at.trinkl.Omimon.Battle.Attack;
import at.trinkl.Omimon.Omimon.Omimon;
import java.util.Comparator;
import java.util.Optional;

/**
 * An implementation of {@link BattleStrategy} that selects the {@link Attack} with the highest base
 * strength, regardless of type effectiveness.
 * <p>
 * This strategy is useful for aggressive behavior where raw power is prioritized over tactical
 * advantages like type matchups.
 * </p>
 */
public class StrengthBattleStrategy implements BattleStrategy {

  /**
   * Selects the {@link Attack} with the highest base strength from the attacker's list.
   *
   * @param attacker The {@link Omimon} choosing the attack.
   * @param defender The {@link Omimon} being targeted (not used in this strategy).
   * @return The {@link Attack} with the highest {@code getStrength()} value.
   * @throws java.util.NoSuchElementException if the attacker has no available attacks.
   */
  @Override
  public Attack selectAttackFromStrategy(Omimon attacker, Omimon defender) {
    Optional<Attack> strongestAttack = attacker.getAttacks().stream()
        .max(Comparator.comparingInt(Attack::getStrength));
    return strongestAttack.get();
  }
}
