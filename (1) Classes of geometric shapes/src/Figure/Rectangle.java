package Figure;

public class Rectangle extends Figure implements Volumable {
    public int x, y, x2, y2;

    public double getA() {
        return Math.abs(x2 - x);
    }

    public double getB() {
        return Math.abs(y2 - y);
    }

    public Rectangle() {
        x2 = 20;
        y2 = 30;
        x = 5;
        y = 10;
    }

    public Rectangle(int x, int y, int x2, int y2) {
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
    }

    public double S() {
        return getA() * getB();
    }

    public double P() {
        return (getA() + getB()) * 2;
    }

    public double Diag() {
        return (Math.sqrt(Math.pow(getA(), 2) + Math.pow(getB(), 2)));
    }

    public boolean isSquare() {
        if (getA() == getB()) return true;
        else return false;
    }

    public String toString() {
        return String.format("this is rect x:%d y:%d x2:%d y2:%d", x, y, x2, y2);
    }

    @Override
    public double Volume() {
        return 0;
    }
}
