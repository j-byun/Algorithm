import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @author jihye.byun
 * BOJ 1647 도시 분할 계획 골드4
 *
 * 문제
 * N개의 집과 그 집들을 연결하는 M개의 길
 * 길은 어느 방향으로든지 다닐 수 있다.
 * 각 길만다 길을 유지하는데 드는 유지비가 있다.
 * 임의의 두 집 사이에 경로가 항상 존재한다.
 * 
 * 마을을 두 개로 분리하자.
 * 각 분리된 마을 안에 집들이 서로 연결되도록 분할해야 한다.
 * 마을에는 집이 하나 이상 있어야 한다.
 * 분리된 두 마을 사이의 길들은 없애고,
 * 각 분리된 마을 안에서도 임의의 두 집 사이에 경로가 항상 존재하게 하면서
 * 나머지 길의 유지비의 합을 최소로 하게 만들자.
 *
 * 조건
 * 집의 개수 N (2 <= N <= 100,000)
 * 길의 개수 M (1 <= M <= 1,000,000)
 * 두 집을 연결하는 길의 유지비 C (1 <= C <= 1,000)
 *
 * 풀이
 * 1. MST - 크루스칼 알고리즘
 * 2. 마을을 두 개로 나누어야 하니 N - 2개 만큼의 길을 연결하자.
 */

public class Main {
	
	private static int findset(int x, int[] parent) {
		if (parent[x] != x)
			return parent[x] = findset(parent[x], parent);
		return x;
	}
	
	private static void union(int x, int y, int[] parent) {
		parent[y] = x;
	}
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int N = Integer.parseInt(st.nextToken()); // 집의 개수
    	int M = Integer.parseInt(st.nextToken()); // 길의 개수
    	
    	int[] parent = new int[N + 1]; // 부모 노드 번호를 저장할 배열 공간
    	PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]); // 두 집을 연결하는 길의 정보를 저장할 배열 공간
    	// 길은 유지비 오름차순으로 정렬된다
    	
    	// makeset
    	for (int idx = 0; idx <= N; idx++) {
    		parent[idx] = idx;
    	}
    	
    	// 길 정보 입력받기
    	for (int idx = 0; idx < M; idx++) {
    		st = new StringTokenizer(br.readLine());
    		pq.add(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
    	}
    	
    	int sum = 0; // 전체 길의 유지비 합을 저장할 변수
    	int count = 0; // 연결시킨 길의 개수를 세어줄 변수
    	
    	// 유지비가 적은 길부터 차례대로 확인해보자
    	for (int idx = 0; idx < M; idx++) {
    		int[] cur = pq.poll();
    		
    		int px = findset(cur[0], parent);
    		int py = findset(cur[1], parent);
    		
    		// 길로 연결된 두 집이 이미 연결되어 있는지 확인하자
    		// 두 집이 이미 연결되었다면 다음 길을 확인하자
    		if (px == py) continue;
    		
    		// 연결시킨 길의 개수가 N-2개가 되었다면 모든 집이 두 개의 마을로 나누어졌으니 탐색을 종료하자
    		if (count == N - 2) break;
    		// 두 집이 아직 연결되지 않았다면 연결시키자
    		union(px, py, parent);
    		// 현재 길의 유지비를 전체 길 유지비에 더해주자
    		sum += cur[2];
    		// 연결시킨 길의 개수를 +1 해주자
    		count++;
    	}
    	
    	// 최종 유지비의 합을 출력하자
    	System.out.println(sum);
    }
}
