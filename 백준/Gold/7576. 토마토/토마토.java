import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author jihye.byun 
 * BOJ 7576 토마토 골드5 그래프
 * 
 * 문제 
 * 격자 모양 상자의 칸에 토마토를 하나씩 넣어서 보관한다. 
 * 보관 후 하루가 지나면, 익은 토마토의 상하좌우 인접한 곳에 있는 익지 않은 토마토들은 익게 된다.
 * 익은 토마토 없이 토마토가 혼자 저절로 익는 경우는 없다고 가정한다. 
 * 창고에 보관된 토마토들이 며칠이 지나면 다 익게 되는지 최소 일수를 구하자.
 * 단, 상자의 일부 칸에는 토마토가 들어있지 않을 수도 있다.
 * 
 * 조건
 * M은 상자의 가로 칸의 수 (col)
 * N은 상자의 세로 칸의 수 (row)
 * 2 ≤ M,N ≤ 1,000
 * 
 * 1 : 익은 토마토
 * 0 : 익지 않은 토마토
 * -1 : 토마토가 들어있지 않은 칸
 * 
 * 초기 토마토가 모두 익어있는 상태이면 0 출력
 * 토마토가 모두 익지 못하는 상황이면 -1 출력
 * 
 * 풀이
 * 1. 날짜를 하루 씩 증가시키며 상자에 저장된 토마토들 중 익지 않은 토마토가 없는지 확인하자.
 * 2. 최소 일수의 범위를 알 수 없으니 while문으로 반복 실행하자.
 * 3. while문 안에서 상자의 모든 칸을 확인하고, 1인 칸이 발견되면 큐에 전부 집어넣자.
 * 4. 한 번의 반복문 안에서 1인 칸을 모두 발견했으면 해당 칸들에 대해 인접 칸의 익지 않은 토마토들을 익히자.
 * 4-1. 바로 인접한 칸의 토마토만 익혀야 하니 새롭게 익힌 토마토들은 큐에 넣으면 안된다.
 * 4-2. 상하좌우 델타배열을 이용해서 탐색하자.
 * 5. 다음 반복문이 시작되면 일단 익지 않은 토마토가 있는 지 확인하고, 하나도 없으면 while문을 탈출하자.
 * => 시간초과!
 * 
 * 코드 실행시간을 줄이기 위한 새로운 풀이 방법
 * 2-1. while문 대신 익은 토마토를 큐에 저장할 때 해당 토마토가 며칠 째에 익은 상태인지를 같이 저장하자.
 * 4-1. bfs를 모든 익은 토마토에 대해 실행하고, 마지막으로 확인한 토마토가 익은 날짜를 출력하자
 * 4-2. 마지막으로 확인한 토마토가 가장 마지막 날에 익은 토마토여야 바로 해당 날짜를 출력할 수 있으니 dfs보다 bfs가 편하다.
 * 4-3. dfs로 익힐 경우 익힌 날짜 중 최대값을 따로 확인해야 한다.
 * 6. 놓친 조건 : 토마토가 모두 익지는 못하는 상황이면 -1 출력을 고려하자.
 * 6-1. bfs탐색 후 박스에 남아있는 안 익은 토마토가 있다면 -1을 출력하자.
 */

public class Main {

	static int[] dr = new int[] { -1, 0, 1, 0 }; // 상우하좌 델타배열
	static int[] dc = new int[] { 0, 1, 0, -1 };

	static int rowSize, colSize;
	static int[][] box;
	static Queue<Node> queue;

	public static int bfs() {
		// 익은 토마토와 인접한 안 익은 토마토를 익혀주는 메서드
		
		int day = 0; // 마지막 토마토가 익은 날짜 저장
		// 큐가 비어있다면 (익은 토마토가 처음부터 없었다면) 0일이 반환된다
		
		while (!queue.isEmpty()) { // 저장한 익은 토마토를 다 확인할 때 까지 반복 실행
			Node cur = queue.poll(); // 익은 토마토 하나를 꺼내서 확인하자

			int curRow = cur.row; // 익은 토마토의 좌표값 확인
			int curCol = cur.col;
			int curDay = cur.day; // 해당 토마토를 익힌 날짜 확인
			day = curDay;
			// while문이 종료됐을 때 마지막 토마토가 익은 날짜가 day에 저장되어 있어야 하니,
			// 한 토마토가 익은 날짜를 확인할 때 마다 day를 업데이트 시켜주자

			// 익은 토마토의 상하좌우 칸을 확인하자
			for (int direction = 0; direction < 4; direction++) {
				int nr = curRow + dr[direction];
				int nc = curCol + dc[direction];

				// 인접 칸이 박스 범위 밖이면 다른 방향을 확인하자
				if (nr < 0 || nr >= rowSize || nc < 0 || nc >= colSize)
					continue;
				// 인접 칸이 안 익은 토마토가 아니면 다른 방향을 확인하자
				if (box[nr][nc] != 0)
					continue;

				// 인접 칸이 박스 범위 이내고, 안 익은 토마토라면
				// 그 칸의 토마토를 익혀주자
				box[nr][nc] = 1;
				
				// 새로 익힌 토마토는 익힌 날짜를 하루 증가시켜서 큐에 넣어주자
				queue.offer(new Node(nr, nc, curDay + 1));
			}
		}
		
		// 모든 토마토가 익은 상태여서 새롭게 익혀줄 토마토가 없으면 초기값인 0이 출력된다
		return day;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		colSize = sc.nextInt(); // 가로 칸의 수 M 입력받기
		rowSize = sc.nextInt(); // 세로 칸의 수 N 입력받기

		box = new int[rowSize][colSize]; // 토마토를 저장할 박스 배열 공간
		queue = new ArrayDeque<Node>(); // 익은 토마토를 저장할 큐 공간
		
		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				box[row][col] = sc.nextInt(); // 박스에 토마토 저장하기
				
				// 익은 토마토는 인접 토마토를 익혀야 하니 일단 큐에 좌표를 저장해주자
				// 초기 상태는 0일이 지났을 때이니 토마토를 익힌 날짜는 0으로 저장하자
				if (box[row][col] == 1)
					queue.offer(new Node(row, col, 0));
			}
		}
		
		// 초기 상태의 익은 토마토를 큐에 다 저장했다면,
		// 익은 토마토의 상하좌우 인접 칸에 위치한 안 익은 토마토를 익혀주자.
		int result = bfs(); // 너비 우선 탐색으로 토마토를 익힌 날짜 반환
		
		// 너비 우선 탐색으로 인접 토마토를 다 익혀줬는데도 상자에 안 익은 토마토가 남아 있다면...
		// 그 때는 안 익은 토마토 주위에 토마토가 하나도 없는 경우이다.
		// 따라서 상자를 확인하며 안 익은 토마토가 하나라도 남아 있다면 결과값을 -1로 바꿔주자
		outer : for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				if (box[row][col] == 0) {
					result = -1;
					break outer; // 안 익은 토마토가 하나라도 있으면 -1 출력
				}
			}
		}
		
		System.out.println(result); // 결과 출력
	}

	public static class Node { // 상자에 저장된 토마토의 좌표값과 익은 날짜를 나타낼 Node 클래스
		int row, col, day;

		public Node(int row, int col, int day) {
			this.row = row;
			this.col = col;
			this.day = day;
		}
	}

}
