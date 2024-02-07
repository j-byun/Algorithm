import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 1800 인터넷 설치 골드1
 *
 * 문제
 * 1~N까지의 학생
 * 서로 인터넷이 연결될 수 있는 P개의 쌍
 * 선마다 가격이 다르다
 * 1번은 인터넷 서버와 바로 연결되어 있다
 * N번 컴퓨터를 인터넷에 연결하자
 * 나머지 컴퓨터는 연결 되어 있거나 연결 안되어 있어도 무방하다
 * K개의 인터넷 선에 대해서는 공짜로 연결해주기로 했다
 * 나머지 인터넷 선에 대해서는 남은 것 중 제일 가격이 비싼 것에 대해서만 가격을 받는다
 * 내야 하는 최소의 값을 구하자
 *
 * 조건
 * 학생 수 N (1 ≤ N ≤ 1,000)
 * 케이블선의 개수 P (1 ≤ P ≤ 10,000)
 * 공짜로 제공하는 케이블선의 개수 K (0 ≤ K < N)
 * 각 케이블의 가격은 1 이상 1,000,000 이하
 * 만약 1번과 N번 컴퓨터를 잇는 것이 불가능 하다면 -1을 출력한다.
 *
 * 풀이
 * 1. 사용한 인터넷 선을 비용 내림차순으로 저장하여 K+1번째 비용을 출력하자
 * 2. 총 비용은 long으로 만들자
 * 3. 다익스트라로 i번 노드로 오는 최단 경로를 찾으며, 그 때 거친 바로 전 노드의 번호를 저장해두자.
 * => 시간초과
 *
 * 1. x원 이하의 비용으로 N번 컴퓨터까지 연결할 수 있는지를 확인하는 문제로 바꿔서 생각하자!
 * 2. x원 구간은 이분 탐색으로 줄여나가자
 */

public class Main {

    static class Node implements Comparable<Node> {
        int dest, cost;

        public Node(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    static int N, K;
    static List<Node>[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 학생 수
        int P = Integer.parseInt(st.nextToken()); // 케이블 선의 개수
        K = Integer.parseInt(st.nextToken()); // 공짜로 제공하는 케이블선의 개수

        // 인접리스트 생성 및 초기화
        adjList = new ArrayList[N + 1];
        for (int idx = 1; idx <= N; idx++) {
            adjList[idx] = new ArrayList<>();
        }

        // P개의 케이블 선 정보 입력받기
        // 케이블 정보 입력받으며 가장 비싼 케이블 가격 찾기
        int maxCost = 0; // 가장 비싼 케이블의 가격을 저장할 변수
        
        for (int cnt = 1; cnt <= P; cnt++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            adjList[start].add(new Node(end, cost));
            adjList[end].add(new Node(start, cost));

            maxCost = Math.max(maxCost, cost);
        }

        // 0원부터 가장 비싼 케이블 금액까지 정해서 해당 비용으로 N번 컴퓨터까지 연결 할 수 있는지 탐색
        int minCost = binarySearch(0, maxCost);

        // 이분 탐색으로 찾은 금액이 가장 비싼 케이블 금액보다 크다면 연결할 수 없는 것
        System.out.println((minCost > maxCost) ? -1 : minCost);
    }

    static int binarySearch(int start, int end) {

        while (start <= end) {
            int mid = (start + end) / 2;

            boolean canConnect = dijkstra(mid);

            // mid 가격 내고 N번 컴퓨터까지 연결할 수 있으면?
            // 더 적은 금액 탐색
            if (canConnect) {
                end = mid - 1;
            }

            // mid 가격 내고 N번 컴퓨터까지 연결 못하면?
            // 더 큰 금액 탐색
            if (!canConnect) {
                start = mid + 1;
            }
        }

        return start;
    }

    static boolean dijkstra(int defaultCost) {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        int[] cost = new int[N + 1];
        for (int idx = 1; idx <= N; idx++) {
            cost[idx] = K + 1;
        }

        pq.add(new Node(1, 0));
        cost[1] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.dest == N && cost[N] <= K) return true;

            if (cur.cost > cost[cur.dest]) continue;

            for (Node next : adjList[cur.dest]) {
                int nextCost = cur.cost;

                if (next.cost > defaultCost) {
                    nextCost += 1;
                }

                if (cost[next.dest] > nextCost) {
                    cost[next.dest] = nextCost;
                    pq.add(new Node(next.dest, nextCost));
                }
            }
        }

        return false;
    }
}
