import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 4963 섬의 개수 실버2
 * 시간 제한 1초 | 메모리 제한 128 MB
 *
 * 문제
 * 섬과 바다 지도에서 섬의 개수를 세자.
 * 가로, 세로, 대각선으로 연결되어 있는 땅은 같은 섬이다.
 *
 * 조건
 * 지도의 너비 w와 높이 h (w와 h는 50보다 작거나 같은 양의 정수)
 * 1은 땅, 0은 바다
 *
 * 풀이
 * 1. bfs
 */

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	StringBuilder sb = new StringBuilder();
    	
    	int[] dr = new int[] {-1, -1, 0, 1, 1, 1, 0, -1};
    	int[] dc = new int[] {0, 1, 1, 1, 0, -1, -1, -1};
    	
    	while (true) {
    		st = new StringTokenizer(br.readLine());
    		
    		int w = Integer.parseInt(st.nextToken());
    		int h = Integer.parseInt(st.nextToken());
    		
    		if (w == 0 && h == 0) break;
    		
    		int[][] map = new int[h][w];
    		
    		for (int row = 0; row < h; row++) {
    			st = new StringTokenizer(br.readLine());
    			for (int col = 0; col < w; col++) {
    				map[row][col] = Integer.parseInt(st.nextToken());
    			}
    		}
    		
    		Queue<Integer> queue = new ArrayDeque<>();
    		boolean[][] visit = new boolean[h][w];
    		int count = 0;
    		
    		for (int row = 0; row < h; row++) {
    			for (int col = 0; col < w; col++) {
    				if (map[row][col] == 0 || visit[row][col]) continue;
    				
    				// bfs
    				count++;
    				visit[row][col] = true;
    				queue.add(row * w + col);
    				
    				while (!queue.isEmpty()) {
    					int cur = queue.poll();
    					int r = cur / w;
    					int c = cur % w;
    					
    					for (int d = 0; d < 8; d++) {
    						int nr = r + dr[d];
    						int nc = c + dc[d];
    						
    						if (nr < 0 || nr >= h || nc < 0 || nc >= w) continue;
    						if (map[nr][nc] == 0) continue;
    						if (visit[nr][nc]) continue;
    						
    						visit[nr][nc] = true;
    						queue.add(nr * w + nc);
    					}
    				}
    			}
    		}
    		
    		sb.append(count).append("\n");
    	}
    	
    	System.out.println(sb.toString());
    }
}
