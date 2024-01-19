import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 2003 수들의 합 2 실버4
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

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < N; idx++) {
            arr[idx] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = 0;
        int sum = arr[0];
        int count = 0;

        while (end < N) {
            if (sum == M) {
                count++;
                sum -= arr[start++];
                continue;
            }

            if (sum < M) {
                if (end == N - 1)
                    break;
                sum += arr[++end];
                continue;
            }

            if (sum > M) {
                sum -= arr[start++];
                continue;
            }
        }

        System.out.println(count);
    }
}