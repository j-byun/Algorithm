import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 1495 기타리스트 실버1
 * 시간 제한 2초 | 메모리 제한 128 MB
 *
 * 문제
 * 공연에서 연주할 N개의 곡
 * 매번 곡이 시작하기 전에 볼륨을 바꾸자
 * 각각의 곡이 시작하기 전에 바꿀 수 있는 볼륨 V[i]
 * 현재 볼륨이 P이면 P+V[i]나 P-V[i]로만 바꿀 수 있다.
 * 0보다 작은 값, 혹은 M보다 큰 값으로는 볼륨을 바꿀 수 없다.
 * 마지막 곡을 연주할 수 있는 볼륨 중 최댓값을 구하자.
 * 모든 곡은 리스트에 적힌 순서대로 연주해야 한다.
 *
 * 조건
 * 곡의 개수 N (1 ≤ N ≤ 50)
 * 시작볼륨 S (0 ≤ S ≤ M)
 * 제한 볼륨 (1 ≤ M ≤ 1,000)
 * 만약 마지막 곡을 연주할 수 없다면 (중간에 볼륨 조절을 할 수 없다면) -1을 출력한다.
 *
 * 풀이
 * 1. 다이나믹 프로그래밍
 * 2. dp[i][j] = i번째 곡을 j볼륨으로 연주할 수 있는지?
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] V = new int[N + 1];
        boolean[][] dp = new boolean[N + 1][M + 1];

        st = new StringTokenizer(br.readLine());
        for (int idx = 1; idx <= N; idx++) {
            V[idx] = Integer.parseInt(st.nextToken());
        }

        dp[0][S] = true;

        for (int idx = 1; idx <= N; idx++) {
            for (int volume = 0; volume <= M; volume++) {
                if (!dp[idx - 1][volume]) continue;

                if (volume - V[idx] >= 0) {
                    dp[idx][volume - V[idx]] = true;
                }

                if (volume + V[idx] <= M) {
                    dp[idx][volume + V[idx]] = true;
                }
            }
        }

        for (int volume = M; volume >= 0; volume--) {
            if (dp[N][volume]) {
                System.out.println(volume);
                return;
            }
        }

        System.out.println(-1);
    }
}
