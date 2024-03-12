import java.util.*;

/**
 * @author jihye.byun
 * BOJ 17626 Four Squares 실버3
 * 시간 제한 0.5초 | 메모리 제한 512 MB
 *
 * 문제
 * 모든 자연수는 넷 혹은 그 이하의 제곱수의 합으로 표현할 수 있다.
 * 자연수 n이 주어질 때, n을 최소 개수의 제곱수 합으로 표현하는 컴퓨터 프로그램을 작성하시오.
 *
 * 조건
 * 1 ≤ n ≤ 50,000
 *
 * 풀이
 */

public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	int n = sc.nextInt();
    	int[] dp = new int[n + 1];
    	
    	Arrays.fill(dp, 5);
    	dp[0] = 0;
    	
    	for (int idx = 1; idx <= n; idx++) {
    		for (int num = 1; num * num <= idx; num++) {
    			if (idx - (num * num) >= 0) {
    				dp[idx] = Math.min(dp[idx], dp[idx - (num * num)] + 1);
    			}
    		}
    	}
    	
    	System.out.println(dp[n]);
    }
}
