package org.example.views;

import org.example.model.SudokuBoard;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class BoardPanel extends JPanel {
    private JTextField[][] cells;
    private static final Font FONT_NUMBERS = new Font("Monospaced", Font.BOLD, 24);
    private static final Font FONT_GIVEN = new Font("Monospaced", Font.BOLD, 26);

    public BoardPanel() {
        setLayout(new GridLayout(SudokuBoard.BOARD_SIZE, SudokuBoard.BOARD_SIZE));
        cells = new JTextField[SudokuBoard.BOARD_SIZE][SudokuBoard.BOARD_SIZE];

        Border thickBorder = BorderFactory.createLineBorder(Color.BLACK, 2);
        Border thinBorder = BorderFactory.createLineBorder(Color.GRAY, 1);

        for (int row = 0; row < SudokuBoard.BOARD_SIZE; row++) {
            for (int col = 0; col < SudokuBoard.BOARD_SIZE; col++) {
                cells[row][col] = new JTextField();
                cells[row][col].setHorizontalAlignment(JTextField.CENTER);
                cells[row][col].setFont(FONT_NUMBERS);

                int top = (row % 3 == 0) ? 2 : 1;
                int left = (col % 3 == 0) ? 2 : 1;
                int bottom = ((row + 1) % 3 == 0) ? 2 : 1;
                int right = ((col + 1) % 3 == 0) ? 2 : 1;

                cells[row][col].setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, Color.BLACK));
                add(cells[row][col]);
            }
        }
        setPreferredSize(new Dimension(540, 540));
    }

    // atualiza a view com base nos dados do model
    public void updateBoard(SudokuBoard board) {
        for (int row = 0; row < SudokuBoard.BOARD_SIZE; row++) {
            for (int col = 0; col < SudokuBoard.BOARD_SIZE; col++) {
                int value = board.getCell(row, col).getValue();
                JTextField cellField = cells[row][col];

                if (value == 0) {
                    cellField.setText("");
                } else {
                    cellField.setText(String.valueOf(value));
                }

                if (board.getCell(row, col).isEditable()) {
                    cellField.setEditable(true);
                    cellField.setForeground(Color.BLUE);
                    cellField.setFont(FONT_NUMBERS);
                } else {
                    cellField.setEditable(false);
                    cellField.setForeground(Color.BLACK);
                    cellField.setFont(FONT_GIVEN);
                }
            }
        }
    }

    public JTextField getCell(int row, int col) {
        return cells[row][col];
    }
}