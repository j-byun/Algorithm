import java.util.*;

/**
 * @author jihye.byun
 * BOJ 15989 1, 2, 3 더하기 4 골드5
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
    	StringBuilder sb = new StringBuilder();
    	
    	int T = sc.nextInt();
    	
    	int[][] dp = new int[10001][4];
    	
    	dp[1][1] = 1;
    	dp[2][1] = 1;
    	dp[2][2] = 1;
    	dp[3][1] = 1;
    	dp[3][2] = 1;
    	dp[3][3] = 1;
    	
    	for (int idx = 4; idx <= 10000; idx++) {
    		dp[idx][1] = dp[idx - 1][1];
    		dp[idx][2] = dp[idx - 2][1] + dp[idx - 2][2];
    		dp[idx][3] = dp[idx - 3][1] + dp[idx - 3][2] + dp[idx - 3][3];
    	}
    	
    	
    	while (T-- > 0) {
    		int n = sc.nextInt();
    		
    		sb.append(dp[n][1] + dp[n][2] + dp[n][3]).append("\n");
    	}
    	
    	System.out.println(sb.toString());
    }
}
