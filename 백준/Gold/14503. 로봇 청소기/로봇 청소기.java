import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 14503 로봇 청소기 골드5
 * 시간 제한 2초 | 메모리 제한 512 MB
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
	
	static int N, M;
	static boolean[][] room, visit;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
//    	StringBuilder sb = new StringBuilder();
    	
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	
    	room = new boolean[N][M];
    	visit = new boolean[N][M];
    	
    	st = new StringTokenizer(br.readLine());
    	int rr = Integer.parseInt(st.nextToken());
    	int rc = Integer.parseInt(st.nextToken());
    	int rd = Integer.parseInt(st.nextToken());
    	
    	for (int row = 0; row < N; row++) {
    		st = new StringTokenizer(br.readLine());
    		for (int col = 0; col < M; col++) {
    			if (Integer.parseInt(st.nextToken()) == 0) {
    				room[row][col] = true;
    			}
    		}
    	}
    	
    	System.out.println(cleaning(rr, rc, rd));
    }
    
    static int cleaning(int row, int col, int dir) {
    	int count = 0;
    	
    	while (true) {
    		// 1. 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
    		if (room[row][col] && !visit[row][col]) {
    			count++;
    			visit[row][col] = true;
    		}
    		
    		boolean isEmpty = false;
    		for (int d = 0; d < 4; d++) {
    			int nr = row + dr[d];
    			int nc = col + dc[d];
    			
    			if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
    			if (room[nr][nc] && !visit[nr][nc]) {
    				isEmpty = true;
    				break;
    			}
    		}
    		
    		// 2. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우
    		if (!isEmpty) {
    			int nr = row - dr[dir];
    			int nc = col - dc[dir];
    			
    			// 2-2. 바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춘다.
    			if (nr < 0 || nr >= N || nc < 0 || nc >= M) break;
    			if (!room[nr][nc]) break;
    			
    			// 2-1. 바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진하고 1번으로 돌아간다.
    			row = nr;
    			col = nc;
    			continue;
    		}
    		
    		// 3. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우
    		if (isEmpty) {
    			for (int d = 0; d < 4; d++) {
    				// 3-1. 반시계 방향으로 90도 회전한다.
    				dir = (dir - 1 + 4) % 4;
    				// 3-2. 바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진한다.
    				int nr = row + dr[dir];
    				int nc = col + dc[dir];
    				
    				if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
    				if (!room[nr][nc]) continue;
    				if (visit[nr][nc]) continue;
    				row = nr;
    				col = nc;
    				break;
    			}
    		}
    	}
    	
    	return count;
    }
}
