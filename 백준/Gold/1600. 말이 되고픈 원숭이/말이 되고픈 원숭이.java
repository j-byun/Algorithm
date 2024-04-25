import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 1600 말이 되고픈 원숭이 골드3
 * 시간 제한 2초 | 메모리 제한 256 MB
 *
 * 문제
 *
 * 조건
 *
 * 풀이
 */

public class Main {
	
	static int[] dr = new int[] {-1, 0, 1, 0};
	static int[] dc = new int[] {0, 1, 0, -1};
	static int[] horseDr = new int[] {-2, -1, 1, 2, 2, 1, -1, -2};
	static int[] horseDc = new int[] {1, 2, 2, 1, -1, -2, -2, -1};
	
	static int K, W, H;
	static int[][] map;
	static boolean[][][] visited;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	// 기타 크기들 입력받기
    	K = Integer.parseInt(st.nextToken());
    	
    	st = new StringTokenizer(br.readLine());
    	W = Integer.parseInt(st.nextToken());
    	H = Integer.parseInt(st.nextToken());
    	
    	map = new int[H][W];
    	visited = new boolean[H][W][K + 1];
    	
    	// 맵 정보 입력받기
    	for (int row = 0; row < H; row++) {
    		st = new StringTokenizer(br.readLine());
    		for (int col = 0; col < W; col++) {
    			map[row][col] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	// 너비 우선 탐색 결과 출력
    	System.out.println(moveMonkey());
    }
    
    static int moveMonkey() {
    	
    	Queue<Monkey> queue = new ArrayDeque<>();
    	visited[0][0][0] = true;
    	queue.add(new Monkey(0, 0, 0, 0));
    	
    	while (!queue.isEmpty()) {
    		Monkey cur = queue.poll();
    		
    		if (cur.row == H - 1 && cur.col == W - 1) {
    			return cur.moveCount;
    		}
    		
    		// 원숭이처럼 이동
    		for (int d = 0; d < 4; d++) {
    			int nr = cur.row + dr[d];
    			int nc = cur.col + dc[d];
    			
    			if (nr < 0 || nr >= H || nc < 0 || nc >= W) continue;
    			if (map[nr][nc] == 1) continue;
    			if (visited[nr][nc][cur.horseMoveCount]) continue;
    			visited[nr][nc][cur.horseMoveCount] = true;
				queue.add(new Monkey(nr, nc, cur.moveCount + 1, cur.horseMoveCount));
    		}
    		
    		// 말처럼 이동할 수 있는 카운트 다썼으면 말처럼 이동 X
    		if (cur.horseMoveCount >= K) continue;
    		
    		// 말처럼 이동할 수 있는 카운트 아직 남았으면 말처럼 이동
    		for (int d = 0; d < 8; d++) {
    			int nr = cur.row + horseDr[d];
    			int nc = cur.col + horseDc[d];
    			
    			if (nr < 0 || nr >= H || nc < 0 || nc >= W) continue;
    			if (map[nr][nc] == 1) continue;
    			if (visited[nr][nc][cur.horseMoveCount + 1]) continue;
    			visited[nr][nc][cur.horseMoveCount + 1] = true;
    			queue.add(new Monkey(nr, nc, cur.moveCount + 1, cur.horseMoveCount + 1));
    		}
    	}
    	
    	return -1;
    }
    
    static class Monkey {
    	int row, col, moveCount, horseMoveCount;
    	
    	public Monkey(int row, int col, int moveCount, int horseMoveCount) {
    		this.row = row;
    		this.col = col;
    		this.moveCount = moveCount;
    		this.horseMoveCount = horseMoveCount;
    	}
    }
}
