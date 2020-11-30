package memory;

public class MemoryGame {
	public static void main(String[] args) {
		String[] frontFileNames = {"can.jpg", "flopsy_mopsy_cottontail.jpg",
				"friends.jpg", "mother_ladybird.jpg", "mr_mcgregor.jpg",
				"mrs_rabbit.jpg", "mrs_tittlemouse.jpg", "radishes.jpg" };
		
		MemoryBoard memoryBoard = new MemoryBoard(frontFileNames.length/2, "back.jpg", frontFileNames);
		MemoryWindow window = new MemoryWindow(memoryBoard);
		
		window.drawBoard();
		int currentTurn = 0;
		MemoryCardImage previousCard = null;
		
		while(!memoryBoard.hasWon()) {
			window.waitForMouseClick();
			
			int r = window.getMouseRow();
			int c = window.getMouseCol();
			System.out.println("Clicked r: " + r + ", c: " + c);
			
			MemoryCardImage card = memoryBoard.getCard(r, c);
			if(card.isBackUp()) {
				memoryBoard.turnCard(r, c);
				if(currentTurn % 2 == 0) {
					previousCard = card;
				} else {
					if(!previousCard.isSameAs(card)) {
						window.clear();
						window.drawBoard();
						window.moveTo(10, 10);
						window.writeText("How many clicks: " + currentTurn);
						
						window.delay(1000);
						card.flip();
						if(previousCard != null) {
							previousCard.flip();
						}
					}
				}
				currentTurn++;
			} else {
				System.out.println("Already turned that one!");
			}
			
			window.clear();
			window.drawBoard();
			window.moveTo(10, 10);
			window.writeText("How many clicks: " + currentTurn);
		}
		
		System.out.println("Job's done, you won!");
	}
}
