import java.util.*;

/**
 * @author jihye.byun
 * BOJ 2302 극장 좌석 실버1
 * 시간 제한 2초 | 메모리 제한 128 MB
 *
 * 문제
 *
 * 조건
 *
 * 풀이
 * 1. 1자리일 떄 : 1
 * 2. 2자리일 때 : 2
 * 3. 3자리일 떄 : 3
 * 4. 4자리일 때 : 5
 * 5. 5자리일 때 : 8
 * 6. dp[i] = dp[i - 1] + dp[i - 1]
 */

public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	int N = sc.nextInt();
    	int[] dp = new int[N + 1];
    	
    	dp[0] = 1;
    	dp[1] = 1;
    	
    	for (int idx = 2; idx <= N; idx++) {
    		dp[idx] = dp[idx - 1] + dp[idx - 2];
    	}
    	
    	int answer = 1;
    	int before = 0;
    	
    	int M = sc.nextInt();
    	for (int cnt = 0; cnt < M; cnt++) {
    		int cur = sc.nextInt();
    		
    		answer *= dp[cur - before - 1];
    		before = cur;
    	}
    	
    	if (before != N) {
    		answer *= dp[N - before];
    	}
    	
    	System.out.println(answer);
    }
}
