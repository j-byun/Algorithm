import java.util.Arrays;
import java.util.Scanner;

// 14500 테트로미노
// 골드4

// 문제
// 정사각형 4개를 이어붙인 폴리오미노 : 테트로미노
// 정사각형의 변끼리 연결되어 있어야 한다

// 각 칸에 정수가 쓰여져 있는 n*m크기 종이 위에 테트로미노 하나를 놓을 때,
// 테트로미노가 놓인 칸에 쓰여 있는 수들의 합을 최대로 하는 프로그램을 작성하시오

// 조건
// 종이의 세로 크기 N과 가로 크기 M (4 ≤ N, M ≤ 500)
// 각 칸에 입력된 수는 1,000을 넘지 않는 자연수
// 		=> 500*500*1000 = int범위 내에서 해결 가능

// 풀이
// 완전탐색 -> 백트래킹
// n*m 종이의 모든 칸에서 시작해서,
// 상하좌우 중 한 방향 고르기를 3번 반복하자
// 첫 칸 + 백트래킹으로 고른 3칸의 합이 최대인 경우를 저장하자

public class Main {

	static int[] dr = new int[] { -1, 0, 1, 0 };
	static int[] dc = new int[] { 0, 1, 0, -1 };

	static int rowSize, colSize, maxSum;
	static int[][] paper;
	static boolean[][] visited;
	static int[] tetroRow = new int[4];
	static int[] tetroCol = new int[4];

	public static void dfs(int depth, int row, int col) {

		if (depth == 4) { // 테트로미노 놓을 4개의 칸을 다 뽑았으면
			int sum = paper[tetroRow[0]][tetroCol[0]] + paper[tetroRow[1]][tetroCol[1]]
					+ paper[tetroRow[2]][tetroCol[2]] + paper[tetroRow[3]][tetroCol[3]];
			// 4칸에 적힌 숫자들의 합을 구하고
			maxSum = Math.max(maxSum, sum); // 지금까지의 합과 비교하여 더 큰 값을 저장한다
			return; // 다른 가지 확인하러 돌아가기
		}

		for (int idx = 0; idx < 4; idx++) { // 4방향에 대해 확인
			int nr = row + dr[idx];
			int nc = col + dc[idx];

			if (nr < 0 || nr >= rowSize || nc < 0 || nc >= colSize)
				continue; // 진행할 방향이 paper 범위를 벗어나면 다른 방향 확인하기
			if (visited[row + dr[idx]][col + dc[idx]])
				continue; // 진행할 방향에 테트로미노가 이미 놓여있으면 다른 방향 확인하기

			tetroRow[depth] = nr; // 진행할 방향의 좌표를 저장하고
			tetroCol[depth] = nc;
			visited[nr][nc] = true; // 해당 칸에 테트로미노가 놓였다고 표시하기
			dfs(depth + 1, nr, nc); // 해당 칸에서 다시 상하좌우 방향에 테트로미노 놓으러 가기
			
			if (depth == 2) { // ㅗ모양을 만들기 위해 깊이가 2일 때 깊이 1일때의 row, col으로 다시 dfs실행
				dfs(depth + 1, row, col);
			}
			
			visited[nr][nc] = false; // 확인이 끝났으면 현재 칸에서 테트로미노 없애기
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		rowSize = sc.nextInt(); // maxRow
		colSize = sc.nextInt(); // maxCol

		paper = new int[rowSize][colSize]; // n*m 크기의 종이
		visited = new boolean[rowSize][colSize]; // 해당 칸에 테트로미노가 이미 놓여있는지 확인할 boolean배열

		for (int row = 0; row < rowSize; row++) { // paper 정보 입력받기
			for (int col = 0; col < colSize; col++) {
				paper[row][col] = sc.nextInt();
			}
		}

		maxSum = Integer.MIN_VALUE; // maxSum으로 최소값으로 가정

		for (int row = 0; row < rowSize; row++) { // 완전탐색 : 모든 칸에 대해 다 확인해보기
			for (int col = 0; col < colSize; col++) {
				tetroRow[0] = row; // 현재 칸의 좌표값을 저장해두고
				tetroCol[0] = col;
				visited[row][col] = true; // 현재 칸에 테트로미노를 놨다고 가정
				dfs(1, row, col); // 모든 방향으로 테트로미노 만들어보기
				visited[row][col] = false; // 탐색 끝났으면 현재 칸의 테트로미노 없애기
			}
		}
		
		System.out.println(maxSum);

	}

}
