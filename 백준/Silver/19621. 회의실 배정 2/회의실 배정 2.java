import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 19621 회의실 배정 2 실버2
 * 시간 제한 1초 | 메모리 제한 256 MB
 *
 * 문제
 * N개의 회의와 하나의 회의실
 *
 * 조건
 * 1 ≤ N ≤ 25
 * 모든 회의의 시작 시간과 끝나는 시간은 231 − 1보다 작거나 같은 자연수 또는 0이다.
 * 회의 인원은 1,000보다 작거나 같은 자연수 이다.
 *
 * 풀이
 * 1. dp 회의실배정
 * 2. 회의 종료시간 기준 오름차순 정렬 후 dp
 * 3. 배열의 최대 범위가 Integer.MAX_VALUE + 1이기 때문에 배열을 회의 개수로 만들자
 */

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int N = Integer.parseInt(st.nextToken()); // 회의 개수
    	
    	int[][] meetings = new int[N][3]; // 회의 정보를 저장할 배열
    	int[] dp = new int[N]; // i번째 회의까지 진행 시 최대 인원 수를 저장할 dp 배열
    	
    	// 회의 정보 저장
    	for (int idx = 0; idx < N; idx++) {
    		st = new StringTokenizer(br.readLine());
    		meetings[idx][0] = Integer.parseInt(st.nextToken());
    		meetings[idx][1] = Integer.parseInt(st.nextToken());
    		meetings[idx][2] = Integer.parseInt(st.nextToken());
    	}
    	
    	// N개의 회의를 차례대로 진행해보자
    	dp[0] = meetings[0][2];
    	
    	if (N >= 2) {
    		dp[1] = Math.max(meetings[0][2], meetings[1][2]);
    	}
    	
    	for (int idx = 2; idx < N; idx++) {
    		dp[idx] = Math.max(dp[idx - 1], dp[idx - 2] + meetings[idx][2]);
    	}
    	
    	System.out.println(dp[N - 1]);
    }
}
