import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 7579 앱 골드3
 *
 * 문제
 * 메모리에 활성화되어 있는 앱
 * 메모리가 부족해지면 비활성화 한다
 * 현재 N개의 앱이 활성화 되어있다.
 * 각각의 앱은 mi 바이트만큼 메모리를 사용하고 있고,
 * 비활성화 후 다시 실행하려면 ci의 비용이 들어간다.
 * 새로운 앱 B를 실행하기 위해 M 바이트의 메모리가 필요할 때,
 * 앱들을 비활성화 했을 경우의 비용 ci의 합을 최소로 만들자.
 *
 * 조건
 * 현재 활성화 되어 있는 앱 정수 N (1 ≤ N ≤ 100)
 * 새로 필요한 메모리 바이트 수 M (1 ≤ M ≤ 10,000,000)
 * 각 앱이 사용 중인 메모리의 바이트 수 mi (1 ≤ mi ≤ 10,000,000)
 * 각 앱을 비활성화 했을 경우의 비용 ci (0 ≤ ci ≤ 100)
 * M ≤ m1 + m2 + ... + mN
 *
 * 풀이
 * 1. dp[i][j] = i번째 앱까지의 비활성화를 고려했을 때 j 비용으로 확보가능한 최대 메모리 크기
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 활성화 되어 있는 앱의 개수
        int M = Integer.parseInt(st.nextToken()); // 새로 필요한 메모리 바이트 수

        // N개의 활성화된 앱 정보를 저장할 메모리, 비용 배열
        int[] memory = new int[N];
        int[] cost = new int[N];

        // 활성화된 앱의 메모리 정보 저장
        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < N; idx++) {
            memory[idx] = Integer.parseInt(st.nextToken());
        }

        // 활성화된 앱의 비용 정보 저장
        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < N; idx++) {
            cost[idx] = Integer.parseInt(st.nextToken());
        }

        // dp배열 생성
        int[][] dp = new int[N][10001];

        int minCost = 10001; // 최소 비용을 저장할 변수

        for (int app = 0; app < N; app++) {
            for (int costIndex = 0; costIndex < 10001; costIndex++) {
                if (app > 0)
                    dp[app][costIndex] = dp[app - 1][costIndex];

                if (costIndex >= cost[app] && app > 0)
                    dp[app][costIndex] = Math.max(dp[app][costIndex], dp[app - 1][costIndex - cost[app]] + memory[app]);

                if (costIndex >= cost[app] && app == 0)
                    dp[app][costIndex] = memory[app];

                if (dp[app][costIndex] >= M)
                    minCost = Math.min(minCost, costIndex);
            }
        }

        System.out.println(minCost);
    }
}
