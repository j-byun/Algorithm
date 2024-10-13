import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 1717 집합의 표현 골드5
 * 시간 제한 2초 | 메모리 제한 128 MB
 *
 * 문제
 * 0 a b : 합집합
 * 1 a b : 같은 집합인지 확인
 *
 * 조건
 *
 * 풀이
 * 1. Union-Find
 */

public class Main {
	
	static int[] parent;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	StringBuilder sb = new StringBuilder();
    	
    	int n = Integer.parseInt(st.nextToken()); // 집합 개수
    	int m = Integer.parseInt(st.nextToken()); // 연산 개수
    	
    	// 부모 배열 초기화
    	parent = new int[n + 1];
    	for (int idx = 0; idx <= n; idx++) {
    		parent[idx] = idx;
    	}
    	
    	for (int cnt = 0; cnt < m; cnt++) {
    		st = new StringTokenizer(br.readLine());
    		int operator = Integer.parseInt(st.nextToken());
    		int a = find(Integer.parseInt(st.nextToken()));
    		int b = find(Integer.parseInt(st.nextToken()));
    		
    		if (operator == 0) {
    			union(a, b);
    		} else {
    			sb.append((a == b) ? "YES" : "NO").append("\n");
    		}
    	}

    	System.out.println(sb.toString());
    }
    
    static int find(int a) {
    	if (a == parent[a]) return a;
    	return parent[a] = find(parent[a]);
    }
    
    static void union(int a, int b) {
    	if (a == b) return;
    	
    	if (a > b) {
    		parent[a] = b;
    	}
    	
    	if (a < b) {
    		parent[b] = a;
    	}
    }
}
