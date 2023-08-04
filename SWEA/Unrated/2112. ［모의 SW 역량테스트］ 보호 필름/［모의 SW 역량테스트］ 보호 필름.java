import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/**
 * @author jhye.byun
 * SWEA 2112 보호 필름 모의 SW 역량테스트 백트래킹
 * 
 * 문제
 * 두께D, 가로W 크기의 보호 필름
 * 각 셀들은 특정 A 또는 특성 B를 가지고 있다.
 * 보호 필름의 성능을 검사하기 위한 합격기준 K : 세로방향으로  W줄에 대해,
 * 동일한 특성의 셀이 K개 이상 연속되어야 성능검사 통과
 * 성능 검사에 통과하기 위해 하나의 행 단위로 약품 사용
 * 약품을 사용하면 하나의 행을 A 또는 B 특성 중 하나로 통일시킨다.
 * 성능검사를 통과하기 위한 최소 약품 투입 횟수를 구하자.
 * 약품을 투입하지 않고도 성능 검사를 통과하는 경우에는 0을 출력한다.
 * 
 * 조건
 * 보호 필름의 두께 D는 3이상 13이하의 정수이다. (3≤D≤13)
 * 보호 필름의 가로크기 W는 1이상 20이하의 정수이다. (1≤W≤20)
 * 합격기준 K는 1이상 D이하의 정수이다. (1≤K≤D)
 * 셀이 가질 수 있는 특성은 A, B 두 개만 존재한다.
 * 특성A는 0, 특성B는 1로 표시된다.
 * 
 * 풀이
 * 1. 최소 약물 투입 횟수만 반환하면 되니까 약물을 0번 투입할 때~D번 투입할 때 까지의 조합을 확인하자.
 * 2. 각 조합에서 투입할 행을 골랐으면, 해당 행에 투입할 약물이 A일 때와 B일 때로 나눠서 확인해보자.
 * 2-1. 한 줄에 투입할 약물을 정할 때 마다, 해당 행을 해당 약품으로 다 바꾸자.
 * 2-2. 약품을 주입할 모든 행에 대해 어떤 약품을 주입할 지 확인했다면, 전체 행에 대해 탐색해서 조건을 만족하는지 확인하자.
 * 2-3. 만일 모든 열이 조건을 만족한다면, static boolean으로 조건을 만족한다고 표시해주고 모든 탐색을 종료하자.
 * 3. D-1개의 조합까지 확인해봤는데 static boolean이 끝까지 false라면, 무조건 모든 행을 다 바꿔야 한다는 뜻이니 D를 출력하자.
 * 3-1. 시간 절약을 위해 D개의 조합을 만드는 경우는 탐색하지 말자.
 * 3-2. 어차피 모든 행을 다 바꿔버리면 조건을 만족할 수 밖에 없으니!
 * 4. 만약 합격기준 K가 1이면 무조건 0을 출력하자. (실행시간 단축)
 */

public class Solution {
	
	static int rowSize, colSize, condition, minInjection;
	static boolean isMeet;

	static int[][] film, originFilm;
	
	static int[] A, B;
	
	private static void comb(int start, int depth, int maxDepth) {
		// 약물을 주입할 행을 고르는 조합 메서드
		
		if (isMeet) return; // 이미 합격 기준을 만족한 경우를 찾았다면 메서드를 종료하자
		
		// 기저조건
		if (depth == maxDepth) {
			// 선택할 수 있는 모든 행에 약품을 다 주입했다면,
			// 합격 기준을 만족하는지 확인해보자
			isPossible();
			// 더 이상 깊이를 증가시킬 수 없으니 반환하자
			return;
		}
		
		// 조합을 만들어보자
		for (int idx = start; idx < rowSize; idx++) {
			
			// 현재 행에 A 약품을 주입해보자
			film[idx] = A;
			comb(idx + 1, depth + 1, maxDepth); // 다음 행에 약품을 주입시키자
			
			// 현재 행에 B 약품을 주입해보자
			film[idx] = B;
			comb(idx + 1, depth + 1, maxDepth); // 다음 행에 약품을 주입시키자
			
			// 재귀 호출이 종료됐으면 원본 배열을 다시 돌려주자
			film[idx] = originFilm[idx];
		}
		
	}
	
