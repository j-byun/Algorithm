import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author jihye.byun
 * BOJ 16236 아기 상어 16236
 * 
 * 문제
 * N*N크기 공간에 물고기 M마리와 아기 상어 1마리
 * 공간은 1*1크기의 정사각형 칸으로 나누어져 있다.
 * 한 칸에는 물고기가 최대 1마리 존재한다.
 * 아기 상어와 물고기는 모두 자연수의 크기를 가지고 있다.
 * 초기 아기 상어 크기 = 2
 * 
 * 아기 상어는 1초에 상하좌우로 인접한 한 칸씩 이동한다.
 * 아기 상어보다 크기가 큰 물고기가 있는 칸 : 지나갈 수 없다.
 * 아기 상어 크기와 같은 물고기가 있는 칸 : 지나갈 수 있다.
 * 아기 상어보다 크기가 작은 물고기가 있는 칸 : 지나갈 수 있고, 물고기를 먹을 수 있다.
 * 
 * -아기 상어의 이동 칸 결정 방법-
 * 1. 더 이상 먹을 수 있는 물고기가 공간에 없다면 아기 상어는 엄마 상어에게 도움을 요청한다.
 * 2. 먹을 수 있는 물고기가 1마리라면, 그 물고기를 먹으러 간다.
 * 3. 먹을 수 있는 물고기가 1마리보다 많다면, 거리가 가장 가까운 물고기를 먹으러 간다.
 * 3-1. 거리는 아기 상어가 있는 칸에서 물고기가 있는 칸으로 이동할 때, 지나야하는 칸의 개수의 최솟값이다.
 * 4. 거리가 가까운 물고기가 많다면, 가장 위에 있는 물고기, 그러한 물고기가 여러마리라면, 가장 왼쪽에 있는 물고기를 먹는다.
 * 
 * 아기 상어의 이동은 1초 걸리고, 물고기를 먹는데 걸리는 시간은 없다.
 * 물고기를 먹으면, 그 칸은 빈 칸이 된다.
 * 
 * 아기 상어는 자신의 크기와 같은 수의 물고기를 먹을 때 마다 크기가 1 증가한다.
 * 크기가 2인 아기 상어는 물고기 2마리를 먹으면 크기가 3이 된다.
 * 
 * 아기 상어가 몇 초 동안 엄마 상어에게 도움을 요청하지 않고 물고기를 잡아먹을 수 있는지 구하는 프로그램을 작성하시오.
 * 
 * 조건
 * 공간의 크기 N(2 ≤ N ≤ 20)
 * 공간의 상태는 0, 1, 2, 3, 4, 5, 6, 9로 이루어져 있다.
 * 
 * 0: 빈 칸
 * 1, 2, 3, 4, 5, 6: 칸에 있는 물고기의 크기
 * 9: 아기 상어의 위치
 * 
 * 아기 상어는 공간에 한 마리 있다.
 * 
 * 풀이
 * 1. 일단 공간을 탐색하며 0보다 크고, 현재 아기 상어 크기보다 작은 물고기들을 찾자.
 * 1-1. 이러한 물고기가 0마리라면 엄마 상어를 호출하자.
 * 1-2. 이러한 물고기가 0마리가 아니라면, 아기 상어에서부터 해당 물고기까지의 거리를 bfs로 탐색하자.
 * 1-3. 이 때, 현재 아기 상어 크기보다 큰 물고기가 있는 칸은 지나갈 수 없음을 고려하자.
 * 1-4. 거리를 찾았다면, 우선순위 큐를 활용해 먹을 수 있는 물고기를 거리 순, 행 순, 열 순으로 정렬하자.
 * 1-5. 먹을 수 있는 모든 물고기를 우선순위 큐에 다 넣어줬다면, 제일 앞의 물고기를 먹고 그 때의 이동시간(거리)를 더해주자.
 * => 시간초과
 * 
 * <보완>
 * 1. 먹을 수 있는 모든 물고기를 우선순위 큐에 넣지 말고 상어 위치에서부터 너비 우선 탐색을 진행해 제일 처음 만나는 먹을 수 있는 물고기를 먹자.
 */

