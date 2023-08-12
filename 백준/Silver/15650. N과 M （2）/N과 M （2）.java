import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author jihye.byun
 * BOJ 15650 N과 M (2) 실버3
 *
 * 문제
 * 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열
 * 고른 수열은 오름차순이어야 한다.
 * 위 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.
 *
 * 조건
 * (1 ≤ M ≤ N ≤ 8)
 *
 * 풀이
 */

public class Main {
	
	static int N;
	static int[] result;
	static StringBuilder sb = new StringBuilder();
	
	private static void dfs(int before, int depth, int maxDepth) {
		
		if (depth == maxDepth) {
			for (int idx = 0; idx < maxDepth; idx++) {
				sb.append(result[idx]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int idx = before + 1; idx <= N; idx++) {
			result[depth] = idx;
			dfs(idx, depth + 1, maxDepth);
		}
	}
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	N = Integer.parseInt(st.nextToken());
    	int M = Integer.parseInt(st.nextToken());
    	
    	result = new int[M];
    	
    	dfs(0, 0, M);
    	
    	System.out.println(sb.toString());
    }
}
