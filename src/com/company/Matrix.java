package com.company;

import java.util.Random;

public class Matrix {
    int rows, columns;
    double[] matrix;

    public Matrix (int rows, int columns) {
        Random random = new Random();
        matrix = new double[rows*columns];
        for (int i = 0;  i < matrix.length ; i++) {
            matrix[i] = random.nextDouble();
        }
    }

    public Matrix (int rows, int columns, int value) {
        Random random = new Random();
        matrix = new double[rows*columns];
        for (int i = 0;  i < matrix.length ; i++) {
            matrix[i] = value;
        }
    }

    public Matrix multiply(Matrix matrix) {
        Matrix result = new Matrix(this.rows,matrix.columns,0);
        if (this.columns == matrix.rows) {
            int length = this.rows*matrix.columns;
            for (int i = 0; i < length; i++ ){

            }

        } else {
            System.out.println("Not possible");
        }
        return result;
    }
}
