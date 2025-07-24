package Omidex.Battle.BattleCommand;

import Omidex.Omimon.Omimon;

public interface BattleCommand {
public void execute(Omimon attacker, Omimon defender);
public Omimon getExecuter();
}
