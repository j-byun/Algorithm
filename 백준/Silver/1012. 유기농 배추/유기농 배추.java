import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author jihye.byun
 * BOJ 1012 유기농 배추 실버2
 *
 * 문제
 * 배추가 상하좌우 바로 옆 칸에 위치한 경우 서로 인접해있는 것이다.
 * 인접한 배추 묶음은 하나로 본다.
 * 배추 묶음 수 만큼 배추흰지렁이를 배치해야한다.
 * 총 몇 묶음의 배추가 있는지 구하자.
 * 0은 배추가 심어져있지 않은 땅이고, 1은 배추가 심어져 있는 땅을 나타낸다.
 *
 * 조건
 * 배추밭의 가로길이 M(1 ≤ M ≤ 50)
 * 배추밭의 세로길이 N(1 ≤ N ≤ 50)
 * 배추가 심어져 있는 위치의 개수 K(1 ≤ K ≤ 2500)
 * 배추의 위치 X(0 ≤ X ≤ M-1), Y(0 ≤ Y ≤ N-1)
 * 두 배추의 위치가 같은 경우는 없다.
 *
 * 풀이
 * 1. 배추의 위치를 배추밭 배열에 표시하고 BFS 탐색을 통해 배추 묶음 수를 확인하자.
 */

public class Main {
	static int[] dr = new int[] {-1, 0, 1, 0};
	static int[] dc = new int[] {0, 1, 0, -1};
	
	static int M, N;
	static int[][] map;
	
	private static void bfs(int row, int col) {
		Queue<int[]> queue = new ArrayDeque<int[]>();
		queue.add(new int[] {row, col});
		
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			if (map[cur[0]][cur[1]] == 9) continue;
			map[cur[0]][cur[1]] = 9; // 방문처리
			
			// 상하좌우 인접한 칸 확인
			for (int d = 0; d < 4; d++) {
				int nr = cur[0] + dr[d];
				int nc = cur[1] + dc[d];
				
				if (nr < 0 || nr >= M || nc < 0 || nc >= N) continue;
				if (map[nr][nc] != 1) continue;
				queue.add(new int[] {nr, nc});
			}
		}
	}
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	
    	int T = Integer.parseInt(st.nextToken()); // 테스트케이스 수
    	
    	for (int tc = 1; tc <= T; tc++) {
    		st = new StringTokenizer(br.readLine());
    		
    		M = Integer.parseInt(st.nextToken()); // 배추밭의 가로길이
    		N = Integer.parseInt(st.nextToken()); // 배추밭의 세로길이
    		int K = Integer.parseInt(st.nextToken()); // 배추의 개수
    		
    		map = new int[M][N]; // 배추밭 배열

    		// K개의 배추 좌표를 입력받아 배추밭 배열에 표시하자
    		for (int cnt = 0; cnt < K; cnt++) {
    			st = new StringTokenizer(br.readLine());
    			map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
    		}
    		
    		// 이제 배추밭 배열을 돌며 베추 묶음 수를 확인하자
    		// 배추 하나를 발견하면 해당 위치에서 BFS 탐색을 실행해 해당 배추 묶음을 방문처리하자
    		int count = 0;
    		for (int row = 0; row < M; row++) {
    			for (int col = 0; col < N; col++) {
    				if (map[row][col] == 1) {
    					bfs(row, col);
    					count++;
    				}
    			}
    		}
    		
    		// 탐색이 끝났으니 배추 묶음 수를 출력하자
    		System.out.println(count);
    	}
    	
    }
}
