package Figure;

public class Triangle {
    private int x, y, x2, y2, x3, y3;

    public Triangle() {
        x = 15;
        y = 5;
        x2 = 5;
        y2 = 20;
        x3 = 30;
        y3 = 35;
    }

    public double S() {
        return Math.abs((x2 - x) * (y3 - y) - (x3 - x) * (y2 - y)) * 0.5;
    }

    public double P() {
        double side = Math.sqrt(Math.pow((x2 - x), 2) + Math.pow((y2 - y), 2));
        double side2 = Math.sqrt(Math.pow((x3 - x), 2) + Math.pow((y3 - y), 2));
        double side3 = Math.sqrt(Math.pow((x3 - x2), 2) + Math.pow((y3 - y2), 2));
        return side + side2 + side3;
    }

    public boolean isTriangle() {
        if (S() > 0) return true;
        else return false;
    }

    public String toString() {
        return String.format("this is triangle x:%d y:%d x2:%d y2:%d x3:%d y3:%d", x, y, x2, y2);
    }
}
