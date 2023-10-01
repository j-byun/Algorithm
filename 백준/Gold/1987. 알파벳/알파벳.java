import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 1987 알파벳 골드4
 *
 * 문제
 * R*C크기의 보드 각 칸에 대문자 알파벳이 하나씩 적혀 있다.
 * 좌측 상단 칸에는 말이 놓여 있다.
 * 말은 상하좌우로 인접한 네 칸 중 한 칸으로 이동할 수 있다.
 * 새로 이동한 칸에 적혀 있는 알파벳은 지금까지 지나온 모든 칸에 적혀 있는 알파벳과는 달라야 한다.
 * 즉, 같은 알파벳이 적힌 칸을 두 번 지날 수 없다.
 * 말이 최대한 몇 칸을 지날 수 있는 지 구하자.
 * 말이 지나는 칸은 좌측 상단의 칸도 포함된다.
 *
 * 조건
 * 1 ≤ R,C ≤ 20
 *
 * 풀이
 * 1. dfs 탐색하며 각 알파벳의 방문 처리를 하자.
 */

public class Main {
	
	static int[] dr = new int[] {-1, 0, 1, 0};
	static int[] dc = new int[] {0, 1, 0, -1};
	
	static int R, C;
	static int[][] map;
	static boolean[] visit;
	static int maxDepth;
	
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	R = sc.nextInt();
    	C = sc.nextInt();
    	
    	map = new int[R][C];
    	visit = new boolean[26];
    	
    	for (int row = 0; row < R; row++) {
    		String line = sc.next();
    		for (int col = 0; col < C; col++) {
    			map[row][col] = line.charAt(col);
    		}
    	}
    	
    	visit[map[0][0] - 'A'] = true;
    	maxDepth = 1;
    	
    	dfs(0, 0, 1);
    	
    	System.out.println(maxDepth);
    }
    
    static void dfs(int row, int col, int depth) {
    	
    	for (int d = 0; d < 4; d++) {
    		int nr = row + dr[d];
    		int nc = col + dc[d];
    		
    		if (nr < 0 || nr >= R || nc < 0 || nc >= C) {
    			maxDepth = Math.max(maxDepth, depth);
    			continue;
    		}
    		
    		if (visit[map[nr][nc] - 'A']) {
    			maxDepth = Math.max(maxDepth, depth);
    			continue;
    		}
    		
    		visit[map[nr][nc] - 'A'] = true;
    		dfs(nr, nc, depth + 1);
    		visit[map[nr][nc] - 'A'] = false;
    	}
    }
}
