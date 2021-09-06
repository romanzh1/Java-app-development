package com.company;

import Figure.Circle;
import Figure.Rectangle;
import Figure.Triangle;

//import java.lang.*; импорт всех классов из пакета

public class Main {

    public static void main(String[] args) {
        Rectangle r = new Rectangle();
        Circle c = new Circle();
        Triangle t = new Triangle();

        System.out.println("Прямоугольник");
        System.out.printf(r.toString());
        System.out.println();
        System.out.println("Площадь: " + r.S());
        System.out.println("Периметр: " + r.P());
        System.out.println("Длина диагонали: " + r.Diag());
        System.out.println("Прямоугольник квадрат: " + r.isSquare());
        System.out.println();

        System.out.println("Окружность");
        System.out.printf(r.toString());
        System.out.println();
        System.out.println("Площадь: " + c.S());
        System.out.println("Длина: " + c.L());
        System.out.println("Диаметр: " + c.D());
        System.out.println();

        System.out.println("Треугольник");
        System.out.printf(r.toString());
        System.out.println();
        System.out.println("Площадь: " + t.S());
        System.out.println("Периметр: " + t.P());
        System.out.println("Треугольник существует: " + t.isTriangle());
    }
}
