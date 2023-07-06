import java.util.Scanner;

/**
 * @author jihye.byun
 * BOJ 11724 연결 요소의 개수 실버2
 *
 * 문제
 *
 * 조건
 *
 * 풀이
 */

public class Main {
	
	static int N, M;
	static int[][] adjArr;
	static boolean[] visit;
	
	private static void dfs(int start) {
		
		visit[start] = true;
		
		for (int idx = 1; idx <= N; idx++) {
			if (adjArr[start][idx] == 0) continue;
			if (visit[idx]) continue;
			dfs(idx);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		adjArr = new int[N + 1][N + 1];
		
		for (int cnt = 0; cnt < M; cnt++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			
			adjArr[start][end] = 1;
			adjArr[end][start] = 1;
		}
		
		visit = new boolean[N + 1];
		int count = 0;
		
		for (int start = 1; start <= N; start++) {
			if (visit[start]) continue;
			for (int end = 1; end <= N; end++) {
				dfs(start);
				count++;
				break;
			}
		}
		
		System.out.println(count);
	}
}
