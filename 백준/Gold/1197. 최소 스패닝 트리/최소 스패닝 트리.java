import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author jihye.byun
 * BOJ 1197 최소 스패닝 트리 골드4
 *
 * 문제
 * 그래프의 최소 스패닝 트리를 구하자.
 * 최소 스패닝 트리 : 주어진 그래프의 모든 정점들을 연결하는 부분 그래프 중에서 그 가중치의 합이 최소인 트리
 *
 * 조건
 * 정점의 개수 V(1 ≤ V ≤ 10,000)
 * 간선의 개수 E(1 ≤ E ≤ 100,000)
 * 각 간선의 정보 A, B, C : A번 정점과 B번 정점이 가중치 C인 간선으로 연결되어 있다는 의미
 * C는 음수일 수도 있으며, 절댓값이 1,000,000을 넘지 않는다.
 * 최소 스패닝 트리의 가중치가 -2,147,483,648보다 크거나 같고, 2,147,483,647보다 작거나 같은 데이터만 입력으로 주어진다.
 *
 * 풀이
 * 1. MST : 크루스칼 / 프림
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
    	
    	int V = Integer.parseInt(st.nextToken()); // 정점의 개수
    	int E = Integer.parseInt(st.nextToken()); // 간선의 개수
    	
    	int[] parent = new int[V + 1]; // 각 노드의 부모 노드 번호를 저장할 배열
    	int[][] edge = new int[E][3]; // 간선 정보를 저장할 배열
    	
    	// 간선 정보 입력받기
    	for (int idx = 0; idx < E; idx++) {
    		st = new StringTokenizer(br.readLine());
    		edge[idx][0] = Integer.parseInt(st.nextToken());
    		edge[idx][1] = Integer.parseInt(st.nextToken());
    		edge[idx][2] = Integer.parseInt(st.nextToken());
    	}
    	
    	// 간선 정보를 가중치 오름차순으로 정렬하기
    	Arrays.sort(edge, (o1, o2) -> o1[2] - o2[2]);
    	
    	// makeset
    	for (int idx = 1; idx <= V; idx++) {
    		parent[idx] = idx;
    	}
    	
    	int sum = 0;
    	int count = 0;
    	
    	// 가중치가 낮은 간선부터 연결하기
    	for (int idx = 0; idx < E; idx++) {
    		int px = findset(edge[idx][0], parent);
    		int py = findset(edge[idx][1], parent);
    		
    		// 이미 연결된 노드면 패스
    		if (px == py) continue;
    		
    		// 연결되어 있지 않은 노드면 연결하고
    		union(px, py, parent);
    		
    		// 연결한 노드 개수 카운트 + 1
    		count++;
    		
    		// 가중치 합에 현재 간선 가중치 더하기
    		sum += edge[idx][2];
    		
    		// V - 1 개 만큼의 간선을 연결했으면 탐색 종료
    		if (count == V - 1) break;
    	}
    	
    	System.out.println(sum);
    }
}