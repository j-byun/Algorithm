import java.util.Scanner;

/**
 * @author jihye.byun
 * BOJ 2631 줄세우기 골드4
 *
 * 문제
 * N명의 아이들을 번호 순서대로 줄세우자.
 * 위치를 옮기는 아이들의 수를 최소로 하여 번호 순서대로 배치하자.
 * 번호 순서대로 배치하기 위해 옮겨지는 아이의 최소 수를 구하는 프로그램을 작성하시오.
 *
 * 조건
 * N은 2 이상 200 이하의 정수
 *
 * 풀이
 * 1. dp를 활용하자.
 * 2. LDS를 구해서 (N - LDS)길이를 출력하자.
 */

public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	int N = sc.nextInt();
    	
    	int[] children = new int[N];
    	int[] dp = new int[N];
    	
    	for (int idx = 0; idx < N; idx++) {
    		children[idx] = sc.nextInt();
    	}
    	
    	dp[0] = 1;
    	int max = 1;
    	for (int idx = 1; idx < N; idx++) {
    		dp[idx] = 1;
    		
    		for (int check = 0; check < idx; check++) {
    			if (children[idx] > children[check])
    				dp[idx] = Math.max(dp[idx], dp[check] + 1);
    		}
    		
    		max = Math.max(max, dp[idx]);
    	}
    	
    	System.out.println(N - max);
    }
}
