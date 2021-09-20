package Figure;

public abstract class Figure implements Comparable{
    abstract public double S();
    abstract public double P();
    abstract public String toString();

    public int compareTo(Object o){
        if (this.S()>((Figure)o).S()) return 1;
        else{
            if (this.S()<((Figure)o).S()) return -1;
            else return 0;
        }
    }
}
