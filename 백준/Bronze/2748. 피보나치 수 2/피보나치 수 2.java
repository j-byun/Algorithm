import java.util.*;

/**
 * @author jihye.byun
 * BOJ 2748 피보나치 수 2 브론즈1
 * 시간 제한 1초 | 메모리 제한 128 MB
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

        int n = sc.nextInt();
        long[] dp = new long[n + 1];

        dp[1] = 1L;

        for (int idx = 2; idx <= n; idx++) {
            dp[idx] = dp[idx - 1] + dp[idx - 2];
        }

        System.out.println(dp[n]);
    }
}
