import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author jihye.byun
 * BOJ 2206 벽 부수고 이동하기 골드3 BFS
 *
 * 문제
 * N*M 행렬 맵
 * 0 : 이동할 수 있는 곳
 * 1 : 이동할 수 없는 벽이 있는 곳
 * (1, 1)에서 (N, M)까지 최단 경로로 이동하자.
 * 최단경로는 맵에서 가장 적은 개수의 칸을 지나는 경로를 말하고,
 * 시작하는 칸과 끝나는 칸도 포함해서 센다.
 * 만약 이동하는 도중에 한 개의 벽을 부수고 이동하는 것이 좀 더 경로가 짧아진다면, 벽을 한 개 까지 부수고 이동하여도 된다.
 * 한 칸에서 이동할 수 있는 칸은 상하좌우로 인접한 칸이다.
 * 맵이 주어졌을 때, 최단 경로를 구해 내는 프로그램을 작성하시오.
 *
 * 조건
 * N(1 ≤ N ≤ 1,000)
 * M(1 ≤ M ≤ 1,000)
 * (1, 1)과 (N, M)은 항상 0이라고 가정하자.
 *
 * 풀이
 * 1. BFS 탐색 시 큐에 x, y좌표, 방문한 칸의 개수, 벽을 부셨는지 여부를 함께 저장하자.
 * 2. 이미 벽을 한 번 부신 경로라면 더 부실 수 없다.
 * 3. 방문체크 배열을 만들어서 같은 칸에 두 번 방문하지 않게 확인하자.
 */

public class Main {
	
	static int[] dr = new int[] {-1, 0, 1, 0};
	static int[] dc = new int[] {0, 1, 0, -1};
	
	static int N, M;
	static boolean[][] map;
	static boolean[][][] visit;
	
	private static int bfs() {
		Queue<Node> queue = new ArrayDeque<>();
		queue.add(new Node(0, 0, 1, 0));
		
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			
			if (cur.isBreak == 0) {
				if (visit[cur.row][cur.col][0]) continue;
				visit[cur.row][cur.col][0] = true;
			} else {
				if (visit[cur.row][cur.col][1]) continue;
				visit[cur.row][cur.col][1] = true;
			}
			
			if (cur.row == N - 1 && cur.col == M - 1)
				return cur.count;
			
			for (int d = 0; d < 4; d++) {
				int nr = cur.row + dr[d];
				int nc = cur.col + dc[d];
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				if (map[nr][nc])
					queue.add(new Node(nr, nc, cur.count + 1, cur.isBreak));
				else {
					if (cur.isBreak == 0)
						queue.add(new Node(nr, nc, cur.count + 1, 1));
				}
			}
		}
		
		// (N,M)에 도착하지 못했다면 -1 출력
		return -1;
	}
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	
    	map = new boolean[N][M]; // true:이동가능, false:이동불가능(벽)
    	visit = new boolean[N][M][2]; // [0]:벽안부수고 방문, [1]:벽부수고 방문
    	
    	// map 정보 입력받기
    	for (int row = 0; row < N; row++) {
    		st = new StringTokenizer(br.readLine());
    		String line = st.nextToken();
    		for (int col = 0; col < M; col++) {
    			if (line.charAt(col) == '0')
    				map[row][col] = true;
    		}
    	}
    	
    	System.out.println(bfs());
    }
    
    private static class Node {
    	int row, col, count, isBreak;

		public Node(int row, int col, int count, int isBreak) {
			this.row = row;
			this.col = col;
			this.count = count;
			this.isBreak = isBreak;
		}
    }
}
