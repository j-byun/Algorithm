import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 1976 여행 가자 골드4
 *
 * 문제
 *
 * 조건
 *
 * 풀이
 */

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int N = Integer.parseInt(st.nextToken());
    	st = new StringTokenizer(br.readLine());
    	int M = Integer.parseInt(st.nextToken());
    	
    	int[][] adjArr = new int[N + 1][N + 1];
    	int[] travel = new int[M];
    	
    	for (int city1 = 1; city1 <= N; city1++) {
    		st = new StringTokenizer(br.readLine());
    		for (int city2 = 1; city2 <= N; city2++) {
    			adjArr[city1][city2] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	st = new StringTokenizer(br.readLine());
    	for (int idx = 0; idx < M; idx++) {
    		travel[idx] = Integer.parseInt(st.nextToken());
    	}
    	
    	boolean[] visit = new boolean[N + 1];
    	
    	bfs(travel[0], adjArr, visit, N);
    	
    	for (int idx = 0; idx < M; idx++) {
    		if (!visit[travel[idx]]) {
    			System.out.println("NO");
    			return;
    		}
    	}
    	System.out.println("YES");
    }
    
    static void bfs(int start, int[][] adjArr, boolean[] visit, int N) {
    	Queue<Integer> queue = new ArrayDeque<>();
    	queue.add(start);
    	visit[start] = true;
    	
    	while (!queue.isEmpty()) {
    		int cur = queue.poll();
    		
    		for (int dest = 1; dest <= N; dest++) {
    			if (adjArr[cur][dest] == 0) continue;
    			if (visit[dest]) continue;
    			visit[dest] = true;
    			queue.add(dest);
    		}
    	}
    }
    
}
