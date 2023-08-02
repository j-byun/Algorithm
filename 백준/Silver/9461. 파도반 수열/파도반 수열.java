import java.util.Scanner;

/**
 * @author jihye.byun
 * BOJ 9461 파도반 수열 실버3
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
    	int[] input = new int[T];
    	int max = 0;
    	
    	for (int tc = 0; tc < T; tc++) {
    		input[tc] = sc.nextInt();
    		max = Math.max(max, input[tc]);
    	}
    	
    	long[] dp = new long[max + 1];
    	
    	dp[1] = 1;
    	if (max >= 2) dp[2] = 1;
    	if (max >= 3) dp[3] = 1;
    	if (max >= 4) dp[4] = 2;
    	if (max >= 5) dp[5] = 2;
    	
    	for (int idx = 6; idx <= max; idx++) {
    		dp[idx] = dp[idx - 1] + dp[idx - 5];
    	}
    	
    	for (int idx = 0; idx < T; idx++) {
    		System.out.println(dp[input[idx]]);
    	}
    	
    }
}
