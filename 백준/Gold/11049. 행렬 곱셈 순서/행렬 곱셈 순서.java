import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 11049 행렬 곱셈 순서 골드3
 *
 * 문제
 * 행렬 N개를 곱하는데 필요한 곱셈 연산의 수
 * 행렬을 곱하는 순서 (괄호로 묶여있다면)에 따라 달라진다
 * 행렬 N개의 크기가 주어졌을 때, 모든 행렬을 곱하는데 필요한 곱셈 연산 횟수의 최솟값을 구하자.
 * 입력으로 주어진 행렬의 순서를 바꾸면 안된다.
 *
 * 조건
 * 행렬의 개수 N(1 ≤ N ≤ 500)
 * 행렬의 크기 r과 c가 주어진다. (1 ≤ r, c ≤ 500)
 * 항상 순서대로 곱셈을 할 수 있는 크기만 입력으로 주어진다.
 * 정답은 2^31-1 보다 작거나 같은 자연수이다.
 * 최악의 순서로 연산해도 연산 횟수가 2^31-1보다 작거나 같다.
 *
 * 풀이
 * 1. dp
 * 2. dp[i][j] = i번째 행렬부터 j번째 행렬까지 곱셈 연산 횟수의 최솟값
 * 3. dp[i][j] = dp[i][k] + dp[k + 1][j] + (i행렬의 행 * k행렬의 열 * j행렬의 열) : k는 임의의 중간지점
 * 4. i~j까지의 간격을 1~n-1까지 늘려가며 탐색 > 시작점을 0번행렬~n-1-간격까지 늘려가며 탐색
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken()); // 행렬의 개수 입력

        int[][] matrix = new int[N][2];  // N개의 행렬 정보를 저장할 배열 생성
        int[][] dp = new int[N][N]; // i번째 행렬부터 j번째 행렬까지 곱셈 연산 횟수의 최솟값을 저장할 dp배열 생성

        // N개의 행렬 정보 입력받기
        for (int idx = 0; idx < N; idx++) {
            st = new StringTokenizer(br.readLine());
            matrix[idx][0] = Integer.parseInt(st.nextToken());
            matrix[idx][1] = Integer.parseInt(st.nextToken());
        }

        // 간격이 0일 때는 곱셈이 일어나지 않아 횟수가 0임
        // 간격이 1일 때 부터 순서대로 증가하며 탐색
        for (int gap = 1; gap < N; gap++) {
            // 시작점이 0번 행렬인 경우부터 차례대로 탐색
            for (int start = 0; start < N - gap; start++) {
                // 이번 탐색에서 확인하는 마지막 행렬 인덱스 end = start + gap
                int end = start + gap;
                // 그럼 start~end까지는 처음 탐색해보는거니까 MAX_VALUE로 dp배열 초기화
                dp[start][end] = Integer.MAX_VALUE;

                // 곱셈 연산 괄호 사이의 중간점 잡아서 탐색
                // 중간점이 start ~ end인 경우까지 차례대로 탐색
                for (int mid = start; mid < end; mid++) {
                    // 이미 찾았던 값과 (start~mid까지 연산, min~end까지 연산 후 둘을 다시 곱하는 경우)의 값 중 최솟값으로 dp배열 업데이트
                    dp[start][end] = Math.min(dp[start][end]
                            , dp[start][mid] + dp[mid + 1][end] + (matrix[start][0] * matrix[mid][1] * matrix[end][1]));
                }
            }
        }

        // dp배열에 최소 연산 값을 다 저장해놨으니, 모든 행렬을 계산하는 경우 (0~n-1번까지)의 최솟값 출력
        System.out.println(dp[0][N - 1]);
    }
}
