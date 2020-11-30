
import java.util.Random;

public class MoleTurtle extends RaceTurtle {
	Random random;
	
	public MoleTurtle(RaceWindow raceWindow, int number) {
		super(raceWindow, number);
		random = new Random();
	}
	
	public void raceStep() {
		if(random.nextDouble() > 0.5) {
			this.penDown();
		} else {
			this.penUp();
		}
		
		super.raceStep();
	}
	
	public String toString() {
		return super.toString() + " - MoleTurtle";
	}
}
