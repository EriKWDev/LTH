import se.lth.cs.pt.window.SimpleWindow;
import se.lth.cs.pt.square.Square;

public class ClickSquare {
	public static void main(String[] args) {
		SimpleWindow window = new SimpleWindow(600, 600, "Moving Square");
		int x = 0;
		int y = 0;
		int goalX = 0;
		int goalY = 0;

		Square square = new Square(x, y, 100);

		while (true) {
			window.waitForMouseClick();
			// window.clear();

			goalX = window.getClickedX();
			goalY = window.getClickedY();

			// int startX = square.getX();
			// int startY = square.getY();

			for (double t = 0; t <= 1; t += 0.05) {
				// Linjär rörelse
				//x = (int)(startX + (goalX - startX)*t);
				//y = (int)(startY + (goalY - startY)*t);

				// (Smoother) Rörelse som beror på förra positionen:
				x = (int) (x + (goalX - x) * t);
				y = (int) (y + (goalY - y) * t);

				// window.clear();
				// square.erase(window);

				square = new Square(x, y, 100);
				square.draw(window);
				SimpleWindow.delay(1000 / 60);
			}

			// SimpleWindow.delay(10);
		}
	}
}
