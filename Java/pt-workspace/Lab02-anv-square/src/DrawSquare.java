import se.lth.cs.pt.window.SimpleWindow;
import se.lth.cs.pt.square.Square;

public class DrawSquare {
	public static void main(String[] args) {
		SimpleWindow window = new SimpleWindow(600, 600, "DrawSquare");
		Square square = new Square(250, 250, 100);
		square.draw(window);
	}
}
