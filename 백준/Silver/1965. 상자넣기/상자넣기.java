import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 1965 상자넣기 실버2
 *
 * 문제
 *
 * 조건
 * 상자의 개수 n (1 ≤ n ≤ 1000)
 * 상자의 크기는 1,000을 넘지 않는 자연수
 *
 * 풀이
 * 1. LIS 구하는거
 */

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int N = Integer.parseInt(st.nextToken());
    	int[] arr = new int[N];
    	int[] dp = new int[N];
    	
    	st = new StringTokenizer(br.readLine());
    	for (int idx = 0; idx < N; idx++) {
    		arr[idx] = Integer.parseInt(st.nextToken());
    	}
    	
    	int max = 1;
    	for (int idx = 0; idx < N; idx++) {
    		dp[idx] = 1;
    		
    		for (int before = 0; before < idx; before++) {
    			if (arr[before] < arr[idx] && dp[idx] <= dp[before]) {
    				dp[idx] = dp[before] + 1;
    				
    				max = Math.max(max, dp[idx]);
    			}
    		}
    	}
    	
    	System.out.println(max);
    }
}
