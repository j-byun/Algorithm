import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringTokenizer st;
        
        for (int row = 0; row < 15; row++) {
            st = new StringTokenizer(sc.nextLine());
            for (int col = 0; col < 15; col++) {
                char cur = st.nextToken().charAt(0);
                
                if (cur == 'w') {
                    System.out.println("chunbae");
                    return;
                } else if (cur == 'b') {
                    System.out.println("nabi");
                    return;
                } else if (cur == 'g') {
                    System.out.println("yeongcheol");
                    return;
                }
            }
        }
    }
}