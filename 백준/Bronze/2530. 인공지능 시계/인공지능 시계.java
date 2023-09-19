import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int hh = sc.nextInt();
        int mm = sc.nextInt();
        int ss = sc.nextInt();
        int time = sc.nextInt();
        
        ss += time % 60;
        mm += (time / 60) % 60;
        hh += time / (60 * 60);
        
        if (ss >= 60) {
            mm++;
            ss %= 60;
        }
        if (mm >= 60) {
            hh++;
            mm %= 60;
        }
        if (hh >= 24) {
            hh %= 24;
        }
        System.out.println(hh + " " + mm + " " + ss);
    }
}