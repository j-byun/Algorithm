import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 18353 병사 배치하기 실버2
 *
 * 문제
 * N명의 병사가 무작위로 나열되어 있다.
 * 각 병사는 특정한 값의 전투력을 보유하고 있다.
 * 병사를 배치할 때는 전투력이 높은 병사가 앞쪽에 오도록 내림차순으로 배치하고자 한다.
 * 앞쪽에 있는 병사의 전투력이 항상 뒤쪽에 있는 병사보다 높아야 한다.
 * 또한 배치 과정에서는 특정한 위치에 있는 병사를 열외시키는 방법을 이용한다.
 * 그러면서도 남아있는 병사의 수가 최대가 되도록 하고 싶다.
 * => 최장 감소 부분 수열
 *
 * 조건
 * 병사의 수 N (1 ≤ N ≤ 2,000)
 * 각 병사의 전투력은 10,000,000보다 작거나 같은 자연수이다.
 *
 * 풀이
 * 1. 최장 감소 부분 수열(LDS)의 길이를 구하자.
 * 2. 전체 병사의 수 - LDS의 길이를 출력하자.
 * 3. dp와 이분 탐색 두 가지 방법으로 풀어보자.
 */

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken()); // 병사의 수
        
        /*
        1. dp 풀이
         */
        int[] arr = new int[N]; // 각 병사들의 전투력을 저장할 배열
        int[] dp = new int[N]; // i번째 병사까지의 LDS값을 저장할 dp배열
        int lds = 0; // LDS의 길이를 저장할 변수
        
        // 병사들의 전투력 입력받기
        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < N; idx++) {
            arr[idx] = Integer.parseInt(st.nextToken());
            dp[idx] = 1;

            // 내 앞 병사들을 확인해서 LDS값 업데이트
            for (int before = 0; before < idx; before++) {
                if (arr[before] > arr[idx]) {
                    dp[idx] = Math.max(dp[idx], dp[before] + 1);
                }
            }

            // LDS의 최대 길이 업데이트
            lds = Math.max(lds, dp[idx]);
        }

        // LDS를 만들기 위해 열외시킬 병사 수 출력
        System.out.println(N - lds);
    }
}
