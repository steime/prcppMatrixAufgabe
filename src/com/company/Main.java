package com.company;

public class Main {

    public static void main(String[] args) {
        Matrix m1 , m2, m3 , m4;
        int rows = 3, columns = 4;
        m1 = new Matrix(rows,columns);
        m2 = new Matrix(columns,rows);
        m3 = new Matrix(rows,columns,3);
        m4 = new Matrix(rows,columns,3);

        System.out.println(m1.equals(m2));
        System.out.println(m3.equals(m4));
        m1.multiply(m2);
    }
}
