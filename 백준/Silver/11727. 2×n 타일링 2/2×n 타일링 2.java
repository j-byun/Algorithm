import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 11727 2×n 타일링 2 실버3
 *
 * 문제
 *
 * 조건
 *
 * 풀이
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] dp = new int[n + 1];

        dp[1] = 1;

        if (n >= 2)
            dp[2] = 3;

        for (int idx = 3; idx <= n; idx++) {
            dp[idx] = (dp[idx - 1] + dp[idx - 2] * 2) % 10007;
        }

        System.out.println(dp[n]);
    }
}
