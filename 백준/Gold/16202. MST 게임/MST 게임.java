import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * @author jihye.byun
 * BOJ 16202 MST 게임 골드4
 *
 * 문제
 *
 * 조건
 * 그래프 정점의 개수 N (2 ≤ N ≤ 1,000)
 * 그래프 간선의 개수 M (1 ≤ M ≤ min(10,000, N×(N-1)/2))
 * 턴의 수 K (1 < K ≤ 100)
 *
 * 풀이
 * 1. 크루스칼 알고리즘을 적용하자.
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
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	
    	int N = Integer.parseInt(st.nextToken()); // 정점의 개수
    	int M = Integer.parseInt(st.nextToken()); // 간선의 개수
    	int K = Integer.parseInt(st.nextToken()); // 턴의 수
    	
    	int[] score = new int[K + 1];
    	int[][] edge = new int[M + 1][2];
    	
    	// 간선 정보 입력받기
    	for (int idx = 1; idx <= M; idx++) {
    		st = new StringTokenizer(br.readLine());
    		edge[idx][0] = Integer.parseInt(st.nextToken());
    		edge[idx][1] = Integer.parseInt(st.nextToken());
    	}
    	
    	int round = 1;
    	
    	while (round <= K) {
    		
    		int[] parent = new int[N + 1];
    		int count = 0;
    		int sum = 0;
    		
    		// makeset
    		for (int idx = 1; idx <= N; idx++) {
    			parent[idx] = idx;
    		}
    		
    		for (int idx = round; idx <= M; idx++) {
    			int px = findset(edge[idx][0], parent);
    			int py = findset(edge[idx][1], parent);
    			
    			if (px == py)
    				continue;
    			
    			union(px, py, parent);
    			count++;
    			sum += idx;
    			
    			if (count == N - 1)
    				break;
    		}
    		
    		if (count != N - 1)
    			break;
    		
    		score[round] = sum;
    		round++;
    	}
    	
    	for (int idx = 1; idx <= K; idx++) {
    		bw.write(score[idx] + " ");
    	}
    	bw.flush();
    }
}