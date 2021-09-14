package Figure;

public class Tetrahedron extends Triangle implements Volumable {
    public int x, y, z, x2, y2, z2, x3, y3, z3, h;

    public double getR1() {
        return Math.sqrt(Math.pow(1/6*(2*getA()*getA()+2*getB()*getB()-getC()*getC()), 2) + Math.pow(getH(), 2));
    }

    public double getR2() {
        return Math.sqrt(Math.pow(1/6*(2*getA()*getA()+2*getB()*getB()-getC()*getC()), 2) + Math.pow(getH(), 2));
    }

    public double getR3() {
        return Math.sqrt(Math.pow(1/6*(2*getA()*getA()+2*getB()*getB()-getC()*getC()), 2) + Math.pow(getH(), 2));
    }

    public double getH() {
        return Math.abs(h - z);
    }

    public Tetrahedron() {
        super();
        z = 5;
        z2 = 5;
        z3 = 5;
        h = 30;
    }

    public Tetrahedron(int x, int y, int z, int x2, int y2, int z2, int x3, int y3, int z3, int h) {
        super(x, y, x2, y2, x3, y3);
        this.z = z;
        this.z2 = z2;
        this.z3 = z3;
        this.h = h;
    }


    public double Volume() {
        return super.S() * getH() * 1/3;
    }

    public double S() {
        double P1 = 0.5 * (getA() + getR2() + getR1());
        double P2 = 0.5 * (getB() + getR2() + getR1());
        double P3 = 0.5 * (getB() + getR2() + getR1());

        double G1 = Math.sqrt(P1 * (P1 - getA()) * (P1 - getR1()) * (P1 - getR2()));
        double G2 = Math.sqrt(P2 * (P2 - getB()) * (P2 - getR1()) * (P2 - getR2()));
        double G3 = Math.sqrt(P3 * (P3 - getC()) * (P3 - getR1()) * (P3 - getR2()));

        return super.S() + G1+G2+G3;
    }

    public double P() {
        return super.P() + getR3()+ getR1() +getR2();
    }

    public boolean isCorrectTetrahedron() {
        if ((getR1() == getR2()) == (getR3() == getR1())) return true;
        return false;
    }

    public String toString() {
        return String.format("this is tetr x:%d y:%d z:%d x2:%d y2:%d z2:%d x3:%d y3:%d z3:%d h:%d",
                super.x, super.y, z, super.x2, super.y2, z2, super.x3, super.y3, z3, h);
    }
}
