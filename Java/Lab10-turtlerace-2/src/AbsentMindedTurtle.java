import java.awt.Color;
import java.util.Random;

public class AbsentMindedTurtle extends RaceTurtle {
	Random random;
	double absentMindedPercentage;
	
	public AbsentMindedTurtle(RaceWindow raceWindow, int number, double absentMindedPercentage) {
		super(raceWindow, number);
		
		this.random = new Random();
		this.absentMindedPercentage = absentMindedPercentage;
	}
	
	public void raceStep() {
		if(random.nextDouble() < absentMindedPercentage) {
			return;
		}
		
		super.raceStep();
	}
	
	public String toString() {
		return super.toString() + " - AbsentMindedTurtle (" + (int) (absentMindedPercentage*100) + "% Frånvarande)";
	}
}
