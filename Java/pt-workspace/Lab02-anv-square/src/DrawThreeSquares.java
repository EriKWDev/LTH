import se.lth.cs.pt.window.SimpleWindow;
import se.lth.cs.pt.square.Square;

public class DrawThreeSquares {
	public static void main(String[] args) {
		SimpleWindow window = new SimpleWindow(600, 600, "DrawSquare");
		Square square1 = new Square(150, 150, 100);
		// Square square1 = null;
		
		// square1.draw(null);
		
		square1.draw(window);
		square1.move(30, 30);
		square1.draw(window);
		square1.move(30, -60);
		square1.draw(window);
	}
}
