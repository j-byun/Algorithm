import java.util.ArrayList;
import java.util.Scanner;


/**
 * @author jhye.byun
 * SWEA 2105 디저트 카페 모의 SW 역량테스트 백트래킹
 * 
 * 문제
 * 한 변의 길이가 N인 정사각형 모양의 지역에 디저트 카페가 모여 있다.
 * 각 칸에는 해당 칸의 카페에서 파는 디저트의 번호가 들어 있다.
 * 카페 사이의 이동은 대각선 방향으로만 가능하다.
 * 디저트 카페 투어 : 한 카페에서 출발해서 대각선 방향으로 움직이고,
 * 				 사각형 모양을 그리며 출발한 카페로 돌아와야 한다.
 * 투어는 지도 범위 안에서만 가능하다.
 * 같은 디저트 번호가 쓰여진 카페에는 다시 방문할 수 없다.
 * 한 카페만 방문하고 투어를 끝낼 수는 없다.
 * 디저트 투어 조건을 만족하는 경우 중 디저트를 가장 많이 먹을 수 있는 경로를 찾고, 그 떄의 디저트 수를 출력하자.
 * 디저트를 먹을 수 없는 경우 -1을 출력하자.
 * 
 * 조건
 * 지역의 한 변의 길이 N은 4 이상 20 이하의 정수이다. (4 ≤ N ≤ 20)
 * 디저트 종류를 나타나는 수는 1 이상 100 이하의 정수이다.
 * 
 * 풀이
 * 1. 디저트를 먹을 수 없는 경우 -1을  출력해야 하니, 최대 디저트 수의 초기값은 -1로 선언하자.
 * 2. 네 방향에 대한 델타 배열을 만들자.
 * 2-1. 순서는 우하1,1 > 좌하1,-1 > 좌상-1,-1 > 우상-1,1
 * 2-2. 델타 배열을 순서대로 한 바퀴 돌아야 하니, 처음 출발점은 col = 1 일 때 부터 col = size -2 일 때 까지로 하자.
 * 3. 각 칸에서 델타 배열 진행 방향을 유지하는 다음 칸을 찾는 경우와, 델타 배열 인덱스를 +1 해서 다음 칸을 찾는 경우 두 가지 재귀를 호출하자.
 * 3-1. 델타 배열 인덱스를 +1 한 값이 4 미만인 경우에만 +1하게 조건문으로 막아주자
 * 3-2. 진행할 칸이 이미 방문했던 칸이 아닌 경우에만 진행하자.
 * 3-3. 진행할 칸의 디저트 종류가 이미 먹은 디저트가 아닌 경우에만 진행하자.
 * 4. 재귀 함수 종료 조건 : 처음 시작 칸에 도착했을 때 => 이 칸이 시작이자 끝인 경우가 아니면, 지금까지 먹은 디저트 종류를 저장하고 반환
 * 					   현재 진행 방향이 델타 배열의 3번 인덱스이고, 더 이상 앞으로 전진할 수 없을 때 => 자동 종료
 */

public class Solution {
	
	static int[] dr = new int[] {1, 1, -1, -1}; // 우하 좌하 좌상 우상 순서로 대각선 이동하는 델타배열
	static int[] dc = new int[] {1, -1, -1, 1};
	
	static int size, maxDessert;
	static int[][] map;
	static boolean[][] visit;
	
