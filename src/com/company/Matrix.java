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
        Random random = new Random();
        matrix = new double[rows * columns];
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = value;
        }
    }

    public Matrix multiply(Matrix matrix) {
        Matrix result = new Matrix(this.rows, matrix.columns, 0);
        double[] m1 = this.getMatrix();
        double[] m2 = matrix.getMatrix();
        double[] res = result.getMatrix();

        if (this.columns == matrix.rows) {
            double sum = 0.0;
            for (int i = 0; i < this.rows; i++) {
                for (int j = 0; j < matrix.columns; j++) {
                    sum = 0.0;
                    for (int k = 0; k < matrix.rows; k++) {
                        sum = sum + m1[i * this.columns + k] * m2[k * matrix.columns + j];
                    }
                    res [i * result.columns + j] = sum;
                }
            }

        } else {
            System.out.println("Not possible");
        }
            result.setMatrix(res);
            return result;
    }

    public Matrix multiplyNative(Matrix matrix) {
        Matrix result = new Matrix(this.rows, matrix.columns, 0);
        if (this.columns == matrix.rows) {
            multiplyC(this.matrix, matrix.matrix, result.matrix, this.rows, this.columns, matrix.columns);
        }
        return result;
    }

    native void multiplyC(double[] a, double[] b, double[] r, int m, int n, int o);

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
