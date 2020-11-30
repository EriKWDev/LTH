import java.awt.Color;
import java.util.Random;

public class DizzyTurtle extends RaceTurtle {
	Random random;
	int dizyness;
	
	public DizzyTurtle(RaceWindow raceWindow, int number, int dizyness) {
		super(raceWindow, number);
		
		this.random = new Random();
		this.dizyness = dizyness;
	}
	
	public void raceStep() {
		
		if(random.nextDouble() > 0.5) {
			left(dizyness);
		} else {
			left(-dizyness);
		}
		
		super.raceStep();
	}
	
	public String toString() {
		return super.toString() + " - DizzyTurtle (Yrsel: " + dizyness + ")";
	}
}
