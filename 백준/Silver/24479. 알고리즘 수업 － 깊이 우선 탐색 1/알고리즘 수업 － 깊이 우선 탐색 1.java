import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 24479 알고리즘 수업 - 깊이 우선 탐색 1
 * 시간 제한 1초 | 메모리 제한 512 MB
 *
 * 문제
 * 정점 R에서 시작하여 깊이 우선 탐색으로 노드를 방문할 경우 노드의 방문 순서를 출력하자.
 * 인접 정점은 오름차순으로 방문한다.
 *
 * 조건
 * 정점의 수 N (5 ≤ N ≤ 100,000)
 * 간선의 수 M (1 ≤ M ≤ 200,000)
 * 시작 정점 R (1 ≤ R ≤ N)
 *
 * 풀이
 */

public class Main {
	
	static List<Integer>[] adjList;
	static int[] order;
	static int count;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	StringBuilder sb = new StringBuilder();
    	
    	int N = Integer.parseInt(st.nextToken()); // 정점의 수
    	int M = Integer.parseInt(st.nextToken()); // 간선의 수
    	int R = Integer.parseInt(st.nextToken()); // 시작 정점 번호
    	
    	adjList = new ArrayList[N + 1]; // 인접 리스트 만들기
    	order = new int[N + 1]; // 각 노드의 방문 순서를 저장할 배열
    	
    	// 인접 리스트 초기화
    	for (int idx = 1; idx <= N; idx++) {
    		adjList[idx] = new ArrayList<>();
    	}
    	
    	// 간선 정보 입력받기
    	for (int cnt = 0; cnt < M; cnt++) {
    		st = new StringTokenizer(br.readLine());
    		int a = Integer.parseInt(st.nextToken());
    		int b = Integer.parseInt(st.nextToken());
    		
    		adjList[a].add(b);
    		adjList[b].add(a);
    	}
    	
    	// 각 노드에 연결된 간선 정보 오름차순으로 정렬
    	for (int idx = 1; idx <= N; idx++) {
    		Collections.sort(adjList[idx]);
    	}
    	
    	// R번 노드부터 방문 시작
    	count = 0;
    	dfs(R);
    	
    	// 방문 순서 출력
    	for (int idx = 1; idx <= N; idx++) {
    		sb.append(order[idx]).append("\n");
    	}
    	
    	System.out.println(sb.toString());
    }
    
    static void dfs(int node) {
    	
    	// 방문 순서 표시
    	order[node] = ++count;
    	
    	// 다음 노드 방문
    	for (Integer next : adjList[node]) {
    		if (order[next] > 0) continue;
    		dfs(next);
    	}
    }
}
