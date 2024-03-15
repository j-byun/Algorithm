import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 11052 카드 구매하기 실버1
 * 시간 제한 1초 | 메모리 제한 256 MB
 *
 * 문제
 * 카드 팩의 가격이 주어졌을 때,
 * N개의 카드를 구매하기 위해 민규가 지불해야 하는 금액의 최댓값을 구하는 프로그램을 작성하시오.
 *
 * 조건
 * 카드의 개수 N (1 ≤ N ≤ 1,000)
 * 1 ≤ Pi ≤ 10,000
 *
 * 풀이
 */

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int N = Integer.parseInt(st.nextToken());
    	int[] dp = new int[N + 1];
    	
    	st = new StringTokenizer(br.readLine());
    	for (int idx = 1; idx <= N; idx++) {
    		dp[idx] = Integer.parseInt(st.nextToken());
    		
    		for (int before = idx - 1; before >= idx / 2; before--) {
    			dp[idx] = Math.max(dp[idx], dp[before] + dp[idx - before]);
    		}
    	}
    	
    	System.out.println(dp[N]);
    }
}
