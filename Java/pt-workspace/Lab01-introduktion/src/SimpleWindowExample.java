import se.lth.cs.pt.window.SimpleWindow;

public class SimpleWindowExample {
	public static void main(String[] args) {
		SimpleWindow window = new SimpleWindow(800, 500, "Drawing Window");
		window.writeText("Hello, World!");
		
		double distance = 1000;
		int amplitude = 100;
		
		for(int x = 0; x < distance; x++) {
			int y = (int) (Math.sin(x*0.1)*amplitude) + amplitude*2;
			// System.out.println(x + " " + y);
			window.lineTo(x, y);
		}
	}
}
