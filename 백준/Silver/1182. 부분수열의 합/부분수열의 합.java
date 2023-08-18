import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author jihye.byun
 * BOJ 1182 부분수열의 합 실버2  백트래킹
 *
 * 문제
 * N개의 정수로 이루어진 수열이 있을 때,
 * 크기가 양수인 부분수열 중에서 그 수열의 원소를 다 더한 값이 S가 되는 경우의 수를 구하자.
 *
 * 조건
 * 정수의 개수를 나타내는 N (1 ≤ N ≤ 20)
 * 정수 S (|S| ≤ 1,000,000)
 *
 * 풀이
 */

public class Main {
	
	static int N, S, count;
	static int[] arr;
	static boolean[] visit;
	
	private static void dfs(int before, int depth, int maxDepth) {
		
		if (depth == maxDepth) {
			int sum = 0;
			for (int idx = 0; idx < N; idx++) {
				if (visit[idx]) sum += arr[idx];
			}
			if (sum == S) count++; 
			return;
		}
		
		for (int idx = before + 1; idx < N; idx++) {
			if (visit[idx]) continue;
			visit[idx] = true;
			dfs(idx, depth + 1, maxDepth);
			visit[idx] = false;
		}
	}
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	N = Integer.parseInt(st.nextToken()); // 정수의 개수
    	S = Integer.parseInt(st.nextToken()); // 조건으로 주어진 부분수열의 합
    	count = 0;
    	
    	arr = new int[N]; // N개의 정수를 저장할 배열
    	visit = new boolean[N]; // 방문처리 배열
    	
    	// 정수 입력받기
    	st = new StringTokenizer(br.readLine());
    	for (int idx = 0; idx < N; idx++) {
    		arr[idx] = Integer.parseInt(st.nextToken());
    	}
    	
    	// 부분수열 탐색
    	for (int length = 1; length <= N; length++) {
    		dfs(-1, 0, length);
    	}
    	
    	System.out.println(count);
    }
}
