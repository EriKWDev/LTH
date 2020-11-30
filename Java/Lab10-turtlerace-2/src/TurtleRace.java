import java.util.ArrayList;
import java.util.Random;

public class TurtleRace {
	public static void main(String[] args) {
		RaceWindow raceWindow = new RaceWindow();
		ArrayList<RaceTurtle> raceTurtles = new ArrayList<RaceTurtle>();
		ArrayList<RaceTurtle> winnerTurtles = new ArrayList<RaceTurtle>();
		
		Random random = new Random();
		
		int numberOfTurtles = 8;
		
		for(int i = 1; i <= numberOfTurtles; i ++) {
			double n = random.nextDouble();
			
			if(n < 1.0/3.0) {
				raceTurtles.add(new MoleTurtle(raceWindow, i));
			} else if (n >= 1.0/3.0 && n <= 2.0/3.0) {
				raceTurtles.add(new AbsentMindedTurtle(raceWindow, i, random.nextDouble()));
			} else {
				raceTurtles.add(new DizzyTurtle(raceWindow, i, random.nextInt(5) + 1));
			}
		}
		
		while(winnerTurtles.size() < numberOfTurtles) {
			for(RaceTurtle turtle : raceTurtles) {
				if(winnerTurtles.contains(turtle)) {
					continue;
				}
				
				turtle.raceStep();
				
				if(turtle.getX() >= RaceWindow.X_END_POS) {
					winnerTurtles.add(turtle);
				}
			}
			
			raceWindow.delay(1);
		}
		
		for(int i = 0; i < numberOfTurtles; i++) {
			System.out.println("På Plats " + (i + 1) + ": " + winnerTurtles.get(i).toString());
		}
	}
}
