import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 2304 창고 다각형 실버2
 * 시간 제한 2초 | 메모리 제한 128 MB
 *
 * 문제
 *
 * 조건
 * 기둥의 개수를 나타내는 정수 N (1 <= N <= 1,000)
 * 각 기둥의 왼쪽 면의 위치를 나타내는 정수 L과 높이를 나타내는 정수 H (1 <= L, H <= 1,000)
 *
 * 풀이
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] arr = new int[1_001];

        for (int cnt = 0; cnt < N; cnt++) {
            st = new StringTokenizer(br.readLine());

            arr[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
        }

        int rightMax = 0;
        int[] right = new int[1_001];
        for (int idx = 0; idx < 1_001; idx++) {
            if (arr[idx] > rightMax) {
                rightMax = arr[idx];
            }

            if (idx > 0) {
                right[idx] = right[idx - 1];
            }

            right[idx] += rightMax;
        }

        int leftMax = 0;
        int[] left = new int[1_001];
        for (int idx = 1_000; idx >= 0; idx--) {
            if (arr[idx] > leftMax) {
                leftMax = arr[idx];
            }

            if (idx < 1_000) {
                left[idx] = left[idx + 1];
            }

            left[idx] += leftMax;
        }

        int min = Integer.MAX_VALUE;
        for (int idx = 0; idx < 1_000; idx++) {
            min = Math.min(min, right[idx] + left[idx + 1]);
        }

        System.out.println(min);
    }
}
