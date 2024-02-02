import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 23034 조별과제 멈춰! 플래티넘5
 *
 * 문제
 * N명의 학생을 2개의 조로 구성하여 과제를 공지하자.
 * 구성된 각 조의 인원은 1명 이상이어야 한다.
 * 각 학생은 1~N의 정수 중 고유한 번호를 학번으로 갖는다.
 *
 * 조교는 각 조의 팀장에게만 공지를 전달한다.
 * 조교는 N명의 학생 사이에 있는 총 M개의 회선의 리스트를 가지고 있다.
 * 리스트에는 각 회선에 연결된 두 학생의 학번 A와 B, 연락에 필요한 비용 C가 적혀있다.
 * 회선이 연결된 두 학생은 서로 연락이 가능하다.
 * 조교가 각 팀장에게 공지를 전달하면, 각 팀장과 공지를 알게 된 팀원은 같은 조의 모든 팀원에게 공지 내용을 회선을 통해서만 전달한다.
 * 어떤 학생이 팀장이 되어도 모든 학생은 공지 내용을 전달받을 수 있다.
 *
 * 모든 학생이 공지 내용을 알게 될 때까지 학생들이 연락하며 소요되는 비용의 총합 T의 최솟값을 알고싶다.
 * 다음과 같은 Q개의 질문을 한다. : X Y : X와 Y가 팀장일 때, T의 최솟값은 무엇인가?
 * Q개의 질문에 답할 수 있는 프로그램을 작성하시오.
 *
 * 조건
 * 학생 수 N(2 ≤ N ≤ 1,000) : 정수
 * 회선 리스트의 크기 M(N ≤ M ≤ 100,000) : 정수
 * 두 학생의 학번과 연락에 필요한 비용 : 정수
 *      A(1 ≤ A ≤ N), B(1 ≤ B ≤ N), C(1 ≤ C ≤ 100,000)
 *      A와 B가 같은 경우는 주어지지 않으며, 두 학생에 대한 회선은 여러개가 주어지지 않는다.
 * 질문의 개수 Q(1 ≤ Q ≤ 10,000)
 * 질문하는 두 학생의 학번 X(1 ≤ X ≤ N), Y(1 ≤ Y ≤ N) : 정수
 *
 * 풀이
 * 1. 두 개의 그룹을 만들기 위해서는 N-2개의 간선으로 연결되어 있어야 한다.
 * 2. 그럼 일단 N-1개의 간선으로 이루어진 MST를 만든 후, 가장 비용이 큰 간선 하나만 제거하자!
 * 3. 크루스칼 알고리즘으로 MST를 만들면서 MST에 포함된 간선들을 따로 표시해두자.
 * 4. MST 생성 후 3번에서 표시된 간선들을 확인하며 X -> Y로 가는 길 중 비용이 가장 큰 간선 하나만 고르자.
 */

public class Main {

    static class Edge {
        int start, end, cost;

        public Edge(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }

    static class Node {
        int end, cost;

        public Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
    }

    static int N;
    static PriorityQueue<Edge> pq;
    static List<Node>[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken()); // 학생 수 입력받기
        int M = Integer.parseInt(st.nextToken()); // 회선 수 입력받기
        
        // 회선을 입력받아서 비용 오름차순으로 정렬할 우선순위 큐 생성
        pq = new PriorityQueue<>((o1, o2) -> {
            return o1.cost - o2.cost;
        });
        
        // 크루스칼 알고리즘으로 MST를 만들면서 MST에 사용된 간선들의 정보를 저장할 인접 리스트 생성
        adjList = new ArrayList[N + 1];
        
        // M개의 회선 입력받기
        for (int cnt = 1; cnt <= M; cnt++) {
            st = new StringTokenizer(br.readLine());
            pq.add(new Edge(Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        // 인접 리스트 초기화
        for (int idx = 1; idx <= N; idx++) {
            adjList[idx] = new ArrayList<>();
        }
        
        // 크루스칼 알고리즘을 적용해서 MST만들고, 이 때의 최소 비용 저장
        int mstCost = kruskal();

        st = new StringTokenizer(br.readLine());
        int Q = Integer.parseInt(st.nextToken()); // 질문의 개수 입력받기

        // 질문 개수만큼 X Y 입력받아서 bfs탐색으로 최고 비용 노드를 찾은 후 MST 길이에서 빼서 출력
        for (int cnt = 1; cnt <= Q; cnt++) {
            st = new StringTokenizer(br.readLine());
            sb.append(mstCost - bfs(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())))
                    .append("\n");
        }

        System.out.println(sb.toString());
    }

    static int kruskal() {

        int[] parents = new int[N + 1]; // 부모 노드를 저장할 배열 생성

        makeSet(parents); // 모든 노드에 대해 자기 자신이 부모가 되도록 초기화

        int count = 0; // MST에 사용한 간선의 개수를 세어줄 변수 생성
        int cost = 0; // MST에 추가되는 비용을 저장할 변수 생성
        
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            // 간선으로 연결된 두 노드의 부모 찾기
            int px = findSet(cur.start, parents);
            int py = findSet(cur.end, parents);
            
            // 두 노드의 부모가 같다면 == 이미 연결되어있다면 더 이상 연결 X : 사이클 발생
            if (px == py) {
                continue;
            }
            
            // 두 노드의 부모가 다르다면 간선으로 연결하자!
            union(px, py, parents);
            count++; // 연결한 간선 수 1개 추가
            cost += cur.cost; // 현재 간선의 비용 추가

            // 연결한 간선 정보를 인접 리스트에 저장
            adjList[cur.start].add(new Node(cur.end, cur.cost));
            adjList[cur.end].add(new Node(cur.start, cur.cost));
            
            // N-1개 만큼의 간선을 연결했다면 == MST가 완성되었다면 탐색 종료
            if (count == N - 1) {
                break;
            }
        }
        
        // MST를 만들 때 필요한 최소 비용 반환
        return cost;
    }

    static void makeSet(int[] parents) {
        for (int idx = 0; idx < parents.length; idx++) {
            parents[idx] = idx;
        }
    }

    static int findSet(int x, int[] parents) {
        if (parents[x] == x) return parents[x];
        return parents[x] = findSet(parents[x], parents);
    }

    static void union(int x, int y, int[] parents) {
        if (x < y) {
            parents[y] = x;
        }
        if (x > y) {
            parents[x] = y;
        }
    }

    static int bfs(int start, int end) {

        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(start, 0));
        
        // 방문 확인용 visit 배열 생성
        // adjList에는 a->b와 b->a가 둘 다 들어있기 때문에 양방향 중복 진행을 방지하기 위해
        boolean[] visit = new boolean[N + 1];
        visit[start] = true; // 시작노드 방문 표시

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            // 마지막 노드까지 도착했다면 그 때까지 찾은 간선 중 최대 비용 반환
            if (cur.end == end) {
                return cur.cost;
            }

            // 연결된 다음 노드들 확인
            for (Node next : adjList[cur.end]) {
                if (visit[next.end]) {
                    continue;
                }

                queue.add(new Node(next.end, Math.max(cur.cost, next.cost)));
                visit[next.end] = true;
            }
        }

        return -1;
    }
}
