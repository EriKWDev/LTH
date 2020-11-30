import java.awt.Color;
import java.util.Random;

public class RaceTurtle extends Turtle {
	int number;
	Random random;
	
	public RaceTurtle(RaceWindow raceWindow, int number) {
		super(raceWindow, RaceWindow.getStartXPos(number), RaceWindow.getStartYPos(number));
		this.number = number;
		this.random = new Random();
		this.penDown();
		this.left(-90);
	}
	
	void raceStep() {
		int distance = random.nextInt(6) + 1;
		this.forward(distance);
	}
	
	public String toString() {
		return "Nummer " + number;
	}
}
