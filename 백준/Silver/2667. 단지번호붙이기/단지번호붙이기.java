import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author jihye.byun
 * BOJ 2667 단지번호붙이기 실버1 그래프
 * 
 * 문제
 * 1은 집이 있는 곳을, 0은 집이 없는 곳을 나타내는 정사각형 지도
 * 상하좌우로 인접한 집의 모임을 단지라고 정의하자.
 * 지도를 입력하여 단지 수를 출력하고, 각 단지에 속하는 집의 수를 오름차순으로 정렬하여 출력하시오.
 * 
 * 조건
 * 지도의 크기 N(정사각형이므로 가로와 세로의 크기는 같으며 5≤N≤25)
 * 
 * 풀이
 * 1. 지도를 탐색하며 1을 발견하면 너비 우선 탐색을 실행하여 한 단지를 찾아내자.
 * 1-1. 단지의 최대 개수는 알 수 없으니 단지를 모두 찾아낸 후 각 단지의 크기를 다시 확인하자.
 * 2. 단지 번호는 static으로 2번부터 시작하게 지정하고, 한 단지에 속하는 집은 모두 해당 단지 번호로 업데이트 하자.
 * 2-1. 단지 번호를 1번부터 시작하면 이미 입력된 집과 구분할 수 없기 떄문이다.
 * 3. 지도의 모든 집 탐색 후 static 단지 번호에서 1을 뺀 숫자가 총 단지 수 이다.
 * 4. 편의를 위해 static 단지 번호 + 1 크기의 배열을 만들어서 2단지부터 각 단지에 속하는 집의 수를 세어주자.
 * 5. 지도의 모든 집을 확인한 후 단지 배열을 sort해서 2번 인덱스부터 출력하자.
 * 5-1. 단지 번호와 배열의 인덱스를 동일하게 사용하기 위해 0번과 1번 인덱스는 비어있기 때문이다.
 */

public class Main {
	
	static int[] dr = new int[] {-1, 0, 1, 0}; // 상우하좌 델타 배열
	static int[] dc = new int[] {0, 1, 0, -1};
	
	static int size;
	static int map[][];
	
	static int apart = 1; // 아파트 단지 번호를 나타낼 변수
	
	public static void bfs(int row, int col) {
		// 현재 입력받은 집 기준으로 상하좌우 인접한 집들을 찾아서 같은 단지로 번호 붙이자
		
		Queue<Node> queue = new ArrayDeque<Node>(); // 인접한 집들을 저장해둘 큐 공간
		// 큐를 구현할 때 ArrayDeque으로 구현하는 것이 LinkedList로 구현하는 것 보다 빠르다.
		// (Java API document에 의함)
		// 원인 1. ArrayDeque은 Array에 의해 지원되며, Array는 LinkedList보다 cache-locality에 더 친숙하다.
		// 원인 2. ArrayDeque은 다음 노드에 대한 추가 참조를 유지할 필요가 없어서 LinkedList보다 메모리 효율적이다.
		// 정리. 큐는 중간에 삽입되거나 삭제되지 않기 때문에 리스트를 활용할 필요가 없다.
		
		map[row][col] = apart; // 현재 집에 새 단지 번호를 붙이자
		queue.offer(new Node(row, col)); // 현재 집과 인접한 집들을 찾기 위해 현재 집의 위치를 큐에 저장하자
		
		while (!queue.isEmpty()) { // 인접한 모든 집을 확인할 때 까지 반복 진행
			Node cur = queue.poll(); // 큐의 제일 앞 집을 꺼내서 확인하자
			
			int curRow = cur.row; // 꺼낸 집의 행, 열 좌표를 확인하자
			int curCol = cur.col;
			
			for (int direction = 0; direction < 4; direction++) {
				// 상하좌우 방향에 인접한 집이 있는지 확인하자
				int nr = curRow + dr[direction];
				int nc = curCol + dc[direction];
				
				// 인접한 칸의 좌표가 지도 범위 밖이면 다른 방향 확인하기
				if (nr < 0 || nr >= size || nc < 0 || nc >= size)
					continue;
				// 인접한 칸에 집이 없거나 이미 단지에 속해있다면 다른 방향 확인하기
				if (map[nr][nc] != 1)
					continue;
				
				// 인접한 칸에 아직 단지에 속하지 않은 집이 있다면...
				map[nr][nc] = apart; // 해당 집에 현재 단지 번호를 붙여주고
				queue.offer(new Node(nr, nc)); // 그 집과 인접한 다른 집을 찾아보기 위해 큐에 저장하자
			}
		}
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		size = sc.nextInt(); // 지도의 크기 N 입력받기
		map = new int[size][size]; // 집 정보를 저장할 정사각형 지도 배열 공간
		
		for (int row = 0; row < size; row++) { // 집 정보 입력받기
			String line = sc.next(); // 문자열 한 줄로 읽어와서
			
			for (int col = 0; col < size; col++) {
				map[row][col] = line.charAt(col) - '0'; // 한 문자씩 끊어서 숫자로 변환하여 입력받기
			}
		}
		
		for (int row = 0; row < size; row++) { // 지도의 모든 구역을 확인하며
			for (int col = 0; col < size; col++) {
				if (map[row][col] == 1) { // 단지에 포함되지 않은 집을 발견하면
					apart++; // 새 단지를 만들어야 하니 단지 번호를 1만큼 증가시키고
					bfs(row, col); // 너비 우선 탐색을 진행하여 인접한 집들을 찾아보자
				}
			}
		}
		
		int[] apartCnt = new int[apart + 1]; // 각 단지에 해당하는 집 수를 저장할 배열 공간
		
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				int cur = map[row][col]; // 현재 칸의 집 정보를 확인하자
				
				if (cur == 0) continue; // 집이 없는 칸이면 다음 칸 확인하기
				
				apartCnt[cur]++; // 집이 있는 칸이면 해당 단지의 집 수를 1만큼 증가시키자
			}
		}
		
		Arrays.sort(apartCnt); // 각 단지의 집 수를 오름차순으로 정렬하자
		
		System.out.println(apart - 1); // 총 단지 수 출력 (단지 번호를 2번부터 사용했으니 1을 빼고 출력하자)
		
		// 단지 번호를 배열의 인덱스로 활용하기 위해 배열의 0번과 1번 인덱스는 비어뒀었으니
		// 2번 인덱스부터 마지막 인덱스까지의 값을 출력하자
		// 0, 1번은 어차피 0이기 때문에 오름차순 정렬했어도 두 칸의 값은 0이다.
		for (int idx = 2; idx <= apart; idx++) {
			System.out.println(apartCnt[idx]); // 각 단지내 집의 수 출력
		}
		
	}
	
	public static class Node { // 행, 열 좌표값을 큐에 저장할 때 사용할 Node 클래스
		int row, col;

		public Node(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
}

