import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 12738 가장 긴 증가하는 부분 수열 3 골드2
 *
 * 문제
 * 수열 A의 가장 긴 증가하는 부분 수열(LIS)을 구하자
 *
 * 조건
 * 수열 A의 크기 N (1 ≤ N ≤ 1,000,000)
 * 수열 A를 이루고 있는 Ai가 주어진다. (-1,000,000,000 ≤ Ai ≤ 1,000,000,000)
 *
 * 풀이
 * 1. 이분 탐색으로 구현하는 LIS (최장 증가 부분 수열) 알고리즘
 * 1-1. dp로 구현할 경우 시간복잡도가 O(n^2)이기 때문에 N이 최대 백만인 본 문제에서는 시간 제한 3초에 맞출 수 없음
 * 2. 같은 크기의 증가 수열에서 마지막 값 중 최솟값만 기억하자!
 * 3. 2번의 최솟값을 저장해놓은 LIS배열의 원소들은 오름차순으로 정렬되게 된다.
 * 3-1. 이전 값이 더 작아야 '증가' 부분 수열을 만족하기 때문
 * 4. 오름차순 정렬된 배열은 이분 탐색 가능!
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 수열의 크기 입력받기
        
        int[] lis = new int[N + 1]; // 증가 부분 수열의 마지막 값을 저장해둘 배열
        // 1번 인덱스부터 사용
        lis[0] = Integer.MIN_VALUE; // 사용하지 않은 0번 인덱스 초기화

        // 지금까지 찾은 최장 증가 부분 수열의 길이를 나타낼 변수 생성
        int length = 0;

        // 수열 A 입력받기
        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < N; idx++) {
            int num = Integer.parseInt(st.nextToken());

            // 지금 들어온 숫자보다 작은 수 중 가장 큰 수 바로 다음에 위치할 수 있음
            // 그렇다면 지금 들어온 숫자와 같거나 큰 수 중 가장 작은 수의 위치를 찾자
            // 배열의 가장 큰 값도 지금 들어온 숫자 보다 작은 경우에는?
            // => 이분 탐색하기 전에 그냥 다음 자리에 지금 들어온 숫자를 넣어버리면 된다
            if (lis[length] < num) {
                lis[++length] = num;
                continue;
            }

            lis[binarySearch(lis, 1, length, num)] = num;
        }

        System.out.println(length);
    }

    static int binarySearch(int[] arr, int start, int end, int num) {

        while (start <= end) {
            int mid = (start + end) / 2;

            if (arr[mid] == num) {
                return mid;
            }

            if (arr[mid] > num) {
                end = mid - 1;
            }

            if (arr[mid] < num) {
                start = mid + 1;
            }
        }

        return start;
    }
}
