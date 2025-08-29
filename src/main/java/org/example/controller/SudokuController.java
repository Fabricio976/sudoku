package org.example.controller;

import org.example.model.SudokuBoard;
import org.example.services.SudokuGenerator;
import org.example.views.SudokuFrame;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.Color;

public class SudokuController {
    private SudokuGenerator generator = new SudokuGenerator();
    private SudokuBoard model;
    private SudokuFrame view;

    public SudokuController(SudokuBoard model, SudokuFrame view) {
        this.model = model;
        this.view = view;
        addListeners();
        startNewGame();
    }

    private void addListeners() {
        view.getNewGameButton().addActionListener(e -> startNewGame());

        view.getCheckButton().addActionListener(e -> checkSolution());

        for (int row = 0; row < SudokuBoard.BOARD_SIZE; row++) {
            for (int col = 0; col < SudokuBoard.BOARD_SIZE; col++) {
                JTextField cellField = view.getBoardPanel().getCell(row, col);
                final int r = row;
                final int c = col;

                cellField.getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        handleInput();
                    }
                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        handleInput();
                    }
                    @Override
                    public void changedUpdate(DocumentEvent e) {
                    }

                    private void handleInput() {
                        SwingUtilities.invokeLater(() -> {
                            String text = cellField.getText();
                            if (text.length() > 1) {
                                text = text.substring(0, 1);
                                cellField.setText(text);
                            }

                            if (text.matches("[1-9]")) {
                                int value = Integer.parseInt(text);
                                if (model.isValidPlacement(r, c, value)) {
                                    model.setCellValue(r, c, value);
                                    cellField.setBackground(Color.WHITE);
                                }
                            } else if (text.isEmpty()) {
                                model.setCellValue(r, c, 0); // limpa a célula no modelo
                                cellField.setBackground(Color.WHITE);
                            } else {
                                cellField.setBackground(Color.RED); // caractere inválido
                            }
                        });
                    }
                });
            }
        }
    }


    private void startNewGame() {
        int[][] newPuzzle = generator.generateNewPuzzle(45);

        model.newGame(newPuzzle);
        view.getBoardPanel().updateBoard(model);
        view.getStatusLabel().setText("Novo jogo aleatório iniciado!");
    }

    private void checkSolution() {
        if (model.isSolved()) {
            view.getStatusLabel().setText("Parabéns, você completou o Sudoku!");
        } else {
            view.getStatusLabel().setText("O jogo ainda não está completo.");
        }
    }
}