	private static void isPossible() {
		// 고려할 모든 행에 약품을 주입하고 합격 기준을 만족하는지 확인하는 메서드
		
		// 이제 각 열에 대해 합격 기준을 만족하는지 확인해보자
		for (int col = 0; col < colSize; col++) {
			int lastMedi = film[0][col]; // 첫번째 행의 약품을 초기값으로 설정
			int curCont = 1; // 약품 한 개만 있어도 연속되는 셀 개수는 1이니 연속 셀 개수의 초기값을 1로 설정
			int maxCont = 1; // 현재 열의 최대 연속 셀 개수
			
			for (int row = 1; row < rowSize; row++) {
				if (film[row][col] == lastMedi)
					curCont++; // 연속되는 셀이면 카운트 증가
				else
					curCont = 1; // 연속되는 셀이 아니면 카운트 1로 초기화
				
				// 카운트를 세줬다면,
				// 1. 마지막 문자를 업데이트해주자
				lastMedi = film[row][col];
				// 2. 현재까지 해당 열의 최대 연속 셀 개수를 업데이트 하자
				maxCont = Math.max(maxCont, curCont);
			}
			
			// 현재 열의 최대 연속 셀 개수를 다 세어 줬으니 조건을 만족하는지 확인하자
			// 조건을 만족하지 못하는 열이 하나라도 있다면 탐색을 종료하자
			if (maxCont < condition) return;
		}
		
		// 모든 열이 합격 기준을 만족한다면, 더 이상의 탐색은 무의미하다
		// 모든 탐색을 종료할 수 있게 static boolean isMeet을 true로 만들어주자
		isMeet = true;
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 실행시간을 줄이기 위해 버퍼드 리더를 사용하자
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int testCase = Integer.parseInt(br.readLine()); // 테스트케이스 개수 입력받기
		
		for (int tc = 1; tc <= testCase; tc++) { // 테스트케이스 개수만큼 반복
			sb.append("#").append(tc).append(" ");
			
			String[] line = br.readLine().split(" ");
			
			rowSize = Integer.parseInt(line[0]); // 보호 필름의 두께 D
			colSize = Integer.parseInt(line[1]); // 보호 필름의 가로크기 W
			condition = Integer.parseInt(line[2]); // 합격기준 K
			
			originFilm = new int[rowSize][colSize]; // D*W 크기의 필름 배열 (원본)
			film = new int[rowSize][colSize]; // D*W 크기의 필름 배열 (약품 주입용)
			
			A = new int[colSize]; // 약품 A를 주입할 배열
			B = new int[colSize]; // 약품 B를 주입할 배열
			
			Arrays.fill(B, 1); // 약품 B는 다 1로 채워주자
			
			// 필름 정보 입력받기
			for (int row = 0; row < rowSize; row++) {
				line = br.readLine().split(" ");
				for (int col = 0; col < colSize; col++) {
					originFilm[row][col] = Integer.parseInt(line[col]);
				}
			}
			
			for (int row = 0; row < rowSize; row++) {
				film[row] = originFilm[row];
			}
			
			minInjection = 14; // 합격기준 K를 만족하기 위한 최소 약물 투입 횟수
			isMeet = false; // 현재 합격기준을 만족하는지 여부를 나타낼 boolean flag
			
			// 만일 합격기준 K가 1이라면, 약품을 주입할 필요없이 그냥 그대로 합격 할 수 있으니
			// 최소 약물 투입 횟수를 0으로 만들고 경우의 수를 탐색하지 말자
			if (condition == 1) {
				isMeet = true;
				minInjection = 0;
			}
			
			// 약물을 0번 투입하는 경우부터 D-1번 투입하는 경우까지의 조합을 차례대로 살펴보자
			// 약물 투입 횟수가 최소여야 하니, 차례대로 진행하며 조건을 만족하는 경우가 발견된다면
			// 더 이상 그 다음 경우의 수를 볼 필요가 없으니 바로 탐색을 종료해버리자
			// 합격기준이 1일 때 반복문이 실행되지 않게 하기 위해 !isMeet 조건을 반복 조건에 넣어주자
			for (int cnt = 0; cnt < rowSize && !isMeet; cnt++) {
				// 약품을 cnt번 만큼 주입하는 조합을 찾아보자
				comb(0, 0, cnt);
				
				// 현재 조합을 탐색한 결과로 합격 기준을 만족했다면,
				// 현재 약품 투입 횟수가 최소 횟수이다!
				// 합격 기준을 만족하는 경우를 한 번 찾으면 반복문의 조건에 걸려서 다음 조합의 경우들은 확인하지 않는다
				if (isMeet)
					minInjection = cnt;
			}
			
			// 만일 반복문이 종료됐는데도 isMeet이 false라면,
			// D-1개의 행을 다 바꿔도 조건을 만족하지 못한 경우이니
			// 최소 약품 주입 횟수를 D로 만들어주자
			// 모든 행을 다 바꿔버리면 조건을 만족할 수 밖에 없기 때문
			// 이 경우를 반복문 밖에서 고려해서 실행시간을 단축시키자
			if (!isMeet)
				minInjection = rowSize;
			
			// 모든 경우를 다 확인했다면 발견한 최소 약품 주입 횟수를 출력하자
			sb.append(minInjection).append("\n");
		}
		
		System.out.println(sb); // 출력
	}

}
