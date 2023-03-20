import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author jihye.byun
 * BOJ 15686 치킨 배달 골드5 브루트포스/백트래킹
 * 
 * 문제
 * N*N 크기의 도시
 * 도시의 간 칸은 빈 칸, 치킨집, 집 중 하나이다.
 * 1~r, 1~c까지의 칸 (r+1, c+1) 크기
 * 치킨 거리 : 집과 가장 가까운 치킨집 사이의 거리
 * 도시의 치킨 거리 : 모든 집의 치킨 거리의 합
 * 두 칸 사이의 거리 = |r1 - r2| + |c1 - c2| (절댓값을 취해서 더한다)
 * 도시의 치킨집 중 가장 수익을 많이 낼 수 있는 치킨집의 개수 M개만 남기고 나머지는 폐업시킬 때,
 * 도시의 치킨 거리가 가장 작게 되는 경우를 구하여 그 때의 도시의 치킨 거리 최솟값을 출력하자.
 * 
 * 조건
 * N(2 ≤ N ≤ 50)
 * M(1 ≤ M ≤ 13)
 * 0:빈칸 1:집 2:치킨집
 * 
 * 풀이
 * 1. map을 N+1 * M+1크기로 만들어서 0번 인덱스를 낭비하자.
 * 2. map 정보를 입력받으면서 치킨집의 위치를 저장해놓자.
 * 3. 치킨집 중 M개만 선택하는 조합을 만들어보자.
 * 3-1. false일 때, true일 때 재귀 호출해서 true가 M개인 경우만 치킨 거리를 확인하자.
 * 4. 치킨거리 확인
 * 4-1. map을 돌면서 집(1)이 나오면, 그 자리에서 true로 표시된 모든 치킨집을 확인하자.
 * 4-2. 모든 치킨집과의 거리를 확인해서 최소 거리를 구하고, 도시 치킨거리에 더해주자
 * 5. 한 조합에 대해 도시 치킨거리 확인이 끝나면, 여태까지의 도시 치킨거리와 비교해서 더 작은 값만 살려주자.
 */


public class Main {
	
	static int size, chickenCnt, curChickenCnt, minChickenDis;
	static int[][] map;
	static ArrayList<Coo> chickenHouse;
	static boolean[] used;
	
	private static void comb(int curChicken) {
		
		// 기저조건
		if (curChicken == curChickenCnt) {
			// 모든 치킨집에 대해 살릴 지 말 지 여부를 다 확인했다면
			// 현재 살린 치킨집 개수가 M개인지 확인하자
			
			// 살린 치킨집 개수가 M개가 아니면 치킨거리 확인 할 필요도 없다
			// 반환해버리자
			if(!checkM())
				return;
			
			// 살린 치킨집 개수가 M개이면
			// 도시 치킨거리를 확인해보자
			checkChickenDistance();
			return;
		}
		
		
		// 현재 치킨집을 살릴 때, 없앨 때의 경우를 고려해보고
		// 다음 치킨집을 살릴 지 말 지 확인하러 재귀호출
		comb(curChicken + 1);
		used[curChicken] = true;
		comb(curChicken + 1);
		used[curChicken] = false;
	}
	
	private static boolean checkM() {
		// 현재 살린 치킨집 개수가 M개인지 확인하는 method
		
		int cnt = 0;
		
		for (int idx = 0; idx < curChickenCnt; idx++) {
			if (used[idx])
				cnt++;
		}
		
		if (cnt == chickenCnt) // M개만 true이면
			return true; // true 반환
		return false; // 아니면 false 반환
	}
	
	private static void checkChickenDistance() {
		
		int chickenDis = 0;
		
		for (int row = 1; row <= size; row++) {
			for (int col = 1; col <= size; col++) {
				// 집이 아니면 다음 칸 확인
				if (map[row][col] != 1)
					continue;
				
				// 현재 칸이 집이면...
				// 모든 치킨집 중 가장 가까운 치킨집을 찾아보자
				int curDis = Integer.MAX_VALUE;
				for (int idx = 0; idx < curChickenCnt; idx++) {
					// 폐업하기로 한 치킨집이면 다음 치킨집을 확인하자
					if (!used[idx])
						continue;
					
					// 살리기로 한 치킨집이라면...
					Coo cur = chickenHouse.get(idx);
					// 치킨 거리를 확인해서 최소 치킨거리로 업데이트 해주자
					curDis = Math.min(curDis, Math.abs(cur.row - row) + Math.abs(cur.col - col));
				}
				// 최소 치킨 거리를 확인했다면
				// 현재의 도시 치킨거리에 더해주자
				chickenDis += curDis;
			}
		}
		
		// 현재 조합에서의 도시 치킨 거리를 구했다면
		// 여태까지의 최소 도시 치킨 거리와 비교해서 최솟값을 살려주자 
		minChickenDis = Math.min(minChickenDis, chickenDis);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		size = sc.nextInt();
		chickenCnt = sc.nextInt();
		
		map = new int[size + 1][size + 1]; // 도시 지도
		chickenHouse = new ArrayList<>(); // 치킨집의 좌표를 저장할 ArrayList
		
		// 도시 지도 입력받기
		for (int row = 1; row <= size; row++) {
			for (int col = 1; col <= size; col++) {
				int cur = sc.nextInt();
				
				map[row][col] = cur;
				
				if (cur == 2) // 치킨집이 입력되면 좌표 저장
					chickenHouse.add(new Coo(row, col));
			}
		}
		
		curChickenCnt = chickenHouse.size(); // 현재 치킨집 개수
		used = new boolean[curChickenCnt]; // 각 치킨집을 사용할 지 표시할 boolean 배열
		
		minChickenDis = Integer.MAX_VALUE; // 도시 치킨 거리의 최솟값을 저장할 변수
		
		comb(0); // 치킨집 중 M개만 살리는 조합을 만들어보자
		
		System.out.println(minChickenDis);
	}
	
	private static class Coo { // 치킨집의 좌표를 저장하기 위한 coo 클래스
		int row, col;

		public Coo(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

}
