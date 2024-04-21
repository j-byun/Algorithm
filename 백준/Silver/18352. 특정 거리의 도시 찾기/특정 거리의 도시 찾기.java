import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 18352 특정 거리의 도시 찾기 실버2
 * 시간 제한 2초 | 메모리 제한 256 MB
 *
 * 문제
 *
 * 조건
 *
 * 풀이
 * 1. 다익스트라 진행 후 거리가 K인 도시만 출력하기.
 */

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	StringBuilder sb = new StringBuilder();
    	
    	int N = Integer.parseInt(st.nextToken()); // 도시의 개수
    	int M = Integer.parseInt(st.nextToken()); // 도로의 개수
    	int K = Integer.parseInt(st.nextToken()); // 거리 정보
    	int X = Integer.parseInt(st.nextToken()); // 출발 도시의 번호
    	
    	final int INF = 300_001;
    	
    	List<Integer>[] adjList = new ArrayList[N + 1]; // 단방향 도로 정보를 저장할 인접 행렬 -> 메모리 초과로 인접 리스트로 수정
    	
    	// 인접 리스트 초기화
    	for (int idx = 0; idx <= N; idx++) {
    		adjList[idx] = new ArrayList<>();
    	}
    	
    	// 도로 정보 입력받아서 인접 리스트에 저장
    	for (int cnt = 0; cnt < M; cnt++) {
    		st = new StringTokenizer(br.readLine());
    		adjList[Integer.parseInt(st.nextToken())].add(Integer.parseInt(st.nextToken()));
    	}
    	
    	int[] dist = new int[N + 1]; // 각 도시에 도착하는 최단 거리를 저장할 배열
    	boolean[] visit = new boolean[N + 1]; // 각 도시 방문 여부를 저장할 배열
    	Arrays.fill(dist, INF); // 최단 거리 배열 INF로 초기화
    	
    	// 다익스트라
    	// 우선순위 큐 정렬 기준은 해당 도시까지 가는 최단 거리
    	// X번 도시에서 시작
    	PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {
    		return dist[o1] - dist[o2];
    	});
    	
    	dist[X] = 0;
    	pq.add(X);
    	
    	while (!pq.isEmpty()) {
    		int cur = pq.poll();
    		
    		if (dist[cur] > K) break;
    		if (visit[cur]) continue;
    		visit[cur] = true;
    		
    		for (int next : adjList[cur]) {
    			if (dist[next] <= dist[cur] + 1) continue;
    			dist[next] = dist[cur] + 1;
    			pq.add(next);
    		}
    	}
    	
    	for (int city = 1; city <= N; city++) {
    		if (dist[city] == K) {
    			sb.append(city).append("\n");
    		}
    	}
    	
    	if (sb.length() == 0) {
    		sb.append(-1);
    	}
    	
    	System.out.println(sb.toString());
    }
}
