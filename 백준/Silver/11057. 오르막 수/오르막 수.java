import java.util.*;

/**
 * @author jihye.byun
 * BOJ 11057 오르막 수 실버1
 * 시간 제한 1초 | 메모리 제한 256 MB
 *
 * 문제
 * 수의 길이 N이 주어졌을 때, 오르막 수의 개수를 구하자.
 * 수는 0으로 시작할 수 있다.
 *
 * 조건
 * N (1 ≤ N ≤ 1,000)
 * 길이가 N인 오르막 수의 개수를 10,007로 나눈 나머지를 출력한다.
 *
 * 풀이
 * 1. 길이가 1일때 0~9 10개
 * 2. 길이가 2일때 10+9+8+...+1개
 * 3. dp[i][j] = 길이 i인 오르막 수 중 j로 시작하는 수의 개수
 */

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        int[][] dp = new int[N + 1][10];
        int answer = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= 9; j++) {
                if (i == 1) {
                    dp[i][j] = 1;
                }

                for (int before = 0; before <= j; before++) {
                    dp[i][j] += dp[i - 1][before];
                }
                dp[i][j] %= 10_007;

                if (i == N) {
                    answer += dp[i][j];
                }
            }
        }

        System.out.println(answer % 10_007);
    }
}
