package Omidex.Battle;

import Omidex.Battle.Strategy.BattleStrategy.BattleStrategy;
import Omidex.Battle.Strategy.ActionStrategy.ActionStrategy;
import Omidex.Omimon.Omimon;

public record BattleContext(
    Omimon omimon,
    BattleStrategy battleStrategy,
    ActionStrategy moveStrategy
) {

}
