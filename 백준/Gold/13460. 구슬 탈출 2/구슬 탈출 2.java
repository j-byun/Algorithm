import java.util.*;

/**
 * @author jihye.byun
 * BOJ 13460 구슬 탈출 2 골드1
 *
 * 문제
 * 빨간 구슬과 파란 구슬을 하나씩 넣고, 빨간 구슬을 구멍을 통해 빼내는 게임
 * 보드의 세로 크기는 N, 가로 크기는 M이고, 1*1크기의 칸으로 나누어져 있다.
 * 가장 바깥 행과 열은 모두 막혀져 있고, 보드에는 구멍이 하나 있다.
 * 빨간 구슬을 구멍을 통해서 빼내자.
 * 이때, 파란 구슬이 구멍에 들어가면 안된다.
 *
 * 보드판은 왼쪽, 오른쪽, 위쪽, 아래쪽으로 기울이기가 가능하다.
 * 각각의 동작에서 공은 동시에 움직인다.
 * 빨간 구슬이 구멍에 빠지면 성공이지만, 파란 구슬이 구멍에 빠지면 실패이다.
 * 빨간 구슬과 파란 구슬이 동시에 구멍에 빠져도 실패이다.
 * 빨간 구슬과 파란 구슬은 동시에 같은 칸에 있을 수 없다.
 * 또, 빨간 구슬과 파란 구슬의 크기는 한 칸을 모두 차지한다.
 * 기울이는 동작을 그만하는 것은 더 이상 구슬이 움직이지 않을 때 까지이다.
 *
 * 보드의 상태가 주어졌을 때, 최소 몇 번 만에 빨간 구슬을 구멍을 통해 빼낼 수 있는지 구하자.
 *
 * 조건
 * 보드의 세로, 가로 크기를 의미하는 두 정수 N, M (3 ≤ N, M ≤ 10)
 * '.'은 빈 칸
 * '#'은 공이 이동할 수 없는 장애물 또는 벽
 * 'O'는 구멍의 위치
 * 'R'은 빨간 구슬의 위치
 * 'B'는 파란 구슬의 위치
 *
 * 입력되는 모든 보드의 가장자리에는 모두 '#'이 있다.
 * 구멍의 개수는 한 개
 * 빨간 구슬과 파란 구슬은 항상 1개
 *
 * 최소 몇 번 만에 빨간 구슬을 구멍을 통해 빼낼 수 있는지 출력한다.
 * 만약 10번 이하로 움직여서 빨간 구슬을 구멍을 통해 빼낼 수 없으면 -1을 출력한다.
 *
 * 풀이
 * 1. bfs로 탐색하며 빨간구슬의 위치, 파란구슬의 위치, 움직인 횟수 저장
 * 2. 빨간구슬의 위치, 파란구슬의 위치 기준으로 방문 체크
 * 3. 움직인 횟수 기준으로 탐색하게 되니 11번 움직이는 경우를 탐색하게 되면 바로 탐색 종료하고 -1 출력
 * 4. 움직인 횟수 기준으로 탐색하게 되니 구슬이 구멍에 들어가게 되면 바로 탐색 종료하고 그 때의 움직인 횟수 출력
 * 5. 빨간 구슬과 파란 구슬이 같은 칸으로 움직이는 경우 좌표값 보정
 */

public class Main {
	
	static class Marble {
		int redRow, redCol, blueRow, blueCol, moveCount;
		public Marble(int redRow, int redCol, int blueRow, int blueCol, int moveCount) {
			this.redRow = redRow;
			this.redCol = redCol;
			this.blueRow = blueRow;
			this.blueCol = blueCol;
			this.moveCount = moveCount;
		}
	}
	
	static int N, M, holeRow, holeCol;
	static char[][] board;
	
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	// 보드판의 크기 입력받아서 보드판 만들기
    	N = sc.nextInt();
    	M = sc.nextInt();
    	board = new char[N][M];
    	
    	// 보드판 정보 입력받기
    	// 빨간구슬, 파란구슬, 구멍의 좌표값 저장
    	int redRow = 0;
    	int redCol = 0;
    	int blueRow = 0;
    	int blueCol = 0;
    	
