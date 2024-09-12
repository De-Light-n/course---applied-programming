package lab_1;
import java.util.Scanner;

public class Main {
    /*
     * The main method reads the number of Fibonacci numbers to generate from user input, 
     * calculates the Fibonacci sequence up to that number, and checks if each Fibonacci 
     * number is a triangular number. It prints out all the triangular Fibonacci numbers 
     * found.
     */
    public static void main(String[] args){
        System.out.print("Enter Fibonacci count:");
        Scanner scanner = new Scanner(System.in);
        int M = scanner.nextInt();
        System.out.println("Triangular numbers equal to Fibonacci:");
        long prev = 0;
        long current = 1;
        for (int i = 1; i <= M; i++) {
            Fibonachi fib = new Fibonachi(i); 

            if (i == 1) {
                fib.setValue(current);
            } else {
                long next = prev + current;
                prev = current;
                current = next;
                fib.setValue(current);
            }

            fib.cheakTriangular();
        
            if (fib.getIsTriangular()) {
                System.out.println("Index: "+ fib.getIndex()+" Value:"+fib.getValue()+" isTriangular: "+fib.getIsTriangular());
            }
        }

        scanner.close();
    }
}
