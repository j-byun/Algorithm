import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 10942 팰린드롬? 골드4
 *
 * 문제
 * 자연수 N개
 * S~E번 인덱스까지가 팰린드롬인지 M번 질문한다.
 * 각 질문에 대해 팰린드롬이면 1, 아니면 0을 출력한다.
 *
 * 조건
 * 수열의 크기 N (1 ≤ N ≤ 2,000)
 * 수열의 원소 N개 (수는 100,000보다 작거나 같은 자연수)
 * 질문의 개수 M (1 ≤ M ≤ 1,000,000)
 *
 * 풀이
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(st.nextToken()); // 수열의 크기
        int[] arr = new int[N + 1]; // 수열을 담을 배열

        // 수열 입력받기
        st = new StringTokenizer(br.readLine());
        for (int idx = 1; idx <= N; idx++) {
            arr[idx] = Integer.parseInt(st.nextToken());
        }

        // 수열의 각 원소에 대해 s~e까지가 팰린드롬인지 구하기
        boolean[][] isPalindrome = new boolean[N + 1][N + 1]; // s~e까지가 팰린드롬인지 저장할 배열
        
        // 길이가 0이면 (s==e이면) 무조건 팰린드롬
        for (int idx = 1; idx <= N; idx++) {
            isPalindrome[idx][idx] = true;
        }

        // 길이가 1이면 arr[s]가 arr[s+1]과 같을 때 팰린드롬
        for (int idx = 1; idx < N; idx++) {
            if (arr[idx] == arr[idx + 1])
                isPalindrome[idx][idx + 1] = true;
        }

        // 길이 2부터 길이를 늘려가며 팰린드롬인지 확인
        // 길이 오름차순 -> 인덱스 증가 순서대로 확인
        for (int length = 2; length < N; length++) {
            for (int idx = 1; idx <= N - length; idx++) {
                if (arr[idx] == arr[idx + length] && isPalindrome[idx + 1][idx + length - 1])
                    isPalindrome[idx][idx + length] = true;
            }
        }

        // M번의 질문에 대해 대답하기
        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken()); // 질문의 개수
        
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken()); // 시작 인덱스
            int E = Integer.parseInt(st.nextToken()); // 끝 인덱스

            sb.append((isPalindrome[S][E]) ? 1 : 0).append("\n");
        }

        System.out.println(sb.toString());
    }
}
