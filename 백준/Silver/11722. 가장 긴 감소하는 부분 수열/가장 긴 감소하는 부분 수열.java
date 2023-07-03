import java.util.Scanner;

/**
 * @author jihye.byun
 * BOJ 11722 가장 긴 감소하는 부분 수열 실버2
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
    	
    	int N = sc.nextInt();
    	
    	int[] num = new int[N];
    	int[] dp = new int[N];
    	int max = 0;
    	
    	for (int idx = 0; idx < N; idx++) {
    		num[idx] = sc.nextInt();
    		dp[idx] = 1;
    		
    		for (int before = 0; before < idx; before++) {
    			if (num[idx] < num[before])
    				dp[idx] = Math.max(dp[idx], dp[before] + 1);
    		}
    		
    		max = Math.max(max, dp[idx]);
    	}
    	
    	System.out.println(max);
    }
}
