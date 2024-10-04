import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 3055 탈출 골드4
 * 시간 제한 1초 | 메모리 제한 128 MB
 *
 * 문제
 *
 * 조건
 *
 * 풀이
 * 1. 지도에 물을 먼저 다 채워놓고, 고슴도치를 움직이자.
 */

public class Main {
	
	static int R, C, answer;
	static char[][] map;
	static int[][] waterMap, hedgehogMap;
	static Queue<Coordination> waterQueue, hedgehogQueue;
	
	static int[] dr = new int[] {-1, 0, 1, 0};
	static int[] dc = new int[] {0, 1, 0, -1};
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    
    	R = Integer.parseInt(st.nextToken());
    	C = Integer.parseInt(st.nextToken());
    	
    	map = new char[R][C];
    	waterMap = new int[R][C];
    	hedgehogMap = new int[R][C];
    	waterQueue = new ArrayDeque<>();
    	hedgehogQueue = new ArrayDeque<>();
    	
    	// 물 도착시간을 저장할 맵 초기화
    	for (int row = 0; row < R; row++) {
    		for (int col = 0; col < C; col++) {
    			waterMap[row][col] = Integer.MAX_VALUE;
    			hedgehogMap[row][col] = Integer.MAX_VALUE;
    		}
    	}
    	
    	// 맵 정보 입력받기
    	for (int row = 0; row < R; row++) {
    		String line = br.readLine();
    		for (int col = 0; col < C; col++) {
    			map[row][col] = line.charAt(col);
    			
    			if (map[row][col] == 'S') {
    				hedgehogQueue.add(new Coordination(row, col));
    				hedgehogMap[row][col] = 0;
    			}
    			
    			if (map[row][col] == '*') {
    				waterQueue.add(new Coordination(row, col));
    				waterMap[row][col] = 0;
    			}
    		}
    	}
    	
    	// 물 퍼뜨리기
    	waterBfs();
    	
    	// 고슴도치 움직이기
    	answer = Integer.MAX_VALUE;
    	hedgehogBfs();
    	
    	System.out.println((answer == Integer.MAX_VALUE) ? "KAKTUS" : answer);
    }
    
    static void waterBfs() {
    	
    	while (!waterQueue.isEmpty()) {
    		Coordination cur = waterQueue.poll();
    		
    		for (int d = 0; d < 4; d++) {
    			int nr = cur.row + dr[d];
    			int nc = cur.col + dc[d];
    			
    			if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
    			if (map[nr][nc] != '.' && map[nr][nc] != 'S') continue;
    			if (waterMap[nr][nc] != Integer.MAX_VALUE) continue;
    			
    			waterMap[nr][nc] = waterMap[cur.row][cur.col] + 1;
    			waterQueue.add(new Coordination(nr, nc));
    		}
    	}
    }
    
    static void hedgehogBfs() {
    	
    	while (!hedgehogQueue.isEmpty()) {
    		Coordination cur = hedgehogQueue.poll();
    		
    		for (int d = 0; d < 4; d++) {
    			int nr = cur.row + dr[d];
    			int nc = cur.col + dc[d];
    			
    			if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
    			if (map[nr][nc] == 'D') {
    				answer = hedgehogMap[cur.row][cur.col] + 1; 
    				return;
    			}
    			if (map[nr][nc] == 'X') continue;
    			if (waterMap[nr][nc] <= hedgehogMap[cur.row][cur.col] + 1) continue;
    			if (hedgehogMap[nr][nc] != Integer.MAX_VALUE) continue;
    			
    			hedgehogMap[nr][nc] = hedgehogMap[cur.row][cur.col] + 1;
    			hedgehogQueue.add(new Coordination(nr, nc));
    		}
    	}
    }
    
    static class Coordination {
    	int row, col;
    	public Coordination(int row, int col) {
    		this.row = row;
    		this.col = col;
    	}
    }
}
