import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] basket = new int[N + 1];
        
        for (int cnt = 0; cnt < M; cnt++) {
            int i = sc.nextInt();
            int j = sc.nextInt();
            int k = sc.nextInt();
           
            for (int idx = i; idx <= j; idx++) {
                basket[idx] = k;
            }
        }
        
        for (int idx = 1; idx <= N; idx++) {
            System.out.print(basket[idx] + " ");
        }
        
    }
}