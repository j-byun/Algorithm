import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 11265 끝나지 않는 파티 골드5
 * 시간 제한 2초 | 메모리 제한 256 MB
 *
 * 문제
 * 1개~N개의 파티장으로 증축하며 새로운 파티장과 기존의 모든 파티장이 직접적으로 연결되는 도로를 만들었다.
 * 도로는 일방 통행
 * 한 파티장에서 다른 파티장까지 시간 내에 도달할 수 있는지 없는지 확인하자.
 *
 * 조건
 * 파티장의 크기 N(5 ≤ N ≤ 500)
 * 서비스를 요청한 손님의 수 M(1 ≤ M ≤ 10,000)
 * 번 파티장에서 j번 파티장으로 직접적으로 연결된 도로를 통해 이동하는 시간 T(1 ≤ T ≤ 1,000,000)
 *
 * 풀이
 * 1. 모든 정점에서 모든 정점까지의 최단 거리 : 플로이드-워셜
 */

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	StringBuilder sb = new StringBuilder();
    	
    	int N = Integer.parseInt(st.nextToken());
    	int M = Integer.parseInt(st.nextToken());
    	
    	int[][] adjArr = new int[N][N];
    	
    	for (int start = 0; start < N; start++) {
    		st = new StringTokenizer(br.readLine());
    		for (int end = 0; end < N; end++) {
    			adjArr[start][end] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	for (int mid = 0; mid < N; mid++) {
    		for (int start = 0; start < N; start++) {
    			for (int end = 0; end < N; end++) {
    				adjArr[start][end] = Math.min(adjArr[start][end],
    						adjArr[start][mid] + adjArr[mid][end]);
    			}
    		}
    	}
    	
    	String answer = "";
    	for (int cnt = 0; cnt < M; cnt++) {
    		st = new StringTokenizer(br.readLine());
    		
    		answer = "Stay here\n";
    		
    		if (adjArr[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] <= Integer.parseInt(st.nextToken())) {
    			answer = "Enjoy other party\n";
    		}
    		
    		sb.append(answer);
    	}
    	
    	System.out.println(sb.toString());
    }
}
