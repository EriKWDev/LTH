import java.util.Scanner;

import se.lth.cs.pt.timer.Timer;

public class GameManager {
	private Board.brickTypes currentPlayerBrick = Board.brickTypes.PLAYER_ONE;
	private Board board = new Board(12, 8);

	public static void main(String[] args) {
		GameManager gameManager = new GameManager();
		Scanner scanner = new Scanner(System.in);

		// Välkomna spelarna
		gameManager.printCoolString("Welcome to an Epic Game of Four-in-a-Row!");

		// Skriv ut brädet en gång innan loopen börjar då scannern hämmar allt exikverande.
		gameManager.board.printBoard();
		gameManager.printPlayerTurn();


		// TODO: Sluta spelet om brädet blir fullt xP
		// TODO: Detektera i loopen om en spelare har vunnit.
		// Spelloopen
		while(true) {
			// Skaffa vilken kolumn spelaren vill spela i.
			int columnInput = scanner.nextInt();

			// Testa att spela på den kolumnen med nuvarande spelaren.
			boolean validMove = gameManager.play(columnInput, gameManager.currentPlayerBrick);

			if(validMove) {
				// Om, och endast om, det var ett korrekt drag, byta till nästa spelare.
				gameManager.switchPlayer();
			}

			// Rita brädet
			gameManager.board.printBoard();
			
			if(!validMove) {
				// Informera spelarna ifall det senaste draget var felaktigt.
				gameManager.printCoolString(columnInput + " is an Invalid Move! Try again.");
			}
			
			// Skriv vems tur det är.
			gameManager.printPlayerTurn();
		}
	}

	private void printCoolString(String message) {
		String decoration = "";
		for(char c : message.toCharArray()) {
			decoration += "=";
		}

		System.out.println();
		System.out.println(decoration);
		System.out.println(message);
		System.out.println(decoration);
		System.out.println();
	}

	public void switchPlayer() {
		currentPlayerBrick = currentPlayerBrick == Board.brickTypes.PLAYER_ONE ? Board.brickTypes.PLAYER_TWO : Board.brickTypes.PLAYER_ONE;
	}

	public String getCurrentPlayerName() {
		String playerName = "";

		switch(currentPlayerBrick) {
			default:
			case PLAYER_ONE:
				playerName = "Player 1 (" + Board.brickTypes.PLAYER_ONE + ")";
				break;
			case PLAYER_TWO:
				playerName = "Player 2 (" + Board.brickTypes.PLAYER_TWO + ")";
			break;
		}

		return playerName;
	}

	public void printPlayerTurn() {
		printCoolString(getCurrentPlayerName() + "'s turn!");
	}

	public boolean play(int col, Board.brickTypes playerBrick) {
        if(col < 0 || col >= board.getWidth()) {
            return false;
        }

        for(int i = board.getHeight()-1; i >= 0; i--) {
            if(board.getBrick(col, i) == Board.brickTypes.NO_PLAYER) {
                board.setBrick(col, i, playerBrick);
                return true;
            }
        }

        return false;
    }
}
