import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();
        
        int min = 987654321;
        int max = 0;
        
        for (int idx = 0; idx < N; idx++) {
            int cur = sc.nextInt();
            
            if (cur < min) min = cur;
            if (cur > max) max = cur;
        }
        
        System.out.println((long) min * (long) max);
    }
}