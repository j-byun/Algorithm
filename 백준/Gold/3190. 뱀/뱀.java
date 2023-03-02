import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// 3190 뱀
// 골드4

// 문제
// 뱀이 기어다니는 게임
// 사과를 먹으면 뱀 길이가 늘어나고
// 벽이나 자기자신과 부딪히면 게임 끝

// 게임은 n*n 정사각 보드 위에서 진행
// 몇몇 칸에는 사과가 놓여져 있고
// 보드 경계에는 벽이 있다

// 게임 시작 시 뱀은 1, 1에 위치하고 뱀 길이는 1이다
// 뱀은 처음에 오른쪽을 향한다

// 뱀의 이동 규칙
// 1. 뱀은 머리를 늘려 다음 칸에 위치시킨다
// 2. 이동한 칸에 사과가 있다면, 그 칸의 사과는 먹어서 없어지고 꼬리는 그대로 있는다
// 3. 이동한 칸에 사과가 없다면, 몸길이를 줄여서 꼬리가 위치한 칸을 비워준다

// 조건
// 보드의 크기 N (2 ≤ N ≤ 100)
// 사과의 개수 K (0 ≤ K ≤ 100)
// 맨 위 맨 좌측 (1행 1열) 에는 사과가 없다
// 뱀의 방향 변환 횟수 L (1 ≤ L ≤ 100)
// 방향 전환 명령어 L : 왼쪽 90도 회전, R : 오른쪽 90도 회전
// 방향 전환이 일어나는 초 X는 10,000 이하의 양의 정수
// 방향 전환 정보는 X가 증가하는 순으로 주어진다

// 풀이
// n*n board에...
// 초기 위치 1,1에 뱀을 1로 표현
// 사과 위치는 2로 표현
// 뱀이 진행하다가 1을 만나면 게임아웃
// 보드 경계를 만나면 게임아웃
// 2를 만나면 뒤의 꼬리 그대로 유지
// 0을 만나면 머리는 움직여서 1로 만들고 꼬리는 0으로 만들어서 길이 줄이기
// 꼬리~ 머리까지의 좌표 저장하기
// 		큐에 저장하고 사과 만나면 그대로 머리 좌표 넣기,
// 		사과 못만나면 하나 빼서 해당 좌표 0으로 만들고 꼬리 버리기

public class Main {
	static int[] dr = new int[] {0, 1, 0, -1}; // 우 하 좌 상 시계방향
	static int[] dc = new int[] {1, 0, -1, 0};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int size = Integer.parseInt(br.readLine()); // 보드 크기 입력받기
		
		int[][] board = new int[size + 2][size + 2]; // n*n 크기의 보드 만들기
		
		int curRow = 1, curCol = 1; // 뱀의 초기 위치
		
		board[curRow][curCol] = 1; // 초기 위치에 뱀 가져다 놓기
		
		int appleCnt = Integer.parseInt(br.readLine()); // 사과 개수 입력받기
		
		int row, col;
		for (int cnt = 0; cnt < appleCnt; cnt++) { // 사과 개수만큼 보드에 사과 위치시키기
			String[] line = br.readLine().split(" ");
			
			row = Integer.parseInt(line[0]); // 사과 좌표 입력받아서
			col = Integer.parseInt(line[1]);
			
			board[row][col] = 2; // 보드에 사과 놓기
			
		}
		
		int rotateCnt = Integer.parseInt(br.readLine()); // 방향 전환 횟수 입력받기
		
		int[] time = new int[rotateCnt]; // 방향 전환이 일어나는 시간을 저장할 공간
		int[] direction = new int[rotateCnt]; // 방향 전환 방향을 저장할 공간
		
		for (int cnt = 0; cnt < rotateCnt; cnt++) { // 방향 전환 정보 입력받기
			String[] line = br.readLine().split(" ");
			
			int sec = Integer.parseInt(line[0]);
			time[cnt] = sec; // 방향 전환이 일어나는 시간 저장
			
			char dir = line[1].charAt(0);
			
			if (dir == 'L') // 방향 전환 방향이 왼쪽 회전이면 델타배열 인덱스 -1
				direction[cnt] = -1;
			else // 방향 전환 방향이 오른쪽 회전이면 델타배열 인데스 +1
				direction[cnt] = 1;
		}
		
		// 게임 실행
		int sec = 0; // 게임 실행 시간
		int cnt = 0; // 방향 전환이 일어난 횟수
		int delta = 0; // 현재 뱀의 이동 방향
		Queue<Integer> snakeRow = new LinkedList<>(); // 뱀의 길이만큼 좌표 저장할 공간
		Queue<Integer> snakeCol = new LinkedList<>();
		snakeRow.offer(curRow); // 초기 뱀 좌표 큐에 저장
		snakeCol.offer(curCol);
		
		
		while (true) {
			
			if (cnt < rotateCnt && time[cnt] == sec) { // 현재 시간에 방향 전환이 일어나면
				delta = (delta + direction[cnt] + 4) % 4; // 방향 전환시키고
				cnt++; // 방향 전환 횟수 ++
			}
			
			sec++;
			
			curRow += dr[delta]; // 이동할 좌표 정하고
			curCol += dc[delta];
			
			if (curRow <= 0 || curRow > size || curCol <= 0 || curCol > size ) // 벽에 부딪히면
				break; // 게임종료
			
			if (board[curRow][curCol] == 1) // 뱀이 자기자신 몸이랑 부딪히면
				break; // 게임종료
			
			if (board[curRow][curCol] == 2) { // 사과를 만나면
				board[curRow][curCol] = 1; // 사과먹고 뱀 길이 늘리기
				snakeRow.offer(curRow); // 뱀 머리 좌표 큐에 저장
				snakeCol.offer(curCol);
			}
			
			if (board[curRow][curCol] == 0) { // 아무것도 없는 칸이면
				board[curRow][curCol] = 1; // 머리는 늘리고
				snakeRow.offer(curRow); // 뱀 머리 좌표 큐에 저장
				snakeCol.offer(curCol);
				
				int rearRow = snakeRow.poll(); // 뱀 꼬리 좌표 뽑아내고
				int rearCol = snakeCol.poll();
				board[rearRow][rearCol] = 0; // 꼬리 줄이기
			}
			
		}
		
		System.out.println(sec); // 게임 종료 됐을 때의 시간 출력하기
		
	}

}

