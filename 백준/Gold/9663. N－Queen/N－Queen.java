import java.util.Scanner;

/**
 * @author jihye.byun
 * BOJ 9663 N-Queen 골드4
 *
 * 문제
 * 크기가 N*N인 체스판 위에 퀸 N개를 서로 공격할 수 없게 놓자.
 * N이 주어졌을 떄, 퀸을 놓는 방법의 수를 구하는 프로그램을 작성하시오.
 *
 * 조건
 * 1 ≤ N < 15
 *
 * 풀이
 */

public class Main {
	static int[] dr = new int[] {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = new int[] {0, 1, 1, 1, 0, -1, -1, -1};
	
	static int N, count;
	
	private static void dfs(int row, int col, int[][] map) {
		
		for (int d = 0; d < 8; d++) {
			for (int range = 1; range < N; range++) {
				int nr = row + dr[d] * range;
				int nc = col + dc[d] * range;
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= N) break;
				if (map[nr][nc] == 1) return;
			}
		}
		
		if (row + 1 == N) {
			count++;
			return;
		}
		
		for (int c = 0; c < N; c++) {
    		map[row + 1][c] = 1;
    		dfs(row + 1, c, map);
    		map[row + 1][c] = 0;
    	}
	}
	
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	N = sc.nextInt();
    	
    	int[][] map = new int[N][N];
    	count = 0;
    	
    	for (int col = 0; col < N; col++) {
    		map[0][col] = 1;
    		dfs(0, col, map);
    		map[0][col] = 0;
    	}
    	
    	System.out.println(count);
    }
}
