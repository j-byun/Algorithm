import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author jhye.byun
 * SWEA 1249 보급로 D4 최단경로
 * 
 * 문제
 * 파손된 도로를 복구하며 지나가자.
 * 출발지 S 에서 도착지 G 까지 최단 시간으로 지나가자.
 * 도로가 1만큼 파손되어 있으면 복구에 드는 시간이 1이다.
 * 출발지에서 도착지까지 가는 경로 중에 복구 시간이 가장 짧은 경로에 대한 총 복구 시간을 구하시오.
 * 
 * 지도는 2차원 배열 형태로 표시된다.
 * 출발지는 좌상단의 칸 0*0, 도착지는 우하단의 칸 N-1*N-1 이다.
 * 이동경로는 상하좌우 방향으로 진행할 수 있으며, 한 칸 씩 움직일 수 있다.
 * 현재 위치한 칸의 도로를 복구해야만 다른 곳으로 이동할 수 있다.
 * 복구 시간 외에 이동 시간은 고려하지 않는다.
 * 
 * 조건
 * 지도의 크기는 최대 100 x 100이다.
 * 
 * 풀이
 * <다익스트라 알고리즘>
 * 간선의 가중치가 있는 그래프에서 두 정점 사이의 경로들 중에 간선의 가중치의 합이 최소인 경로
 * 음의 가중치를 허용하지 않는다.
 * 시작 정점에서 거리가 최소인 정점을 선택해 나가면서 최단 경로를 구하는 방식이다.
 * 1. 시작 정점을 입력 받는다.
 * 2. 거리를 저장할 D배열을 무한대로 초기화 한 후, 시작점에서 갈 수 있는 곳의 값은 바꿔 놓는다.
 * 3. 아직 방문하지 않은 점들이 가지고 있는 거리 값과 현재 정점에서 방문하지 않은 정점까지의 가중치의 합이 작다면 변경하여 적는다.
 * 4. 모든 정점을 방문할 깨 까지 반복한다.
 */

public class Solution {
	
	static int[] dr = new int[] {-1, 0, 1, 0}; // 상우하좌 델타배열
	static int[] dc = new int[] {0, 1, 0, -1};
	
	static int size;
	static int INF = 987654321;
	static int[][] map, time;
	
	public static void dijkstra() {
		// 시작점에서 도착점까지 가는 경로를 따져보며 최단경로를 저장해보자
		
		// 가중치 오름차순으로 정렬시킬 우선순위 큐를 만들자
		PriorityQueue<Coo> pq = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
		
		// 시작점을 넣자!
		time[0][0] = map[0][0];
		pq.add(new Coo(0, 0, time[0][0]));
		
		while (!pq.isEmpty()) {
			Coo curr = pq.poll();
			
			int row = curr.row;
			int col = curr.col;
			int weight = curr.weight;
			
			// 우선순위 큐를 사용해서 최단 경로 우선으로 탐색했기 때문에
			// 도착점에 한 번 도달했으면 그게 곧 최단경로이다!
			if (row == size - 1 && col == size - 1)
				return;
			
			// 상하좌우 네 방향에 대해 확인해보자
			for (int d = 0; d < 4; d++) {
				int nr = row + dr[d];
				int nc = col + dc[d];
				
				// 지도 범위를 벗어나면 다른 방향 탐색
				if (nr < 0 || nr >= size || nc < 0 || nc >= size)
					continue;
				
				// 이미 저장된 경로가 지금 찾은 경로보다 빠르면 다른 방향 탐색
				if (time[nr][nc] <= weight + map[nr][nc])
					continue;
				
				// 지금 찾은 경로가 최단 경로라면 경로 업데이트하고 큐에 넣어주자
				time[nr][nc] = weight + map[nr][nc];
				pq.add(new Coo(nr, nc, time[nr][nc]));
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int testCase = sc.nextInt(); // 테스트케이스 개수 입력받기
		
		for (int tc = 1; tc <= testCase; tc++) { // 테스트케이스 개수만큼 반복
			
			size = sc.nextInt(); // 지도 한 변의 크기 입력받기
			
			map = new int[size][size]; // 파손된 도로 정보를 저장할 지도 공간 만들기
			
			time = new int[size][size]; // 지도의 해당 위치까지 가는데에 걸리는 시간을 저장할 공간 만들기
			
			// 지도 정보 입력받기
			for (int row = 0; row < size; row++) {
				String line = sc.next();
				for (int col = 0; col < size; col++) {
					map[row][col] = line.charAt(col) - '0';
				}
			}
			
			// time공간의 모든 값을 무한대로 초기화하기
			for (int row = 0; row < size; row++) {
				for (int col = 0; col < size; col++) {
					time[row][col] = INF;
				}
			}
			
			// 다익스트라 알고리즘으로 한 점에서 네방향에 대해 복구 시간을 확인해보며
			// 최단 복구 시간을 저장하자
			dijkstra();
			
			// 탐색이 끝났다면 도착점에 저장된 시간이 복구에 드는 최단 시간이다
			// 그대로 출력하자
			System.out.printf("#%d %d\n", tc, time[size - 1][size - 1]); // 출력
		}
	}
	
	// 큐에 좌표를 저장하기 위해 사용할 Coo 클래스
	public static class Coo {
		int row, col, weight;
		public Coo(int row, int col, int weight) {
			this.row = row;
			this.col = col;
			this.weight = weight;
		}
	}
	
}
