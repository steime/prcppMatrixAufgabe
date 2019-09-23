package com.company;

public class Main {

    public static void main(String[] args) {
        Matrix m1 , m2, m3 , m4, m5 ,m6 ,m7;
        int rows1 = 500, columns1 = 6000, rows2 = 6000, columns2 = 400;
        m1 = new Matrix(rows1,columns1,2);
        m2 = new Matrix(rows2,columns2,3);
        m3 = new Matrix(250,250,2);

        // Debug matrices
       // m1 = new Matrix(3,3,2);
       // m2 = new Matrix(3,3,3);
//        m3 = new Matrix(3,3,2);

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

        long start3 = System.currentTimeMillis();
        m4 = m3.power(91);
        long end3 = System.currentTimeMillis();
        System.out.println("Zeit in Java Power: " + (end3 - start3));
        long start4 = System.currentTimeMillis();
        m7 = m3.powerNative(91);
        long end4 = System.currentTimeMillis();
        System.out.println("Zeit in C++ Power: " + (end4 - start4));
        System.out.println("Sind Matrizen gleich? " + m4.equals(m7));
    }

        /*
        Durchlauf mit debug dll
        Zeit in Java: 4390 ms
        Zeit in C++: 7776 ms
        Sind Matrizen gleich? true

        Durchlauf mit release dll
        Zeit in Java: 5353 ms
        Zeit in C++: 5228 ms
        Sind Matrizen gleich? true
        Zeit in Java Power: 2201
        Zeit in C++ Power: 1786
        Sind Matrizen gleich? true

         */
}
