import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @author jihye.byun
 * BOJ 2887 행성 터널 플래티넘5
 *
 * 문제
 * N개의 행성으로 이루어진 왕국을 효율적으로 지배하기 위해 행성을 연결하는 터널을 만들자.
 * 행성은 3차원 좌표위의 한 점으로 , 두 행성 A(xA,yA,zA)와 B(xB,yB,zB)를 터널로 연결할 때 드는 비용은
 * min(|xA-xB|, |yA-yB|, |zA-zB|)이다.
 * 터널을 총 N-1개 건설해서 모든 행성이 서로 연결되게 하려고 한다.
 * 이떄, 모든 행성을 터널로 연결하는데 필요한 최소 비용을 구하시오.
 *
 * 조건
 * 행성의 개수 N (1 <= N <= 100,000)
 * 각 행성의 좌표는 -10^9보다 크거나 같고, 10^9보다 작거나 같은 정수이다.
 * 한 위치에 행성이 두 개 이상 있는 경우는 없다.
 *
 * 풀이
 * 1. MST - 크루스칼 알고리즘을 적용하자.
 * 2. N*N개의 간선을 고려하면 메모리가 초과되니 고려해야할 간선의 개수를 줄이자.
 * 2-1. x, y, z 기준으로 행성들을 정렬해서 각각 바로 옆 좌표에 위치한 행성과의 거리만을 고려하자.
 * 2-2. 어차피 필요한건 '최소' 거리이기 때문에 바로 옆 좌표가 아닌 행성은 고려 대상이 아니다.
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
    	int[][] planet = new int[N][4]; // 각 행성의 인덱스 번호와 x, y, z 좌표를 저장할 배열 공간
    	int[] parent = new int[N]; // 각 행성의 부모 행성 번호를 저장할 배열 공간
    	
    	// 행성 번호와 좌표를 입력받자
    	for (int idx = 0; idx < N; idx++) {
    		st = new StringTokenizer(br.readLine());
    		planet[idx][0] = idx;
    		planet[idx][1] = Integer.parseInt(st.nextToken());
    		planet[idx][2] = Integer.parseInt(st.nextToken());
    		planet[idx][3] = Integer.parseInt(st.nextToken());
    	}
    	
    	// 두 행성의 번호와 그 사이의 거리를 저장할 우선순위 큐 공간을 만들자
    	// 거리 오름차순으로 정렬된다
    	PriorityQueue<Node> pq = new PriorityQueue<>();
    	
    	// 행성들을 x좌표 오름차순으로 정렬하자
    	Arrays.sort(planet, (o1, o2) -> o1[1] - o2[1]);
    	// x좌표 차이에 대해 고려해서 우선순위 큐에 넣어주자
    	for (int idx = 1; idx < N; idx++) {
    		pq.add(new Node(planet[idx - 1][0], planet[idx][0], Math.abs(planet[idx - 1][1] - planet[idx][1])));
    	}
    	
    	// 행성들을 y좌표 오름차순으로 정렬하자
    	Arrays.sort(planet, (o1, o2) -> o1[2] - o2[2]);
    	// y좌표 차이에 대해 고려해서 우선순위 큐에 넣어주자
    	for (int idx = 1; idx < N; idx++) {
    		pq.add(new Node(planet[idx - 1][0], planet[idx][0], Math.abs(planet[idx - 1][2] - planet[idx][2])));
    	}
    	
    	// 행성들을 z좌표 오름차순으로 정렬하자
    	Arrays.sort(planet, (o1, o2) -> o1[3] - o2[3]);
    	// z좌표 차이에 대해 고려해서 우선순위 큐에 넣어주자
    	for (int idx = 1; idx < N; idx++) {
    		pq.add(new Node(planet[idx - 1][0], planet[idx][0], Math.abs(planet[idx - 1][3] - planet[idx][3])));
    	}
    	
    	// makeset
    	for (int idx = 0; idx < N; idx++) {
    		parent[idx] = idx;
    	}

    	int count = 0; // 연결한 간선의 개수를 세어줄 변수
    	int sum = 0; // 연결에 사용된 비용(거리)의 합을 저장할 변수
    	
    	// 두 행성 간의 거리가 제일 짧은 간선부터 차례대로 확인해보자
    	while (!pq.isEmpty()) {
    		Node cur = pq.poll();
    		
    		// 간선으로 연결된 두 행성의 부모 행성을 확인해서 두 행성이 이미 연결된 상태인지 알아보자
    		int pa = findset(cur.a, parent);
    		int pb = findset(cur.b, parent);
    		
    		// 두 행성이 이미 연결된 상태라면 다음 간선을 확인하자
    		if (pa == pb) continue;
    		
    		// 두 행성이 아직 연결되지 않은 상태라면 연결시키자
    		union(pa, pb, parent);
    		// 연결 비용에 현재 간선 비용을 더해주자
    		sum += cur.cost;
    		// 연결시킨 간선의 개수를 +1 하자
    		count++;
    		// 간선의 개수가 N-1개가 되었다면 탐색을 종료하자
    		if (count == N - 1) break;
    	}
    	
    	// 탐색이 끝났다면 최종 비용을 출력하자
    	System.out.println(sum);
    }
    
    private static class Node implements Comparable<Node>{
    	int a, b, cost;

		public Node(int a, int b, int cost) {
			this.a = a;
			this.b = b;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
    }
}
