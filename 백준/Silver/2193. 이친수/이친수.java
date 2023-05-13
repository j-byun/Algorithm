import java.util.Scanner;

/**
 * @author jihye.byun
 * BOJ 2193 이친수 실버3 다이나믹 프로그래밍
 * 
 * 문제
 * 0과 1로 이루어진 이진수 중 다음의 성질을 만족하는 수를 이친수라고 한다.
 * <이친수의 성질>
 * 1. 0으로 시작하지 않는다.
 * 2. 1이 두 번 연속으로 나타나지 않는다.
 * N자리 이친수의 개수를 구하자.
 * 
 * 조건
 * N(1 ≤ N ≤ 90)
 * 
 * 풀이
 * 1. dfs탐색으로 브루트 포스를 실행하자.
 * 2. 1로 시작하고, 마지막으로 들어왔던 수가 1이면 무조건 0만 들어갈 수 있게 하자.
 * => 시간초과
 * 
 * 3. 다이나믹 프로그래밍으로 풀어보자.
 */

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt(); // 자릿수 입력받기
		long[] dp = new long[N + 1]; // 1번째~N번째 자리수까지의 이친수 개수를 저장할 배열 공간
		
		dp[1] = 1; // 첫째 자리에는 무조건 1만 들어갈 수 있다.
		if (N >= 2)
			dp[2] = 1; // 그럼 둘째 자리에는 무조건 0만 들어갈 수 있다.
		
		for (int idx = 3; idx <= N; idx++) {
			// 현재 자리가 0인 경우 : idx-1번째가 0이든 1이든 상관없다 : dp[idx - 1]
			// 현재 자리가 1인 경우 : idx-2번째가 0이든 1이든 상관없다 (idx-1번째는 무조건 0) : dp[idx - 2]
			dp[idx] = dp[idx - 1] + dp[idx - 2];
		}
		
		// 이친수의 개수 출력
		System.out.println(dp[N]);
	}
}
