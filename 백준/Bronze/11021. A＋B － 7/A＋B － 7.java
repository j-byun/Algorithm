import java.util.Scanner;

/**
 * @author jihye.byun
 * BOJ 11021 A+B - 7 브론즈 5
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
    		
    		System.out.println("Case #" + tc + ": " + (a + b));
    	}
    	
    }
}
