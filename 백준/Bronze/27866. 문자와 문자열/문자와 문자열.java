import java.util.*;

public class Main {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        
        String word = sc.next();
        int index = sc.nextInt();
        
        System.out.println(word.charAt(index - 1));
    }
}