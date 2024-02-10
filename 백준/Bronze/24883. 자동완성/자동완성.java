import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        char input = sc.next().charAt(0);
        
        if (input == 'N' || input == 'n') {
            System.out.println("Naver D2");
        } else {
            System.out.println("Naver Whale");
        }
    }
}

