package Omidex.Battle.BattleCommand;

import Omidex.Battle.Attack;
import Omidex.Omimon.Omimon;

public class AttackBattleCommand implements BattleCommand {
private Attack attack;
private Omimon attacker;
private Omimon defender;

public AttackBattleCommand(Attack attack, Omimon attacker, Omimon defender) {
  this.attack = attack;
  this.attacker = attacker;
  this.defender = defender;
}

  @Override
  public void execute() {
    attack.OnAttack(attacker, defender);
  }
}
