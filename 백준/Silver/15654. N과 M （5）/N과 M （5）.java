import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author jihye.byun
 * BOJ 15654 N과 M (5) 실버3
 *
 * 문제
 * N개의 자연수 중에서 M개를 고른 수열
 * 위 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.
 *
 * 조건
 * (1 ≤ M ≤ N ≤ 8)
 * 입력으로 주어지는 수는 10,000보다 작거나 같은 자연수이다.
 * 수열은 사전 순으로 증가하는 순서로 출력해야 한다.
 *
 * 풀이
 */

public class Main {
	
	static int N;
	static int[] arr, result;
	static boolean[] visit;
	static StringBuilder sb = new StringBuilder();
	
	private static void dfs(int before, int depth, int maxDepth) {
		
		if (depth == maxDepth) {
			for (int idx = 0; idx < maxDepth; idx++) {
				sb.append(result[idx]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int idx = 0; idx < N; idx++) {
			if (visit[idx]) continue;
			result[depth] = arr[idx];
			visit[idx] = true;
			dfs(idx, depth + 1, maxDepth);
			visit[idx] = false;
		}
	}
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	N = Integer.parseInt(st.nextToken());
    	int M = Integer.parseInt(st.nextToken());
    	arr = new int[N];
    	visit = new boolean[N];
    	
    	st = new StringTokenizer(br.readLine());
    	for (int idx = 0; idx < N; idx++) {
    		arr[idx] = Integer.parseInt(st.nextToken());
    	}
    	Arrays.sort(arr);
    	
    	result = new int[M];
    	
    	dfs(0, 0, M);
    	
    	System.out.println(sb.toString());
    }
}
