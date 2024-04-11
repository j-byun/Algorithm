import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 1890 점프 실버1
 * 시간 제한 1초 | 메모리 제한 128 MB
 *
 * 문제
 *
 * 조건
 *
 * 풀이
 */

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int N = Integer.parseInt(st.nextToken());
    	
    	int[][] map = new int[N][N];
    	long[][] dp = new long[N][N];
    	
    	for (int row = 0; row < N; row++) {
    		st = new StringTokenizer(br.readLine());
    		for (int col = 0; col < N; col++) {
    			map[row][col] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	dp[0][0] = 1;
    	
    	for (int row = 0; row < N; row++) {
    		for (int col = 0; col < N; col++) {
    			if (map[row][col] == 0) continue;
    			
    			if (row + map[row][col] < N) {
    				dp[row + map[row][col]][col] += dp[row][col];
    			}
    			
    			if (col + map[row][col] < N) {
    				dp[row][col + map[row][col]] += dp[row][col];
    			}
    		}
    	}
    	
    	System.out.println(dp[N - 1][N - 1]);
    }
}
