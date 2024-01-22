import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 1766 문제집 골드2
 *
 * 문제
 * 1~N번 까지 N개의 문제
 * 문제는 난이도 순서로 출제되어, 1번이 가장 쉬운 문제, N번이 가장 어려운 문제이다
 * 몇몇 문제들 사이에는 '먼저 푸는 것이 좋은 문제'가 있다.
 * 다음 세 가지 조건에 따라 문제를 풀자.
 *
 * 1. N개의 문제는 모두 풀어야 한다.
 * 2. 먼저 푸는 것이 좋은 문제가 있는 문제는, 먼저 푸는 것이 좋은 문제를 반드시 먼저 풀어야 한다.
 * 3. 가능하면 쉬운 문제부터 풀어야 한다.
 *
 * 문제의 개수와 먼저 푸는 것이 좋은 문제에 대한 정보가 주어졌을 때,
 * 주어진 조건을 만족하면서 풀 문제의 순서를 결정하자.
 *
 * 조건
 * 문제의 수 N(1 ≤ N ≤ 32,000)
 * 먼저 푸는 것이 좋은 문제에 대한 정보의 개수 M(1 ≤ M ≤ 100,000)
 * A B : A번 문제는 B번 문제보다 먼저 푸는 것이 좋다
 *
 * 풀이
 * 1. 위상정렬
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken()); // 문제의 수
        int M = Integer.parseInt(st.nextToken()); // 먼저 푸는 것이 좋은 문제에 대한 정보의 개수

        ArrayList<Integer>[] adjList = new ArrayList[N + 1]; // 먼저 푸는 것이 좋은 문제 정보를 저장할 인접 리스트
        int[] inDegree = new int[N + 1]; // 각 문제의 진입차수를 표시할 배열

        // 인접 리스트 초기화
        for (int idx = 1; idx <= N; idx++) {
            adjList[idx] = new ArrayList<>();
        }

        // 먼저 푸는 것이 좋은 문제 정보 저장
        for (int cnt = 0; cnt < M; cnt++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adjList[a].add(b); // 인접 리스트에 저장
            inDegree[b]++; // b의 진입차수 증가
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>(); // 위상정렬 결과를 차례대로 저장할 큐
        // 쉬운 문제를 먼저 풀어야 하니 큐 안에서 숫자를 오름차순 정렬해주는 PriorityQueue 사용

        // 진입차수가 0인 문제들 먼저 큐에 담아주기
        for (int idx = 1; idx <= N; idx++) {
            if (inDegree[idx] > 0) continue;

            queue.add(idx);
        }

        // 위상정렬 수행 결과를 큐에 차례대로 담아주고, 큐가 빌 때 까지 다음 문제 탐색
        while (!queue.isEmpty()) {
            Integer cur = queue.poll();

            sb.append(cur).append(" ");

            for (Integer next : adjList[cur]) {
                inDegree[next]--;
                if (inDegree[next] == 0)
                    queue.add(next);
            }
        }

        // 위상정렬 결과 출력
        System.out.println(sb.toString());
    }
}
