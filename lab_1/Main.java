package lab_1;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
/*
 * Requests a number from the user. 
 * Finds a given number of Fibonacci lines and then checks which ones are triangular. 
 * Displays triangles on the screen.
 */
    public static void main(String[] args){
        System.out.print("Enter Fibonacci count:");
        Scanner scanner = new Scanner(System.in);
        int M = scanner.nextInt();
        Fibonachi fib = new Fibonachi(M);
        ArrayList<Long> result = fib.findTriangularFibonachiNumbers();
        System.out.println("Triangular numbers equal to Fibonacci:");
        for(int i = 0; i < result.size(); i++){
            System.out.print(result.get(i)+" ");
        }
        scanner.close();
    }
}
