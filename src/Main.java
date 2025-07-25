import at.trinkl.Omimon.Battle.Battle;
import at.trinkl.Omimon.Battle.Events.BattleEventListener;
import at.trinkl.Omimon.Battle.Events.ConsoleBattleLogger;
import at.trinkl.Omimon.Battle.Strategy.ActionStrategy.CautiousActionStrategy;
import at.trinkl.Omimon.Battle.Strategy.ActionStrategy.ConfidentActionStrategy;
import at.trinkl.Omimon.Omimon.Omidex;
import at.trinkl.Omimon.Omimon.Omimon;
import at.trinkl.Omimon.Trainer;


public class Main {

  public static void main(String[] args) {
    Omidex omidex= Omidex.getInstance();
    Trainer trainerA = new Trainer("Ash","Male",123456,new ConfidentActionStrategy());
    Trainer trainerB = new Trainer("Bob","Male",124456,new CautiousActionStrategy());
    trainerA.captureOmmimon("Corgimon","Lizzy",10);
    trainerB.captureOmmimon("Queenmon","Camilo",10);
    trainerA.captureOmmimon("Picka","Kman",10);
    trainerB.captureOmmimon("Glumandi","Bernd",10);
    trainerA.captureOmmimon("Picka","Toast",10);
    trainerB.captureOmmimon("Queenmon","Snowball",10);
    //Battle battle = new Battle(trainerA, trainerB);
    //battle.addBattleEventListener(new ConsoleBattleLogger());
    //battle.executeBattle();

    Omimon wild = omidex.getBluePrint("Corgimon").createInstance("",10,null);
    trainerA.healAllOmimon();
    Battle wildBattle = new Battle(trainerA, wild);
    wildBattle.addBattleEventListener(new ConsoleBattleLogger());
    wildBattle.executeBattle();
  }
}