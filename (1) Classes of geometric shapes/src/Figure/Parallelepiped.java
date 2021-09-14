package Figure;

public class Parallelepiped extends Rectangle implements Volumable{
    public int x, y, z, x2, y2, z2;

    private double getC(){
        return Math.abs(z2-z);
    }

    public Parallelepiped(){
        super();
        z = 5;
        z2 = 10;
    }

    public Parallelepiped(int x, int y, int z, int x2, int y2, int z2){
        super(x, y, x2, y2);
        this.z = z;
        this.z2 = z2;
    }

    public double Volume(){
        return super.S() * getC();
    }

    public double S(){
        return 2 * (getA() * getC() + getB() * getC() + super.S());
    }

    public double P(){
        return 2*super.P() + 4*getC();
    }

    public boolean isCube(){
        if (isSquare()) return getA() == getC();
        return false;
    }

    public String toString() {
        return String.format("this is paral x:%d y:%d z:%d x2:%d y2:%d z2:%d", super.x, super.y, z, super.x2, super.y2, z2);
    }
}
