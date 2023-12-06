import java.util.*;

/**
 * @author jihye.byun
 * BOJ 23971 ZOAC 4 브론즈3
 *
 * 문제
 *
 * 조건
 *
 * 풀이
 */

public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	int H = sc.nextInt();
    	int W = sc.nextInt();
    	int N = sc.nextInt();
    	int M = sc.nextInt();
    	
    	System.out.println((((H - 1) / (N + 1)) + 1) * (((W - 1) / (M + 1)) + 1));
    }
}
