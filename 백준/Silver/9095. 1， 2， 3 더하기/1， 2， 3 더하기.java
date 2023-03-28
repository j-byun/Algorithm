import java.util.Scanner;

/**
 * @author jihye.byun
 * BOJ 9095 1, 2, 3 더하기 실버3
 * 
 * 문제
 * 정수 n이 주어졌을 때, n을 1, 2, 3의 합으로 나타내는 방법의 수를 구하자
 * 
 * 조건
 * n은 양수이며 11보다 작다.
 * 
 * 풀이
 * 1. 1을 나타내는 경우의 수 1개
 * 2. 2를 나타내는 경우의 수 2개
 * 3. 3을 나타내는 경우의 수 4개
 * 4. 4를 나타내는 경우의 수 7개
 * => 수영장처럼 풀어보자!
 */

public class Main {
	
	static int count;
	
	public static void recursion(int sum, int maxSum) {
		
		// 기준이 되는 수를 넘어갔으면 탐색 중단
		if (sum > maxSum) {
			return;
		}
		
		// 기준이 되는 수가 됐으면 카운트 증가
		if (sum == maxSum) {
			count++;
			return;
		}
		
		recursion(sum + 1, maxSum); // 현재 위치에서 1을 사용할 때
		recursion(sum + 2, maxSum); // 2를 사용할 때
		recursion(sum + 3, maxSum); // 3을 사용할 때
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int testCase = sc.nextInt();
		
		for (int tc = 1; tc <= testCase; tc++) {
			
			count = 0; // 경우의 수 개수를 세어줄 변수
			recursion(0, sc.nextInt()); // 재귀 호출로 경우의 수를 찾아보자
			
			System.out.println(count); // 찾은 경우의 수 출력
		}
	}
	
}
