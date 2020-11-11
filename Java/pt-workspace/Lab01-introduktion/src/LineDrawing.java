import java.awt.Color;

import se.lth.cs.pt.window.SimpleWindow;

public class LineDrawing {
	public static void main(String[] args) {
		int width = 2000;
		int height = 1000;
		
		SimpleWindow window = new SimpleWindow(width, height, "Test");
		window.moveTo(0, 0);
		window.setLineColor(Color.red);
		window.setLineWidth(3);
		
		while (true) {
			window.waitForMouseClick();
			// getMouseX() ger koordinaterna på skärmen,
			// vi vill ha i fönstret.
			// window.lineTo(window.getMouseX(), window.getMouseY());
			window.lineTo(window.getClickedX(), window.getClickedY());
		}
	}
}
