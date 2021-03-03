package map;

import java.util.HashMap;
import java.util.Random;

public class SimpleHashMapApp {
	public static void main(String[] args) {
		SimpleHashMap<Integer, Integer> shm = new SimpleHashMap();
		shm.put(-128, 10);
		shm.put(128, 48);
		shm.put(128, 56);
		shm.put(128, 89);
		
		int n = 130;
		for(int i = -n; i <= n; i++) {
			shm.put(i, i*2);
		}
		
//		Random random = new Random();
		
//		for(int i = 0; i < 100; i++) {
//			int val = random.nextInt(100) - 100;
//			shm.put(val + random.nextInt(10), val);
//		}
		
		shm.remove(0);
		
		shm.put(10, 4);
		shm.put(2, 345);
		
		System.out.println(shm.show());
	}
}
