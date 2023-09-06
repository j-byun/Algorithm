import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int T = sc.nextInt();
        
        for (int tc = 1; tc <= T; tc++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int result = 1;
            
            for (int cnt = 1; cnt <= b; cnt++) {
            	result = (result * a) % 10;
            }
            
            System.out.println((result != 0)? result : 10);
        }
    }
}