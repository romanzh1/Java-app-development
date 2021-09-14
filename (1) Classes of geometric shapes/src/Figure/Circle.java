package Figure;

public class Circle extends Figure {
    public int x, y, r;

    public Circle() {
        x = 5;
        y = 10;
        r = 4;
    }

    public Circle(int x, int y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public double S() {
        return Math.PI * r * r;
    }

    public double P() {
        return Math.PI * r * 2;
    }

    public double D() {
        return r * 2;
    }

    public String toString() {
        return String.format("this is circle x:%d y:%d r:%d", x, y, r);
    }
}
