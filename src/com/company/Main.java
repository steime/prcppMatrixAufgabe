package com.company;

public class Main {

    public static void main(String[] args) {
        Matrix m1 , m2, m3 , m4, m5 ,m6;
        int rows1 = 500, columns1 = 6000, rows2 = 6000, columns2 = 400;
        m1 = new Matrix(rows1,columns1,2);
        m2 = new Matrix(rows2,columns2,3);
        m3 = new Matrix(rows1,columns1,2);
        m4 = new Matrix(rows2,columns2,3);

        long start1 = System.currentTimeMillis();
        m5 = m1.multiply(m2);
        long end1 = System.currentTimeMillis();
        long diff1 = end1 - start1;
        System.out.println("Zeit in Java: " + diff1 + " ms");
        long start2 = System.currentTimeMillis();
        m6 = m1.multiplyNative(m2);
        long end2 = System.currentTimeMillis();
        long diff2 = end2 - start2;
        System.out.println("Zeit in C++: " + diff2 + " ms");
        System.out.println("Sind Matrizen gleich? " + m5.equals(m6));
    }

        /*
        Durchlauf mit debug dll
        Zeit in Java: 4390 ms
        Zeit in C++: 7776 ms
        Sind Matrizen gleich? true

        Durchlauf mit release dll
        Zeit in Java: 4579 ms
        Zeit in C++: 4224 ms
        Sind Matrizen gleich? true*/
}
