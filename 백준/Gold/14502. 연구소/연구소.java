import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 14502 연구소
// 골드4

// 연구소 크기 N×M


public class Main {
	static int[] dr = new int[] {-1, 0, 1, 0};
	static int[] dc = new int[] {0, 1, 0, -1};
	
	static int rowSize, colSize;
	static int maxSafe = Integer.MIN_VALUE;
	
	static int[][] map;
	
	public static void dfs(int depth) {
		
		if (depth == 3) { // 벽 3개를 설치했으면 bfs 탐색 시작
			bfs();
			return;
		}
		
		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				if (map[row][col] != 0) continue;  // 벽은 빈 칸에만 설치 가능
				map[row][col] = 1;
				dfs(depth + 1);
				map[row][col] = 0;
			}
		}
	}
	
	public static void bfs() {
		Queue<Node> node = new LinkedList<>();
		int[][] copiedMap = new int[rowSize][colSize]; // 현재 벽 3개 설치 상태를 복사해서 바이러스를 퍼뜨리자
		
		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				copiedMap[row][col] = map[row][col];
				
				if (map[row][col] == 2) {
					node.offer(new Node(row, col)); // 바이러스가 발견되면 큐에 좌표 저장
				}
			}
		}
		
		while (!node.isEmpty()) { // 모든 바이러스를 처리할 때 까지 반복
			Node cur = node.poll(); // 바이러스의 좌표값 하나를 꺼내와서
			int r = cur.x;
			int c = cur.y;
			
			for (int d = 0; d < 4; d++) { // 상하좌우 네 방향에 바이러스 퍼뜨리기
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if (nr < 0 || nr >= rowSize || nc < 0 || nc >= colSize) continue; // 퍼뜨릴 칸이 map범위 내에 있을 때만 퍼뜨리고
				if (copiedMap[nr][nc] != 0) continue; // 빈 칸일 때만 퍼뜨리자
				
				copiedMap[nr][nc] = 2; // 바이러스를 퍼뜨리고
				node.offer(new Node(nr, nc)); // 바이러스 좌표를 저장할 큐에 추가해주자
			}
		}
		
		int sumSafe = 0; // 현재 설치한 3개의 벽에 대해 바이러스 퍼뜨리기가 끝났다면
		
		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				if (copiedMap[row][col] == 0) { // 안전지역의 개수를 세자
					sumSafe++;
				}
			}
		}
		
		maxSafe = Math.max(maxSafe, sumSafe); // 지금까지 확인했던 안전지역 개수 중 최대값만 저장하자
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		rowSize = sc.nextInt();
		colSize =sc.nextInt();
		
		map = new int[rowSize][colSize];
		
		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				map[row][col] = sc.nextInt();
			}
		}
		
		dfs(0); // 벽 3개를 설치하는 모든 경우의 수를 백트래킹으로 완전탐색하자
		
		System.out.println(maxSafe);
	}
	
	public static class Node {
		int x;
		int y;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
}
