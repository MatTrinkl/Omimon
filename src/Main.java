import Omidex.Battle.Battle;
import Omidex.Battle.Strategy.ActionStrategy.CautiousActionStrategy;
import Omidex.Battle.Strategy.ActionStrategy.ConfidentActionStrategy;
import Omidex.Battle.Strategy.ActionStrategy.SmartActionStrategy;
import Omidex.Omimon.*;
import Omidex.Trainer;


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
    Battle battle = new Battle(trainerA, trainerB);
    battle.executeBattle();
  }
}