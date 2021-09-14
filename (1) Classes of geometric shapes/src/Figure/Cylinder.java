package Figure;

public class Cylinder extends Circle implements Volumable {
    public int x, y, z, r, h;

    private double getH() {
        return Math.abs(h - z);
    }

    public Cylinder() {
        super();
        z = 5;
        h = 15;
    }

    public Cylinder(int x, int y, int z, int r, int h) {
        super(x, y, r);
        this.z = z;
        this.h = h;
    }


    public double Volume() {
        return super.S() * getH();
    }

    public double S() {
        return (Math.PI * r + getH() + super.S()) * 2;
    }

    public double P() {
        return super.P() * 2;
    }

    public String toString() {
        return String.format("this is cylinder x:%d y:%d z:%d r:%d h:%d", super.x, super.y, z, super.r, h);
    }
}
