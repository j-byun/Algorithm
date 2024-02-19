import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 20922 겹치는 건 싫어 실버1
 * 시간 제한 1초 | 메모리 제한 1024 MB
 *
 * 문제
 * 같은 원소가 K개 이하로 들어 있는 최장 연속 부분 수열의 길이를 구하자
 * 100,000이하의 양의 정수로 이루어진 길이가 N인 수열
 * 이 수열에서 같은 정수를 K개 이하로 포함한 최장 연속 부분 수열의 길이를 구하자.
 *
 * 조건
 * 정수 N (1 <= N <= 200,000)
 * 정수 K (1 <= K <= 100)
 * 수열의 원소 a (1 <= 1 <= 100,000)
 *
 * 풀이
 * 1. 투포인터 -> 시간초과 예상
 * => 진짜 시간초과 (2%까지 실행)
 * 
 * 1. 전체 원소의 개수를 확인하지 말고, 포인터를 한 칸씩 움직일거니까 그 때 추가/삭제된 원소의 개수만 확인하자
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken()); // 수열의 길이 입력받기
        int K = Integer.parseInt(st.nextToken()); // 최대 중복 원소 개수 입력받기
        
        int[] arr = new int[N]; // 수열 생성
        int maxNum = 0; // 수열의 원소 중 최댓값을 저장할 변수 생성

        // 수열 정보 입력받기
        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < N; idx++) {
            arr[idx] = Integer.parseInt(st.nextToken());

            // 원소의 최댓값 업데이트
            maxNum = Math.max(maxNum, arr[idx]);
        }
        
        int[] count = new int[maxNum + 1]; // 수열에 각 원소들이 몇 번 등장했는지 체크할 카운트 배열 생성

        // 투포인터를 활용해서 배열의 원소들을 확인하며 중복 원소가 K개 이하일 때의 최장 길이 확인하기
        int start = 0;
        int end = -1;
        int maxLength = 0;
        count[arr[++end]]++;

        while (end < N && start <= end) {
            if (count[arr[end]] > K) {
                count[arr[start++]]--;
            }

            if (count[arr[end]] <= K) {
                maxLength = Math.max(maxLength, end - start + 1);
                if (end == N - 1) {
                    break;
                }
                count[arr[++end]]++;
            }
        }

        System.out.println(maxLength);
    }
}
