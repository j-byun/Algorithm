import java.util.*;

/**
 * @author jihye.byun
 * BOJ 1189 컴백홈 실버1
 *
 * 문제
 *
 * 조건
 * R(1 ≤ R ≤ 5)
 * C(1 ≤ C ≤ 5)
 * K(1 ≤ K ≤ R×C)
 *
 * 풀이
 */

public class Main {
	
	static int[] dr = new int[] {-1, 0, 1, 0};
	static int[] dc = new int[] {0, 1, 0, -1};
	
	static int R, C, K, count;
	static int[][] map;
	static boolean[][] visit;
	
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	R = sc.nextInt();
    	C = sc.nextInt();
    	K = sc.nextInt();
    	
    	map = new int[R][C];
    	visit = new boolean[R][C];
    	
    	for (int row = 0; row < R; row++) {
    		String line = sc.next();
    		for (int col = 0; col < C; col++) {
    			if (line.charAt(col) == 'T')
    				map[row][col] = -1;
    		}
    	}
    	
    	count = 0;
    	visit[R - 1][0] = true;
    	dfs(R - 1, 0, 1);
    	
    	System.out.println(count);
    }
    
    static void dfs(int row, int col, int dist) {
    	
    	if (row == 0 && col == C - 1) {
    		if (dist == K) count++;
    		return;
    	}
    	
    	if (dist >= K) {
    		return;
    	}
    	
    	for (int d = 0; d < 4; d++) {
    		int nr = row + dr[d];
    		int nc = col + dc[d];
    		
    		if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
    		if (map[nr][nc] == -1) continue;
    		if (visit[nr][nc]) continue;
    		
    		visit[nr][nc] = true;
    		dfs(nr, nc, dist + 1);
    		visit[nr][nc] = false;
    	}
    }
}
