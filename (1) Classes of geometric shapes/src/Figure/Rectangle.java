package Figure;

public class Rectangle {
    private int x, y, x2, y2;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public Rectangle() {
        x2 = 20;
        y2 = 30;
        x = 5;
        y = 10;
    }

    public int S() {
        return Math.abs((x2 - x) * (y2 - y));
    }

    public int P() {
        return Math.abs((x2 - x) * 2) + Math.abs((y2 - y) * 2);
    }

    public double Diag() {
        return (Math.sqrt(Math.pow((x2 - x), 2) + Math.pow((y2 - y), 2)));
    }

    public boolean isSquare() {
        if (Math.abs(x2 - x) == Math.abs(y2 - y)) return true;
        else return false;
    }

    public String toString() {
        return String.format("this is rect x:%d y:%d x2:%d y2:%d", x, y, x2, y2);
    }
}
