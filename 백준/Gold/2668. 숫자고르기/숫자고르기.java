import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 2668 숫자고르기 골드5
 *
 * 문제
 *
 * 조건
 * N(1≤N≤100)
 *
 * 풀이
 */

public class Main {
	
	static int N;
	static int[] arr;
	static boolean[] visit;
	static Queue<Integer> queue;
	
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	N = sc.nextInt();
    	arr = new int[N + 1];
    	visit = new boolean[N + 1];
    	
    	for(int idx = 1; idx <= N; idx++) {
    		arr[idx] = sc.nextInt();
    	}
    	
    	queue = new ArrayDeque<>();
    	for (int idx = 1; idx <= N; idx++) {
    		visit[idx] = true;
    		dfs(idx, idx);
    		visit[idx] = false;
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	sb.append(queue.size()).append("\n");
    	while (!queue.isEmpty()) {
    		sb.append(queue.poll()).append("\n");
    	}
    	
    	System.out.println(sb.toString());
    }
    
    static void dfs(int start, int target) {
    	if (arr[start] == target)
    		queue.add(target);
    	
    	if (!visit[arr[start]]) {
    		visit[arr[start]] = true;
    		dfs(arr[start], target);
    		visit[arr[start]] = false;
    	}
    }
    
}
