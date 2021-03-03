
public class SudokuSolver implements ISudokuSolver {
	private static int DIMENSION = 9;
	private static int SECTION_DIMENSION = 3;
	private static int MAX_BACKTRACK_DEPTH = 100;
	int[][] boardMatrix;
	
	public SudokuSolver() {
		fillBoardWithZeros();
	}
	
	public SudokuSolver(int[][] matrix) {
		setMatrix(matrix);
	}
	
	private boolean isValidRowAndColumn(int r, int c) {
		return r >= 0 && r < DIMENSION && c >= 0 && c < DIMENSION;
	}
	
	private boolean isValidNumber(int number) {
		return number >= 0 && number <= DIMENSION;
	}
	
	@Override
	public void setNumber(int r, int c, int nbr) {
		if(!isValidRowAndColumn(r, c)) throw new IllegalArgumentException();
		if(!isValidNumber(nbr)) throw new IllegalArgumentException();
		
		boardMatrix[r][c] = nbr;
	}

	@Override
	public int getNumber(int r, int c) {
		if(!isValidRowAndColumn(r, c)) throw new IllegalArgumentException();
		
		return boardMatrix[r][c];
	}

	@Override
	public void clearNumber(int r, int c) {
		if(!isValidRowAndColumn(r, c)) throw new IllegalArgumentException();
		
		setNumber(r, c, 0);
	}

	@Override
	public boolean isValid(int r, int c, int nbr) {
		if(!isValidRowAndColumn(r, c)) throw new IllegalArgumentException();
		if(!isValidNumber(nbr)) throw new IllegalArgumentException();
		
		int[][] temporaryMatrix = boardMatrix;
		temporaryMatrix[r][c] = nbr;
		
		return isAllValid(temporaryMatrix);
	}

	@Override
	public boolean isAllValid() {
		return isAllValid(boardMatrix);
	}
	
	private boolean isAllValid(int[][] matrixToCheck) {
		int[][] oldMatrix = getMatrix();
		setMatrix(matrixToCheck);
		
		boolean matrixIsValid =  sectionsAreValid() && rowsAreValid() && columnsAreValid();
		
		setMatrix(oldMatrix);
		return matrixIsValid;
	}
	
	private boolean rowsAreValid() {
		for(int r = 0; r < DIMENSION; r++) {
			int[] numbers = new int[DIMENSION + 1];
			
			for(int c = 0; c < DIMENSION; c++) {
				int number = getNumber(r, c);
				
				numbers[number]++;
				if(number != 0 && numbers[number] > 1) return false;
			}
		}
		
		return true;
	}
	
	private boolean columnsAreValid() {
		for(int c = 0; c < DIMENSION; c++) {
			int[] numbers = new int[DIMENSION + 1];
			
			for(int r = 0; r < DIMENSION; r++) {
				int number = getNumber(r, c);
				
				numbers[number]++;
				if(number != 0 && numbers[number] > 1) return false;
			}
		}
		
		return true;
	}
	
	private boolean sectionsAreValid() {
		
		for(int startR = 0; startR < DIMENSION; startR += SECTION_DIMENSION) {
			for(int startC = 0; startC < DIMENSION; startC += SECTION_DIMENSION) {
				
				// Check so that sections doesn't have same number twice
				int[] numbers = new int[SECTION_DIMENSION*SECTION_DIMENSION + 1];

				for(int r = startR; r < startR + SECTION_DIMENSION; r++) {
					for(int c = startC; c < startC + SECTION_DIMENSION; c++) {
						int number = getNumber(r, c);
						
						numbers[number]++;
						if(number != 0 && numbers[number] > 1) return false;
					}
				}
			}
		}
		
		return true;
	}

	@Override
	public boolean solve() {
		return solve(0, 0);
	}
	
	private boolean solve(int startR, int startC) {
		return solve(startR, startC, 0);
	}
	
	private boolean solve(int startR, int startC, int depth) {
		if(!isAllValid()) return false;
		if(depth > MAX_BACKTRACK_DEPTH) throw new Error();
		
		if(startR >= DIMENSION) {
			return true;
		} else {
			int[][] oldBoard = getMatrix();
			boolean solved = false;
			
			int nextR = startR;
			int nextC = startC + 1;
			
			if(startC + 1 >= DIMENSION) {
				nextR++;
				nextC = 0;
			}
			
			if(getNumber(startR, startC) > 0) return solve(nextR, nextC, depth);
			
			for(int i = 1; i <= DIMENSION; i++) {
				if(!isValid(startR, startC, i)) continue;
				
				setNumber(startR, startC, i);
				solved = solve(nextR, nextC, depth + 1);
				if(solved) break;
			}
			
			if(!solved) setMatrix(oldBoard);
			
			return solved;
		}
	}
	
	private void fillBoardWithZeros() {
		this.boardMatrix = new int[DIMENSION][DIMENSION];
		for(int r = 0; r < DIMENSION; r++) {
			for(int c = 0; c < DIMENSION; c++) {
				setNumber(r, c, 0);
			}
		}
	}

	@Override
	public void clear() {
		fillBoardWithZeros();
	}

	@Override
	public int[][] getMatrix() {
		int[][] matrix = new int[DIMENSION][DIMENSION];
		
		for(int r = 0; r < DIMENSION; r++) {
			for(int c = 0; c < DIMENSION; c++) {
				matrix[r][c] = boardMatrix[r][c];
			}
		}
		
		return matrix;
	}

	@Override
	public void setMatrix(int[][] nbrs) {
		if(nbrs.length > DIMENSION) throw new IllegalArgumentException();
		
		for(int r = 0; r < DIMENSION; r++) {
			if(nbrs[r].length > DIMENSION) throw new IllegalArgumentException();
			
			for(int c = 0; c < DIMENSION; c++) {
				setNumber(r, c, nbrs[r][c]);
			}
		}
	}

	@Override
	public int getDimension() {
		return DIMENSION;
	}

	@Override
	public void printBoard() {
		System.out.println(toString());
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for(int r = 0; r < DIMENSION; r++) {
			for(int c = 0; c < DIMENSION; c++) {
				sb.append(getNumber(r, c) < 1 ? "_" : getNumber(r, c));
				sb.append(" ");
				if((c + 1) % 3 == 0) sb.append(" ");
			}
			sb.append("\n");
			if((r + 1) % 3 == 0) sb.append("\n");
		}
		
		sb.append("valid: "); sb.append(isAllValid(boardMatrix)); sb.append("\n");
		sb.append("Sections are valid: "); sb.append(sectionsAreValid()); sb.append("\n");
		sb.append("Columns are valid: "); sb.append(columnsAreValid()); sb.append("\n");
		sb.append("Rows are valid: "); sb.append(rowsAreValid()); sb.append("\n");
		return sb.toString();
	}
}
