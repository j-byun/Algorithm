import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 11403 경로 찾기 실버1
 * 시간 제한 1초 | 메모리 제한 256 MB
 *
 * 문제
 * i에서 j로 가는 길이가 양수인 경로가 있는지 구하자.
 *
 * 조건
 * 정점의 개수 N (1 ≤ N ≤ 100)
 *
 * 풀이
 */

public class Main {
	
	static int N;
	static int[][] adjArr, ansArr;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	StringBuilder sb = new StringBuilder();
    	
    	N = Integer.parseInt(st.nextToken());
    	
    	adjArr = new int[N][N];
    	ansArr = new int[N][N];
    	
    	for (int i = 0; i < N; i++) {
    		st = new StringTokenizer(br.readLine());
    		for (int j = 0; j < N; j++) {
    			adjArr[i][j] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	// 경로를 거꾸로 탐색하며 j까지 도달할 수 있는 모든 i를 찾자
    	for (int j = 0; j < N; j++) {
    		dfs(j, j);
    	}
    	
    	for (int i = 0; i < N; i++) {
    		for (int j = 0; j < N; j++) {
    			sb.append(ansArr[i][j]).append(" ");
    		}
    		sb.append("\n");
    	}
    	
    	System.out.println(sb.toString());
    }
    
    static void dfs(int cur, int end) {
    	
    	for (int i = 0; i < N; i++) {
    		if (adjArr[i][cur] != 1) continue;
    		if (ansArr[i][end] == 1) continue;
    		ansArr[i][end] = 1;
    		dfs(i, end);
    	}
    }
}
