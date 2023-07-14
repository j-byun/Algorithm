import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @author jihye.byun
 * BOJ 14950 정복자 골드3
 *
 * 문제
 * N개의 도시와 M개의 도로
 * 1번 도시가 모든 도시를 정복하자.
 *
 * 조건
 * 도시의 개수 N은 10000보다 작거나 같은 자연수
 * 도로의 개수 M은 30000보다 작거나 같은 자연수
 * t는 10이하의 자연수
 * A와 B를 연결하는 도로의 비용 C는 10000이하의 자연수
 *
 * 풀이
 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        boolean[] visit = new boolean[N + 1];
        List<Node>[] edge = new ArrayList[N + 1];

        for (int idx = 1; idx <= N; idx++) {
            edge[idx] = new ArrayList<Node>();
        }

        for (int idx = 0; idx < M; idx++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            edge[start].add(new Node(end, dist));
            edge[end].add(new Node(start, dist));
        }

        int count = 0;
        int sum = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();

        visit[1] = true;
        pq.addAll(edge[1]);

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (visit[cur.end])
                continue;

            visit[cur.end] = true;
            sum += cur.dist + (t * count);
            count++;

            if (count == N - 1)
                continue;

            pq.addAll(edge[cur.end]);
        }

        System.out.println(sum);
    }

    private static class Node implements Comparable<Node> {
        int end, dist;
        public Node(int end, int dist) {
            this.end = end;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }
    }
}
