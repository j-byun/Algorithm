import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();
        int M = sc.nextInt();
        
        int[] basket = new int[N + 1];
        
        for (int idx = 1; idx <= N; idx++) {
            basket[idx] = idx;
        }
        
        for (int count = 0; count < M; count++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            
            int tmp = basket[a];
            basket[a] = basket[b];
            basket[b] = tmp;
        }
        
        for (int idx = 1; idx <= N; idx++) {
            System.out.print(basket[idx] + " ");
        }
    }
}