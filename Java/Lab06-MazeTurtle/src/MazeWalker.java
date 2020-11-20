import se.lth.cs.pt.maze.Maze;
import se.lth.cs.pt.window.SimpleWindow;

public class MazeWalker {
	private Turtle turtle;
	private boolean succeeded = false;
	private int steps = 0;
	private int turns = 0;
	
	public MazeWalker(Turtle turtle) {
		this.turtle = turtle;
	}
	
	public void walk(Maze maze) {
		turtle.penUp();
		
		// Börja vid labyrintens ingång oavset var turteln befann sig
		turtle.jumpTo(maze.getXEntry(), maze.getYEntry());
		turtle.penDown();
		
		// Ett arbiträrt stort nummer
		int maxSteps = 20000;
		SimpleWindow window = turtle.getWindow();
		
		// Använder for-loop istälet för while-loop för att enkelt ha en "maxsteps" ifall labyrinten inte löses.
		for(int i = 0; i < maxSteps; i++) {
			// Om en vägg är framför oss, sväng till höger
			if(maze.wallInFront(turtle.getDirection(), turtle.getX(), turtle.getY())) {
				turtle.left(-90);
				continue;
			}
			
			// Om vi har en vägg till vänster, fortsätt framåt
			if(maze.wallAtLeft(turtle.getDirection(), turtle.getX(), turtle.getY())) {
				turtle.forward(1);
				steps++;
			} else {
				// Om vi inte har en vägg till vänster längre så svänger vi till vänster
				turtle.left(90);
				turns++;
				
				// Om vi efter det inte har en vägg framför oss, då kan vi gå framåt
				if(!maze.wallInFront(turtle.getDirection(), turtle.getX(), turtle.getY())) {
					turtle.forward(1);
					steps++;
				}
			}
			
			// Om vi befinner oss vid utgången, avsluta
			if(maze.atExit(turtle.getX(), turtle.getY())) {
				succeeded = true;
				break;
			}
			
			// Vänta eventuellt 1 ms
			window.delay(1);
		}
	}
	
	public int getSteps() {
		return steps;
	}
	
	public int getTurns() {
		return turns;
	}
	
	public boolean foundExit() {
		return succeeded;
	}
}
