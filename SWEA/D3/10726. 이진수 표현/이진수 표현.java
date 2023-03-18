import java.util.Scanner;

/**
 * @author jhye.byun
 * SWEA 10726 이진수 표현 D3 자료구조 정리/비트연산자
 * 
 * 문제
 * 정수 N, M이 주어질 때,
 * M의 이진수 표현의 마지막 N비트가 모두 1로 켜져 있는지 아닌지 판별하자
 *
 * 조건
 * (1 ≤ N ≤ 30 , 0 ≤ M ≤ 10^8)
 * 
 * 풀이
 * 1. M을 2로 나눈 나머지가 1인지 확인 (제일 마지막 비트)
 * 2. 1번 연산의 몫을 2로 나눈 나머지가 1인지 확인 (마지막에서 두 번째 비트)
 * 3. 위 과정을 N번 반복하며, 한 번이라도 1이 아닌 경우가 나오면 OFF 출력
 * 
 * 자료구조를 활용하려면 이진수로 변환해서 큐에 차례대로 넣은 후,
 * 큐에서 N번만큼 뽑아서 1인지 확인하자.
 * 만약 이진수의 앞자리부터 N개의 비트를 확인라하고 했다면,
 * 큐가 아닌 스택에 넣어서 차례대로 뽑아 확인하자.
 */


public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		int testCase = sc.nextInt(); // 테케 개수 입력
		
		// 테케 개수만큼 반복 실행
		for (int tc = 1; tc <= testCase; tc++) {
			sb.append("#").append(tc).append(" "); // 출력 양식
			
			int N = sc.nextInt();
			int M = sc.nextInt();
			
			String result = "ON";
			
			for (int cnt = 0; cnt < N; cnt++) {
				// 이진수의 가장 마지막 자리부터 차례대로 1인지 확인하고,
				// 하나라도 1이 아닐 경우 결과를 OFF로 바꾸고 탐색을 중단한다
				if (M % 2 == 0) {
					result = "OFF";
					break;
				}
				
				// 확인한 자리가 1이면, 다음 자리를 확인하러 가야하니
				// M을 2로 나눈 몫으로 업데이트 해준다
				M = M / 2;
			}
			
			sb.append(result).append("\n");
		}
		
		System.out.println(sb); // 출력
	}

}
