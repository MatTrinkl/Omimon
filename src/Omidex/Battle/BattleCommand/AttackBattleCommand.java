package Omidex.Battle.BattleCommand;

import Omidex.Battle.Attack;
import Omidex.Omimon.Omimon;

public class AttackBattleCommand implements BattleCommand {
private Attack attack;
private Omimon executer;

public AttackBattleCommand(Attack attack, Omimon executer) {
  this.attack = attack;
  this.executer = executer;
}

  @Override
  public void execute(Omimon attacker, Omimon defender) {
  System.out.println(attacker.getName()+ " has attacked " +defender.getName()+" with " + attack.getName());
    attack.OnAttack(attacker, defender);
  }

  public Omimon getExecuter() {
    return executer;
  }
}
