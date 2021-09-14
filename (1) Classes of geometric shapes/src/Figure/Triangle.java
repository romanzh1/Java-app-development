package Figure;

import com.company.TriangleException;

public class Triangle extends Figure implements Volumable {
    public int x, y, x2, y2, x3, y3;

    public double getA() {
        return Math.sqrt(Math.pow((x2 - x), 2) + Math.pow((y2 - y), 2));
    }

    public double getB() {
        return Math.sqrt(Math.pow((x3 - x), 2) + Math.pow((y3 - y), 2));
    }

    public double getC() {
        return Math.sqrt(Math.pow((x3 - x2), 2) + Math.pow((y3 - y2), 2));
    }

    public Triangle() {
        x = 15;
        y = 5;
        x2 = 5;
        y2 = 20;
        x3 = 30;
        y3 = 35;
    }

    public Triangle(int x, int y, int x2, int y2, int x3, int y3) {
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;

        try {
            // можно ли из сторон a, b, c создать треугольник
            if (((getA() + getB()) <= getC()) || ((getA() + getC()) <= getB()) || ((getB() + getC()) <= getA()))
                throw new TriangleException();
        } catch (TriangleException e) {
            System.out.println("Exception: " + e.isNotTriangle());
            return;
        } catch (ArithmeticException e) {
            System.out.println("arith");
            return;
        }
    }

    public double S() {
        return Math.abs((x2 - x) * (y3 - y) - (x3 - x) * (y2 - y)) * 0.5;
    }

    public double P() {
        double side = getA();
        double side2 = getB();
        double side3 = getC();
        return side + side2 + side3;
    }

    public boolean isTriangle() {
        if (S() > 0) return true;
        else return false;
    }

    public String toString() {
        return String.format("this is triangle x:%d y:%d x2:%d y2:%d x3:%d y3:%d", x, y, x2, y2, x3, y3);
    }

    @Override
    public double Volume() {
        return 0;
    }
}
