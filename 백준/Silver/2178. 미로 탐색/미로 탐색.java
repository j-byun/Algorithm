import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 그래프
// 2178 미로탐색
// 실버1

// 문제
// N*M크기 배열로 표현되는 미로
// 1 : 이동할 수 있는 칸
// 0 : 이동할 수 없는 칸
// (1, 1)에서 출발하여 (N, M)의 위치로 이동할 때
// 지나야 하는 최소의 칸 수를 구하는 프로그램을 작성하시오

// 조건
// 한 칸에서 다른 칸으로 이동할 때, 서로 인접한 칸으로만 이동할 수 있다
// 칸을 셀 때에는 시작 위치와 도착 위치도 포함한다
// N, M(2 ≤ N, M ≤ 100)

// 풀이
// dfs는 많이 써봤으니 bfs로 탐색해보자
// 상하좌우 4방향 델타배열 만들어서
// 확인할 방향의 좌표가 미로 범위 이내이고, 아직 대기열에 들어가지 않은 칸이고, 이동할 수 있는 칸이라면
// 확인할 대기열에 넣어주자
// 거리 우선 탐색이기 때문에 가장 먼저 발견된 N,M까지 도달했을 때의 지나온 칸 수가 바로 최소 칸 수가 된다
// => dfs탐색의 경우에는 N,M에 도착했을 때 지나온 칸 수 중 최솟값을 확인해야한다

public class Main {
	static int[] dr = new int[] {-1, 0, 1, 0}; // 상우하좌 시계방향 순서
	static int[] dc = new int[] {0, 1, 0, -1};
	
	static boolean[][] map, isVisited;
	static int rowSize, colSize;
	
	public static int bfs() { // 너비 우선 탐색
		
		// 대기열로 활용할 큐 공간 만들기
		// 확인할 칸의 행,열 좌표와 이동 거리를 한 번에 저장하기 위해 Node정보를 저장할 큐로 만들자
		Queue<Node> search = new LinkedList<Node>();
		// 시작점 1,1의 좌표와 1,1칸에서의 최소 이동 거리 1을 대기열에 넣어주자
		search.offer(new Node(1, 1, 1));
		isVisited[1][1] = true; // 대기열에 들어간 칸의 방문여부를 true로 바꿔주자
		
		while (!search.isEmpty()) { // 대기열이 빌 때 까지 반복 탐색
			Node cur = search.poll(); // 대기열의 가장 앞에 있는 노드를 꺼내오자
			
			// 대기 순서 1번이었던 노드의 행,열 좌표값과 이동 거리를 꺼내오자
			int row = cur.row;
			int col = cur.col;
			int pass = cur.pass;
			
			if (row == rowSize && col == colSize) // 현재 확인한 칸이 미로의 끝이라면
				return pass; // 지금까지 움직인 거리를 반환하고 탐색 종료
			
			// 현재 확인한 칸이 미로의 끝이 아니라면...
			// 상하좌우 4방향에 대해 확인해보자
			for (int direction = 0; direction < 4; direction++) {
				int nr = row + dr[direction];
				int nc = col + dc[direction];
				
				// 확인할 방향이 미로 범위 밖이면 확인 불가
				if (nr < 1 || nr > rowSize || nc < 1 || nc > colSize) continue;
				// 확인할 칸이 이미 지나온 칸이면 확인 불가
				if (isVisited[nr][nc]) continue;
				// 확인할 칸이 이동할 수 없는 칸이면 (false면) 확인 불가
				if (!map[nr][nc]) continue;
				
				// 확인할 칸이 위의 조건을 모두 불만족한다면 대기열에 넣어주자
				// 확인할 칸의 행,열 좌표와 지금까지 지나온 이동거리에+1 해서 넣어주자
				search.offer(new Node(nr, nc, pass + 1));
				isVisited[nr][nc] = true; // 대기열에 넣어준 칸의 방문 여부 true로 바꾸기
			}
			
		}
		// 어차피 while문 안에서 N,M에 도착했을 때의 최소 이동 거리를 반환해 주기 때문에
		// while문은 항상 강제 종료 (return pass에 의해) 된다
		
		return Integer.MIN_VALUE; // 반환형 필수
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		rowSize = sc.nextInt(); // N입력받기
		colSize = sc.nextInt(); // M입력받기
		
		// 미로의 칸 정보를 저장할 map배열 만들기
		// 미로의 각 칸은 0과 1로만 표현되기 때문에
		// boolean배열로 만들어서 미로 탐색 시 편하게 사용하자
		// => true, false로 표현되면 if문 확인 시 편하다
		// N,M좌표 번호를 그대로 사용하기 위해
		// N+1*M+1크기로 만들자
		map = new boolean[rowSize + 1][colSize + 1];
		
		// 미로의 각 칸의 방문 여부를 저장할 isVisited 배열 만들기
		isVisited = new boolean[rowSize + 1][colSize + 1];
		
		// 미로 정보 입력받기
		for (int row = 1; row <= rowSize; row++) {
			// 한 행의 정보는 붙어서 입력되니 줄 단위로 입력받아서
			// 다음 col for문에서 한 문자 씩 끊어서 map에 저장하자
			String line = sc.next();
			
			for (int col = 1; col <= colSize; col++) {
				// col 인덱스가 1부터 시작되니 읽어온 문자열에서 문자를 분리할 때 인덱스-1 해서 분리하자
				// 어차피 int형 배열이 아니기 때문에 integer로 변환할 필요가 없다
				char cur = line.charAt(col - 1);
				
				if (cur == '1') // 현재 칸이 이동 가능한 칸이면
					map[row][col] = true; // 해당 칸을 true로 표시하자
				// 현재 칸이 이동 불가능한 칸이라면 false로 표시할건데...
				// 이미 boolean 배열의 초기상태가 false이기 때문에
				// if문으로 고려해서 상태를 바꿔줄 필요가 없다 
			}
		}
		
		// 너비 우선 탐색 실행
		// 탐색 결과로 확인한 최소 이동 거리를 minPass로 받아주자
		int minPass = bfs();
		
		System.out.println(minPass); // 최소 이동 거리 출력
	}
	
	public static class Node {
		// 대기열에 행,열좌표값과 현재까지의 이동거리를 한 번에 저장하기 위해 Node 클래스를 만들어서 활용하자
		
		int row; // 행 번호
		int col; // 열 번호
		int pass; // 현재까지 이동거리
		
		// 파라미터 생성자 만들기
		public Node(int row, int col, int pass) {
			this.row = row;
			this.col = col;
			this.pass = pass;
		}
		
	}
	
}