	private static void tour(int startRow, int startCol, int curRow, int curCol, int depth, int direction, ArrayList<Integer> list) {
		
		// direction이 3일 때
		// 시작지점에 도착했다면 depth-1이 현재 경로에서 먹은 디저트 종류이다
		// 처음 먹은 디저트와 마지막 디저트가 같으니 depth에서 중복된 수 하나를 뺴줘야 함
		if(direction == 3 && startRow == curRow && startCol == curCol) {
			maxDessert = Math.max(maxDessert, depth - 1);
			return;
		}
		
		// 다음 칸으로 갈 수 있는지 확인
		if (isPossible(startRow, startCol, curRow, curCol, direction, list)) {
			// 진행할 방향의 좌표 확인
			int nr = curRow + dr[direction];
			int nc = curCol + dc[direction];
			
			visit[nr][nc] = true; // 진행할 칸의 방문 여부를 true로 만들어주고
			ArrayList<Integer> newList = (ArrayList<Integer>)list.clone(); // 원본 리스트를 건들이지 않기 위해 복사된 리스트를 만들자
			newList.add(map[nr][nc]); // 해당 칸의 디저트를 먹고
			tour(startRow, startCol, nr, nc, depth + 1, direction, newList); // 그 다음 칸을 탐색하러 가보자
			visit[nr][nc] = false; // 탐색이 끝났으면 방문 여부를 다시 false로 돌려주자
		}
		
		// 방향을 전환해서 다음 칸으로 갈 수 있는지 확인
		if (direction < 3 && isPossible(startRow, startCol, curRow, curCol, direction + 1, list)) {
			// 진행할 방향의 좌표 확인
			int nr = curRow + dr[direction + 1];
			int nc = curCol + dc[direction + 1];
			
			visit[nr][nc] = true; // 진행할 칸의 방문 여부를 true로 만들어주고
			ArrayList<Integer> newList = (ArrayList<Integer>)list.clone(); // 원본 리스트를 건들이지 않기 위해 복사된 리스트를 만들자
			newList.add(map[nr][nc]); // 해당 칸의 디저트를 먹고
			tour(startRow, startCol, nr, nc, depth + 1, direction + 1, newList); // 그 다음 칸을 탐색하러 가보자
			visit[nr][nc] = false; // 탐색이 끝났으면 방문 여부를 다시 false로 돌려주자
		}
		
	}
	
	private static boolean isPossible(int startRow, int startCol, int curRow, int curCol, int direction, ArrayList<Integer> list) {
		 
		// 진행할 방향의 좌표 확인
		int nr = curRow + dr[direction];
		int nc = curCol + dc[direction];
		
		// 맵의 범위를 벗어나면 진행 불가
		if (nr < 0 || nr >= size || nc < 0 || nc >= size) return false;
		
		// 이미 방문했던 칸이면 진행 불가
		if (visit[nr][nc]) return false;
		
		// direction이 3이고 다음 칸이 시작칸이 아닐 때, 다음 칸의 디저트 종류가 이미 먹은 디저트면 진행 불가
		if (!(direction == 3 && startRow == nr && startCol == nc) && list.contains(map[nr][nc]))
			return false;
		
		// 위의 조건들에 걸리지 않으면 진행하려는 방향으로 진행 가능
		return true;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int testCase = sc.nextInt(); // 테스트케이스 개수 입력받기
		
		for (int tc = 1; tc <= testCase; tc++) { // 테스트케이스 개수만큼 반복
			sb.append("#").append(tc).append(" ");
			
			size = sc.nextInt(); // 지도의 한 변의 크기
			map = new int[size][size]; // 디저트 카페가 있는 지역
			visit = new boolean[size][size]; // 해당 카페의 방문 여부를 나타낼 boolean 배열
			
			// 디저트 카페 정보 입력받기
			for (int row = 0; row < size; row++) {
				for (int col = 0; col < size; col++) {
					map[row][col] = sc.nextInt();
				}
			}
			
			maxDessert = -1; // 최대로 먹을 수 있는 디저트 종류의 초기값을 -1로 설정
			
			// 처음 시작 지점 설정
			// row < size - 2 이고 col이 1~size-2 일때 까지
			// 처음 시작점은 다시 돌아와야 하니 visit을 true로 만들지 말자
			for (int row = 0; row < size - 2; row++) {
				for (int col = 1; col < size - 1; col++) {
					ArrayList<Integer> ate = new ArrayList<>();
					ate.add(map[row][col]); // 현 위치의 디저트를 먹자
					tour(row, col, row, col, 1, 0, ate); // 다음 카페를 찾아보자
				}
			}
			
			// 백트래킹을 통해 각 사람을 사용할지 말지 여부를 다 확인했으니,
			// 사람탑의 높이가 선반 높이 이상인 경우 중 최솟값과 선반 높이의 차를 출력하자
			sb.append(maxDessert).append("\n");
		}
		
		System.out.println(sb); // 출력
	}

}