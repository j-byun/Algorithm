import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        String num = sc.next();
        int B = sc.nextInt();
        int answer = 0;
        int tmp = 1;
        
        for (int idx = num.length() - 1; idx >= 0; idx--) {
            char cur = num.charAt(idx);
            if (cur >= '0' && cur <= '9')
                answer += (cur - '0') * tmp;
            else
                answer += (cur - 'A' + 10) * tmp;
            
            tmp *= B;
        }
        
        System.out.println(answer);
    }
}