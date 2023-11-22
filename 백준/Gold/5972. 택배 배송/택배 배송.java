import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 5972 택배 배송 골드5
 *
 * 문제
 * 농부 현서는 헛간 1에 있고 농부 찬홍이는 헛간 N에 있음
 * 농부 현서가 가져가야 될 최소 여물을 출력
 *
 * 조건
 * N (1 <= N <= 50,000) 개의 헛간
 * M (1 <= M <= 50,000) 개의 양방향 길
 * 각각의 길은 C_i (0 <= C_i <= 1,000) 마리의 소가 있습니다.
 * 두 개의 헛간은 하나 이상의 길로 연결되어 있을 수도 있음
 * 농부 현서는 헛간 1에 있고 농부 찬홍이는 헛간 N에 있음
 * 농부 현서가 가져가야 될 최소 여물을 출력
 *
 * 풀이
 * 1. 다익스트라
 */

public class Main {
	
	private static class Node implements Comparable<Node> {
		int end, cost;
		
		public Node(int end, int cost) {
			this.end = end;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}
	
	static final int HYUNSEO = 1;
	static int CHANHONG; 
	static int N, M;
	static ArrayList<ArrayList<Node>> adjList;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	N = Integer.parseInt(st.nextToken()); // 헛간 개수
    	M = Integer.parseInt(st.nextToken()); // 길의 개수
    	
    	CHANHONG = N;
    	adjList = new ArrayList<ArrayList<Node>>();
    	
    	for (int idx = 0; idx <= N; idx++) {
    		adjList.add(new ArrayList<Node>());
    	}
    	
    	// 인접 간선 정보 입력
    	for (int cnt = 0; cnt < M; cnt++) {
    		st = new StringTokenizer(br.readLine());
    		
    		int a = Integer.parseInt(st.nextToken());
    		int b = Integer.parseInt(st.nextToken());
    		int c = Integer.parseInt(st.nextToken());

    		adjList.get(a).add(new Node(b, c));
    		adjList.get(b).add(new Node(a, c));
    	}
    	
    	System.out.println(diijkstra());
    }
    
    private static int diijkstra() {
    	PriorityQueue<Node> pq = new PriorityQueue<>();
    	int[] cows = new int[N + 1];
    	
    	for (int idx = 1; idx <= N; idx++) {
    		cows[idx] = 987654321;
    	}

    	pq.add(new Node(HYUNSEO, 0));
    	cows[HYUNSEO] = 0;
    	
    	
    	while (!pq.isEmpty()) {
    		Node cur = pq.poll();
    		
    		if (cows[cur.end] < cur.cost) continue;
    		if (cur.end == CHANHONG) break;
    		
    		for (Node node : adjList.get(cur.end)) {
        		if (cows[node.end] <= cur.cost + node.cost) continue;
        		cows[node.end] = cur.cost + node.cost;
        		pq.add(new Node(node.end, cows[node.end]));
        	}
    	}
    	
    	return cows[CHANHONG];
    }
}
