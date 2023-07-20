import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @author jihye.byun
 * BOJ 16398 행성 연결 골드4
 *
 * 문제
 * 홍익 제국의 중심 행성 T
 * N개의 행성 간에 플로우를 설치하자.
 * 두 행성 간에 플로우를 설치하면 제국군 주둔하는 비용이 든다.
 * 제국의 모든 행성을 연결하면서 관리 비용을 최소한으로 하자.
 *
 * 조건
 * 행성의 수 N (1 ≤ N ≤ 1000)
 * i행성과 j행성 간의 플로우 관리 비용 Cij (1 ≤ i, j ≤ N), ( 1 ≤ Cij ≤ 100,000,000)
 * Cij = Cji, Cii = 0
 *
 * 풀이
 * 1. MST - 크루스칼 알고리즘을 적용하자.
 * 2. Cij = Cji이니, j > i 일 때에만 비용 오름차순 우선순위 큐에 저장하자.
 */

public class Main {
	
	private static int findset(int x, int[] parent) {
		if (x != parent[x])
			return parent[x] = findset(parent[x], parent);
		return x;
	}
	
	private static void union(int x, int y, int[] parent) {
		parent[y] = x;
	}

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int N = Integer.parseInt(st.nextToken()); // 행성의 개수
    	int[][] arr = new int[N][N]; // 두 행성을 연결하는 데에 드는 관리 비용을 저장할 배열
    	int[] parent = new int[N]; // 각 행성의 부모 행성 번호를 저장할 배열
    	
    	// 행성 연결 관리 비용 입력받기
    	for (int row = 0; row < N; row++) {
    		st = new StringTokenizer(br.readLine());
    		for (int col = 0; col < N; col++) {
    			arr[row][col] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	// 비용 오름차순으로 정렬되는 우선순위 큐에 두 행성을 연결하는 데에 드는 관리 비용을 저장하자
    	// Cit = Cji 이기 때문에 arr 배열에서 j가 i보다 큰 경우만 고려해주자
    	PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
    	
    	for (int row = 0; row < N; row++) {
    		for (int col = row + 1; col < N; col++) {
    			pq.add(new int[] {row, col, arr[row][col]});
    		}
    		
    		// makeset
    		parent[row] = row;
    	}
    	
    	// N-1개 만큼의 간선을 연결시키자
    	int count = 0;
    	long sum = 0;
    	
    	while (!pq.isEmpty()) {
    		int[] cur = pq.poll();
    		
    		int px = findset(cur[0], parent);
    		int py = findset(cur[1], parent);
    		
    		if (px == py) continue;
    		
    		union(px, py, parent);
    		count++;
    		sum += cur[2];
    		if (count == N - 1)
    			break;
    	}
    	
    	System.out.println(sum);
    }
}
