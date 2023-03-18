import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author jihye.byun 
 * BOJ 1753 최단경로 골드4 그래프
 * 
 * 문제
 * 주어진 시작점에서 다른 모든 정점으로의 최단 경로를 구하자.
 * 모든 간선의 가중치는 10이하의 자연수이다.
 * 
 * 조건
 * 정점의 개수 V (1 ≤ V ≤ 20,000)
 * 간선의 개수 E (1 ≤ E ≤ 300,000)
 * 시작 정점의 번호 K(1 ≤ K ≤ V)
 * 
 * 풀이
 * 1. 다익스트라 알고리즘
 * 1-1. 정점의 개수 20,000 * 가중치 10 = INF는 200,000 이상의 값
 * 2. 시간 복잡도를 낮추기 위해 인접 행렬 대신 인접 리스트와 우선순위 큐를 활용
 * 
 * <다익스트라 알고리즘>
 * 음의 가중치가 없는 그래프의 한 노드에서 각 모든 노드까지의 최단거리를 구하는 알고리즘
 * 그리디 알고리즘, 다이나믹 프로그래밍 기법 사용
 * 1. 방문하지 않는 노드 중 가장 비용이 적은 노드 선택 (그리디 알고리즘)
 * 2. 해당 노드로부터 갈 수 있는 노드들의 비용 갱신 (다이나믹 프로그래밍)
 */

public class Main {
	
	static int INF = 1000000;
	
	static ArrayList<Node>[] graph;
	static boolean[] visited;
	static int[] dist;
	
	private static void dijkstra(int startV) {
		// 다익스트라 알고리즘
		
		// 가중치를 기준으로 오름차순 정렬하는 우선순위 큐
		// 우선순위 큐 사용방법 2.
		// 우선순위 큐를 선언할 때 정렬 기준을 정의해준다
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
		
		// 시작노드에서 자기 자신 노드로 가는 거리는 0으로 초기화
		pq.add(new Node(startV, 0));
		dist[startV] = 0;
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			// 현재 거리가 가장 짧은 노드를 꺼내서 확인하자
			
			if (visited[cur.v]) continue; // 이미 방문했던 노드면 패스하자
			visited[cur.v] = true; // 노드 방문 처리
			
			// 현재 노드와 연결된 다음 노드들을 확인하자
			for (Node next : graph[cur.v]) {
				// 이미 방문했던 노드면 다른 노드를 확인하러 가자
				if (visited[next.v]) continue;
				
				// 방문하지 않았던 노드고, 현재 노드를 거쳐서 가는 거리가 더 짧을 경우
				if (dist[next.v] > cur.cost + next.cost) {
					// 최단 거리를 업데이트 해주고
					dist[next.v] = cur.cost + next.cost;
					// 최단 거리가 바뀌었으니 큐에 넣어서 인접 노드들을 확인하자
					pq.add(new Node(next.v, dist[next.v]));
				}
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int vertexCnt = sc.nextInt(); // 정점의 개수 V 입력받기
		int edgeCnt = sc.nextInt(); // 간선의 개수 E 입력받기
		int startV = sc.nextInt(); // 시작 정점의 번호 K 입력받기
		
		graph = new ArrayList[vertexCnt + 1];
		// graph[i] : i번 노드에서 연결되어 있는 노드에 대한 정보를 담는 리스트
		visited = new boolean[vertexCnt + 1];
		// 해당 노드를 방문한 적이 있는지 체크하는 배열
		dist = new int[vertexCnt + 1];
		// 시작 노드에서 해당 인덱스 노드까지 가는 최단 거리를 저장할 배열
		
		// 다익스트라 알고리즘
		// 1. graph 배열 초기화
		for (int cnt = 1; cnt <= vertexCnt; cnt++) {
			graph[cnt] = new ArrayList<>();
			// graph 배열에 바로 노드 정보를 넣을 수 있게 각 배열의 칸에 리스트 공간 생성
		}
		
		// 2. dist 배열 초기화
		// 최단 거리를 찾기 위해 일단 최대값 infinity로 초기화하자
		// 배열 값 일괄 초기화 메서드인 Arrays.fill()을 사용하거나
		// 위의 graph 초기화 for문 안에 같이 넣어서 초기화하는 방법이 있다.
		// Arrays.fill()을 호출하면 for문을 한 번 더 돌아 실행 시간이 늘어난다.
		// 메서드 사용법을 익히기 위해 Arrays.fill()을 사용해보자
		Arrays.fill(dist, INF);
		
		// 간선 정보를 입력받자
		// start : 시작 노드 번호
		// end : 도착 노드 번호
		// weight : 가중치
		int start = 0, end = 0, weight = 0; // 변수 초기화
		for (int cnt = 1; cnt <= edgeCnt; cnt++) {
			start = sc.nextInt(); // 출발 정점 번호
			end = sc.nextInt(); // 도착 정점 번호
			weight = sc.nextInt(); // 가중치
			
			graph[start].add(new Node(end, weight));
			// 출발 정점의 인접 리스트에 도착 정점 번호와 가중치 정보 저장
		}
		sc.close();
		
		dijkstra(startV); // 다익스트라 알고리즘 실행
		
		// 최단 경로로 업데이트된 dist 배열 출력
		StringBuilder sb = new StringBuilder();
		for (int idx = 1; idx <= vertexCnt; idx++) {
			// 최단 경로가 초기값인 INF라면 해당 노드로 가는 경우가 없을 때이니
			// "INF" 문자를 출력하고, 아니라면 배열의 값을 출력하자
			sb.append((dist[idx] == INF)? "INF" : dist[idx]).append("\n");
		}
		System.out.println(sb);
	}

	static class Node {
		int v, cost; // 정점 번호와 해당 정점으로 이동할 때의 가중치를 저장할 Node 클래스

		public Node(int v, int cost) {
			this.v = v;
			this.cost = cost;
		}
	}
	
	/* 우선순위 큐 사용방법 1.
	// 간선의 가중치를 기준으로 오름차순 정렬하기 위해 comparable을 구현한 Node 클래스
	static class Node implements Comparable<Node>{
		int v, cost;
		
		public Node(int v, int cost) {
			this.v = v;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.cost, o.cost);
		}
	}
	*/
}
