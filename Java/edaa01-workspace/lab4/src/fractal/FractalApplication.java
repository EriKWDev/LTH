package fractal;

import koch.Koch;
import mountain.*;

public class FractalApplication {
	public static void main(String[] args) {
		Fractal[] fractals = new Fractal[2];
		
		fractals[1] = new Koch(300);
		
		fractals[0] = new Mountain(
				40.0,
				new Point(100 + 00, 500 - 90),
				new Point(300 - 30, 100 + 00),
				new Point(500 + 30, 500 - 30)
		);
	    new FractalView(fractals, "Fraktaler", 600, 600);
	}

}
