import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 2143 두 배열의 합 골드3
 * 시간 제한 2초 | 메모리 제한 64 MB
 *
 * 문제
 * 한 배열의 부 배열은 길이 1이상, 연속된 부분배열이다.
 * 각 원소가 정수인 두 배열 A와 B가 주어졌을 때,
 * A의 부 배열 합에 B의 부 배열 합을 더해서 T가 되는 모든 부 배열 쌍의 개수를 구하시오.
 *
 * 조건
 * 만들어야 하는 합 T(-1,000,000,000 ≤ T ≤ 1,000,000,000)
 * A 배열의 크기 n(1 ≤ n ≤ 1,000)
 * B 배열의 크기 m(1 ≤ m ≤ 1,000)
 * 각각의 배열의 원소는 절댓값이 1,000,000을 넘지 않는 정수
 *
 * 풀이
 * 1. 두 배열의 모든 부분집합을 만들어 부분집합의 합을 카운트하자
 * 2. 2차원 배열을 만들어 arr[i][j] = i~j까지의 부분집합의 합을 저장하자
 * 3. 배열의 원소로 음수가 올 수 있어 카운트 배열은 인덱스 처리에 부적합하다. HashMap을 만들어 카운트해보자
 * 4. 두 개의 카운트 맵에서 합이 T가 되는 쌍을 찾고, 그 개수를 곱해서 전체에 더하자
 * => 68% 틀렸습니다 : 전체 개수가 int 범위를 벗어날 수 있다!
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken()); // 만들어야 하는 합 입력받기

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // A배열의 크기 입력받기
        int[] arrA = new int[n]; // A배열 생성

        // A배열 정보 입력받기
        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < n; idx++) {
            arrA[idx] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken()); // B배열의 크기 입력받기
        int[] arrB = new int[m]; // B배열 생성

        // B배열 정보 입력받기
        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < m; idx++) {
            arrB[idx] = Integer.parseInt(st.nextToken());
        }

        // A배열의 부분집합을 만들고, 그 부분집합의 합을 카운트하자
        int[][] subsetA = new int[n][n];
        Map<Integer, Integer> countA = new HashMap<>();

        for (int row = 0; row < n; row++) {
            int subset = arrA[row];
            subsetA[row][row] = subset;

            countA.put(subset, countA.getOrDefault(subset, 0) + 1);

            for (int col = row + 1; col < n; col++) {
                subset = subsetA[row][col - 1] + arrA[col];
                subsetA[row][col] = subset;

                countA.put(subset, countA.getOrDefault(subset, 0) + 1);
            }
        }

        // B배열의 부분집합을 만들고, 그 부분집합의 합을 카운트하자
        int[][] subsetB = new int[m][m];
        Map<Integer, Integer> countB = new HashMap<>();

        for (int row = 0; row < m; row++) {
            int subset = arrB[row];
            subsetB[row][row] = subset;

            countB.put(subset, countB.getOrDefault(subset, 0) + 1);

            for (int col = row + 1; col < m; col++) {
                subset = subsetB[row][col - 1] + arrB[col];
                subsetB[row][col] = subset;

                countB.put(subset, countB.getOrDefault(subset, 0) + 1);
            }
        }

        // 이제 두 개의 카운트 맵에 대해 합이 T가 되는 경우를 찾자
        long answer = 0;
        for (int sumA : countA.keySet()) {
            if (countB.containsKey(T - sumA)) {
                answer += (long) countA.get(sumA) * (long) countB.get(T - sumA);
            }
        }

        System.out.println(answer);
    }
}
