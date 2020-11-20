import se.lth.cs.pt.window.SimpleWindow;

public class Turtle {
	private SimpleWindow window;
	private double x;
	private double y;
	private double angle = 0;
	private boolean penIsDown = false;

	/** Skapar en sköldpadda som ritar i ritfönstret w. Från början 
	    befinner sig sköldpaddan i punkten x, y med pennan lyft och 
	    huvudet pekande rakt uppåt i fönstret (i negativ y-riktning). */
	public Turtle(SimpleWindow w, int x, int y) {
		this.window = w;
		this.x = x;
		this.y = y;
		this.turnNorth();
	}

	/** Sänker pennan. */
	public void penDown() {
		penIsDown = true;
	}
	
	public SimpleWindow getWindow() {
		return window;
	}
	
	/** Lyfter pennan. */
	public void penUp() {
		penIsDown = false;
	}
	
	/** Går rakt framåt n pixlar i den riktning huvudet pekar. */
	public void forward(int n) {
		window.moveTo((int) Math.round(x), (int) Math.round(y));
		
		double endX = x + Math.cos(angle) * n;
		double endY = y - Math.sin(angle) * n;
		
		if(penIsDown) {
			window.lineTo((int) Math.round(endX), (int) Math.round(endY));
		}
		
		this.x = endX;
		this.y = endY;
	}

	/** Vrider beta grader åt vänster runt pennan. */
	public void left(int beta) {
		angle += Math.toRadians(beta);
	}

	/** Går till punkten newX, newY utan att rita. Pennans läge (sänkt
	    eller lyft) och huvudets riktning påverkas inte. */
	public void jumpTo(int newX, int newY) {
		x = newX;
		y = newY;
		window.moveTo((int) Math.round(x), (int) Math.round(y));
	}

	/** Återställer huvudriktningen till den ursprungliga. */
	public void turnNorth() {
		angle = Math.toRadians(90);
	}

	/** Tar reda på x-koordinaten för sköldpaddans aktuella position. */
	public int getX() {
		return (int) Math.round(x);
	}

 	/** Tar reda på y-koordinaten för sköldpaddans aktuella position. */
	public int getY() {
		return (int) Math.round(y);
	}
  
	/** Tar reda på sköldpaddans riktning, i grader från den positiva X-axeln. */
 	public int getDirection() {
 		return (int) Math.round(Math.toDegrees(angle));
	}
}
