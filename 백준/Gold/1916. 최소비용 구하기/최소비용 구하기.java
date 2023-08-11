import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @author jihye.byun
 * BOJ 1916 최소비용 구하기 골드5 다익스트라
 *
 * 문제
 * N개의 도시
 * 한 도시에서 출발하여 다른 도시에 도착하는 M개의 버스
 * A도시에서 B도시까지 가는데 드는 최소비용을 출력하자.
 * 도시 번호는 1부터 N까지이다.
 *
 * 조건
 * 도시의 개수 N(1 ≤ N ≤ 1,000)
 * 버스의 개수 M(1 ≤ M ≤ 100,000)
 * 버스 비용은 0보다 크거나 같고, 100,000보다 작은 정수이다.
 *
 * 풀이
 */

public class Main {
	static int N, start, end;
	static ArrayList<Stop>[] bus;
	
	private static int dijkstra(int start) {
		int INF = 987654321;
		int[] cost = new int[N + 1];
		Arrays.fill(cost, INF);
		
		PriorityQueue<Stop> pq = new PriorityQueue<>();
		cost[start] = 0;
		pq.add(new Stop(start, 0));
		
		while (!pq.isEmpty()) {
			Stop cur = pq.poll();
			
			if (cost[cur.dest] < cur.cost)
				continue;
			
			for(Stop s : bus[cur.dest]) {
				if (cur.cost + s.cost < cost[s.dest]) {
					cost[s.dest] = cur.cost + s.cost;
					pq.add(new Stop(s.dest, cost[s.dest]));
				}
			}
		}
		
		return cost[end];
	}
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken()); // 도시의 개수
    	st = new StringTokenizer(br.readLine());
    	int M = Integer.parseInt(st.nextToken()); // 버스의 개수
    	
    	bus = new ArrayList[N + 1];
    	
    	for (int idx = 0; idx <= N; idx++) {
    		bus[idx] = new ArrayList<>();
    	}
    	
    	for (int idx = 0; idx < M; idx++) {
    		st = new StringTokenizer(br.readLine());
    		
    		bus[Integer.parseInt(st.nextToken())].add(new Stop(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
    	}
    	
    	st = new StringTokenizer(br.readLine());
    	start = Integer.parseInt(st.nextToken());
    	end = Integer.parseInt(st.nextToken());
    	
    	System.out.println(dijkstra(start));
    }
    private static class Stop implements Comparable<Stop> {
    	int dest, cost;

		public Stop(int dest, int cost) {
			this.dest = dest;
			this.cost = cost;
		}

		@Override
		public int compareTo(Stop o) {
			// TODO Auto-generated method stub
			return this.cost - o.cost;
		}
    }
}
