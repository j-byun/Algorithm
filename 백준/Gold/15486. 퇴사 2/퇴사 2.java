import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 15486 퇴사2 골드5
 * 시간 제한 2초 | 메모리 제한 512 MB
 *
 * 문제
 * N일 동안 최대한 많은 상담을 하고 N+1일에 퇴사하자!
 * 하루에 하나씩 서로 다른 사람의 상담을 잡아놓았다.
 * 각각의 상담은 상담을 완료하는데 걸리는 기간 T와 상담을 했을 때 받을 수 있는 금액 P로 이루어져 있다.
 * 상담을 적절히 했을 때, 얻을 수 있는 최대 수익을 구하자.
 *
 * 조건
 * N (1 ≤ N ≤ 1,500,000)
 * 1 ≤ Ti ≤ 50
 * 1 ≤ Pi ≤ 1,000
 *
 * 풀이
 * 1. 다이나믹 프로그래밍
 * 2. dp[i] = i일에 얻을 수 있는 최대 수익
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 퇴사까지 남은 일자 입력받기
        
        // 각 상담에 걸리는 기간과 금액을 저장할 배열 생성
        int[] period = new int[N + 1];
        int[] pay = new int[N + 1];

        // 상담 정보 입력받기
        for (int idx = 1; idx <= N; idx++) {
            st = new StringTokenizer(br.readLine());

            period[idx] = Integer.parseInt(st.nextToken());
            pay[idx] = Integer.parseInt(st.nextToken());
        }

        // 최대 수익 정보를 저장할 dp배열 생성
        int[] dp = new int[N + 1];

        for (int day = 1; day <= N; day++) {
            dp[day] = Math.max(dp[day], dp[day - 1]);

            int endDay = day + period[day] - 1;

            if (endDay > N) continue;

            dp[endDay] = Math.max(dp[endDay], dp[day - 1] + pay[day]);
        }

        System.out.println(dp[N]);
    }
}
