import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author jihye.byun
 * BOJ 5014 스타트링크 실버1 DFS/BFS
 *
 * 문제
 * 스타트링크는 총 F층으로 이루어진 고층 건물에 사무실이 있다.
 * 스타트링크는 G층에 있다.
 * 강호가 지금 있는 곳은 S층이고, 이제 엘레베이터를 타고 G층으로 이동하려고 한다.
 * 엘레베이터에는 위로 U층을 가는 버튼과 아래로 D층을 가는 버튼 2개밖에 없다.
 * 만약 U층 위, 또는 D층 아래에 해당하는 층이 없을 때는 엘레베이터가 움직이지 않는다.
 * 강호가 G층에 도착하려면 버튼을 적어도 몇 번 눌러야 하는지 구하는 프로그램을 작성하시오.
 * 만약 엘레베이터를 이용해서 G층에 갈 수 없다면, "use the stairs"를 출력한다.
 *
 * 조건
 * 첫째 줄에 F, S, G, U, D가 주어진다.
 * (1 ≤ S, G ≤ F ≤ 1000000, 0 ≤ U, D ≤ 1000000)
 * 건물은 1층부터 시작하고, 가장 높은 층은 F층이다.
 *
 * 풀이
 * 1. BFS로 탐색하고, 큐가 비었는데 G층에 도착하지 못했다면 use the stairs를 출력하자.
 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int F = Integer.parseInt(st.nextToken()); // 건물의 총 층수
        int S = Integer.parseInt(st.nextToken()); // 강호의 현재 위치
        int G = Integer.parseInt(st.nextToken()); // 사무실의 위치
        int U = Integer.parseInt(st.nextToken()); // 위로 올라갈 수 있는 층수
        int D = Integer.parseInt(st.nextToken()); // 아래로 내려갈 수 있는 층수

        int[] building = new int[F + 1]; // 건물의 각 층수에 도착한 시간을 저장할 배열 공간

        // building배열을 INF = 123456789으로 초기화
        for (int idx = 0; idx <= F; idx++) {
            building[idx] = 123456789;
        }

        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {S, 0});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (building[cur[0]] != 123456789) continue;
            building[cur[0]] = cur[1];
            if (cur[0] == G) break;

            // 위로 이동
            if (cur[0] + U <= F)
                queue.add(new int[] {cur[0] + U, cur[1] + 1});

            // 아래로 이동
            if (cur[0] - D > 0)
                queue.add(new int[] {cur[0] - D, cur[1] + 1});
        }

        // G층에 도착할 수 없다면 계단 이용 문구를 출력하자
        System.out.println((building[G] == 123456789) ? "use the stairs" : building[G]);
    }
}
