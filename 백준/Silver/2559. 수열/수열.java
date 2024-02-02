import java.util.*;

/**
 * @author jihye.byun
 * BOJ 2559 수열 실버3
 *
 * 문제
 *
 * 조건
 *
 * 풀이
 */

public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	int N = sc.nextInt();
    	int K = sc.nextInt();
    	
    	int[] arr = new int[N];
    	
    	for (int idx = 0; idx < N; idx++) {
    		arr[idx] = sc.nextInt();
    	}
    	
    	int sum = 0;
    	int maxSum = 0;
    	
    	for (int idx = 0; idx < K; idx++) {
    		sum += arr[idx];
    	}
    	
    	maxSum = sum;
    	
    	int start = 0;
    	int end = K - 1;
    	
    	while (end < N - 1) {
    		sum += arr[++end];
    		sum -= arr[start++];
    		
    		maxSum = Math.max(maxSum, sum);
    	}
    	
    	System.out.println(maxSum);
    }
}
