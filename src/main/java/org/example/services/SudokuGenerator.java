package org.example.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SudokuGenerator {

    private int[][] board;
    private static final int BOARD_SIZE = 9;
    private static final int EMPTY_CELL = 0;
    private Random random = new Random();

    public SudokuGenerator() {
        board = new int[BOARD_SIZE][BOARD_SIZE];
    }

    // gera um novo jogo aleatorio.
    public int[][] generateNewPuzzle(int difficulty) {
        // limpa o jogo
        board = new int[BOARD_SIZE][BOARD_SIZE];

        fillBoard(0, 0);
        removeNumbers(difficulty);

        return board;
    }

    private boolean fillBoard(int row, int col) {
        // se chegar ao final do tabuleiro, a solução está completa
        if (row == BOARD_SIZE) {
            return true;
        }

        int nextRow = (col == BOARD_SIZE - 1) ? row + 1 : row;
        int nextCol = (col == BOARD_SIZE - 1) ? 0 : col + 1;

        // gera uma lista de números (1 a 9) e embaralha, garante aleatoriedade
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= BOARD_SIZE; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);

        for (int num : numbers) {
            if (isValidPlacement(row, col, num)) {
                board[row][col] = num;

                if (fillBoard(nextRow, nextCol)) {
                    return true;
                }

                board[row][col] = EMPTY_CELL;
            }
        }
        return false;
    }

    private void removeNumbers(int numbersToRemove) {
        int count = numbersToRemove;
        while (count > 0) {
            int row = random.nextInt(BOARD_SIZE);
            int col = random.nextInt(BOARD_SIZE);
            if (board[row][col] != EMPTY_CELL) {
                board[row][col] = EMPTY_CELL;
                count--;
            }
        }
    }

    // verifica se é válido colocar um número em uma célula.
    private boolean isValidPlacement(int row, int col, int num) {
        // Verifica a linha
        for (int c = 0; c < BOARD_SIZE; c++) {
            if (board[row][c] == num) {
                return false;
            }
        }

        // verifica a coluna
        for (int r = 0; r < BOARD_SIZE; r++) {
            if (board[r][col] == num) {
                return false;
            }
        }

        // verifica o bloco 3x3
        int boxStartRow = row - row % 3;
        int boxStartCol = col - col % 3;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (board[boxStartRow + r][boxStartCol + c] == num) {
                    return false;
                }
            }
        }

        return true; 
    }
}
