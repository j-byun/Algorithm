import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 2839 설탕 배달
// 실버4

// 문제
// 사탕가게에 설탕을 정확하게 n킬로그램 배달해야 된다
// 설탕은 3키로, 5키로 봉지에 담겨져 있다
// 최대한 적은 봉지를 들고가자

// 조건
// (3 ≤ N킬로그림 ≤ 5000)

// 풀이
// 1. if (n % 5) % 3 == 0 일 때...
// n / 5 개수만큼 5키로 봉지 가져가고
// (n % 5) / 3 개수만큼 3키로 봉지를 가져가자
// 2. else if (n % 3) % 5 == 0 일 때...
// n / 3 개수만큼 3키로 봉지 가져가고
// (n % 3) / 5 개수만큼 5키로 봉지를 가져가자
// 3. else
// 정확하게 무게 맞추기 불가 => -1 출력

// => 5키로와 3키로 개수가 애매하게 섞여있을 때 찾을 수 없음

// 완전 탐색으로 모든 경우의 수를 확인해보자!
// 5키로 봉지 개수와 3키로 봉지 개수의 모든 경우의 수를 확인해서...
// 5키로 개수 * 5 + 3키로 개수 + 3 = 총 무게 n이 만들어진다면
// 이 때의 두 봉지 개수의 합 중 가장 작은 값을 출력하자!
// => 만약 이런 경우가 발생하지 않는다면 -1을 출력

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int maxWeight = Integer.parseInt(br.readLine()); // n 킬로그램 입력받기
		
		/*
		if ((maxWeight % 5) % 3 == 0)
			System.out.println(maxWeight / 5 + (maxWeight % 5) / 3);
		else if ((maxWeight % 3) % 5 == 0)
			System.out.println(maxWeight / 3 + (maxWeight % 3) / 5);
		else
			System.out.println(-1);
		*/
		
		int minCnt = Integer.MAX_VALUE;
		
		for (int fiveCnt = 0; fiveCnt <= maxWeight / 5; fiveCnt++) { // 5키로그램 봉지 개수
			for (int threeCnt = 0; threeCnt <= maxWeight / 3; threeCnt++) { // 3키로그램 봉지 개수
				
				if (fiveCnt * 5 + threeCnt * 3 == maxWeight) { // 정확하게 무게를 만들 수 있다면
					minCnt = Math.min(minCnt, fiveCnt + threeCnt); // 정확한 무게를 만들 수 있는 설탕 봉지의 최소 개수 업데이트
				}
			}
		}
		
		if (minCnt == Integer.MAX_VALUE) // 모든 경우의 수를 확인해 봐도 정확한 무게를 만들 수 없다면
			minCnt = -1; // -1을 출력한다
		
		System.out.println(minCnt);
		
	}

}

