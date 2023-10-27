import java.util.*;

/**
 * @author jihye.byun
 * BOJ 16918 봄버맨 실버1
 *
 * 문제
 * R*C 크기의 격자판
 * 폭탄은 3초 후 폭발, 폭발 후에는 빈 칸이 된다.
 * 폭탄과 인접한 네 칸도 함께 파괴되며, 연쇄 반응은 없다.
 * 
 * <봄버맨의 행동>
 * - 가장 처음에 일부 칸에 폭탄을 설치해 놓는다. 모든 폭탄이 설치된 시간은 같다.
 * - 다음 1초 동안 봄버맨은 아무것도 하지 않는다.
 * - 다음 1초 동안 폭탄이 설치되어 있지 않은 모든 칸에 폭탄을 설치한다. 즉, 모든 칸은 폭탄을 가지고 있게 된다. 폭탄은 모두 동시에 설치했다고 가정한다.
 * - 1초가 지난 후에 3초 전에 설치된 폭탄이 모두 폭발한다.
 * - 3과 4를 반복한다.
 * 
 * 폭탄을 설치해놓은 초기 상태가 주어졌을 때, N초가 흐른 후의 격자판 상태를 구하려고 한다.
 *
 * 조건
 * R, C, N (1 ≤ R, C, N ≤ 200)
 *
 * 풀이
 * 1. 빈 칸을 -1로, 폭탄이 있는 칸은 0~시간으로 표현하자.
 */

public class Main {
	
	static class Point {
		int row, col;
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	static int[][] map;
	static int R, C, N;
	
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	R = sc.nextInt(); // 행의 개수
    	C = sc.nextInt(); // 열의 개수
    	N = sc.nextInt(); // 격자판을 확인할 시간
    	
    	map = new int[R][C]; // 격자판
    	
    	// 폭탄 정보 입력받기
    	for (int row = 0; row < R; row++) {
    		String input = sc.next();
    		for (int col = 0; col < C; col++) {
    			if (input.charAt(col) == '.')
    				map[row][col] = -1;
    		}
    	}
    	
    	// 2초에 설치
    	// 3초에 폭발
    	// 위 과정을 반복
    	for (int time = 2; time <= N; time++) {
    		if (time % 2 == 0)
    			plantBombs(time);
    		else
    			explodeBombs(time);
    	}
    	
    	// 격자판 상태 출력
    	StringBuilder sb = new StringBuilder();
    	for (int row = 0; row < R; row++) {
    		for (int col = 0; col < C; col++) {
    			if (map[row][col] == -1)
    				sb.append('.');
    			else
    				sb.append('O');
    		}
    		sb.append("\n");
    	}
    	
    	System.out.println(sb.toString());
    }
    
    // 빈 칸에 폭탄을 설치하는 메서드
    static void plantBombs(int time) {
    	
    	for (int row = 0; row < R; row++) {
    		for (int col = 0; col < C; col++) {
    			if (map[row][col] == -1)
    				map[row][col] = time;
    		}
    	}
    }
    
    // time초에 폭탄을 폭발시키는 메서드
    static void explodeBombs(int time) {
    	int[] dr = new int[] {0, -1, 0, 1, 0};
    	int[] dc = new int[] {0, 0, 1, 0, -1};
    	
    	Queue<Point> queue = new ArrayDeque<>();
    	
    	for (int row = 0; row < R; row++) {
    		for (int col = 0; col < C; col++) {
    			// 설치한 지 3초 지난 폭탄은 폭발시키자
    			if (map[row][col] + 3 == time)
    				queue.add(new Point(row, col));
    		}
    	}
    	
    	while (!queue.isEmpty()) {
    		Point cur = queue.poll();
    		
    		for (int d = 0; d < 5; d++) {
				int nr = cur.row + dr[d];
				int nc = cur.col + dc[d];
				
				if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
				map[nr][nc] = -1;
			}
    	}
    }
}
