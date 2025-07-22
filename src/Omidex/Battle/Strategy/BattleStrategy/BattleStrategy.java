package Omidex.Battle.Strategy.BattleStrategy;

import Omidex.Battle.Attack;
import Omidex.Omimon.Omimon;

public interface BattleStrategy {
public Attack selectAttackViaStrategy(Omimon attacker, Omimon defender);
}
