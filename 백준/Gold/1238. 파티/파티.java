import java.util.*;
import java.io.*;


/**
 * @author jihye.byun
 * BOJ 1238 파티 골드3 다익스트라
 *
 * 문제
 * N개의 숫자로 구분된 각각의 마을에 한 명의 학생이 살고 있다.
 * N명의 학생이 X마을에 모였다가 다시 그들의 마을로 돌아가는 최단 시간을 구하자.
 * N명의 학생들 중 오고 가는데 가장 많은 시간을 소비하는 학생을 구하자.
 * 오고 가는데 가장 오래 걸리는 학생의 소요시간을 출력한다.
 *
 * 조건
 * 마을의 수 N(1 ≤ N ≤ 1,000)
 * 단방향 도로의 개수 M(1 ≤ M ≤ 10,000)
 * 모일 마을 번호 X(1 ≤ X ≤ N)
 * i번째 길을 지나는데 소비하는 시간 Ti(1 ≤ Ti ≤ 100)
 *
 * 풀이
 * 1. 마을 X를 기준으로 X로 가는 시간, 오는 시간을 따로 구하자.
 * 1-1. 가는 시간과 오는 시간을 따로 구하기 위해 인접 행렬로 만들자.
 */

public class Main {
	
	private static class Node implements Comparable<Node>{
		int dest, time;
		public Node(int dest, int time) {
			this.dest = dest;
			this.time = time;
		}
		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.time - o.time;
		}
	}
	
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int N = Integer.parseInt(st.nextToken()); // 마을의 수
    	int M = Integer.parseInt(st.nextToken()); // 도로의 개수
    	int X = Integer.parseInt(st.nextToken()); // 모일 마을 번호
    	
    	int[][] adjArr = new int[N + 1][N + 1]; // 도로 연결 정보를 저장할 인접행렬
    	
    	// 도로 정보 입력받기
    	for (int cnt = 0; cnt < M; cnt++) {
    		st = new StringTokenizer(br.readLine());
    		adjArr[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
    	}
    	
    	// 최소시간을 저장할 배열 생성
    	int[] timeToX = new int[N + 1];
    	int[] timeFromX = new int[N + 1];
    	
    	// 배열의 모든 칸을 INF로 초기화
    	for (int idx = 0; idx <= N; idx++) {
    		timeToX[idx] = 987654321;
    		timeFromX[idx] = 987654321;
    	}
    	
    	// X로 가는 최단시간 구하기
    	dijkstraToX(X, timeToX, adjArr, N);
    	
    	// X에서 돌아오는 최단시간 구하기
    	dijkstraFromX(X, timeFromX, adjArr, N);
    	
    	// 최장시간 구하기
    	int maxTime = 0;
    	for (int idx = 1; idx <= N; idx++) {
    		maxTime = Math.max(maxTime, timeToX[idx] + timeFromX[idx]);
    	}
    	
    	System.out.println(maxTime);
    }
    
    private static void dijkstraToX(int startIdx, int[] time, int[][] adjArr, int size) {
    	PriorityQueue<Node> pq = new PriorityQueue<>();
    	boolean[] visit = new boolean[size + 1];
    	
    	time[startIdx] = 0;
    	pq.add(new Node(startIdx, 0));
    	
    	while (!pq.isEmpty()) {
    		Node cur = pq.poll();
    		
    		if (visit[cur.dest]) continue;
    		visit[cur.dest] = true;
    		
    		for (int idx = 1; idx <= size; idx++) {
    			if (adjArr[cur.dest][idx] == 0) continue;
    			
    			int curTime = cur.time + adjArr[cur.dest][idx];
    			
    			if (curTime < time[idx]) {
    				time[idx] = curTime;
    				pq.add(new Node(idx, curTime));
    			}
    		}
    	}
    }
    
    private static void dijkstraFromX(int startIdx, int[] time, int[][] adjArr, int size) {
    	PriorityQueue<Node> pq = new PriorityQueue<>();
    	boolean[] visit = new boolean[size + 1];
    	
    	time[startIdx] = 0;
    	pq.add(new Node(startIdx, 0));
    	
    	while (!pq.isEmpty()) {
    		Node cur = pq.poll();
    		
    		if (visit[cur.dest]) continue;
    		visit[cur.dest] = true;
    		
    		for (int idx = 1; idx <= size; idx++) {
    			if (adjArr[idx][cur.dest] == 0) continue;
    			
    			int curTime = cur.time + adjArr[idx][cur.dest];
    			
    			if (curTime < time[idx]) {
    				time[idx] = curTime;
    				pq.add(new Node(idx, curTime));
    			}
    		}
    	}
    }
}
