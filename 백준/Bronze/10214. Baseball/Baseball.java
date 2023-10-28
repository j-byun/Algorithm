import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int T = sc.nextInt();
        
        while (T-- > 0) {
            int Y = 0;
            int K = 0;
            
            for (int cnt = 1; cnt <= 9; cnt++) {
                Y += sc.nextInt();
                K += sc.nextInt();
            }
            
            if (Y > K) System.out.println("Yonsei");
            else if (Y < K) System.out.println("Korea");
            else System.out.println("Draw");
        }
    }
}