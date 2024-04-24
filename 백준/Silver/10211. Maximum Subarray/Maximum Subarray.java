import java.util.*;

/**
 * @author jihye.byun
 * BOJ 10211 Maximum Subarray 실버4
 * 시간 제한 1초 | 메모리 제한 256 MB
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
    	
    	int T = sc.nextInt();
    	
    	while (T-- > 0) {
    		int N = sc.nextInt();
    		
    		int prefixSum = 0;
    		int minSum = 0;
    		int maxSum = Integer.MIN_VALUE;
    		int answer = Integer.MIN_VALUE;
    		
    		for (int idx = 0; idx < N; idx++) {
    			int cur = sc.nextInt();
    			
    			prefixSum += cur;
    			answer = Math.max(answer, prefixSum - minSum);
    				
    			if (prefixSum < minSum) minSum = prefixSum;
    		}
    		
    		System.out.println(answer);
    	}
    }
}
