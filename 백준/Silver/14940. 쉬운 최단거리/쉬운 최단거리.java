import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 14940 쉬운 최단거리 실버1
 * 시간 제한 1초 | 메모리 제한 128 MB
 *
 * 문제
 * 가로와 세로로만 움직여서 목표지점까지의 거리를 구하자.
 *
 * 조건
 * 세로 n, 가로 m (2 ≤ n ≤ 1000, 2 ≤ m ≤ 1000)
 * 0은 갈 수 없는 땅
 * 1은 갈 수 있는 땅
 * 2는 목표지점
 * 입력에서 2는 단 한개이다.
 * 원래 갈 수 없는 땅인 위치는 0을 출력하고,
 * 원래 갈 수 있는 땅인 부분 중에서 도달할 수 없는 위치는 -1을 출력한다.
 *
 * 풀이
 * 1. bfs탐색하자
 */

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	StringBuilder sb = new StringBuilder();
    	
    	int n = Integer.parseInt(st.nextToken());
    	int m = Integer.parseInt(st.nextToken());
    	
    	int[][] map = new int[n][m];
    	int[][] dist = new int[n][m];
    	
    	Queue<Integer> queue = new ArrayDeque<>();
    	
    	for (int row = 0; row < n; row++) {
    		st = new StringTokenizer(br.readLine());
    		for (int col = 0; col < m; col++) {
    			map[row][col] = Integer.parseInt(st.nextToken());
    			dist[row][col] = 987654321;
    			
    			if (map[row][col] == 2) {
    				queue.add(row * m + col);
    				dist[row][col] = 0;
    			}
    		}
    	}
    	
    	int[] dr = new int[] {-1, 0, 1, 0};
    	int[] dc = new int[] {0, 1, 0, -1};
    	
    	while (!queue.isEmpty()) {
    		int cur = queue.poll();
    		int row = cur / m;
    		int col = cur % m;
    		
    		for (int d = 0; d < 4; d++) {
    			int nr = row + dr[d];
    			int nc = col + dc[d];
    			
    			if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
    			if (map[nr][nc] != 1) continue;
    			if (dist[nr][nc] <= dist[row][col] + 1) continue;
    			dist[nr][nc] = dist[row][col] + 1;
    			queue.add(nr * m + nc);
    		}
    	}
    	
    	for (int row = 0; row < n; row++) {
    		for (int col = 0; col < m; col++) {
    			
    			if (dist[row][col] == 987654321) {
    				if (map[row][col] == 0) dist[row][col] = 0;
    				if (map[row][col] != 0) dist[row][col] = -1;
    			}
    			
    			sb.append(dist[row][col]).append(" ");
    		}
    		sb.append("\n");
    	}
    	
    	System.out.println(sb.toString());
    }
}
