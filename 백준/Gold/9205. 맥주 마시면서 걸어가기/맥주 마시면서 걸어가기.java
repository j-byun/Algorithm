import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 9205 맥주 마시면서 걸어가기 골드5
 * 시간 제한 1초 | 메모리 제한 128 MB
 *
 * 문제
 *
 * 조건
 *
 * 풀이
 * 1. 순열을 만들면서 다음 위치까지의 거리가 1,000 이내인지 확인하자.
 * -> 시간 초과
 * 2. 모든 편의점을 들리지는 않아도 되는구나!
 */

public class Main {
	
	static int n; // 편의점의 개수 최대 100
	static int[][] coord; // 좌표를 저장할 배열
	static boolean[] visit; // 각 위치의 방문 여부를 표시할 배열
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	StringBuilder sb = new StringBuilder();
    	
    	int t = Integer.parseInt(br.readLine());
    	
    	for (int tc = 1; tc <= t; tc++) {
    		n = Integer.parseInt(br.readLine());
    		coord = new int[n + 2][2];
    		visit = new boolean[n + 2];
    		
    		for (int idx = 0; idx < n + 2; idx++) {
    			st = new StringTokenizer(br.readLine());
    			coord[idx][0] = Integer.parseInt(st.nextToken());
    			coord[idx][1] = Integer.parseInt(st.nextToken());
    		}
    		
    		sb.append(bfs() ? "happy" : "sad").append("\n");
    	}
    	
    	System.out.println(sb.toString());
    }
    
    static boolean bfs() {
    	Queue<int[]> queue = new ArrayDeque<>();
    	queue.add(coord[0]);
    	
    	while (!queue.isEmpty()) {
    		int[] cur = queue.poll();
    		
    		if (calcDist(cur[0], cur[1], coord[n + 1][0], coord[n + 1][1]) <= 1000) {
    			return true;
    		}
    		
    		for (int idx = 1; idx <= n; idx++) {
    			if (visit[idx]) continue;
    			if (calcDist(cur[0], cur[1], coord[idx][0], coord[idx][1]) > 1000) continue;
    			queue.add(coord[idx]);
    			visit[idx] = true;
    		}
    	}
    	
    	return false;
    }
    
    static int calcDist(int x, int y, int a, int b) {
    	return Math.abs(x - a) + Math.abs(y - b);
    }
}
