import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 1208 부분수열의 합 2 골드1
 *
 * 문제
 * N개의 정수로 이루어진 수열
 * 크기가 양수인 부분수열 중에서 그 수열의 원소를 다 더한 값이 S가 되는 경우의 수를 구하자
 *
 * 조건
 * 정수의 개수 N (1 ≤ N ≤ 40)
 * 정수 S (|S| ≤ 1,000,000)
 * 주어지는 정수의 절댓값은 100,000을 넘지 않는다.
 *
 * 풀이
 * 1. 정수의 개수가 최대 40이기 때문에 완전탐색의 경우 2^40으로 시간 제한(1초) 초과
 * 2. 배열을 절반으로 나누어서 2^20 * 2 만큼으로 탐색시간을 줄인 후, 두 배열에서 찾은 합을 투 포인터로 확인하자.
 */

public class Main {
    
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken()); // 정수의 개수 입력받기
        int S = Integer.parseInt(st.nextToken()); // 만들어야 하는 원소의 합 입력받기
        
        arr = new int[N]; // 정수들을 입력받을 배열 생성

        // 배열의 원소들 입력받기
        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < N; idx++) {
            arr[idx] = Integer.parseInt(st.nextToken());
        }
        
        // 두 개로 나눈 수열들의 부분수열의 합을 저장할 list 생성
        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();

        // 두 개로 나눈 수열들에 대해 부분수열의 합 구하기
        getSubsequence(0, N / 2, 0, leftList);
        getSubsequence(N / 2, N, 0, rightList);

        // 두 개의 list 오름차순 정렬
        Collections.sort(leftList);
        Collections.sort(rightList);

        // 합이 S가 되는 경우의 수 구하기
        long count = calcCount(N, S, leftList, rightList);

        // 합이 0이 되는 경우는 0을 출력해야 하기 때문에 구해진 0-0 경우를 빼고 출력하자
        // 문제 조건에 부분수열의 크기는 양수여야 함이 주어졌다
        System.out.println((S == 0) ? count - 1 : count);
    }

    static long calcCount(int size, int sum, List<Integer> leftList, List<Integer> rightList) {

        long count = 0;
        int leftIndex = 0;
        int rightIndex = rightList.size() - 1;
        int leftSize = leftList.size();

        while (leftIndex < leftSize && rightIndex >= 0) {
            int left = leftList.get(leftIndex);
            int right = rightList.get(rightIndex);

            if (left + right == sum) {
                long leftCount = 0;
                long rightCount = 0;

                while (leftIndex < leftSize && leftList.get(leftIndex) == left) {
                    leftCount++;
                    leftIndex++;
                }

                while (rightIndex >= 0 && rightList.get(rightIndex) == right) {
                    rightCount++;
                    rightIndex--;
                }

                count += leftCount * rightCount;
                continue;
            }

            if (left + right > sum) {
                rightIndex--;
                continue;
            }

            if (left + right < sum) {
                leftIndex++;
                continue;
            }
        }

        return count;
    }

    static void getSubsequence(int depth, int maxDepth, int sum, List<Integer> list) {

        if (depth == maxDepth) {
            list.add(sum);
            return;
        }

        getSubsequence(depth + 1, maxDepth, sum, list);
        getSubsequence(depth + 1, maxDepth, sum + arr[depth], list);
    }
}
