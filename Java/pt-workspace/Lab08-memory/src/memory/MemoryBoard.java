package memory;

import java.util.Random;

public class MemoryBoard {
	MemoryCardImage[][] memoryCardImageBoard;
	int size;

	/** Skapar ett memorybräde med size * size kort. backFileName är filnamnet 
	    för filen med baksidesbilden. Vektorn frontFileNames innehåller filnamnen 
	    för frontbilderna. */
	public MemoryBoard(int size, String backFileName, String[] frontFileNames) {
		this.memoryCardImageBoard = new MemoryCardImage[size][size];
		this.size = size;
		
		createCards(backFileName, frontFileNames);
	}

	/* Skapar size * size / 2 st memorykortbilder.
	   Placerar ut varje kort på två slumpmässiga ställen på spelplanen. */
	private void createCards(String backFileName, String[] frontFileNames) {
		Random random = new Random();
		int n = 0;
 		int numberOfCards = size * (int) size/2;
		
		for(String frontFileName : frontFileNames) {
			if(n >= numberOfCards) {
				break;
			}
			
			for(int i = 0; i < 2; i++) {
				boolean hasFoundEmptySpot = false;
				while(!hasFoundEmptySpot) {
					int r = random.nextInt(size);
					int c = random.nextInt(size);
					
					if(memoryCardImageBoard[r][c] == null) {
						memoryCardImageBoard[r][c] = new MemoryCardImage(frontFileName, backFileName);
						hasFoundEmptySpot = true;
					}
				}
			}
			
			n++;
		}
		
		for(MemoryCardImage[] t : memoryCardImageBoard) {
			for(MemoryCardImage card : t) {
				System.out.println(card);
			}
		}
	}

	/** Tar reda på brädets storlek. */
	public int getSize() {
		return size;
	}
	
	/** Hämtar den tvåsidiga bilden av kortet på rad r, kolonn c.
	    Raderna och kolonnerna numreras från 0 och uppåt. */
	public MemoryCardImage getCard(int r, int c) {
		return memoryCardImageBoard[r][c];
	}

	/** Vänder kortet på rad r, kolonn c. */
	public void turnCard(int r, int c) {
		memoryCardImageBoard[r][c].flip();
	}
	
	/** Returnerar true om kortet r, c har framsidan upp. */
	public boolean frontUp(int r, int c) {
		if(memoryCardImageBoard[r][c] == null) {
			return false;
		}
		
		return !memoryCardImageBoard[r][c].isBackUp();
	}
	
	/** Returnerar true om det är samma kort på rad r1, kolonn c2 som på rad r2, 
	    kolonn c2. */
	public boolean same(int r1, int c1, int r2, int c2) {
		return getCard(r1, c1).isSameAs(getCard(r2, c2));
	}

	/** Returnerar true om alla kort har framsidan upp. */
	public boolean hasWon() {
		boolean allCardsAreFlipped = true;
		for(MemoryCardImage[] r : memoryCardImageBoard) {
			if(!allCardsAreFlipped) {
				break;
			}
			
			for(MemoryCardImage card : r) {
				if(card.isBackUp()) {
					allCardsAreFlipped = false;
					break;
				}
			}
		}
		
		return allCardsAreFlipped;
	}	
}
