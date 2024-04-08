import java.util.*;

/**
 * @author jihye.byun
 * BOJ 15988 1, 2, 3 더하기 3 실버2
 * 시간 제한 1초 | 메모리 제한 512 MB
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
    	int[] input = new int[T];
    	int maxSize = 0;
    	
    	for (int idx = 0; idx < T; idx++) {
    		input[idx] = sc.nextInt();
    		maxSize = Math.max(maxSize, input[idx]);
    	}
    	
    	int[] dp = new int[maxSize + 1];
    	
    	dp[0] = 1;
    	
    	for (int idx = 1; idx <= maxSize; idx++) {
    		// 1 더하는 방법
    		dp[idx] = (dp[idx] + dp[idx - 1]) % 1_000_000_009;
    		
    		// 2 더하는 방법
    		if (idx < 2) continue;
    		dp[idx] = (dp[idx] + dp[idx - 2]) % 1_000_000_009;
    		
    		// 3 더하는 방법
    		if (idx < 3) continue;
    		dp[idx] = (dp[idx] + dp[idx - 3]) % 1_000_000_009;
    	}
    	
    	for (int i : input) {
    		sb.append(dp[i]).append("\n");
    	}
    	
    	System.out.println(sb.toString());
    }
}
