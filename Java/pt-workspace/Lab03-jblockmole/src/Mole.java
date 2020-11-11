

public class Mole {
	private Graphics graphics = new Graphics(50, 50, 20);
	
	public static void main(String[] args) {
		System.out.println("Keep on digging!");
		
		Mole mole = new Mole();
		mole.drawWorld();
		mole.dig();
	}
	
	public void dig() {
		int x = graphics.getWidth() / 2; // För att börja på mitten
		int y = graphics.getHeight() / 2;
		
		int oldX = x;
		int oldY = y;
		
		while (true) {
			if(oldX != x && oldY != y) {
				graphics.block(oldX, oldY, Colors.TUNNEL_COLOR);
			}
			
			graphics.block(x, y, Colors.MOLE_COLOR);
			char key = graphics.waitForKey();
			
			oldX = x;
		    oldY = y;
			
			//     if (key == 'w') { y = y > 0 ? y - 1 : y; }
		    //else if (key == 'a') { x = x > 0 ? x - 1 : x; }
		    //else if (key == 's') { y = y < graphics.getHeight()-1 ? y + 1 : y; }
		    //else if (key == 'd') { x = x < graphics.getWidth()-1 ? x + 1 : x; }
			     
			switch(key) {
				default:
				case 'w': y = y > 0 ? y - 1 : y; break;
				case 'a': x = x > 0 ? x - 1 : x; break;
				case 's': y = y < graphics.getHeight()-1 ? y + 1 : y; break;
				case 'd': x = x < graphics.getWidth()-1 ? x + 1 : x; break;
			}
		}
	}
	
	public void drawWorld() {
		// graphics.square();
		graphics.rectangle(0, 0, graphics.getWidth(), graphics.getHeight(), Colors.SOIL_COLOR);
		
		// graphics.block(10, 10, Colors.MOLE_COLOR);
		// graphics.block(20, 10, Colors.SOIL_COLOR);
		// graphics.block(13, 23, Colors.TUNNEL_COLOR);
	}
}
