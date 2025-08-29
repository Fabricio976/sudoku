package org.example.model;

public class Cell {
    private int row;
    private int col;
    private int value;
    private boolean isEditable;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.value = 0; // 0 representa célula vazia
        this.isEditable = true;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isEditable() {
        return isEditable;
    }

    public void setEditable(boolean editable) {
        isEditable = editable;
    }

}