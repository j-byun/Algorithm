import java.util.Scanner;

/**
 * @author jihye.byun
 * BOJ 14503 로봇 청소기 골드5
 * 
 * 문제
 * 로봇 청소기와 방의 상태가 주어졌을 때, 청소하는 영역의 개수를 구하시오.
 * 방의 크기 N*M의 직사각형, 1*1크기의 정사각형 칸으로 나누어져 있다.
 * 각각의 칸은 벽 또는 빈칸이다.
 * 청소기는 동서남북 중 한 방향을 바라보고 있다.
 * 처음에 빈 칸은 전부 청소되지 않은 상태이다.
 * 로봇 청소기가 작동을 시작한 후 작동을 멈출 때까지 청소하는 칸의 개수를 출력한다.
 * 
 * 로봇 청소기의 작동 원리
 * 1. 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
 * 2. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우,
 * 2-1. 바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진하고 1번 과정으로 돌아간다.
 * 2-2. 바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춘다.
 * 3. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우,
 * 3-1. 반시계 방향으로 90도 회전한다.
 * 3-2. 바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진해서 1번 과정으로 돌아간다.
 * 3-3. 앞쪽 칸이 청소된 칸이면 어쩌라고????????????????????
 * 3-4. 청소된 칸이면 다시 90도 돌까...?
 * 
 * 조건
 * 방의 크기 N과 M (3 <= N, M <= 50)
 * 로봇 청소기가 바라보는 방향 d = 0:북 1:동 2:남 3:서
 * 방의 가장 북쪽, 가장 남쪽, 가장 서쪽, 가장 동쪽 줄 중 하나 이상에 위치한 모든 칸에는 벽이 있다.
 * 로봇 청소기가 있는 칸은 항상 빈 칸이다.
 * 
 * 풀이
 * 1. 그냥 문제에서 주어진 그대로 구현하면 끝
 * 2. 청소한 칸은 2로 바꿔주자!
 */


public class Main {
	static int[] dr = new int[] {-1, 0, 1, 0}; // 북동남서 시계방향 델타배열
	static int[] dc = new int[] {0, 1, 0, -1};
	
	static int rowSize, colSize, vacuumRow, vacuumCol, d;
	static int[][] room;
	
	private static void cleaning() {
		// 로봇청소기 작동
		
		room[vacuumRow][vacuumCol] = 2; // 현재 칸 청소
		
		// 주변 4칸을 확인하자!
		boolean flag = true;
		for (int direction = 0; direction < 4; direction++) {
			int nr = vacuumRow + dr[direction];
			int nc = vacuumCol + dc[direction];
			
			// 방 범위 벗어났으면 다른 방향 확인
			if (nr < 0 || nr >= rowSize || nc < 0 || nc >= colSize)
				continue;
			
			if (room[nr][nc] == 0) { // 주변 4칸 중 청소 안 된 방이 있으면 flag를 false로 만들자
				flag = false;
				break;
			}
		}
		
		if (flag) { // 청소 안 된 방이 없으면
			int nr = vacuumRow + dr[(d + 2) % 4];
			int nc = vacuumCol + dc[(d + 2) % 4];
			
			// 후진 불가능하면 청소를 끝낸다
			if (nr < 0 || nr >= rowSize || nc < 0 || nc >= colSize)
				return;
			if (room[nr][nc] == 1)
				return;
			
			// 후진할 수 있으면 후진해서 다시 주변 4방향 확인
			vacuumRow = nr;
			vacuumCol = nc;
			cleaning();
			
		} else { // 청소 안 된 방이 있으면
			
			while (true) {
				// 반시계 방향으로 90도 회전한다
				d = (d - 1 + 4) % 4;
				
				int nr = vacuumRow + dr[d];
				int nc = vacuumCol + dc[d];
				
				// 전진 불가능하면 다음 방향 확인
				if (nr < 0 || nr >= rowSize || nc < 0 || nc >= colSize)
					continue;
				
				// 청소된 방이거나 벽이면 다음 방향 확인
				if (room[nr][nc] != 0)
					continue;
				
				// 앞쪽 칸이 청소되지 않은 칸이면
				if (room[nr][nc] == 0) {
					vacuumRow = nr;
					vacuumCol = nc;
					cleaning(); // 전진해서 청소하자
					return;
				}
			}
		}
		
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		rowSize = sc.nextInt(); // 방 크기
		colSize = sc.nextInt();
		
		vacuumRow = sc.nextInt(); // 현재 청소기 좌표
		vacuumCol = sc.nextInt();
		d = sc.nextInt(); // 현재 바라보고 있는 방향
		
		room = new int[rowSize][colSize]; // 방
		
		// 방 정보 입력받기
		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				room[row][col] = sc.nextInt();
			}
		}
		
		cleaning(); // 로봇청소기 작동
		
		// 청소가 끝났으면 청소된 칸 (2로 표현된 칸)의 개수를 세어주자
		int cnt = 0;
		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				if (room[row][col] == 2)
					cnt++;
			}
		}
		
		System.out.println(cnt);
	}

}
