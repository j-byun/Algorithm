import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 2169 로봇 조종하기 골드2
 * 시간 제한 1초 / 메모리 제한 512 MB
 *
 * 문제
 * N*M 배열의 화성을 탐사하는 로봇
 * 로봇은 움직일 때 배열에서 왼쪽, 오른쪽, 아래쪽으로 이동할 수 있지만, 위쪽으로는 이동할 수 없다
 * 한 번 탐사한 칸은 탐사하지 않는다
 * 각각의 칸은 탐사 가치가 있다
 * 로봇을 배열의 왼쪽 위 (1, 1)에서 출발시켜 오른쪽 아래 (N, M)으로 보내자
 * 위의 조건을 만족하며, 탐사한 지역들의 가치의 합이 최대가 되도록 하는 프로그램을 작성하자
 *
 * 조건
 * N, M(1≤N, M≤1,000)
 * 배열의 각 수는 절댓값이 100을 넘지 않는 정수
 *
 * 풀이
 * 1. dfs/bfs 완전 탐색으로는 시간초과 발생
 * 2. 그렇다면 dp로 최적화하여 O(N*M) 시간에 끝내자
 * 3. 위쪽 방향으로는 이동 불가하니, row를 증가시키며 탐색해나가면 되겠다
 * 4. 하지만 왼쪽방향으로는 이동 가능하니, 오른쪽에서부터 오는 경우는 고려해야 한다
 * 5. (1, 1) 시작 칸은 무조건 지나가야 하니, 첫 번째 행은 무조건 오른쪽으로만 진행
 * 6. 그 다음부터는 dp[row - 1][col - 1] + mars[row][col - 1] || dp[row - 1][col] || dp[row - 1][col + 1] + mars[row][col + 1] 세 경우 중 가장 큰 값을 고르면 되겠다
 * 7. 근데 저~멀리 오른쪽에서부터 온 값은 col+1로 처리되지 않음
 * 8. 그럼 그냥 한 줄에 대해 위, 왼쪽, 오른쪽 방향으로 세 번 진행해주자
 * 9. 시간복잡도는 O(N*M*3)
 * 10. 근데 왼쪽 다~ 돌고나서 오른쪽 돌면 같은 칸을 왕복하게 될텐데?
 * 11. 왼쪽에서 오는 값이랑 오른쪽에서 오는 값을 분리해서 저장해둬야겠다
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 행의 크기 입력받기
        int M = Integer.parseInt(st.nextToken()); // 열의 크기 입력받기

        int[][] mars = new int[N + 1][M + 1]; // 화성 배열 생성
        int[][] dp = new int[N + 1][M + 1]; // (row, col)까지 갈 때의 최댓값을 저장할 dp 배열 생성
        int[][] left = new int[N + 1][M + 1]; // 왼쪽으로 진행하는 dp값을 저장할 배열
        int[][] right = new int[N + 1][M + 1]; // 오른쪽으로 진행하는 dp값을 저장할 배열

        // 화성 배열 정보 입력받기
        for (int row = 1; row <= N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 1; col <= M; col++) {
                mars[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        // 첫 번째 행은 전부 오른쪽 방향으로 진행시키기
        for (int col = 1; col <= M; col++) {
            dp[1][col] = mars[1][col];

            if (col > 1) {
                dp[1][col] += dp[1][col - 1];
            }
        }

        // 두 번째 행 부터는 왼쪽, 위, 오른쪽에서 오는 경우 고려해주기
        for (int row = 2; row <= N; row++) {
            // 위에서 오는 경우
            for (int col = 1; col <= M; col++) {
                dp[row][col] = dp[row - 1][col] + mars[row][col];
            }

            // 왼쪽에서 오는 경우
            left[row][1] = dp[row][1];
            for (int col = 2; col <= M; col++) {
                left[row][col] = Math.max(dp[row][col], left[row][col - 1] + mars[row][col]);
            }

            // 오른쪽에서 오는 경우
            right[row][M] = dp[row][M];
            for (int col = M - 1; col > 0; col--) {
                right[row][col] = Math.max(dp[row][col], right[row][col + 1] + mars[row][col]);
            }

            // 위, 왼쪽, 오른쪽 세 값 중 최댓값으로 dp배열 업데이트
            for (int col = 1; col <= M; col++) {
                dp[row][col] = Math.max(dp[row][col], Math.max(left[row][col], right[row][col]));
            }
        }

        // 찾은 최댓값 출력
        System.out.println(dp[N][M]);
    }
}
