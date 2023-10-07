import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int[] answer = new int[] {1, 1, 2, 2, 2, 8};
        
        for (int idx = 0; idx < 6; idx++) {
            int input = sc.nextInt();
            
            System.out.print(answer[idx] - input + " ");
        }
    }
}