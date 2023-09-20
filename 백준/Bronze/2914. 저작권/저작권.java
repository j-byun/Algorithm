import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int A = sc.nextInt();
        int I = sc.nextInt();
        
        System.out.println((A == 1) ? (A * I) : (A * (I - 1) + 1));
    }
}