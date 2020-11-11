import se.lth.cs.pt.window.SimpleWindow;

public class Graphics {
	private SimpleWindow window;
	private int width;
	private int height;
	private int blockSize;
	
	public Graphics(int width, int height, int blockSize) {
		this.width = width;
		this.height = height;
		this.blockSize = blockSize;
		this.window = new SimpleWindow(width * blockSize, height * blockSize, "Digging!");
	}
	
	public void block(int x, int y, java.awt.Color color) {
		window.setLineColor(color);
		
		int left   = x * blockSize;
		int right  = left + blockSize - 1;
		int top    = y * blockSize;
		int bottom = top + blockSize - 1;
		
		// g) Ordningen blir LT, RT, LB, RB
		for(int row = top; row <= bottom; row++) {
			window.moveTo(left, row);
			window.lineTo(right, row);
		}
	}
	
	public char waitForKey() {
		return window.waitForKey();
	}
	
	public void square() {
		window.moveTo(10, 10);
		window.lineTo(10, 20);
		window.lineTo(20, 20);
		window.lineTo(20, 10);
		window.lineTo(10, 10);
	}
	
	public void rectangle(int x, int y, int w, int h, java.awt.Color c) {
		for (int yy = y; yy < y + h; yy++) {
			for(int xx = x; xx < x + w; xx++) {
				block(xx, yy, c);
			}
		}
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
