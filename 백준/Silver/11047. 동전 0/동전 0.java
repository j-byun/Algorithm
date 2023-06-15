import java.util.Scanner;

/**
 * @author jihye.byun
 * BOJ 11047 동전0 실버4
 * 
 * 문제
 * 
 * 조건
 * 
 * 풀이
 * 1. dp배열로 만들어보자.
 */

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt(); // 동전 개수
		int K = sc.nextInt(); // 만들어야 하는 금액
		
		int[] coin = new int[N];
		
		// 각 동전 금액 입력받기
		for (int idx = 0; idx < N; idx++)
			coin[idx] = sc.nextInt();
		
		// 각 금액을 만들기 위한 최소 동전 개수를 저장할 dp배열
		int[] dp = new int[K + 1];
		
		dp[1] = 1;
		
		for (int fee = 2; fee <= K; fee++) {
			dp[fee] = dp[fee - 1] + 1; // 1원짜리만 쓸 때
			
			for (int idx = 1; idx < N; idx++) {
				if (fee - coin[idx] >= 0)
					dp[fee] = Math.min(dp[fee], dp[fee - coin[idx]] + 1);
			}
		}
		
		System.out.println(dp[K]);
	}
}
