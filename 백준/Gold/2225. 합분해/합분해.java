import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 2225 합분해 골드5
 * 시간 제한 2초 | 메모리 제한 128 MB
 *
 * 문제
 * 0부터 N까지의 정수 K개를 더해서 그 합이 N이 되는 경우의 수를 구하자.
 * 덧셈의 순서가 바뀐 경우는 다른 경우로 센다.
 * 또한 한 개의 수를 여러 번 쓸 수도 있다.
 *
 * 조건
 * N(1 ≤ N ≤ 200)
 * K(1 ≤ K ≤ 200)
 *
 * 풀이
 * 1. 다이나믹 프로그래밍
 * 2. dp[i][j] = i개의 숫자를 사용해서 합이 j가 되는 경우의 수
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 만들어야 하는 합 N, 사용할 수의 개수 K 입력받기
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        int[][] dp = new int[K + 1][N + 1]; // dp배열 생성

        // 수를 1개만 쓸 때는 모든 합을 만드는 경우의 수를 1(자기 자신만 사용)로 초기화
        for (int sum = 0; sum <= N; sum++) {
            dp[1][sum] = 1;
        }

        // 수를 2개 이상 쓰는 경우 확인
        // dp[i][j] = dp[i-1][0]~dp[i-1][j]까지의 합
        // 그렇다면 dp[i][j+1] = dp[i][j]에 j까지 누적합이 저장돼있으니 dp[i-1][j+1]만 더해주면 된다
        for (int count = 2; count <= K; count++) {
            for (int sum = 0; sum <= N; sum++) {
                if (sum == 0) {
                    dp[count][sum] = dp[count - 1][sum];
                }
                if (sum != 0) {
                    dp[count][sum] = (dp[count - 1][sum] + dp[count][sum - 1]) % 1_000_000_000;
                }
            }
        }
        
        System.out.println(dp[K][N]);
    }
}
