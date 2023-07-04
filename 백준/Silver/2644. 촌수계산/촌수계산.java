import java.util.Scanner;

/**
 * @author jihye.byun
 * BOJ 2644 촌수계산 실버2
 *
 * 문제
 *
 * 조건
 *
 * 풀이
 */

public class Main {
	
	static int n, end;
	static int[][] adjArr;
	static boolean[] visit;
	
	static int result;
	
	private static void dfs(int start, int depth) {
		
		if (start == end) {
			result = depth;
			return;
		}
		
		for (int idx = 1; idx <= n; idx++) {
			if (adjArr[start][idx] == 0) continue;
			if (visit[idx]) continue;
			visit[idx] = true;
			dfs(idx, depth + 1);
			visit[idx] = false;
		}
	}
	
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	n = sc.nextInt();
    	int start = sc.nextInt();
    	end = sc.nextInt();
    	
    	adjArr = new int[n + 1][n + 1];
    	
    	int m = sc.nextInt();
    	
    	for (int idx = 0; idx < m; idx++) {
    		int a = sc.nextInt();
    		int b = sc.nextInt();
    		
    		adjArr[a][b] = 1;
    		adjArr[b][a] = 1;
    	}
    	
    	visit = new boolean[n + 1];
    	result = -1;
    	dfs(start, 0);
    	
    	System.out.println(result);
    }
}
