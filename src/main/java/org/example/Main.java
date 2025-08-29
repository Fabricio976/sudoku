package org.example;


import org.example.controller.SudokuController;
import org.example.model.SudokuBoard;
import org.example.views.SudokuFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SudokuBoard model = new SudokuBoard();
            SudokuFrame view = new SudokuFrame();
            new SudokuController(model, view);

            view.setVisible(true);
        });
    }
}