    	for (int row = 0; row < N; row++) {
    		String line = sc.next();
    		for (int col = 0; col < M; col++) {
    			board[row][col] = '.';
    			
    			switch (line.charAt(col)) {
    			case '#' :
    				board[row][col] = '#';
    				break;
    			case 'R' :
    				redRow = row;
    				redCol = col;
    				break;
    			case 'B' :
    				blueRow = row;
    				blueCol = col;
    				break;
    			case 'O' :
    				holeRow = row;
    				holeCol = col;
    				break;
    			}
    		}
    	}
    	
    	// 초기 보드판 정보 기준으로 보드판을 움직여서 빨간 구슬을 빼낼 수 있는 최소 움직임 횟수 출력
    	System.out.println(moveBoard(new Marble(redRow, redCol, blueRow, blueCol, 0)));
    }
    
    static int moveBoard(Marble start) {
    	// 상하좌우 4방향 탐색용 델타배열
    	int[] dr = new int[] {-1, 0, 1, 0};
    	int[] dc = new int[] {0, 1, 0, -1};
    	
    	// 순차적으로 bfs탐색할 대기열 큐
    	Queue<Marble> queue = new ArrayDeque<>();
    	
    	// 같은 좌표로 돌아가지 않기 위한 방문체크 배열
    	boolean[][][][] visit = new boolean[N][M][N][M];
    	
    	// 초기 보드판 상태에서 시작
    	queue.add(start);
    	// 초기 구슬 위치 방문체크
    	visit[start.redRow][start.redCol][start.blueRow][start.blueCol]= true; 
    	
    	while (!queue.isEmpty()) {
    		Marble cur = queue.poll();
    		
    		// 10번 이하로 움직여서 구슬 탈출 못했으면 -1 출력
    		if (cur.moveCount >= 10)
    			return -1;
    			
    		// 4방향 탐색
    		for (int d = 0; d < 4; d++) {
    			// 벽에 부딪힐 때 까지 두 구슬 굴리기
    			int newRedRow = cur.redRow;
    			int newRedCol = cur.redCol;
    			int newBlueRow = cur.blueRow;
    			int newBlueCol = cur.blueCol;
    			
    			boolean isRedGoal = false;
    			boolean isBlueGoal = false;
    			
    			while (board[newRedRow + dr[d]][newRedCol + dc[d]] != '#') {
    				newRedRow += dr[d];
    				newRedCol += dc[d];
    				
    				if (newRedRow == holeRow && newRedCol == holeCol) {
    					isRedGoal = true;
    					break;
    				}
    			}
    			
    			while (board[newBlueRow + dr[d]][newBlueCol + dc[d]] != '#') {
    				newBlueRow += dr[d];
    				newBlueCol += dc[d];
    				
    				if (newBlueRow == holeRow && newBlueCol == holeCol) {
    					isBlueGoal = true;
    					break;
    				}
    			}
    			
    			// 두 구슬이 구멍에 들어갔는지 확인
    			// 파란구슬이 구멍에 빠지면 이 방향은 무조건 실패
    			if (isBlueGoal)
    				continue;
    			
    			// 빨간구슬만 구멍에 들어갔다면 게임 종료
    			if (isRedGoal)
    				return cur.moveCount + 1;
    			
    			// 두 구슬이 구멍에 들어가지 않고, 같은 좌표로 이동했다면 좌표값 보정
    			if (newRedRow == newBlueRow && newRedCol == newBlueCol) {
    				// 파란구슬이 더 뒤에 있었으면 파란구슬을 한 칸 뒤로 보내고, 아니라면 빨간구슬을 한 칸 뒤로 보내자
    				if ((((cur.redRow - cur.blueRow) ^ dr[d]) >= 0) && (((cur.redCol - cur.blueCol) ^ dc[d]) >= 0)) {
    					newBlueRow -= dr[d];
    					newBlueCol -= dc[d];
    				} else {
    					newRedRow -= dr[d];
    					newRedCol -= dc[d];
    				}
    			}
    			
    			// 이미 방문했던 칸이면 재방문하지 않음
    			if (visit[newRedRow][newRedCol][newBlueRow][newBlueCol])
    				continue;
    			
    			// 방문하지 않았던 칸이라면 새로운 좌표를 탐색하기 위해 대기열 큐에 넣고 방문체크
    			queue.add(new Marble(newRedRow, newRedCol, newBlueRow, newBlueCol, cur.moveCount + 1));
    			visit[newRedRow][newRedCol][newBlueRow][newBlueCol] = true;
    		}
    	}
    	
    	return -1;
    }
}
