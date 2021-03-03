import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SudokuTester {

	SudokuSolver sudokuSolver;
	
	@BeforeEach
	void setUp() {
		sudokuSolver = new SudokuSolver();
	}
	
	@AfterEach
	void tearDown() {
		sudokuSolver = null;
	}
	
	@Test
	void testSolveEmpty() {
		sudokuSolver.printBoard();
		assertTrue(sudokuSolver.solve());
		sudokuSolver.printBoard();
	}
	
	@Test
	void testUnsolvableStartingPositions() {
		// Two fives in first row directly after each other
		sudokuSolver.setNumber(0, 0, 5);
		sudokuSolver.setNumber(1, 0, 5);
		assertFalse(sudokuSolver.isAllValid());
		assertFalse(sudokuSolver.solve());
		sudokuSolver.clear();
		
		// Two fives in first column, different regions
		sudokuSolver.setNumber(0, 0, 5);
		sudokuSolver.setNumber(0, 5, 5);
		assertFalse(sudokuSolver.isAllValid());
		assertFalse(sudokuSolver.solve());
		sudokuSolver.clear();
		
		// Two fives in first column, different regions
		sudokuSolver.setNumber(0, 0, 5);
		sudokuSolver.setNumber(0, 5, 5);
		assertFalse(sudokuSolver.isAllValid());
		assertFalse(sudokuSolver.solve());
		sudokuSolver.clear();
	}
	
	@Test
	void testSolveAfterClear() {
		// Two fives in first row directly after each other
		sudokuSolver.setNumber(0, 0, 5);
		sudokuSolver.setNumber(1, 0, 5);
		assertFalse(sudokuSolver.isAllValid());
		assertFalse(sudokuSolver.solve());
		sudokuSolver.clear();
		assertTrue(sudokuSolver.isAllValid());
		assertTrue(sudokuSolver.solve());
	}
	
	@Test
	void testInvalidMatrixDimensions() {
		int[][] matrix1 = {
			{0, 0, 8,  0, 0, 9,  0, 6, 2},
			{0, 0, 0,  0, 0, 0,  0, 0, 5},
			{1, 0, 2,  5, 0, 0,  0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			
			{0, 0, 0,  2, 1, 0,  0, 9, 0},
			{0, 5, 0,  0, 0, 0,  6, 0, 0},
			{6, 0, 0,  0, 0, 0,  0, 2, 8},
			
			{4, 1, 0,  6, 0, 8,  0, 0, 0},
			{8, 6, 0,  0, 3, 0,  1, 0, 0},
			{0, 0, 0,  0, 0, 0,  4, 0, 0},
		};
		assertThrows(IllegalArgumentException.class, () -> sudokuSolver.setMatrix(matrix1));
		
		int[][] matrix2 = {
			{0, 0, 8,  0, 0, 9,  0, 6, 2},
			{0, 0, 0,  0, 0, 0,  0, 0, 5},
			{1, 0, 2,  5, 0, 0,  0, 0, 0},
			
			{0, 0, 0,  2, 1, 0,  0, 9, 0},
			{0, 5, 0,  0, 0, 0,  6, 0, 0},
			{6, 0, 0,  0, 0, 0,  0, 2, 8},
			
			{4, 1, 0,  6, 0, 8,  0, 0, 0},
			{8, 6, 0,  0, 3, 0,  1, 0, 0},
			{0, 0, 0,  0, 0, 0,  4, 0, 0},
			
			{4, 1, 0,  6, 0, 8,  0, 0, 0},
			{8, 6, 0,  0, 3, 0,  1, 0, 0},
			{0, 0, 0,  0, 0, 0,  4, 0, 0},
		};
		assertThrows(IllegalArgumentException.class, () -> sudokuSolver.setMatrix(matrix2));
	}
	
	@Test
	void testSetSudokuFromInstructions() {
		int[][] matrix = {
			{0, 0, 8,  0, 0, 9,  0, 6, 2},
			{0, 0, 0,  0, 0, 0,  0, 0, 5},
			{1, 0, 2,  5, 0, 0,  0, 0, 0},
			
			{0, 0, 0,  2, 1, 0,  0, 9, 0},
			{0, 5, 0,  0, 0, 0,  6, 0, 0},
			{6, 0, 0,  0, 0, 0,  0, 2, 8},
			
			{4, 1, 0,  6, 0, 8,  0, 0, 0},
			{8, 6, 0,  0, 3, 0,  1, 0, 0},
			{0, 0, 0,  0, 0, 0,  4, 0, 0},
		};
		
		int[][] solutionMatrix = {
			{5, 4, 8,  1, 7, 9,  3, 6, 2}, 
			{3, 7, 6,  8, 2, 4,  9, 1, 5},  
			{1, 9, 2,  5, 6, 3,  8, 7, 4},  

			{7, 8, 4,  2, 1, 6,  5, 9, 3},  
			{2, 5, 9,  3, 8, 7,  6, 4, 1},  
			{6, 3, 1,  9, 4, 5,  7, 2, 8},  

			{4, 1, 5,  6, 9, 8, 2, 3, 7},  
			{8, 6, 7,  4, 3, 2,  1, 5, 9},  
			{9, 2, 3,  7, 5, 1,  4, 8, 6},
		};
		
		sudokuSolver.setMatrix(matrix);
		assertTrue(sudokuSolver.solve());
		
		int[][] sudokuSolverSolution = sudokuSolver.getMatrix();
		
		for(int r = 0; r < 9; r++) {
			for(int c = 0; c < 9; c++) {
				assertEquals(sudokuSolverSolution[r][c], solutionMatrix[r][c]);
			}
		}
	}
	
	@Test
	void testAllIsValid() {
		sudokuSolver.setNumber(0, 0, 1);
		sudokuSolver.setNumber(1, 0, 1);
		
		assertFalse(sudokuSolver.isAllValid());
		sudokuSolver.clear();
		
		sudokuSolver.setNumber(0, 0, 1);
		sudokuSolver.setNumber(0, 1, 1);
		assertFalse(sudokuSolver.isAllValid());
		sudokuSolver.clear();
		
		int[][] matrix1 = {
			{0, 0, 8,  0, 0, 9,  0, 6, 2},
			{0, 0, 0,  0, 0, 0,  0, 0, 5},
			{1, 0, 2,  5, 0, 0,  0, 0, 0},
			
			{0, 0, 0,  2, 1, 0,  0, 9, 0},
			{0, 5, 0,  0, 0, 0,  6, 0, 0},
			{6, 0, 0,  0, 0, 0,  0, 2, 8},
			
			{4, 1, 0,  6, 0, 8,  0, 0, 0},
			{8, 6, 0,  0, 3, 0,  1, 0, 0},
			{0, 0, 0,  0, 0, 0,  4, 0, 0},
		};
		sudokuSolver.setMatrix(matrix1);
		assertTrue(sudokuSolver.isAllValid());
		sudokuSolver.clear();
		
		int[][] matrix2 = {
			{1, 2, 3,  4, 5, 6,  7, 8, 9},
			{2, 0, 0,  0, 0, 0,  0, 0, 0},
			{3, 0, 0,  0, 0, 0,  0, 0, 0},
			
			{4, 0, 0,  0, 0, 0,  0, 0, 0},
			{5, 0, 0,  0, 0, 0,  0, 0, 0},
			{6, 0, 0,  0, 0, 0,  0, 0, 0},
			
			{7, 0, 0,  0, 0, 0,  0, 0, 0},
			{8, 0, 0,  0, 0, 0,  0, 0, 0},
			{9, 0, 0,  0, 0, 0,  0, 0, 0},
		};
		sudokuSolver.setMatrix(matrix2);
		assertFalse(sudokuSolver.isAllValid());
		sudokuSolver.clear();
		
		int[][] matrix3 = {
			{1, 2, 3,  0, 0, 0,  0, 0, 0},
			{4, 5, 6,  0, 0, 0,  0, 0, 0},
			{7, 8, 9,  0, 0, 0,  0, 0, 0},
			
			{0, 0, 0,  0, 0, 0,  0, 0, 0},
			{0, 0, 0,  0, 0, 0,  0, 0, 0},
			{0, 0, 0,  0, 0, 0,  0, 0, 0},
			
			{0, 0, 0,  0, 0, 0,  0, 0, 0},
			{0, 0, 0,  0, 0, 0,  0, 0, 0},
			{0, 0, 0,  0, 0, 0,  0, 0, 0},
		};
		sudokuSolver.setMatrix(matrix3);
		assertTrue(sudokuSolver.isAllValid());
		sudokuSolver.clear();
		
		int[][] matrix4 = {
			{1, 1, 3,  0, 0, 0,  0, 0, 0},
			{4, 5, 6,  0, 0, 0,  0, 0, 0},
			{7, 8, 9,  0, 0, 0,  0, 0, 0},
			
			{0, 0, 0,  0, 0, 0,  0, 0, 0},
			{0, 0, 0,  0, 0, 0,  0, 0, 0},
			{0, 0, 0,  0, 0, 0,  0, 0, 0},
			
			{0, 0, 0,  0, 0, 0,  0, 0, 0},
			{0, 0, 0,  0, 0, 0,  0, 0, 0},
			{0, 0, 0,  0, 0, 0,  0, 0, 0},
		};
		sudokuSolver.setMatrix(matrix4);
		assertFalse(sudokuSolver.isAllValid());
		sudokuSolver.clear();
	}
	
	@Test
	void testClear() {
		sudokuSolver.setNumber(0, 0, 2);
		sudokuSolver.clear();
		int[][] matrix = sudokuSolver.getMatrix();
		
		for(int r = 0; r < 9; r++) {
			for(int c = 0; c < 9; c++) {
				assertEquals(0, matrix[r][c]);
			}
		}
	}
	
	@Test
	void testInvalidSetNumbers() {
		assertThrows(IllegalArgumentException.class, () -> sudokuSolver.setNumber(-1, 0, 2));
		assertThrows(IllegalArgumentException.class, () -> sudokuSolver.setNumber(0, 20, 2));
		assertThrows(IllegalArgumentException.class, () -> sudokuSolver.setNumber(0, 0, -1));
		assertThrows(IllegalArgumentException.class, () -> sudokuSolver.setNumber(3, 3, -1));
	}
	
	@Test
	void testInvalidGetNumbers() {
		assertThrows(IllegalArgumentException.class, () -> sudokuSolver.getNumber(-1, 0));
		assertThrows(IllegalArgumentException.class, () -> sudokuSolver.getNumber(0, 20));
		assertThrows(IllegalArgumentException.class, () -> sudokuSolver.getNumber(100, 100));
		assertThrows(IllegalArgumentException.class, () -> sudokuSolver.getNumber(-500, 500));
	}
	
	@Test
	void testInvalidClearNumber() {
		assertThrows(IllegalArgumentException.class, () -> sudokuSolver.clearNumber(-1, 0));
		assertThrows(IllegalArgumentException.class, () -> sudokuSolver.clearNumber(0, 20));
		assertThrows(IllegalArgumentException.class, () -> sudokuSolver.clearNumber(100, 100));
		assertThrows(IllegalArgumentException.class, () -> sudokuSolver.clearNumber(-500, 500));
	}
	
	@Test
	void testInvalidIsValid() {
		assertThrows(IllegalArgumentException.class, () -> sudokuSolver.isValid(-1, 0, 100));
		assertThrows(IllegalArgumentException.class, () -> sudokuSolver.isValid(0, 20, 3));
		assertThrows(IllegalArgumentException.class, () -> sudokuSolver.isValid(100, 100, 2));
		assertThrows(IllegalArgumentException.class, () -> sudokuSolver.isValid(-500, 500, 1));
		assertThrows(IllegalArgumentException.class, () -> sudokuSolver.isValid(2, 2, -1));
	}
}
