import java.util.Scanner;

/**
 * @author jhye.byun
 * SWEA 5215 햄버거 다이어트 D3 부분집합/조합
 * 
 * 문제
 * 원하는 재료만 넣어서 조합할 수 있는 햄버거 가게
 * 각 재료에 대한 맛 점수와 칼로리가 주어졌을 때,
 * 정해진 칼로리 이하의 조합 중에서 가장 점수가 높은 햄버거를 조합하자.
 *
 * 조건
 * 같은 재료를 여러 번 사용할 수 없다.
 * 재료의 수 N (1 ≤ N ≤ 20)
 * 제한 칼로리 L (1 ≤ L ≤ 10^4)
 * 각 재료에 대한 맛 점수 Ti (1 ≤ Ti ≤ 10^3)
 * 각 재료의 칼로리 Ki (1 ≤ Ki ≤ 10^3)
 * 
 * 풀이
 * 1. 재귀호출로 각 재료를 사용할 때, 사용하지 않을 때로 구분하자.
 * 2. 모든 재료에 대해 사용여부를 정한 후, 제한 칼로리를 넘었으면 패스하자.
 * 2-1. 제한칼로리를 넘지 않았으면 맛 점수를 최대값으로 업데이트하자.
 */


public class Solution {
	
	static int toppingCnt, limitKcal, maxTaste;
	static int[] taste, kcal;
	static boolean[] used;
	
	private static void dfs(int depth) {
		
		if (depth == toppingCnt) { // 기저조건
			// 모든 재료에 대해 사용 여부 확인이 끝났다면
			// 현재 조합이 제한 칼로리를 넘지 않는지 확인하자
			checkKcal();
			return; // 더 이상 깊이를 증가시키지 못하게 반환하자
		}
		
		dfs(depth + 1); // 현재 재료를 미사용 상태로 두고, 다음 재료를 확인하자
		used[depth] = true;
		dfs(depth + 1); // 현재 재료를 사용하고, 다음 재료를 확인하자
		used[depth] = false;
	}
	
	private static void checkKcal() {
		// 현재 재료의 조합이 제한 칼로리를 넘지 않는지 확인하고,
		// 제한 칼로리를 넘지 않는다면 최대 맛 점수와 비교해서 업데이트 하는 method
		
		int sumKcal = 0; // 현재 조합의 칼로리 합을 저장할 변수
		int sumTaste = 0; // 현재 조합의 맛 점수 합을 저장할 변수
		
		// 모든 재료에 대해 해당 재료 사용 여부를 확인해보자
		for (int cnt = 0; cnt < toppingCnt; cnt++) {
			// 해당 재료를 사용하지 않았다면 다음 재료를 확인하러 가자
			if (!used[cnt]) continue;
			
			// 해당 재료를 사용했다면...
			// 재료의 칼로리와 맛 점수를 sum변수에 더해주자
			sumKcal += kcal[cnt];
			sumTaste += taste[cnt];
		}
		
		// 현재 조합의 칼로리 합이 제한 칼로리를 초과했다면
		// 맛 점수 업데이트 없이 반환하자
		if (sumKcal > limitKcal) return;
		
		// 제한 칼로리를 초과하지 않았다면...
		// 맛 점수를 여태까지 확인한 조합들의 점수 중 최대값으로 업데이트 해주자
		maxTaste = Math.max(maxTaste, sumTaste);
		return;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		int testCase = sc.nextInt(); // 테스트케이스 개수 입력
		
		// 테스트케이스 개수만큼 반복 실행
		for (int tc = 1; tc <= testCase; tc++) {
			sb.append("#").append(tc).append(" "); // 출력 양식
			
			toppingCnt = sc.nextInt(); // 재료의 개수
			limitKcal = sc.nextInt(); // 제한 칼로리
			
			taste = new int[toppingCnt]; // 각 재료의 맛 점수를 저장할 배열 공간
			kcal = new int[toppingCnt]; // 각 재료의 칼로리를 저장할 배열 공간
			used = new boolean[toppingCnt]; // 각 재료의 사용 여부를 저장할 배열 공간
			
			for (int cnt = 0; cnt < toppingCnt; cnt++) {
				taste[cnt] = sc.nextInt();
				kcal[cnt] = sc.nextInt();
			}
			
			maxTaste = 0; // 제한 칼로리 내에서 가장 높은 맛 점수를 저장할 변수
			
			dfs(0); // 깊이우선탐색으로 각 재료의 조합을 만들어보자
			
			// 깊이우선탐색을 통해 제한 칼로리 내에서의 최대 맛 점수를 찾아 업데이트 해줬으니
			// 최대 맛 점수를 출력하자
			sb.append(maxTaste).append("\n");
		}
		
		System.out.println(sb); // 출력
	}

}
