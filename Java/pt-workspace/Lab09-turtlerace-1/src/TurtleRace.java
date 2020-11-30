import java.util.ArrayList;

public class TurtleRace {
	public static void main(String[] args) {
		RaceWindow raceWindow = new RaceWindow();
		ArrayList<RaceTurtle> raceTurtles = new ArrayList<RaceTurtle>();
		ArrayList<RaceTurtle> winnerTurtles = new ArrayList<RaceTurtle>();
		
		int numberOfTurtles = 8;
		
		for(int i = 1; i <= numberOfTurtles; i ++) {
			raceTurtles.add(new RaceTurtle(raceWindow, i));
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
			
			raceWindow.delay(30);
		}
		
		for(int i = 0; i < 3; i++) {
			System.out.println("På Plats " + (i + 1) + ": " + winnerTurtles.get(i).toString());
		}
	}
}
