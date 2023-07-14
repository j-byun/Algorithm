import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import com.sun.corba.se.spi.servicecontext.UEInfoServiceContext;

/**
 * @author jihye.byun
 * BOJ 1185 유럽여행 플래티넘4 프림
 *
 * 문제
 * N개의 나라들 사이를 이동 가능한 M개의 길이 있는데,
 * N-1개의 길만 사용해서 N개의 나라를 모두 연결시키자.
 * 각 길을 통과하기 위한 비용이 각기 다르고 한 나라를 도착하면 내야할 비용 역시 나라마다 다르다.
 * 모든 도시를 최소 한번 이상 방문하면서 최소 비용이 드는 방법을 찾고 있다.
 * 첫 시작 나라는 민식이가 정할 수 있고, 마지막 나라는 시작 나라이어야 한다.
 *
 * 조건
 * 방문할 나라의 수 N(5 ≤ N ≤ 10,000)
 * 나라들 사이를 연결하는 길의 수 P(N-1 ≤ P ≤ 100,000)
 * i번째 나라를 방문할 때 드는 비용 Ci (1 ≤ Ci ≤ 1,000)
 * Sj번째 나라와 Ej번째 나라 사이를 연결짓는 길을 통과하기 위해 드는 비용 Lj(Sj ≠ Ej, 0 ≤ Lj ≤ 1,000)
 *
 * 풀이
 * 1. 프림 알고리즘을 적용해서 N개의 도시를 선택하자.
 * 2. 가중치는 (도로 이용료) * 2 + 도시1 통행료 + 도시2 통행료 이다.
 * 2-1. 시작점으로 다시 돌아와야 하기 때문에 무조건 도로는 왕복하게 된다.
 */

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 방문할나라의 개수
		int M = Integer.parseInt(st.nextToken()); // 이동 가능한 길의 개수
		
		int[] toll = new int[N + 1]; // 각 나라의 통행료를 저장할 배열
		int min = 1001;
		
		// 각 나라의 통행료 입력받기
		for (int idx = 1; idx <= N; idx++) {
			st = new StringTokenizer(br.readLine());
			toll[idx] = Integer.parseInt(st.nextToken());
			
			// 도시 방문 요금이 최소인 도시의 금액 구하기 (시작점)
			min = Math.min(min, toll[idx]);
		}
		
		// 도로 연결 정보 입력받기
		ArrayList<Edge>[] edge = new ArrayList[N + 1];
		
		for (int idx = 0; idx <= N; idx++) {
			edge[idx] = new ArrayList<>();
		}
		
		int start, end, cost;
		for (int cnt = 0; cnt < M; cnt++) {
			st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());
			
			edge[start].add(new Edge(end, cost * 2 + toll[start] + toll[end]));
			edge[end].add(new Edge(start, cost * 2 + toll[start] + toll[end]));
		}
		
		// 각 도시를 한 번 씩 방문하기
		int sum = min; // 시작점부터 방문하고 출발
		int count = 0;
		
		boolean[] visit = new boolean[N + 1];
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.addAll(edge[1]);
		visit[1] = true;
		count++;
		
		while (count < N) {
			Edge cur = pq.poll();
			
			if (visit[cur.end])
				continue;
			
			visit[cur.end] = true;
			sum += cur.cost;
			count++;
			
			for (Edge e : edge[cur.end])
				pq.add(e);
		}
		
		System.out.println(sum);
	}
	
	private static class Edge implements Comparable<Edge> {
		int end, cost;
		public Edge(int end, int cost) {
			super();
			this.end = end;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
	}
}
