package Omidex.Battle;

import Omidex.Omimon.Omimon;
import Omidex.Trainer;

public class Battle {
  private Trainer trainerA, trainerB;
  private Omimon fighterA, fighterB;
  private Queue<Command> commandQueue = new LinkedList<>();

  public Battle(Trainer trainerA, Omimon wildOmimon) {
    this.trainerA = trainerA;
    this.trainerB = null;
    this.fighterA = this.trainerA.getRandomOmimonWithCanFight();
    this.fighterB = wildOmimon;
    this.fighterA.RegisterToBattle(this);
    this.fighterB.RegisterToBattle(this);
  }
  public Battle(Trainer trainerA, Trainer trainerB){
    this.trainerA = trainerA;
    this.trainerB = trainerB;
    this.fighterA = this.trainerA.getRandomOmimonWithCanFight();
    this.fighterB = this.trainerB.getRandomOmimonWithCanFight();
    this.fighterA.RegisterToBattle(this);
    this.fighterB.RegisterToBattle(this);
  }
public void executeBattle(){
    if(trainerB!=null){
      while(trainerA.hasBattleReadyOmimons && fighterB.isAlive())
    }
}
  //Commandpattern nutzen

  //observer mit omimon sagen wann sie dran sind


}
