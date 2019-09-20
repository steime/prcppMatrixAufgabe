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
            res = multi(m1,m2,res,this.rows,this.columns,matrix.columns);
        } else {
            System.out.println("Not possible");
        }
            result.setMatrix(res);
            return result;
    }

    private double[] multi(double[] m1, double[] m2, double[] res, int m1rows, int m1columns, int m2columns) {
        double sum = 0.0;
        for (int i = 0; i < m1rows; i++) {
            for (int j = 0; j < m2columns; j++) {
                sum = 0.0;
                for (int k = 0; k < m1columns; k++) {
                    sum = sum + m1[i * m1columns + k] * m2[k * m2columns + j];
                }
                res [i * m2columns + j] = sum;
            }
        }
        return res;
    }

    public Matrix power(int k) {
        if (this.rows != this.columns) {
            throw new IllegalArgumentException("Matrix not quadratic");
        }
        int length = this.rows;
        Matrix empty = new Matrix(length,length,0);
        Matrix result = this;
        double[] res = result.matrix;
        double[] emp = empty.matrix;
        for (int i = 1; i < k ; i++) {
           res = multi(res,this.matrix,emp,length,length,length);
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

    public Matrix powerNative(int k) {
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
