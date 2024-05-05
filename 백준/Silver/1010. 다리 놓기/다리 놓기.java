import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 1010 다리 놓기 실버5
 * 시간 제한 0.5초 | 메모리 제한 128 MB
 *
 * 문제
 *
 * 조건
 *
 * 풀이
 * 1. dp[i][j] = i번 다리를 j번 다리에 연결할 수 있는 경우의 수
 * 2. MCN 순열 = M! / (N! * (M-N)!)
 */

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	StringBuilder sb = new StringBuilder();
    	
    	int T = Integer.parseInt(st.nextToken());
//    	long[][] dp;
    	
    	for (int tc = 0; tc < T; tc++) {
    		st = new StringTokenizer(br.readLine());
    		
    		int N = Integer.parseInt(st.nextToken());
    		int M = Integer.parseInt(st.nextToken());
    		
//    		dp = new long[N + 1][M + 1];
//    		
//    		for (int j = 1; j <= M; j++) {
//    			dp[1][j] = 1;
//    		}
//    		
//    		for (int i = 2; i <= N; i++) {
//    			for (int j = i; j <= M; j++) {
//    				dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
//    			}
//    		}
//    		
//    		long answer = 0L;
//    		
//    		for (int j = 1; j <= M; j++) {
//    			answer += dp[N][j];
//    		}
    		
    		int max = Math.max(N, M - N);
    		int min = Math.min(N, M - N);
    		
    		long answer = 1L;
    		
    		for (int num = M; num > max; num--) {
    			answer *= num;
    		}
    		
    		for (int num = 1; num <= min; num++) {
    			answer /= num;
    		}
    		
    		sb.append(answer).append("\n");
    	}
    	
    	System.out.println(sb.toString());
    }
}
