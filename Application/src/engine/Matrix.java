/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

/**
 *
 * @author Ermengarde
 */
public class Matrix {
    private double matrix[][];
    private int rows;
    private int cols;
    
    public Matrix(double matrix[][]){
        this.matrix = matrix;
        this.rows = this.matrix.length;
        if(this.matrix.length>0){
            this.cols = matrix[0].length;
        }
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }
    
    public boolean isVector() {
        if(getRows() == 1){
            return true;
        }
        else{
            return getCols()==1;
        }
    }
    
    public Double[] toPackedArray() {
        Double result[] = new Double[getRows() * getCols()];
        int index = 0;
        for (int r=0;r<getRows();r++) {
            for (int c=0;c<getCols();c++) {
                result[index++] = this.matrix[r][c];
            }
        }
        return result;
    }
}
