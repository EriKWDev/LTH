
public class Board {
    public static enum brickTypes {
        NO_PLAYER {
            @Override
            public String toString() { return " "; }
        },
        PLAYER_ONE {
            @Override
            public String toString() { return "#"; }
        },
        PLAYER_TWO {
            @Override
            public String toString() { return "@"; }
        }
    }

    private int width;
    private int height;
    private brickTypes[][] board;

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setBrick(int row, int column, brickTypes brickType) {
        board[row][column] = brickType;
    }

    public brickTypes getBrick(int row, int column) {
        return board[row][column];
    }

    public void printBoard() {
        String boardString = "";
        for(int i = 0; i < getHeight(); i++) {
            if(i == 0) {
                for(int j = 0; j < getWidth(); j++) {
                    boardString += " " + j + (j < 10 ? "  " : " ");
                }
                boardString += "\n";
            }

            for(int j = 0; j < getWidth(); j++) {
                boardString += "[" + board[j][i] + "] ";
            }
            boardString += "\n";
        }

        System.out.print(boardString);
        System.out.println();
    }

    public Board(int width, int height) {
        this.height = height;
        this.width = width;

        this.board = new brickTypes[width][height];
        for(int i = 0; i < getHeight(); i++) {
            for(int j = 0; j < getWidth(); j++) {
                board[j][i] = brickTypes.NO_PLAYER;
            }
        }
    }
}
