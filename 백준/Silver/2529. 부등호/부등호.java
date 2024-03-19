import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 2529 부등호 실버1
 * 시간 제한 1초 | 메모리 제한 256 MB
 *
 * 문제
 *
 * 조건
 * 부등호 문자의 개수를 나타내는 정수 k (2 ≤ k ≤ 9)
 *
 * 풀이
 * 1. 각 자리에 0~9까지의 숫자를 넣어가며 모든 경우를 재귀로 확인해보자
 * 2. 조건에 맞게 백트래킹해서 재귀 호출 횟수를 줄이자
 */

public class Main {

    static int k;
    static char[] sign;
    static boolean[] used;
    static int[] answer;
    static String minValue, maxValue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        k = Integer.parseInt(st.nextToken()); // 부등호 개수
        sign = new char[k + 1]; // 부등호를 저장할 배열
        used = new boolean[10]; // 0~9까지의 숫자의 사용 여부를 저장할 배열
        answer = new int[k + 1]; // 각 자리에 사용된 숫자를 저장할 배열

        // 부등호 정보 입력받기
        st = new StringTokenizer(br.readLine());
        for (int idx = 1; idx <= k; idx++) {
            sign[idx] = st.nextToken().charAt(0);
        }

        // 최소, 최대값을 저장할 변수 초기화
        maxValue = "0";
        minValue = "9999999999";

        for (int start = 0; start <= 9; start++) {
            answer[0] = start;
            used[start] = true;
            dfs(1);
            used[start] = false;
        }

        System.out.println(maxValue);
        System.out.println(minValue);
    }

    static void dfs(int depth) {

        if (depth == k + 1) {
            StringBuilder sb = new StringBuilder();
            for (int idx = 0; idx <= k; idx++) {
                sb.append(answer[idx]);
            }

            if (maxValue.compareTo(sb.toString()) < 0) {
                maxValue = sb.toString();
            }
            if (minValue.compareTo(sb.toString()) > 0) {
                minValue = sb.toString();
            }
            return;
        }

        for (int next = 0; next <= 9; next++) {
            if (used[next]) continue;
            if (sign[depth] == '<' && answer[depth - 1] > next) continue;
            if (sign[depth] == '>' && answer[depth - 1] < next) continue;
            answer[depth] = next;
            used[next] = true;
            dfs(depth + 1);
            used[next] = false;
        }
    }
}