public class Main {
	
	static int[] dr = new int[] {-1, 0, 0, 1};
	static int[] dc = new int[] {0, -1, 1, 0};
	
	static int time, N;
	static int[][] map;
	static Shark shark;
	static PriorityQueue<Fish> pq;
	static boolean trapped;
	
	private static boolean search() {
		
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				int cur = map[row][col];
				
				// 먹을 수 있는 물고기가 한 마리라도 있다면 true 반환
				if (cur != 0 && cur < shark.size)
					return true;
			}
		}
		
		// 바다 다 돌았는데 먹을 수 있는 물고기가 한 마리도 없다면 false 반환
		return false;
	}
	
	private static void eatFish() {
		// 먹을 수 있는 물고기들의 거리를 확인해서 먹자
		
		PriorityQueue<Fish> q = new PriorityQueue<>();
		q.add(new Fish(0, shark.row, shark.col));
		
		Fish fish = new Fish(0, N, N);
		
		boolean[][] visit = new boolean[N][N];
		
		while (!q.isEmpty()) {
			Fish cur = q.poll();
			
			// 제일 먼저 발견한 물고기를 먹자
			if (map[cur.row][cur.col] != 0 && map[cur.row][cur.col] < shark.size) {
				fish.dist = cur.dist;
				fish.row = cur.row;
				fish.col = cur.col;
				break;
			}
			
			for (int d = 0; d < 4; d++) {
				int nr = cur.row + dr[d];
				int nc = cur.col + dc[d];
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= N)
					continue;
				
				if (map[nr][nc] > shark.size)
					continue;
				
				if (visit[nr][nc]) continue;
				
				q.add(new Fish(cur.dist + 1, nr, nc));
				visit[nr][nc] = true;
			}
		}
		
		if (fish.dist == 0) {
			trapped = true;
			return;
		}
		
//		map[shark.row][shark.col] = 0;
		shark.eat++;
		shark.row = fish.row;
		shark.col = fish.col;
		map[fish.row][fish.col] = 0;
		time += fish.dist;
		
		if (shark.eat == shark.size) {
			shark.size++;
			shark.eat = 0;
		}
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		map = new int[N][N]; // 바다 공간
		
		// 바다 공간 정보를 입력받자
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				map[row][col] = sc.nextInt();
				
				// 아기 상어 좌표 저장
				if (map[row][col] == 9) {
					shark = new Shark(2, 0, row, col);
					map[row][col] = 0;
				}
			}
		}
		
		time = 0; // 엄마 상어 호출없이 물고기를 먹은 시간
		trapped = false;
		
		// 엄마 상어 호출 없이 물고기를 먹어보자
		// 먹을 수 있는 물고기가 있는지 확인해보자
		// 먹을 수 있는 물고기가 없다면 엄마 상어를 호출하자 (게임종료)
		while (search()) {
			// 먹을 수 있는 물고기가 한 마리라도 있다면
			// 물고기들의 거리를 확인해서 먹자
			eatFish();
			
			if (trapped) break;
		}
		
		System.out.println(time);
	}
	
	private static class Shark {
		int size, eat, row, col;
		public Shark(int size, int eat, int row, int col) {
			this.size = size;
			this.eat = eat;
			this.row = row;
			this.col = col;
		}
	}
	
	private static class Fish implements Comparable<Fish> {
		int dist, row, col;
		public Fish(int dist, int row, int col) {
			this.dist = dist;
			this.row = row;
			this.col = col;
		}
		@Override
		public int compareTo(Fish o) {
			if (this.dist != o.dist)
				return this.dist - o.dist;
			if (this.row != o.row)
				return this.row - o.row;
			return this.col - o.col;
		}
	}
}
