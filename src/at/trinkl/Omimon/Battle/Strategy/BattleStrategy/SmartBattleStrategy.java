package at.trinkl.Omimon.Battle.Strategy.BattleStrategy;

import at.trinkl.Omimon.Battle.Attack;
import at.trinkl.Omimon.Omimon.Omimon;
import java.util.Comparator;
import java.util.Optional;

/**
 * An implementation of {@link BattleStrategy} that selects the most effective {@link Attack} based
 * on the attack's strength against the defender's type(s).
 * <p>
 * This strategy evaluates all available attacks from the attacker and chooses the one with the
 * highest strength value, as determined by {@code getStrengthComparedWithType(defender)}.
 * </p>
 */
public class SmartBattleStrategy implements BattleStrategy {

  /**
   * Selects the strongest available {@link Attack} from the attacker, based on its effectiveness
   * against the defender.
   *
   * @param attacker The {@link Omimon} choosing an attack.
   * @param defender The {@link Omimon} being targeted.
   * @return The {@link Attack} with the highest calculated strength.
   * @throws java.util.NoSuchElementException if the attacker has no available attacks.
   */
  @Override
  public Attack selectAttackFromStrategy(Omimon attacker, Omimon defender) {
    Optional<Attack> strongestAttack = attacker.getAttacks().stream()
        .max(Comparator.comparingInt(s -> s.getStrengthComparedWithType(defender)));
    return strongestAttack.get();
  }
}
