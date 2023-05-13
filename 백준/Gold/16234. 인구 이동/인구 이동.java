import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author jihye.byun
 * BOJ 16234 인구 이동 골드5
 * 
 * 문제
 * N×N크기의 땅의 각 칸에는 나라가 하나씩 존재한다.
 * r행 c열에 있는 나라에는 A[r][c]명이 살고 있다.
 * 
 * <인구 이동 방법> : 인구 이동은 하루 동안 진행
 * 1. 국경선을 공유하는 두 나라의 인구 차이가 L명 이상, R명 이하라면, 두 나라가 공유하는 국경선을 오늘 하루 동안 연다.
 * 2. 위 조건에 의해 열어야하는 국경선이 모두 열렸따면, 인구 이동을 시작한다.
 * 3. 국경선이 열려있어 인접한 칸만을 이용해 이동할 수 있으면, 그 나라를 오늘 하루 동안은 연합이라고 한다.
 * 4. 연합을 이루고 있는 각 칸의 인구수는 (연합의 인구 수)/(연합을 이루고 있는 칸의 개수)가 된다. (소수점 버림)
 * 5. 연합을 해제하고, 모든 국경선을 닫는다.
 * 
 * 각 나라의 인구 수가 주어졌을 때, 인구 이동이 며칠 동안 발생하는지 구하는 프로그램을 구하자.
 * 
 * 조건
 * 1 ≤ N ≤ 50
 * 1 ≤ L ≤ R ≤ 100
 * 0 ≤ A[r][c] ≤ 100
 * 인구 이동이 발생하는 일수가 2,000번 보다 작거나 같은 입력만 주어진다.
 * 
 * 풀이
 * 1. 날짜를 1씩 증가시키며 인구 이동 가능 여부를 확인하자.
 * 2. 인구 이동이 가능하다면 bfs로 연합을 이룰 수 있는 나라들을 찾자.
 * 3. 인구 이동 시 연합 번호, 해당 연합의 총 인구수와 연합 칸의 수를 저장하자.
 */

public class Main {
	
	static int[] dr = new int[] {-1, 0, 1, 0};
	static int[] dc = new int[] {0, 1, 0, -1};
	
	static int N, L, R;
	static int[][] map, union;
	
	private static boolean canMove() {
		// 현재 상황에서 인구 이동이 가능한지 확인하는 메서드
		
		// 모든 나라에 대해 확인
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				// 상하좌우 네 방향에 대해 확인
				for (int d = 0; d < 4; d++) {
					int nr = row + dr[d];
					int nc = col + dc[d];
					
					// 지도 범위 벗어나는거 확인
					if (nr < 0 || nr >= N || nc < 0 || nc >= N)
						continue;
					
					// 확인 방향 나라와의 인구 수 차이 확인
					int dif = Math.abs(map[nr][nc] - map[row][col]);
					// 인구 수 차이가 L 이상 R 이하라면 인구 이동이 가능하니 TRUE 반환
					if (dif >= L &&  dif <= R)
						return true;
				}
			}
		}
		
		// 모든 나라, 모든 방향에 대해 다 확인했는데 인구 이동이 불가하다면 FALSE 반환
		return false;
	}
	
	private static void move() {
		// 인구 이동이 가능한 칸을 찾아 해당 칸에서부터 인접 칸들을 탐색하며 연합을 만들어 나가는 메서드
		
		// 각 칸이 포함된 연합 번호를 저장할 배열 공간을 만들자
		union = new int[N][N];
		
		// 오늘의 연합 개수 초기값은 0으로 초기화 하자
		int unionNum = 0;
		
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				// 이미 연합에 들어간 칸이면 패스
				if (union[row][col] != 0)
					continue;
				
				// 인접 칸 확인
				for (int d = 0; d < 4; d++) {
					int nr = row + dr[d];
					int nc = col + dc[d];
					
					if (nr < 0 || nr >= N || nc < 0 || nc >= N)
						continue;
					
					// 확인 방향 나라와의 인구 수 차이 확인
					int dif = Math.abs(map[nr][nc] - map[row][col]);
					// 인구 수 차이가 L 이상 R 이하라면 인구 이동이 가능하니 bfs 탐색 실행
					if (dif >= L &&  dif <= R) {
						bfs(row, col, ++unionNum);
						break;
					}
				}
			}
		}
	}
	
	private static void bfs(int startRow, int startCol, int unionNum) {
		
		Queue<Coo> q = new ArrayDeque<>();
		q.add(new Coo(startRow, startCol));
		
		int unionCount = 0; // 연합 칸의 개수
		int unionSum = 0; // 연합 칸의 인구 수 합
		
		while (!q.isEmpty()) {
			Coo curr = q.poll();
			int row = curr.row;
			int col = curr.col;
			
			if (union[row][col] != 0)
				continue;
			
			union[row][col] = unionNum; // 현재 칸의 연합 포함 여부를 나타내기 위해 지금 연합 번호 저장
			unionCount++; // 현재 연합 칸의 개수 하나 증가
			unionSum += map[row][col]; // 현재 연합 칸의 인구 수에 지금 칸 인구 수 추가
			
			// 인접 칸 확인
			for (int d = 0; d < 4; d++) {
				int nr = row + dr[d];
				int nc = col + dc[d];
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= N)
					continue;
				
				if (union[nr][nc] != 0)
					continue;
				
				// 확인 방향 나라와의 인구 수 차이 확인
				int dif = Math.abs(map[nr][nc] - map[row][col]);
				// 인구 수 차이가 L 이상 R 이하라면 연합에 추가
				if (dif >= L &&  dif <= R)
					q.add(new Coo(nr, nc));
			}
		}
		
		// 현재 연합 탐색이 끝났다면 이제 인구 이동을 시켜보자
		int people = unionSum / unionCount;
		
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				if (union[row][col] == unionNum)
					map[row][col] = people;
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt(); // 지도 크기
		L = sc.nextInt(); // L명 이상
		R = sc.nextInt(); // R명 이하
		
		map = new int[N][N]; // 각 나라의 인구 수를 저장할 배열 공간
		
		// 각 나라의 인구 수를 입력받자
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				map[row][col] = sc.nextInt();
			}
		}
		
		// 0일부터 시작해서 하루씩 인구 이동을 진행해보자
		int day = 0;
		while (true) {
			
			// 현재 인구 이동이 가능한 상황인지 확인해보고, 불가능한 상황이면 반복문을 탈출하자
			if (!canMove())
				break;
			
			// 인구 이동이 가능한 상황이라면 인구 이동을 진행하자
			move();
			
			// 오늘의 인구 이동이 잘 끝났다면 다음날로 넘어가자
			day++;
		}
		
		// 인구 이동이 종료된 날짜를 출력하자
		System.out.println(day);
	}
	
	private static class Coo {
		int row, col;
		public Coo (int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
}
