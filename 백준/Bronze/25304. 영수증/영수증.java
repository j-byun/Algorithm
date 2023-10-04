import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int sum = sc.nextInt();
        
        int N = sc.nextInt();
        
        for (int idx = 0; idx < N; idx++) {
            int amount = sc.nextInt();
            int count = sc.nextInt();
            
            sum -= (amount * count);
        }
        
        System.out.println((sum == 0) ? "Yes" : "No");
    }
}