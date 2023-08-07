import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author jihye.byun
 * BOJ 11053 가장 긴 증가하는 부분 수열 실버2 DP
 *
 * 문제
 * 수열 A가 주어졌을 떄, 가장 긴 증가하는 부분 수열을 구하는 프로그램을 작성하시오.
 *
 * 조건
 * 수열 A의 크기 N (1 ≤ N ≤ 1,000)
 * 수열 A를 이루고 있는 Ai가 주어진다. (1 ≤ Ai ≤ 1,000)
 *
 * 풀이
 */

public class Main {
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int N = Integer.parseInt(st.nextToken()); // 수열 A의 크기
    	int[] arr = new int[N];
    	int[] dp = new int[N];
    	
    	// 입력받기
    	st = new StringTokenizer(br.readLine());
    	for (int idx = 0; idx < N; idx++) {
    		arr[idx] = Integer.parseInt(st.nextToken());
    	}
    	
    	// dp
    	for (int idx = 0; idx < N; idx++) {
    		dp[idx] = 1;
    		
    		for (int before = 0; before < idx; before++) {
    			if (arr[before] < arr[idx])
    				dp[idx] = Math.max(dp[idx], dp[before] + 1);
    		}
    	}
    	
    	int max = 1;
    	for (int idx = 0; idx < N; idx++) {
    		max = Math.max(max, dp[idx]);
    	}
    	System.out.println(max);
    }
}
