import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 15657 N과 M (8) 실버3	
 *
 * 문제
 *
 * 조건
 *
 * 풀이
 */

public class Main {
	static int N;
	static int[] arr, perm;
    static boolean[] visit;
	static StringBuilder sb;
	static LinkedHashSet<String> answer;
	
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	N = sc.nextInt();
    	int M = sc.nextInt();
    	
    	arr = new int[N];
        visit = new boolean[N];
        perm = new int[M];
    	
    	for (int idx = 0; idx < N; idx++) {
    		arr[idx] = sc.nextInt();
    	}
    	Arrays.sort(arr);
    	
    	answer = new LinkedHashSet<>();
    	dfs(0, M);
    	
    	for (String perm : answer) {
    		System.out.println(perm);
    	}
    }
    
    static void dfs(int depth, int maxDepth) {
    	
    	if (depth == maxDepth) {
    		sb = new StringBuilder();
    		for (int idx = 0; idx < maxDepth; idx++) {
    			sb.append(perm[idx]).append(" ");
    		}
    		answer.add(sb.toString());
    		return;
    	}
    	
    	for (int idx = 0; idx < N; idx++) {
    		if (visit[idx]) continue;
    		perm[depth] = arr[idx];
            visit[idx] = true;
    		dfs(depth + 1, maxDepth);
            visit[idx] = false;
    	}
    }
}
