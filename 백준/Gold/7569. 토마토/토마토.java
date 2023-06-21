import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author jihye.byun
 * BOJ 7569 토마토 골드5
 * 
 * 문제
 * 익은 토마토의 위, 아래, 왼, 오, 앞, 뒤 토마토를 익히자.
 * 며칠이 지나면 토마토들이 모두 익는지, 그 최소 일수를 구하자.
 * 
 * 조건
 * 상자의 일부 칸에는 토마토가 들어있지 않을 수도 있다.
 * 가로 M, 세로 N, 높이 H
 * 2 ≤ M ≤ 100, 2 ≤ N ≤ 100, 1 ≤ H ≤ 100
 * 1:익은 토마토, 0:익지 않은 토마토, -1:토마토가 들어있지 않은 칸
 * 저장될 때부터 모든 토마토가 익어있는 상태 => 0출력
 * 토마토가 모두 익지는 못하는 상황 => -1 출력
 * 
 * 풀이
 * 1. tomato[H][N][M] 3차원 배열에 토마토의 익은 여부와 익은 날짜 정보를 저장하자.
 * 2. 초기 상태가 익어있는 토마토를 찾아 큐에 담고, 6방향에 대해 너비 우선 탐색을 진행하자.
 * 3. 큐가 비면, 토마토 배열을 돌며 익은 날짜의 최대값을 찾고(초기값 0),
 * 3-1. 만일 아직 익지 않은 토마토(0)가 남아 있다면 배열 탐색을 중단하고 -1을 출력하자.
 */

public class Main {
	
	static int M, N, H;
	static Tomato[][][] tomato;
	
	private static int bfs() {
		// 위, 아래, 왼쪽, 오른쪽, 앞, 뒤
		int[] dm = new int[] {0, 0, -1, 1, 0, 0};
		int[] dn = new int[] {0, 0, 0, 0, -1, 1};
		int[] dh = new int[] {1, -1, 0, 0, 0, 0};
		
		Queue<Tomato> q = new ArrayDeque<>();
		
		// 초기 익은 토마토 큐에 담기
		for (int h = 0; h < H; h++) {
			for (int n = 0; n < N; n++) {
				for (int m = 0; m < M; m++) {
					if (tomato[h][n][m].status == 1)
						q.add(new Tomato(h, n, m, 0));
				}
			}
		}
		
		// 옆에 있는 토마토 익히기
		while (!q.isEmpty()) {
			Tomato cur = q.poll();
			
			for (int d = 0; d < 6; d++) {
				int nm = cur.m + dm[d];
				int nn = cur.n + dn[d];
				int nh = cur.h + dh[d];
				
				if (nm < 0 || nm >= M || nn < 0 || nn >= N || nh < 0 || nh >= H) continue;
				
				Tomato next = tomato[nh][nn][nm];
				if (next.status != 0) continue;
				
				// 인접 토마토가 안익은 토마토면 익히고 큐에 넣기
				next.status = 1;
				next.day = cur.day + 1;
				q.add(new Tomato(nh, nn, nm, next.day));
			}
		}
		
		// 인접 토마토를 다 익혔으면 마지막 익은 날짜 찾기
		int lastDay = 0;
		
		for (int h = 0; h < H; h++) {
			for (int n = 0; n < N; n++) {
				for (int m = 0; m < M; m++) {
					Tomato cur = tomato[h][n][m];
					
					if (cur.status == 0)
						return -1;
					
					lastDay = Math.max(lastDay, cur.day);
				}
			}
		}
		
		return lastDay;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		M = sc.nextInt();
		N = sc.nextInt();
		H = sc.nextInt();
		
		tomato = new Tomato[H][N][M];
		
		for (int h = 0; h < H; h++) {
			for (int n = 0; n < N; n++) {
				for (int m = 0; m < M; m++) {
					tomato[h][n][m] = new Tomato(sc.nextInt(), 0);
				}
			}
		}
		
		System.out.println(bfs());
	}
	
	// 3차원 배열에 저장할 토마토 정보를 나타내는 클래스
	private static class Tomato {
		int status, day, m, n, h;

		public Tomato(int status, int day) {
			this.status = status;
			this.day = day;
		}
		
		public Tomato(int h, int n, int m, int day) {
			this.h = h;
			this.n = n;
			this.m = m;
			this.day = day;
		}
	}
}
