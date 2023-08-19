import java.util.Scanner;

/**
 * @author jihye.byun
 * BOJ 11022 A+B - 8 브론즈5
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
    	
    	int T = sc.nextInt();
    	
    	for (int tc = 1; tc <= T; tc++) {
    		int a = sc.nextInt();
    		int b = sc.nextInt();
    		
    		System.out.println("Case #" + tc + ": " + a + " + " + b + " = " + (a+b));
    	}
    }
}
