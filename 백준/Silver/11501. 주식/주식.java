import java.util.*;

/**
 * @author jihye.byun
 * BOJ 11501 주식 실버2
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
    	StringBuilder sb = new StringBuilder();
    	
    	int tc = sc.nextInt();
    	
    	while (tc-- > 0) {
    		int n = sc.nextInt();
    		int[] arr = new int[n];
    		
    		for (int idx = 0; idx < n; idx++) {
    			arr[idx] = sc.nextInt();
    		}
    		
    		int max = arr[n - 1];
    		long sum = 0;
    		
    		for (int idx = n - 1; idx >= 0; idx--) {
    			if (arr[idx] >= max)
    				max = arr[idx];
    			else
    				sum += max - arr[idx];
    		}
    		
    		sb.append(sum).append("\n");
    	}
    	
    	System.out.println(sb.toString());
    }
}
