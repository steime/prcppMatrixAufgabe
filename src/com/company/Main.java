package com.company;

public class Main {

    public static void main(String[] args) {
        Matrix m1 , m2, m3 , m4, m5 ,m6;
        int rows = 3, columns = 4;
        m1 = new Matrix(rows,columns);
        m2 = new Matrix(columns,rows);
        m3 = new Matrix(rows,columns);
        m4 = new Matrix(columns,rows);


        m5 = m1.multiply(m2);
        m6 = m1.multiplyNative(m2);
        System.out.println(m5.equals(m6));
    }
}
