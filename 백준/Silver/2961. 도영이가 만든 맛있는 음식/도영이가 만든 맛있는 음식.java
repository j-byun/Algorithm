import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 2961 도영이가 만든 맛있는 음식 실버2
 * 시간 제한 1초 | 메모리 제한 128 MB
 *
 * 문제
 * N개의 재료
 * 각 재료의 신맛 S 쓴맛 B
 * 여러 재료를 이용해서 요리할 때, 그 름식의 신맛은 사용한 신맛의 곱, 쓴맛은 합
 * 재료를 적절히 섞어 요리의 신맛과 쓴맛의 차이를 작게 만들자
 * 재료는 적어도 하나 사용해야 한다.
 * 재료의 신맛과 쓴맛이 주어졌을 떄, 신맛과 쓴맛의 차이가 가장 작은 요리를 만들자.
 *
 * 조건
 * 재료의 개수 N(1 ≤ N ≤ 10)
 * 모든 재료 사용 시 신맛과 쓴맛은 모두 1,000,000,000보다 작은 양의 정수
 *
 * 풀이
 * 1. 1번재료만 고려했을 때, 2번 재료까지 고려했을 때, ..., N번 재료까지 고려했을 때를 생각해보자.
 * 2. dp[i] = i번 재료까지 고려했을 떄의 신맛/쓴맛
 * => 합이 아니고 곱이 껴있어서 안되겠다
 * 3. N의 최댓값이 10밖에 안되니 완전 탐색을 하자.
 */

public class Main {

    static int N, minTasteGap;
    static int[] sour, bitter;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 재료의 개수 N 입력받기
        
        // 각 재료의 신맛, 쓴맛 정보를 저장할 배열 생성
        sour = new int[N];
        bitter = new int[N];
        visit = new boolean[N]; // 각 재료의 사용여부를 저장할 배열 생성

        // 각 재료의 신맛, 쓴맛 정보 입력받기
        for (int idx = 0; idx < N; idx++) {
            st = new StringTokenizer(br.readLine());
            sour[idx] = Integer.parseInt(st.nextToken());
            bitter[idx] = Integer.parseInt(st.nextToken());
        }

        minTasteGap = 1_000_000_000; // 두 맛 차이의 기본값 설정

        dfs(0); // 각 재료를 사용할지 안할지 깊이 우선 탐색으로 확인해보기

        System.out.println(minTasteGap); // dfs로 찾은 최소 맛 차이 출력
    }

    static void dfs (int depth) {
        // N개의 재료를 다 확인했으면 맛 차이 구하기
        if (depth == N) {
            calcTasteGap();
            return;
        }

        // 아직 고려하지 않은 재료가 있으면 고려해보기
        visit[depth] = false;
        dfs(depth + 1);
        visit[depth] = true;
        dfs(depth + 1);
    }

    static void calcTasteGap() {
        int sourTaste = 1;
        int bitterTaste = 0;
        boolean empty = true; // 재료가 하나도 사용되지 않았을 때를 확인하기 위한 플래그 생성
        
        for (int idx = 0; idx < N; idx++) {
            if (!visit[idx]) continue;
            sourTaste *= sour[idx];
            bitterTaste += bitter[idx];
            empty = false;
        }

        // 재료가 하나도 사용되지 않았으면 맛 차이를 계산하지 않고 탈출
        if (empty) {
            return;
        }

        minTasteGap = Math.min(minTasteGap, Math.abs(sourTaste - bitterTaste));
    }
}
