package Figure;

public class Rhombus extends Figure{
    public int x, y, x2, y2;

    public double getD1() {
        return Math.abs(x2-x) * 2;
    }

    public double getD2() {
        return Math.abs(y2-y) * 2;
    }

    public double getA() {
        return Math.sqrt(x * x + y2 * y2);
    }

    public Rhombus() {
        x2 = 20;
        y2 = 30;
        x = 5;
        y = 10;
    }

    public Rhombus(int x, int y, int x2, int y2) {
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
    }

    public double S() {
        return 0.5 * getD1() * getD2();
    }

    public double P() {
        return getA() * 4;
    }

    public boolean isSquare() {
        if (getD1() == getD2()) return true;
        else return false;
    }

    public String toString() {
        return String.format("this is rhombus x:%d y:%d x2:%d y2:%d", x, y, x2, y2);
    }
}
