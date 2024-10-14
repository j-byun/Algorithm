import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 2169 로봇 조종하기 골드2
 * 시간 제한 1초 | 메모리 제한 512 MB
 *
 * 문제
 * 위로 이동 불가
 * 한 번 탐사한 지역 재방문 불가
 *
 * 조건
 *
 * 풀이
 * 1. 한 줄 단위로 실행 (위로는 못올라가니까 차례때로 내려가면 됨)
 * 2. 위에서 내려오기 쭉 진행, 왼쪽에서 쭉, 오른쪽에서 쭉 (줄 단위로 한꺼번에 진행)
 */

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	StringBuilder sb = new StringBuilder();
    	
    	int N = Integer.parseInt(st.nextToken());
    	int M = Integer.parseInt(st.nextToken());
    	int[][] map = new int[N][M];
    	int[][] dp = new int[N][M];
    	
    	// map 정보 입력받기
    	for (int row = 0; row < N; row++) {
    		st = new StringTokenizer(br.readLine());
    		for (int col = 0; col < M; col++) {
    			map[row][col] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	// dp맵 초기화
    	for (int row = 0; row < N; row++) {
    		for (int col = 0; col < M; col++) {
    			dp[row][col] = Integer.MIN_VALUE;
    		}
    	}
    	
    	// 최대값 찾기
    	// 0행 초기값 세팅
    	dp[0][0] = map[0][0];
    	for (int col = 1; col < M; col++) {
    		dp[0][col] = dp[0][col - 1] + map[0][col];
    	}
    	
    	int[] left, right;
    	for (int row = 1; row < N; row++) {
    		// 1행 부터는 위, 왼, 오 순서대로 쭉 진행
    		// 1. 위
    		for (int col = 0; col < M; col++) {
    			dp[row][col] = dp[row - 1][col] + map[row][col];
    		}
    		
    		// 2. 왼
    		left = new int[M];
    		left[0] = dp[row][0];
    		for (int col = 1; col < M; col++) {
    			left[col] = Math.max(dp[row][col], left[col - 1] + map[row][col]);
    		}
    		
    		// 2. 오
    		right = new int[M];
    		right[M - 1] = dp[row][M - 1];
    		for (int col = M - 2; col >= 0; col--) {
    			right[col] = Math.max(dp[row][col], right[col + 1] + map[row][col]);
    		}
    		
    		for (int col = 0; col < M; col++) {
    			dp[row][col] = Math.max(dp[row][col], Math.max(left[col], right[col]));
    		}
    	}
    	
    	sb.append(dp[N - 1][M - 1]);
    	System.out.println(sb.toString());
    }
}
