package Figure;

public class Pyramid extends Rhombus implements Volumable {
    public int x, y, z, x2, y2, z2, h;

    public double getR1() {
        return Math.sqrt(Math.pow(0.5 * getD1(), 2) + Math.pow(getH(), 2));
    }

    public double getR2() {
        return Math.sqrt(Math.pow(0.5 * getD2(), 2) + Math.pow(getH(), 2));
    }

    private double getH() {
        return Math.abs(h);
    }

    public Pyramid() {
        super();
        z = 0;
        z2 = 0;
        h = 30;
    }

    public Pyramid(int x, int y, int z, int x2, int y2, int z2) {
        super(x, y, x2, y2);
        this.z = z;
        this.z2 = z2;
    }


    public double Volume() {
        return super.S() * getH();
    }

    public double S() {
        double P = 0.5 * (getR1() + getR2() + getA());
        return super.S() + 4 * Math.sqrt(P * (P - getA()) * (P - getR1()) * (P - getR2()));
    }

    public double P() {
        return super.P() + 2 * (getR1() + getR2());
    }

    public boolean isCorrectPyramid() {
        if (getR1() == getR2()) return true;
        return false;
    }

    public String toString() {
        return String.format("this is pyramid x:%d y:%d z:%d x2:%d y2:%d z2:%d h:%d", super.x, super.y, z, super.x2, super.y2, z2, h);
    }
}
