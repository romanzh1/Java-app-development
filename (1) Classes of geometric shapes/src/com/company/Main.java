package com.company;

import Figure.*;

import java.util.*;
//import java.lang.*; импорт всех классов из пакета

public class Main {

    public static void main(String[] args) {
        Rectangle r = new Rectangle(15, 15, 5, 10);
        Circle c = new Circle(5, 5, 4);
        Triangle t;
        Rhombus rh = new Rhombus(15, 15, 5, 10);
        try {
            t = new Triangle(5, 5, 0, 0, 0, 0);
        } catch (Exception e) {
            t = new Triangle();
        }
        Figure[] f = new Figure[9];
        f[0] = new Cylinder();
        f[1] = c;
        f[2] = new Parallelepiped(50, 70, 90, 100, 110, 120);
        f[3] = new Rectangle(5, 3, 2, 6);
        f[4] = new Pyramid();
        f[5] = rh;
        f[6] = t;
        f[7] = new Cylinder();
        f[8] = new Tetrahedron(2, 5, 5, 4, 8, 5, 8, 11, 5, 14);

        for (int i = 0; i < f.length; i++) {
            System.out.println(f[i].getClass().getName() + ":");
            System.out.println(f[i].toString());
            System.out.println("Площадь: " + f[i].S());
            System.out.println("Периметр: " + f[i].P());
            if (f[i] instanceof Rectangle && !(f[i] instanceof Parallelepiped)) {
                System.out.println("Длина диагонали: " + ((Rectangle) f[i]).Diag());
                System.out.println("Прямоугольник квадрат: " + r.isSquare());
            }
            if (f[i] instanceof Rhombus) {
                System.out.println("Длина диагоналей: " + ((Rhombus) f[i]).getD1() + " и " + ((Rhombus) f[i]).getD2());
                System.out.println("Ромб квадрат: " + ((Rhombus) f[i]).isSquare());
            }
            if (f[i] instanceof Parallelepiped)
                System.out.println("Параллелепипед есть куб: " + ((Parallelepiped) f[i]).isCube());
            if (f[i] instanceof Pyramid)
                System.out.println("Пирамида правильная:" + ((Pyramid) f[i]).isCorrectPyramid());
            if (f[i] instanceof Triangle)
                System.out.println("Треугольник существует: " + ((Triangle) f[i]).isTriangle());
            if (f[i] instanceof Circle) System.out.println("Диаметр: " + ((Circle) f[i]).D());
            if (f[i] instanceof Tetrahedron)
                System.out.println("Тэтраэдр правильный: " + ((Tetrahedron) f[i]).isCorrectTetrahedron());
            if (f[i] instanceof Volumable) {
                System.out.println("Объём:" + ((Volumable) f[i]).Volume());
            }
            System.out.println();

        }

        List list = new ArrayList<Figure>(); // односвязный список
        List list2 = new LinkedList(); // двусвязный список

        list.add(new Rectangle());
        list.add(new Circle(10, 15, 10));
        list.add(new Pyramid());
        list.add(new Rhombus());
        list.add(new Rectangle());
        list.add(new Circle());

        list.remove(list.size() - 1);
        for(Object item : list){
            System.out.println(((Figure)item).toString());
        }

        System.out.println("==========");
        list.addAll(Arrays.asList(f));
        list.removeIf(item -> item instanceof Rectangle);

        for(Object item : list){
            System.out.println(((Figure)item).toString());
        }
        System.out.println("-------------");

        Collections.sort(list);
        for(Object item : list){
            System.out.println(((Figure)item).toString() + ((Figure)item).S());
        }
    }
}
