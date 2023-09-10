import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();
        int F = sc.nextInt();
        N = (N / 100) * 100;
        
        int answer = 0;
        if (N % F != 0) {
        	answer = F - (N % F);
        }
         
        System.out.println((answer < 10)? "0" + answer : answer);
    }
}