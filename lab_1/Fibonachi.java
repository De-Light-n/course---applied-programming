package lab_1;
import java.util.ArrayList;

public class Fibonachi {
    public ArrayList<Long> fibonachi = new ArrayList<>();
    private int M;
    /*
     * The Fibonacci class immediately searches
     * for Fibonacci numbers upon initialization
     */
    public Fibonachi(int M){
        this.M = M;
        fibonachi.add(1l);
        fibonachi.add(1l);
        this.calculateFibonachi(M);
    }

    public long calculateFibonachi(int n){
        if (fibonachi.size() >= n) {
            return fibonachi.get(n - 1);
        }

        for (int i = fibonachi.size(); i < n; i++) {
            long nextFib = fibonachi.get(i - 1) + fibonachi.get(i - 2);
            fibonachi.add(nextFib);
        }
        return fibonachi.get(n - 1);
    }

    /*
     * checks whether a number is triangular
     * @return is number Triangular
     */
    public boolean isTriangular(long number) {
        long n = 1;
        long triangular = n * (n + 1) / 2;
        while (triangular < number) {
            n++;
            triangular = n * (n + 1) / 2;
        }
        return triangular == number;
    }

    /*
     * finding a triangular number
     * @return array of triangular number
     */
    public ArrayList<Long> findTriangularFibonachiNumbers() {
        ArrayList<Long> triangularFibonachis = new ArrayList<>();
        for (int i = 1; i <= M; i++) {
            long fibNumber = calculateFibonachi(i);
            if (isTriangular(fibNumber)) {
                triangularFibonachis.add(fibNumber);
            }
        }
        return triangularFibonachis;
    }
    
}
