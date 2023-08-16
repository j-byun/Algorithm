import java.util.Scanner;

/**
 * @author jihye.byun
 * BOJ 8393 합 브론즈5
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
    	
    	int n = sc.nextInt();
    	Long result = 0L;
    	
    	for (int idx = 1; idx <= n; idx++) {
    		result += idx;
    	}
    	System.out.println(result);
    }
}
