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
public class MatrixMath {
    private MatrixMath(){
        
    }
    
    public static Matrix add(Matrix a, Matrix b){
        if(a.getRows()==b.getRows() && a.getCols()==b.getCols()){
            double result[][] = new double[a.getRows()][a.getCols()];
            for(int i=0;i<result.length;i++){
                for(int j=0;j<result[i].length;j++){
                    result[i][j] = a.getMatrix()[i][j] + b.getMatrix()[i][j];
                }
            }
            return new Matrix(result);
        }
        else return null;
    }
    
    public static Matrix multiply(Matrix a, double b) {
        double result[][] = new double[a.getRows()][a.getCols()];
        for(int row=0;row<a.getRows();row++){
                for(int col=0;col<a.getCols();col++){
                        result[row][col] = a.getMatrix()[row][col] * b;
                }
        }
        return new Matrix(result);
    }
    
    public static Matrix multiply(Matrix a, Matrix b){
        if(a.getCols()==b.getRows()){
            double result[][] = new double[a.getRows()][b.getCols()];
            for(int i=0;i<a.getRows();i++){
                for(int j=0;j<b.getCols();j++){
                    double val = 0;
                    for(int k=0;k<a.getCols();k++){
                        val += a.getMatrix()[i][k] * b.getMatrix()[k][j];
                    }
                    result[i][j] = val;
                }
            }
            return new Matrix(result);
        }
        else{
            return null;
        }
    }
    
    public static double dotProduct(Matrix a, Matrix b){
        if(a.isVector() && b.isVector()){
            Double aArray[] = a.toPackedArray();
            Double bArray[] = b.toPackedArray();
            if(aArray.length==bArray.length){
                double result = 0;
                for(int i=0;i<aArray.length;i++) {
			result += aArray[i] * bArray[i];
		}
                return result;
            }
            else{
                return Double.NaN;
            }
        }
        else return Double.NaN;
    }
    
    public static double vectorLength(Matrix input) {
        if(input.isVector()){
            Double v[] = input.toPackedArray();
            double rtn = 0.0;
            for(int i=0;i<v.length;i++) {
                rtn += Math.pow(v[i], 2);
            }
            return Math.sqrt(rtn);
        }
        else{
            return Double.NaN;
        }
    }
}
