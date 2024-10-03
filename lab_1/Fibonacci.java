package lab_1;

public class Fibonacci {
    private int index;
    private long value;
    private boolean isTriangular;

    /**
     * Constructor initializes the Fibonacci index
     */
    public Fibonacci(int index) {
        this.index = index;
    }

    /**
     * Checks whether a number is triangular.
     * 
     * @return is number triangular.
     */
    public void cheakTriangular() {
        long n = 1;
        long triangular = n * (n + 1) / 2;
        while (triangular < this.value) {
            n++;
            triangular = n * (n + 1) / 2;
        }
        this.isTriangular = (triangular == this.value);
    }

    /**
     * Getter for value
     */
    public long getValue() {
        return this.value;
    }

    /**
     * Getter for isTriangular
     */
    public boolean getIsTriangular() {
        return this.isTriangular;
    }

    /**
     * Getter for index
     */
    public int getIndex() {
        return index;
    }

    /**
     * Setter for value
     */
    public void setValue(Long value) {
        this.value = value;
    }

    /**
     * Setter for isTriangular
     */
    public void setIsTriangular(boolean isTriangular) {
        this.isTriangular = isTriangular;
    }
}
