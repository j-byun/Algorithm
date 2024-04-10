import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 1520 내리막 길 골드3
 * 시간 제한 2초 | 메모리 제한 128 MB
 *
 * 문제
 * 왼쪽위에서 오른쪽아래칸으로 이동
 * 상하좌우 이웃한 칸으로 이동 가능
 * 항상 높이가 더 낮은 칸으로만 이동
 * 위의 조건을 만족하는 경로의 개수를 구하자
 *
 * 조건
 * 지도의 세로의 크기 M과 가로의 크기 N
 * M과 N은 각각 500이하의 자연수
 * 각 지점의 높이는 10000이하의 자연수
 *
 * 풀이
 * 1. bfs로 탐색
 * 2. 어차피 높이 낮은 칸으로만 이동가능해서 방문체크같은거 필요없음
 * -> 메모리 초과
 * 3. dp도입이 필요함
 */

public class Main {
	
	static int[] dr = new int[] {-1, 0, 1, 0};
	static int[] dc = new int[] {0, 1, 0, -1};
	static int M, N;
	static int[][] map, dp;
	static boolean[][] visit;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	M = Integer.parseInt(st.nextToken()); // 세로 길이
    	N = Integer.parseInt(st.nextToken()); // 가로 길이
    	map = new int[M][N]; // 지도
    	
    	// 지도 정보 입력
    	for (int row = 0; row < M; row++) {
    		st = new StringTokenizer(br.readLine());
    		for (int col = 0; col < N; col++) {
    			map[row][col] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	// i,j칸에서 마지막칸까지 갈 수 있는 경로의 수를 저장할 배열
    	dp = new int[M][N];
    	visit = new boolean[M][N];
    	dp[M - 1][N - 1] = 1;
    	visit[M - 1][N - 1] = true;
    	
    	dfs(0, 0);
    	
    	// 0,0에서 마지막칸까지 갈 수 있는 경로의 수 출력
    	System.out.println(dp[0][0]);
    }
    
    static void dfs(int row, int col) {
    	
    	if (row == M - 1 && col == N - 1) {
    		return;
    	}
    	
    	for (int d = 0; d < 4; d++) {
    		int nr = row + dr[d];
    		int nc = col + dc[d];
    		
    		if (nr < 0 || nr >= M || nc < 0 || nc >= N) continue;
    		if (map[row][col] <= map[nr][nc]) continue;
    		
    		if (visit[nr][nc]) {
    			dp[row][col] += dp[nr][nc];
    		}
    		if (!visit[nr][nc]) {
    			dfs(nr, nc);
    			visit[nr][nc] = true;
    			dp[row][col] += dp[nr][nc];
    		}
    	}
    }
}
