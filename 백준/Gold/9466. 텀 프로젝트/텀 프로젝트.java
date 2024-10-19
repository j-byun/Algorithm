import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 9466 텀 프로젝트 골드3
 * 시간 제한 3초 | 메모리 제한 256 MB
 *
 * 문제
 *
 * 조건
 *
 * 풀이
 */

public class Main {
	
	static int[] arr;
	static boolean[] visit, team;
	static int count;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	StringBuilder sb = new StringBuilder();

    	int T = Integer.parseInt(st.nextToken());
    	
    	for (int tc = 1; tc <= T; tc++) {
    		st = new StringTokenizer(br.readLine());
    		int n = Integer.parseInt(st.nextToken());
    		
    		arr = new int[n + 1];
    		visit = new boolean[n + 1];
    		team = new boolean[n + 1];
    		
    		// 입력받기
    		st = new StringTokenizer(br.readLine());
    		for (int idx = 1; idx <= n; idx++) {
    			arr[idx] = Integer.parseInt(st.nextToken());
    		}
    		
    		// 팀 만들기
    		count = 0;
    		for (int idx = 1; idx <= n; idx++) {
    			if (team[idx]) continue;
    			dfs(idx);
    		}
    		
    		// 팀 없는 애들 확인
    		sb.append(n - count).append("\n");
    	}
    	
    	System.out.println(sb.toString());
    }
    
    static void dfs(int cur) {
    	if (team[cur]) return;
    	if (visit[cur]) {
    		team[cur] = true;
    		count++;
    	}
    	else visit[cur] = true;

    	dfs(arr[cur]);
    	
    	visit[cur] = false;
    	team[cur] = true;
    }
}
