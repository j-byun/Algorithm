import java.util.*;

/**
 * @author jihye.byun
 * BOJ 11048 이동하기 실버2
 *
 * 문제
 *
 * 조건
 * 1 ≤ N, M ≤ 1,000
 * 사탕의 개수는 0보다 크거나 같고, 100보다 작거나 같다.
 *
 * 풀이
 */

public class Main {
//	static int[] dr = new int[] {1, 0, 1};
//	static int[] dc = new int[] {0, 1, 1};
	
	static int N, M, maxCandy;
	static int[][] map;
	
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	N = sc.nextInt();
    	M = sc.nextInt();
    	map = new int[N + 1][M + 1];
    	
    	for (int row = 1; row <= N; row++) {
    		for (int col = 1; col <= M; col++) {
    			map[row][col] = sc.nextInt();
    		}
    	}
    	
//    	maxCandy = 0;
//    	
//    	dfs(0, 0, map[0][0]);
    	
    	for (int row = 1; row <= N; row++) {
    		for (int col = 1; col <= M; col++) {
    			map[row][col] += Math.max(map[row - 1][col], map[row][col - 1]);
    		}
    	}
    	
    	System.out.println(map[N][M]);
    }
    
//    static void dfs(int row, int col, int candy) {
//    	if (row == N - 1 && col == M - 1) {
//    		maxCandy = Math.max(maxCandy, candy);
//    		return;
//    	}
//    	
//    	for (int d = 0; d < 3; d++) {
//    		int nr = row + dr[d];
//    		int nc = col + dc[d];
//    		
//    		if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
//    		dfs(nr, nc, candy + map[nr][nc]);
//    	}
//    }
}
