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
		
		// B�rja vid labyrintens ing�ng oavset var turteln befann sig
		turtle.jumpTo(maze.getXEntry(), maze.getYEntry());
		turtle.penDown();
		
		// Ett arbitr�rt stort nummer
		int maxSteps = 20000;
		SimpleWindow window = turtle.getWindow();
		
		// Anv�nder for-loop ist�let f�r while-loop f�r att enkelt ha en "maxsteps" ifall labyrinten inte l�ses.
		for(int i = 0; i < maxSteps; i++) {
			// Om en v�gg �r framf�r oss, sv�ng till h�ger
			if(maze.wallInFront(turtle.getDirection(), turtle.getX(), turtle.getY())) {
				turtle.left(-90);
				continue;
			}
			
			// Om vi har en v�gg till v�nster, forts�tt fram�t
			if(maze.wallAtLeft(turtle.getDirection(), turtle.getX(), turtle.getY())) {
				turtle.forward(1);
				steps++;
			} else {
				// Om vi inte har en v�gg till v�nster l�ngre s� sv�nger vi till v�nster
				turtle.left(90);
				turns++;
				
				// Om vi efter det inte har en v�gg framf�r oss, d� kan vi g� fram�t
				if(!maze.wallInFront(turtle.getDirection(), turtle.getX(), turtle.getY())) {
					turtle.forward(1);
					steps++;
				}
			}
			
			// Om vi befinner oss vid utg�ngen, avsluta
			if(maze.atExit(turtle.getX(), turtle.getY())) {
				succeeded = true;
				break;
			}
			
			// V�nta eventuellt 1 ms
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
