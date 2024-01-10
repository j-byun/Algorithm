import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 1325 효율적인 해킹 실버1
 *
 * 문제
 * N개의 컴퓨터
 * A가 B를 신뢰하는 경우에는 B를 해킹하면 A도 해킹할 수 있다.
 * 한 번에 가장 많은 컴퓨터를 해킹할 수 있는 컴퓨터의 번호를 오름차순으로 출력하자.
 *
 * 조건
 * 전체 컴퓨터의 개수 N은 10,000보다 작거나 같은 자연수
 * 신뢰하는 관계의 개수 M은 100,000보다 작거나 같은 자연수
 * 컴퓨터는 1번부터 N번까지 번호가 하나씩 매겨져 있다.
 *
 * 풀이
 */

public class Main {
	
	static int N;
	static int[] hack;
	static ArrayList<Integer>[] adjList;
	static boolean[] visit;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	
    	N = Integer.parseInt(st.nextToken()); // 전체 컴퓨터 개수 N
    	int M = Integer.parseInt(st.nextToken()); // 신뢰하는 관계의 개수 M
    	
    	hack = new int[N + 1]; // 각 컴퓨터가 해킹할 수 있는 컴퓨터 대수를 저장할 배열
    	
    	adjList = new ArrayList[N + 1]; // 인접리스트
    	// * LinkedList보다 ArrayList로 접근이 더 빠름
    	
    	// 인접리스트 초기화
    	for (int idx = 0; idx <= N; idx++) {
    		adjList[idx] = new ArrayList<>();
    	}
    	
    	// M개의 신뢰하는 관계 정보 입력
    	for (int cnt = 0; cnt < M; cnt++) {
    		st = new StringTokenizer(br.readLine());
    		adjList[Integer.parseInt(st.nextToken())].add(Integer.parseInt(st.nextToken()));
    	}
    	
    	// 1~N번 노드까지 확인하며 a노드를 해킹할 수 있는 b노드에 해킹가능 수 +1
    	for (int idx = 1; idx <= N; idx++) {
    		bfs(idx);
    	}
    	
    	// 최대 해킹 가능 컴퓨터 대수 찾기
    	int maxHack = 0;
    	
    	for (int idx = 1; idx <= N; idx++) {
    		maxHack = Math.max(maxHack, hack[idx]);
    	}
    	
    	// 최대 해킹 가능 컴퓨터 대수 오름차순으로 출력
    	for (int idx = 1; idx <= N; idx++) {
    		if (hack[idx] < maxHack) continue;
    		bw.write(idx + " ");
    	}

    	bw.flush();
    }
    
    static void bfs(int start) {
    	
    	Queue<Integer> queue = new ArrayDeque<>();
    	visit = new boolean[N + 1];
    	
    	visit[start] = true;
    	queue.add(start);
    	
    	while (!queue.isEmpty()) {
    		int cur = queue.poll();
    		
    		for (int next : adjList[cur]) {
    			if (visit[next]) continue;
    			hack[next]++;
    			visit[next] = true;
    			queue.add(next);
    		}
    	}
    	
    }
}
