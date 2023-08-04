import java.util.Scanner;

/**
 * @author jhye.byun
 * SWEA 1952 수영장 DP
 * 
 * 문제
 * 가장 적은 비용으로 수영장을 이용하자.
 * 이용권 종류 : 1일 / 1달 / 3달 / 1년
 * 
 * 조건
 * 모든 종류의 이용권 요금은 10 이상 3,000 이하의 정수
 * 각 달의 이용 계획은 각 달의 마지막 일자보다 크지 않다.
 * 
 * 풀이
 * 1. 1월부터 12월까지 차례대로 확인하며 네 종류의 이용권 중 제일 저렴한 이용권을 사용하자.
 */

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int testCase = sc.nextInt(); // 테스트케이스 개수 입력받기
		
		for (int tc = 1; tc <= testCase; tc++) { // 테스트케이스 개수만큼 반복 실행
			
			// 네 종류의 이용권 요금을 저장할 배열을 만들어주자
			int[] fee = new int[4];
			
			// 이용권 요금 입력받기
			for (int idx = 0; idx < 4; idx++) {
				fee[idx] = sc.nextInt();
			}
			
			// 1월부터 12월까지의 이용 계획을 입력받자
			int[] plan = new int[13];
			
			for (int idx = 1; idx <= 12; idx++) {
				plan[idx] = sc.nextInt();
			}
			
			// 1월부터 12월까지 차례대로 확인하며 네 종류의 이용권 중 제일 저렴한 경우를 dp배열에 저장하자
			int[] dp = new int[13];
			
			for (int idx = 1; idx <= 12; idx++) {
				int minFee = 987654321;
				
				// 1일 이용권을 사용하는 경우
				minFee = Math.min(minFee, dp[idx - 1] + fee[0] * plan[idx]);
				
				// 한 달 이용권을 사용하는 경우
				minFee = Math.min(minFee, dp[idx - 1] + fee[1]);
				
				// 세 달 이용권을 사용하는 경우
				// 3월에 이용권을 사면 1~3월까지 커버된다고 생각하자
				if (idx >= 3)
					minFee = Math.min(minFee, dp[idx - 3] + fee[2]);
				
				// 1년 이용권을 사용하는 경우
				if (idx >= 12)
					minFee = Math.min(minFee, dp[idx - 12] + fee[3]);
				
				// 네 경우를 모두 고려했으면, 네 종류의 이용권 중 제일 저렴한 경우의 비용을 dp배열에 저장하자
				dp[idx] = minFee;
			}
			
			System.out.printf("#%d %d\n", tc, dp[12]);
		}
	}

}
