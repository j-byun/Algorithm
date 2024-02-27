import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 1980 햄버거 사랑 실버4
 * 시간 제한 2초 | 메모리 제한 128 MB
 *
 * 문제
 * 타워버거 먹는데 n분, 불고기버거 먹는데 m분이 걸린다.
 * t분 동안 햄버거를 최대한 많이 먹자
 * 햄버거를 먹는 도중에 t분이 끝나면 안되고,
 * 아무것도 안 먹고 있을 때는 콜라를 마신다.
 * 먹은 햄버거의 개수와 콜라를 마신 시간을 구하자.
 *
 * 조건
 * n, m, t는 1만 이하의 자연수이다.
 *
 * 풀이
 * 1. dp
 * 2. 햄버거 개수와 콜라 마신 시간을 같이 저장하자.
 * 3. 햄버거 개수를 업데이트 할 때마다 그 때의 콜라 마신 시간을 확인해서 같이 업데이트 해야함.
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 타워버거 먹는데 걸리는 시간
        int m = Integer.parseInt(st.nextToken()); // 불고기버거 먹는데 걸리는 시간
        int t = Integer.parseInt(st.nextToken()); // 전체 시간

        int[] dp = new int[t + 1]; // 각 시간별 최대 햄버거 개수를 저장할 배열
        int[] coke = new int[t + 1]; // 각 시간별 최대 햄버거 개수에 따른 콜라 시간을 저장할 배열

        for (int time = 1; time <= t; time++) {
            // 햄버거 안마시고 콜라 마시는 경우
            dp[time] = dp[time - 1];
            coke[time] = coke[time - 1] + 1;

            // 타워버거 먹는 경우
            if (time >= n && (coke[time] > coke[time - n] || (coke[time] >= coke[time - n] && dp[time] <= dp[time - n] + 1))) {
                dp[time] = dp[time - n] + 1;
                coke[time] = coke[time - n];
            }

            // 불고기버거 먹는 경우
            if (time >= m && (coke[time] > coke[time - m] || (coke[time] >= coke[time - m] && dp[time] <= dp[time - m] + 1))) {
                dp[time] = dp[time - m] + 1;
                coke[time] = coke[time - m];
            }
        }

        System.out.println(dp[t] + " " + coke[t]);
    }
}
