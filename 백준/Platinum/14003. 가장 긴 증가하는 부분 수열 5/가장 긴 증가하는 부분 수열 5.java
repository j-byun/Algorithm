import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 14003 가장 긴 증가하는 부분 수열 5 플래티넘5
 *
 * 문제
 * 수열 A의 가장 긴 증가하는 부분 수열(LIS)을 구하자
 *
 * 조건
 * 수열 A의 크기 N (1 ≤ N ≤ 1,000,000)
 * 수열 A를 이루고 있는 Ai가 주어진다. (-1,000,000,000 ≤ Ai ≤ 1,000,000,000)
 *
 * 풀이
 * <12738 가장 긴 증가하는 부분 수열 3 풀이>
 * 1. 이분 탐색으로 구현하는 LIS (최장 증가 부분 수열) 알고리즘
 * 1-1. dp로 구현할 경우 시간복잡도가 O(n^2)이기 때문에 N이 최대 백만인 본 문제에서는 시간 제한 3초에 맞출 수 없음
 * 2. 같은 크기의 증가 수열에서 마지막 값 중 최솟값만 기억하자!
 * 3. 2번의 최솟값을 저장해놓은 LIS배열의 원소들은 오름차순으로 정렬되게 된다.
 * 3-1. 이전 값이 더 작아야 '증가' 부분 수열을 만족하기 때문
 * 4. 오름차순 정렬된 배열은 이분 탐색 가능!
 * 
 * 위 풀이 그대로 탐색 후 LIS 배열의 원소들까지 모두 출력하자
 * => 역추적 배열이 필요함
 */

public class Main {
    public static void main(String[] args) throws IOException {
        /*
        12738번 풀이

         수열의 크기 입력받기
         증가 부분 수열의 마지막 값을 저장해둘 배열
         1번 인덱스부터 사용
         사용하지 않은 0번 인덱스 초기화
         지금까지 찾은 최장 증가 부분 수열의 길이를 나타낼 변수 생성
         수열 A 입력받기
         지금 들어온 숫자보다 작은 수 중 가장 큰 수 바로 다음에 위치할 수 있음
         그렇다면 지금 들어온 숫자와 같거나 큰 수 중 가장 작은 수의 위치를 찾자
         배열의 가장 큰 값도 지금 들어온 숫자 보다 작은 경우에는?
         => 이분 탐색하기 전에 그냥 다음 자리에 지금 들어온 숫자를 넣어버리면 된다
         */

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int[] arr = new int[N]; // 수열 A
        int[] lis = new int[N + 1]; // 길이가 i인 증가 부분 수열의 마지막 값 중 최솟값을 저장할 배열
        int[] index = new int[N]; // 수열의 원소 i가 lis에 들어갔을 때의 인덱스를 저장할 배열

        int length = 0;
        lis[length] = Integer.MIN_VALUE;

        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < N; idx++) {
            int num = Integer.parseInt(st.nextToken());
            arr[idx] = num;

            if (lis[length] < num) {
                lis[++length] = num;
                index[idx] = length;
                continue;
            }

            int insertIndex = binarySearch(lis, 1, length, num);
            lis[insertIndex] = num;
            index[idx] = insertIndex;
        }

        sb.append(length).append("\n");

        Stack<Integer> stack = new Stack<>();
        for (int idx = N - 1; idx >= 0; idx--) {
            if (index[idx] == length) {
                stack.push(arr[idx]);
                length--;
            }
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }

        System.out.println(sb.toString());
    }

    static int binarySearch(int[] arr, int start, int end, int num) {

        while (start <= end) {
            int mid = (start + end) / 2;
            int midValue = arr[mid];

            if (midValue == num) {
                return mid;
            }

            if (midValue > num) {
                end = mid - 1;
            }

            if (midValue < num) {
                start = mid + 1;
            }
        }

        return start;
    }
}
