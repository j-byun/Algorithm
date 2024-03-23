import java.util.*;

/**
 * @author jihye.byun
 * BOJ 10844 쉬운 계단 수 실버1
 * 시간 제한 1초 | 메모리 제한 256 MB
 *
 * 문제
 * 길이가 N인 계단 수의 개수를 구하자.
 * 0으로 시작하는 수는 계단수가 아니다.
 * 정답을 1,000,000,000으로 나눈 나머지를 출력한다.
 *
 * 조건
 * N은 1보다 크거나 같고, 100보다 작거나 같은 자연수
 *
 * 풀이
 * 1. dp[i][j] = 길이가 i이고 i번째 수가 j인 계단 수의 개수
 */

public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	int N = sc.nextInt(); // 계단 수의 길이
    	int[][] dp = new int[N + 1][10]; // 길이가 i이고 i번째 수가 j인 계단 수의 개수를 저장할 배열
    	
    	// 길이가 1일 때 dp배열 초기화
    	for (int j = 1; j <= 9; j++) {
    		dp[1][j] = 1;
    	}
    	
    	// 길이가 2일 때~N일 때까지 업데이트
    	for (int i = 2; i <= N; i++) {
    		for (int j = 0; j <= 9; j++) {
    			if (j - 1 >= 0) {
    				dp[i][j] += dp[i - 1][j - 1];
    			}
    			if (j + 1 <= 9) {
    				dp[i][j] += dp[i - 1][j + 1];
    			}
    			dp[i][j] %= 1_000_000_000;
    		}
    	}
    	
    	// 길이가 N일 때의 전체 계단 수의 개수 출력
    	int answer = 0;
    	
    	for (int j = 0; j <= 9; j++) {
    		answer += dp[N][j];
    		answer %= 1_000_000_000;
    	}
    	
    	System.out.println(answer);
    }
}
