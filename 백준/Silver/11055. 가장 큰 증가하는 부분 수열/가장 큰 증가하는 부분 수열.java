import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 11055 가장 큰 증가하는 부분 수열 실버2
 * 시간 제한 1초 | 메모리 제한 256 MB
 *
 * 문제
 * 수열 A의 증가하는 부분 수열 중에서 합이 가장 큰 것을 구하자.
 *
 * 조건
 * 수열 A의 크기 N (1 ≤ N ≤ 1,000)
 * 수열 A를 이루고 있는 Ai (1 ≤ Ai ≤ 1,000)
 *
 * 풀이
 * 1. 이분 탐색으로 가장 긴 증가하는 부분 수열을 만들면서 그 때의 합도 저장하자.
 * 2. 최대합이 계속 바뀔 수 있으니 새로운 합을 구할 때 마다 최댓값과 비교해서 갱신하자.
 * => 틀렸습니다. 1 100 2 50 200 40 5 6 7 8 처럼 덮어씌워지기 전 값을 살려야 하는 경우 틀림
 * 3. 그렇다면 모든 경우의 수를 다 저장하고 있어야 하니 길이가 1~N인 부분 수열을 구하는 모든 경우를 확인해보자.
 * => 메모리 16448 KB | 시간 1836 ms
 * => 모든 길이를 구할 필요가 없다! 1차원 dp 배열로 dp[i] : i번째 항으로 끝나는 부분 수열 합의 최댓값 이라는 정의를 통해 해결
 */

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int N = Integer.parseInt(st.nextToken()); // 수열 A의 크기 입력받기
    	
    	int[] arr = new int[N];
    	int[] dp = new int[N];
    	
    	st = new StringTokenizer(br.readLine());
    	for (int idx = 0; idx < N; idx++) {
    		arr[idx] = Integer.parseInt(st.nextToken());
    	}
    	
    	int maxSum = 0;
    	
    	for (int idx = 0; idx < N; idx++) {
    		dp[idx] = arr[idx];
    		
    		for (int before = 0; before < idx; before++) {
    			if (arr[before] < arr[idx]) {
    				dp[idx] = Math.max(dp[idx], dp[before] + arr[idx]);
    			}
    		}
    		
    		maxSum = Math.max(maxSum, dp[idx]);
    	}
    	
    	System.out.println(maxSum);
    	
    	/*
    	int[] arr = new int[N + 1]; // 수열 A 만들기
    	int[][] dp = new int[N + 1][N + 1]; // dp배열 만들기
    	// dp[i][j] : 길이가 i인 부분 수열, 부분 수열의 마지막 숫자가 수열의 j번째 숫자일 때의 부분 수열 합의 최댓값
    	
    	// 수열 A 입력받기
    	st = new StringTokenizer(br.readLine());
    	for (int idx = 1; idx <= N; idx++) {
    		arr[idx] = Integer.parseInt(st.nextToken());
    	}
    	
    	int maxSum = 0; // dp배열에 저장된 부분 수열의 합 중 가장 큰 값을 저장할 변수
    	
    	// 길이가 1일때 ~ N일때 까지
    	// i번 숫자로 LIS가 끝나는 경우의 부분 수열의 합을 dp배열에 저장하자
    	// dp배열에 값을 저장하며 최대합을 업데이트하자
    	for (int size = 1; size <= N; size++) {
    		for (int lastIndex = 1; lastIndex <= N; lastIndex++) {
    			int maxBefore = 0;
    			for (int beforeIndex = 1; beforeIndex < lastIndex; beforeIndex++) {
    				if (arr[beforeIndex] >= arr[lastIndex]) continue;
    				maxBefore = Math.max(maxBefore, dp[size - 1][beforeIndex]);
    			}
    			
    			dp[size][lastIndex] = maxBefore + arr[lastIndex];
    			maxSum = Math.max(maxSum, dp[size][lastIndex]);
    		}
    	}
    	
    	System.out.println(maxSum);
    	*/
    }
}
