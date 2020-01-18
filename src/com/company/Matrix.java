package com.company;

import java.util.Arrays;
import java.util.Random;

public class Matrix {
    int rows, columns;
    double[] matrix;

    static {
        System.loadLibrary("prcppMatrixAufgabe");
    }

    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        Random random = new Random();
        matrix = new double[rows * columns];
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = random.nextDouble();
        }
    }

    public Matrix(int rows, int columns, int value) {
        this.rows = rows;
        this.columns = columns;
        matrix = new double[rows * columns];
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = value;
        }
    }

    public Matrix(int rows, int columns, boolean eye) {
        this.rows = rows;
        this.columns = columns;
        matrix = new double[rows * columns];
        if (eye) {
            int place = 0;
            for (int i = 0; i < rows; i++) {
                matrix[place] = 1;
                place = place + columns + 1;
            }
        } else {
            Matrix m = new Matrix(rows,columns);
        }
    }


    public Matrix multiply(Matrix matrix) {
        Matrix result = new Matrix(this.rows, matrix.columns, 0);
        double[] m1 = this.getMatrix();
        double[] m2 = matrix.getMatrix();
        double[] res = result.getMatrix();


        if (this.columns == matrix.rows) {
            multi(m1,m2,res,this.rows,this.columns,matrix.columns);
        } else {
            System.out.println("Not possible");
        }
        result.setMatrix(res);
        return result;
    }

    private void multi(double[] m1, double[] m2, double[] res, int m1rows, int m1columns, int m2columns) {
        double sum = 0.0;
        for (int i = 0; i < m1rows; i++) {
            int c = i * m1columns, d = i * m2columns;
            for (int j = 0; j < m2columns; j++) {
                sum = 0.0;
                for (int k = 0; k < m1columns; k++) {
                    sum = sum + m1[c + k] * m2[k * m2columns + j];
                }
                res [d + j] = sum;
            }
        }
    }

    public Matrix power(int k) {
        if (this.rows != this.columns) {
            throw new IllegalArgumentException("Matrix not quadratic");
        }
        if (k == 0) return new Matrix(this.rows,this.columns,true);
        if (k == 1) return this;
        int length = this.rows;
        Matrix empty = new Matrix(length,length,0);
        Matrix result = new Matrix(length,length,0);
        double[] emp = empty.matrix;
        double[] temp = this.matrix;
        double[] swap;

        for (int i = 1; i < k ; i++) {
           multi(temp,this.matrix,emp,length,length,length);
           swap = emp;
           emp = temp;
           temp = swap;
        }

        result.setMatrix(temp);
        return result;
    }

    public Matrix multiplyNative(Matrix matrix) {
        Matrix result = new Matrix(this.rows, matrix.columns, 0);
        if (this.columns == matrix.rows) {
            multiplyC(this.matrix, matrix.matrix, result.matrix, this.rows, this.columns, matrix.columns);
        }
        return result;
    }

    public Matrix powerNative(int k) {
        if (this.rows != this.columns) {
            throw new IllegalArgumentException("Matrix not quadratic");
        }
        if (k == 0) return new Matrix(this.rows,this.columns,true);
        if (k == 1) return this;
        Matrix result = new Matrix(this.rows,this.columns,0);
        powerC(this.matrix, result.matrix, k, this.columns);
        return result;
    }

    native void multiplyC(double[] a, double[] b, double[] r, int m, int n, int o);

    native void powerC(double[] a, double[] res, int k, int columns);

    public boolean equals(Matrix matrix) {
        double[] m1 = this.getMatrix();
        double[] m2 = matrix.getMatrix();
        return Arrays.equals(m1,m2);
    }

    public double[] getMatrix() {
        return matrix;
    }

    public void setMatrix(double[] matrix) {
        this.matrix = matrix;
    }
}
