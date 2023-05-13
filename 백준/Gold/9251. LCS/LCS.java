import java.util.Scanner;

/**
 * @author jihye.byun
 * BOJ 9251 LCS 골드5 다이나믹 프로그래밍
 * 
 * 문제
 * 최장 공통 부분 수열의 길이를 출력하자.
 * 
 * 조건
 * 최대 1000글자
 * 
 * 풀이
 * 1. 다이나믹 프로그래밍을 활용하자.
 * 2. 문자열1의 길이 * 문자열2의 길이 크기의 dp 공간을 만들자.
 * 2-1. dp배열의 각 칸에는 해당 칸 인덱스까지의 문자열에서 찾을 수 있는 LCS길이를 저장하자.
 * 2-2. 현재 두 문자열 인덱스가 가르키고 있는 문자가 동일하다면 각 인덱스-1 번째 칸의 값에 +1 해서 저장하자.
 * 2-3. 두 문자가 동일하지 않다면 문자열1인덱스만 -1 했을 때의 값과, 문자열2인덱스만 -1했을 때의 값 중 큰 값을 저장하자.
 */

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String line1 = sc.next();
		String line2 = sc.next();
		
		int len1 = line1.length();
		int len2 = line2.length();
		
		int[][] dp = new int[len1 + 1][len2 + 1];
		
		for (int idx1 = 1; idx1 <= len1; idx1++) {
			for (int idx2 = 1; idx2 <= len2; idx2++) {
				if (line1.charAt(idx1 - 1) == line2.charAt(idx2 - 1))
					dp[idx1][idx2] = dp[idx1 - 1][idx2 - 1] + 1;
				else
					dp[idx1][idx2] = Math.max(dp[idx1 - 1][idx2], dp[idx1][idx2 - 1]);
			}
		}
		
		System.out.println(dp[len1][len2]);
	}
}
