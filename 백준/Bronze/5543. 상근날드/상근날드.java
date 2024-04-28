import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int[] burger = new int[3];
        int[] drink = new int[2];
        
        for (int idx = 0; idx < 3; idx++) {
            burger[idx] = sc.nextInt();
        }
        
        for (int idx = 0; idx < 2; idx++) {
            drink[idx] = sc.nextInt();
        }
        
        Arrays.sort(burger);
        Arrays.sort(drink);
        
        System.out.println(burger[0] + drink[0] - 50);
    }
}