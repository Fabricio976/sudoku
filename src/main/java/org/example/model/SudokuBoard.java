package org.example.model;

public class SudokuBoard {
    private Cell[][] board;
    public static final int BOARD_SIZE = 9;

    public SudokuBoard() {
        board = new Cell[BOARD_SIZE][BOARD_SIZE];
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                board[row][col] = new Cell(row, col);
            }
        }
    }

    // Inicia o tabuleiro com um quebra-cabeça
    public void newGame(int[][] puzzle) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                int value = puzzle[row][col];
                board[row][col].setValue(value);
                board[row][col].setEditable(value == 0);
            }
        }
    }

    public Cell getCell(int row, int col) {
        return board[row][col];
    }

    public void setCellValue(int row, int col, int value) {
        if (board[row][col].isEditable()) {
            board[row][col].setValue(value);
        }
    }

    // verifica se o lugar do número é válido
    public boolean isValidPlacement(int row, int col, int value) {
        // linha
        for (int c = 0; c < BOARD_SIZE; c++) {
            if (board[row][c].getValue() == value) {
                return false;
            }
        }
        // coluna
        for (int r = 0; r < BOARD_SIZE; r++) {
            if (board[r][col].getValue() == value) {
                return false;
            }
        }

        // bloco
        int boxStartRow = row - row % 3;
        int boxStartCol = col - col % 3;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (board[boxStartRow + r][boxStartCol + c].getValue() == value) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isSolved() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (board[row][col].getValue() == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}