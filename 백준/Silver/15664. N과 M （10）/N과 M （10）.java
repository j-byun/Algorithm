import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 15664 N과 M (10) 실버2
 * 시간 제한 1초 | 메모리 제한 512 MB
 *
 * 문제
 * N개의 자연수와 자연수 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하자.
 * 1. N개의 자연수 중에서 M개를 고른 수열
 * 2. 고른 수열은 비내림차순이어야 한다.
 *
 * 조건
 * 1 ≤ M ≤ N ≤ 8
 * 입력으로 주어지는 수는 10,000보다 작거나 같은 자연수이다.
 * 중복되는 수열을 여러 번 출력하면 안되며, 각 수열은 공백으로 구분해서 출력해야 한다.
 * 수열은 사전 순으로 증가하는 순서로 출력해야 한다.
 *
 * 풀이
 */

public class Main {
	
	static int N, M;
	static int[] arr, answer;
	static StringBuilder sb;
	static HashSet<String> set;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	sb = new StringBuilder();
    	
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	
    	arr = new int[N];
    	answer = new int[N];
    	
    	st = new StringTokenizer(br.readLine());
    	for (int idx = 0; idx < N; idx++) {
    		arr[idx] = Integer.parseInt(st.nextToken());
    	}
    	
    	Arrays.sort(arr);
    	
    	set = new HashSet<>();
    	perm(0, 0);
    	
    	System.out.println(sb.toString());
    }
    
    static void perm(int start, int depth) {
    	
    	if (depth == M) {
    		StringBuilder tempSb = new StringBuilder();
    		
    		for (int idx = 0; idx < M; idx++) {
    			tempSb.append(answer[idx]).append(" ");
    		}
    		
    		if (!set.contains(tempSb.toString())) {
    			sb.append(tempSb.toString()).append("\n");
    			set.add(tempSb.toString());
    		}
    		
    		return;
    	}
    	
    	for (int idx = start; idx < N; idx++) {
    		answer[depth] = arr[idx];
    		perm(idx + 1, depth + 1);
    	}
    }
}
