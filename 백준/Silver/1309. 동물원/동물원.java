import java.util.*;

/**
 * @author jihye.byun
 * BOJ 1309 동물원 실버1
 *
 * 문제
 *
 * 조건
 *
 * 풀이
 */

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        long[][] dp = new long[N + 1][3];

        dp[1][0] = dp[1][1] = dp[1][2] = 1;

        for (int idx = 2; idx <= N; idx++) {
            dp[idx][0] = (dp[idx - 1][0] + dp[idx - 1][1] + dp[idx - 1][2]) % 9901;
            dp[idx][1] = (dp[idx - 1][0] + dp[idx - 1][2]) % 9901;
            dp[idx][2] = (dp[idx - 1][0] + dp[idx - 1][1]) % 9901;
        }

        System.out.println((dp[N][0] + dp[N][1] + dp[N][2]) % 9901);
    }
}
