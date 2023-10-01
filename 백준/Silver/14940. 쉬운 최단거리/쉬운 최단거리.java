import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 14940 쉬운 최단거리 실버1
 *
 * 문제
 *
 * 조건
 *
 * 풀이
 */

public class Main {
	
	static class Point {
		int row, col;
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	static int[] dr = new int[] {-1, 0, 1, 0};
	static int[] dc = new int[] {0, 1, 0, -1};
	
	static int n, m;
	static int[][] map, time;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	map = new int[n][m];
    	time = new int[n][m];
    	
    	Point start = new Point(-1, -1);
    	
    	for (int row = 0; row < n; row++) {
    		st = new StringTokenizer(br.readLine());
    		for (int col = 0; col < m; col++) {
    			map[row][col] = Integer.parseInt(st.nextToken());
    			
    			if (map[row][col] == 2) {
    				start = new Point(row, col);
    			}
    		}
    	}
    	
    	bfs(start);
    	
    	for (int row = 0; row < n; row++) {
    		for (int col = 0; col < m; col++) {
    			if (map[row][col] == 1 && time[row][col] == 0) {
    				time[row][col] = -1;
    			}
    			bw.write(time[row][col] + " ");
    		}
    		bw.write("\n");
    	}
    	bw.flush();
    }
    
    static void bfs(Point start) {
    	Queue<Point> queue = new ArrayDeque<>();
    	queue.add(start);
    	
    	while (!queue.isEmpty()) {
    		Point cur = queue.poll();
    		
    		for (int d = 0; d < 4; d++) {
    			int nr = cur.row + dr[d];
    			int nc = cur.col + dc[d];
    			
    			if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
    			if (time[nr][nc] != 0) continue;
    			if (map[nr][nc] != 1) continue;
    			time[nr][nc] = time[cur.row][cur.col] + 1; 
    			queue.add(new Point(nr, nc));
    		}
    	}
    }
    
}
