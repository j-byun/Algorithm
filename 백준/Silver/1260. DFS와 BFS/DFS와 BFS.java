import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 1260 DFS와 BFS 실버2
 * 시간 제한 2초 | 메모리 제한 128 MB
 *
 * 문제
 * 그래프를 DFS로 탐색한 결과와 BFS로 탐색한 결과를 출력하자.
 * 방문할 수 있는 정점이 여러 개인 경우에는 번호가 작은 것을 먼저 방문한다.
 * 방문할 수 있는 점이 없는 경우 종료한다.
 * 정점 번호는 1번부터 N번까지이다.
 *
 * 조건
 * 정점의 개수 N(1 ≤ N ≤ 1,000)
 * 간선의 개수 M(1 ≤ M ≤ 10,000)
 * 입력으로 주어지는 간선은 양방향이다.
 *
 * 풀이
 */

public class Main {
	
	static StringBuilder sb;
	static int N;
	static boolean[][] adjArr;
	static boolean[] visit;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	sb = new StringBuilder();
    	
    	N = Integer.parseInt(st.nextToken()); // 정점의 개수
    	int M = Integer.parseInt(st.nextToken()); // 간선의 개수
    	int V = Integer.parseInt(st.nextToken()); // 시작할 정점의 번호
    	
    	adjArr = new boolean[N + 1][N + 1]; // 연결 정보를 나타내는 인접 행렬
    	
    	for (int count = 0; count < M; count++) {
    		st = new StringTokenizer(br.readLine());
    		int a = Integer.parseInt(st.nextToken());
    		int b = Integer.parseInt(st.nextToken());
    		
    		adjArr[a][b] = true;
    		adjArr[b][a] = true;
    	}
    	
    	visit = new boolean[N + 1];
    	dfs(V);
    	sb.append("\n");
    	
    	visit = new boolean[N + 1];
    	bfs(V);
    	
    	System.out.println(sb.toString());
    }
    
    static void dfs(int start) {
    	sb.append(start).append(" ");
    	visit[start] = true;
    	
    	for (int next = 1; next <= N; next++) {
    		if (!adjArr[start][next]) continue;
    		if (visit[next]) continue;
    		dfs(next);
    	}
    }
    
    static void bfs(int start) {
    	Queue<Integer> queue = new ArrayDeque<>();
    	queue.add(start);
    	visit[start] = true;
    	
    	while (!queue.isEmpty()) {
    		int cur = queue.poll();
    		sb.append(cur).append(" ");
    		
    		for (int next = 1; next <= N; next++) {
    			if (!adjArr[cur][next]) continue;
        		if (visit[next]) continue;
        		visit[next] = true;
        		queue.add(next);
    		}
    	}
    }
}
