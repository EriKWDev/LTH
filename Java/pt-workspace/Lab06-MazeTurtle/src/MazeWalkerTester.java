import java.util.Scanner;

import se.lth.cs.pt.maze.Maze;
import se.lth.cs.pt.window.SimpleWindow;

public class MazeWalkerTester {
	private SimpleWindow window = new SimpleWindow(400, 400, "aMaze Tester 5000");
	
	public static void main(String[] args) {
		MazeWalkerTester tester = new MazeWalkerTester();
		while(true) {
			tester.runAllTests();
		}
		// tester.testSelectedMaze();
		// tester.testIndividualMaze(new Maze(4));
	}
	
	public void testSelectedMaze() {
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			int mazeId = scanner.nextInt();
			window.clear();
			testIndividualMaze(new Maze(mazeId));
		}
	}
	
	public void runAllTests() {
		for(int i = 1; i <= 5; i++) {
			window.clear();
			Maze mazeToRun = new Maze(i);
			boolean succeeded = testIndividualMaze(mazeToRun);
			
			if(!succeeded) {
				System.out.println("Test for maze #" + i + " failed miserably! Do better!");
				break;
			} else {
				System.out.println("Test for maze #" + i + " succeeded!");
			}
			
			window.delay(1000);
		}
	}
	
	public boolean testIndividualMaze(Maze maze) {
		Turtle turtle = new Turtle(window, maze.getXEntry(), maze.getYEntry());
		MazeWalker walker = new MazeWalker(turtle);
		maze.draw(window);
		
		walker.walk(maze);
		
		window.moveTo(10, 10);
		
		int steps = walker.getSteps();
		int turns = walker.getTurns();
		
		window.writeText("Steps: " + steps + ", Turns: " + turns);
		
		return walker.foundExit();
	}
}
