import java.util.Random;

import se.lth.cs.pt.window.SimpleWindow;

public class FemOchSex {
	private SimpleWindow window = new SimpleWindow(800, 800, "Uppgift 5 och 6");
	
	public static void main(String[] args) {
		FemOchSex solver = new FemOchSex();
		
		//solver.runFem();
		solver.runSex();
	}
	
	public void runFem() {
		while(true) {
			fem(1);
			
			//fem(5); // Kör fem() fast uppskalat 5 gånger
			//fem(3); // Kör fem() fast uppskalat 3 gånger
			
			window.waitForKey();
			
			if(window.getKey() == 'c') {
				window.clear();
			}
		}
	}
	
	public void fem(int scale) {
		Turtle turtle = new Turtle(window, window.getWidth()/2, window.getHeight()/2);
		Random random = new Random();
		
		turtle.penDown();
		for(int n = 0; n < 1000; n++) {
			int turnAngle = random.nextInt(360) - 180;
			turtle.left(turnAngle);
			
			int distance = random.nextInt(10) + 1;
			turtle.forward(distance * scale);
		}
	}
	
	public void runSex() {
		sex(2);
		
		//sex(5); // Kör sex() fast uppskalat 5 gånger
		//sex(3); // Kör sex() fast uppskalat 3 gånger
	}
	
	public void sex(int scale) {
		double distanceBetweenTurtles = Double.MAX_VALUE;
		Turtle turtle1 = new Turtle(window, 250, 250);
		Turtle turtle2 = new Turtle(window, 350, 350);
		Random random = new Random();
		
		turtle1.penDown();
		turtle2.penDown();
		while(distanceBetweenTurtles >= 50*scale) {
			// turtle 1
			int turnAngle1 = random.nextInt(360) - 180;
			turtle1.left(turnAngle1);
			
			int distance1 = random.nextInt(10) + 1;
			turtle1.forward(distance1*scale);
			
			// turtle 2
			int turnAngle2 = random.nextInt(360) - 180;
			turtle2.left(turnAngle2);
			
			int distance2 = random.nextInt(10) + 1;
			turtle2.forward(distance2*scale);
			
			// Beräkna avståndet
			// Math.hypot();
			distanceBetweenTurtles = Math.sqrt(Math.pow(turtle1.getX() - turtle2.getX(), 2) + Math.pow(turtle1.getY() - turtle2.getY(), 2));
			
			// Vänta 10 ms
			// window.delay(10);
			
			// Vänta 1 ms istället..
			window.delay(1);
		}
	}
}
