package org.example.views;


import javax.swing.*;
import java.awt.*;

public class SudokuFrame extends JFrame {
    private BoardPanel boardPanel;
    private JPanel buttonPanel;
    private JButton newGameButton;
    private JButton checkButton;
    private JLabel statusLabel;

    public SudokuFrame() {
        super("Sudoku Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        boardPanel = new BoardPanel();
        add(boardPanel, BorderLayout.CENTER);

        buttonPanel = new JPanel();
        newGameButton = new JButton("Novo Jogo");
        checkButton = new JButton("Verificar");
        statusLabel = new JLabel("Bem-vindo!");

        buttonPanel.add(newGameButton);
        buttonPanel.add(checkButton);
        add(buttonPanel, BorderLayout.SOUTH);
        add(statusLabel, BorderLayout.NORTH);

        pack();
        setLocationRelativeTo(null);
    }

    public BoardPanel getBoardPanel() {
        return boardPanel;
    }

    public JButton getNewGameButton() {
        return newGameButton;
    }

    public JButton getCheckButton() {
        return checkButton;
    }

    public JLabel getStatusLabel() {
        return statusLabel;
    }
}