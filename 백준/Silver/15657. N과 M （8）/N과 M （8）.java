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
	static int[] arr, answer;
	static StringBuilder sb;
	
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	N = sc.nextInt();
    	int M = sc.nextInt();
    	
    	arr = new int[N];
    	answer = new int[M];
    	
    	for (int idx = 0; idx < N; idx++) {
    		arr[idx] = sc.nextInt();
    	}
    	Arrays.sort(arr);
    	
    	sb = new StringBuilder();
    	dfs(0, 0, M);
    	
    	System.out.println(sb);
    }
    
    static void dfs(int start, int depth, int maxDepth) {
    	
    	if (depth == maxDepth) {
    		for (int idx = 0; idx < maxDepth; idx++) {
    			sb.append(answer[idx]).append(" ");
    		}
    		sb.append("\n");
    		return;
    	}
    	
    	for (int idx = start; idx < N; idx++) {
    		answer[depth] = arr[idx];
    		dfs(idx, depth + 1, maxDepth);
    	}
    }
}
