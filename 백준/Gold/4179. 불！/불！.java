import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author jihye.byun
 * BOJ 4179 불! 골드4 BFS
 *
 * 문제
 * 지훈이를 미로에서 탈출하도록 도와주자.
 * 미로에서의 지훈이의 위치와 불이 붙은 위치를 감안해서 지훈이가 불에 타기전에 탈출할 수 있는지의 여부,
 * 그리고 얼마나 빨리 탈출할 수 있는지를 결정해야한다.
 * 지훈이와 불은 매 분마다 한칸씩 수평또는 수직으로 이동한다.
 * 불은 각 지점에서 네 방향으로 확산된다.
 * 지훈이는 미로의 가장자리에 접한 공간에서 탈출 할 수 있다.
 * 지훈이와 불은 벽이 있는 공간은 통과하지 못한다.
 *
 * 조건
 * R : 미로 행의 개수
 * c : 미로 열의 개수
 * 1 ≤ R, C ≤ 1000
 * 
 * # : 벽
 * . : 지나갈 수 있는 공간
 * J : 지훈이의 미로에서의 초기위치 (지나갈 수 있는 공간)
 * F : 불이 난 공간
 * 
 * J는 입력에서 하나만 주어진다.
 * 지훈이가 불이 도달하기 전에 미로를 탈출 할 수 없는 경우 IMPOSSIBLE을 출력한다.
 * 지훈이가 미로를 탈출할 수 있는 경우에는 가장 빠른 탈출시간을 출력한다.
 *
 * 풀이
 */

public class Main {
	static int[] dr = new int[] {-1, 0, 1, 0};
	static int[] dc = new int[] {0, 1, 0, -1};
	
	static int R, C;
	static int[][] map;
	static boolean[][] visit;
	
	static Queue<Coo> jihun = new ArrayDeque<>();
	static Queue<Coo> fire = new ArrayDeque<>();
	
	private static void bfsFire() {
		
		while (!fire.isEmpty()) {
			Coo cur = fire.poll();
			
			for (int d = 0; d < 4; d++) {
				int nr = cur.row + dr[d];
				int nc = cur.col + dc[d];
				
				if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
				if (map[nr][nc] == -2) continue;
				if (map[nr][nc] >= 0) continue;
				map[nr][nc] = cur.time + 1;
				fire.add(new Coo(nr, nc, cur.time + 1));
			}
		}
	}
	
	private static int bfsJihun() {
		
		while (!jihun.isEmpty()) {
			Coo cur = jihun.poll();
			
			for (int d = 0; d < 4; d++) {
				int nr = cur.row + dr[d];
				int nc = cur.col + dc[d];
				
				if (nr < 0 || nr >= R || nc < 0 || nc >= C) return (cur.time + 1);
				if (visit[nr][nc]) continue;
				if (map[nr][nc] == -2) continue;
				if ((map[nr][nc] != -1) && (map[nr][nc] <= cur.time + 1)) continue;
				visit[nr][nc] = true;
				jihun.add(new Coo(nr, nc, cur.time + 1));
			}
		}
		
		return -1;
	}
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	// map의 크기 입력받기
    	R = Integer.parseInt(st.nextToken());
    	C = Integer.parseInt(st.nextToken());
    	
    	// map 공간 생성
    	map = new int[R][C];
    	visit = new boolean[R][C];
    	
    	// map의 칸 정보 입력받기
    	for (int row = 0; row < R; row++) {
    		st = new StringTokenizer(br.readLine());
    		String line = st.nextToken();
    		for (int col = 0; col < C; col++) {
    			switch (line.charAt(col)) {
    			case '#' :
    				map[row][col] = -2;
    				break;
    			case '.' :
    				map[row][col] = -1;
    				break;
    			case 'J' : 
    				map[row][col] = -1;
    				visit[row][col] = true;
    				jihun.add(new Coo(row, col, 0));
    				break;
    			case 'F' :
    				map[row][col] = 0;
    				fire.add(new Coo(row, col, 0));
    				break;
    			}
    		}
    	}
    	
    	bfsFire();
    	int result = bfsJihun();
    	System.out.println(result == -1 ? "IMPOSSIBLE" : result);
    	
//    	for (int row = 0; row < R; row++) {
//    		System.out.println();
//    		for (int col = 0; col < C; col++) {
//    			System.out.print(map[row][col]);
//    		}
//    	}
    	
    }
    
    private static class Coo {
    	int row, col, time;
    	public Coo(int row, int col, int time) {
    		this.row = row;
    		this.col = col;
    		this.time = time;
    	}
    }
}
