import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author jihye.byun
 * BOJ 1149 RGB거리 실버1 DP
 *
 * 문제
 *
 * 조건
 * 집의 수 N(2 ≤ N ≤ 1,000)
 * 집을 칠하는 비용은 1,000보다 작거나 같은 자연수
 *
 * 풀이
 */

public class Main {
	
	static final int RED = 0;
	static final int GREEN = 1;
	static final int BLUE = 2;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int N = Integer.parseInt(st.nextToken());
    	
    	int[][] cost = new int[N][3];
    	int[][] dp = new int[N][3];
    	
    	for (int idx = 0; idx < N; idx++) {
    		st = new StringTokenizer(br.readLine());
    		for (int color = 0; color < 3; color++) {
    			cost[idx][color] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	dp[0][RED] = cost[0][RED];
    	dp[0][GREEN] = cost[0][GREEN];
    	dp[0][BLUE] = cost[0][BLUE];
    	
    	for (int idx = 1; idx < N; idx++) {
    		for (int color = 0; color < 3; color++) {
    			if (color == RED)
    				dp[idx][RED] = Math.min(dp[idx - 1][GREEN], dp[idx - 1][BLUE]) + cost[idx][RED];
    			if (color == GREEN)
    				dp[idx][GREEN] = Math.min(dp[idx - 1][RED], dp[idx - 1][BLUE]) + cost[idx][GREEN];
    			if (color == BLUE)
    				dp[idx][BLUE] = Math.min(dp[idx - 1][RED], dp[idx - 1][GREEN]) + cost[idx][BLUE];
    		}
    	}
    	
    	System.out.println(Math.min(dp[N - 1][RED], Math.min(dp[N - 1][GREEN], dp[N - 1][BLUE])));
    }
}
