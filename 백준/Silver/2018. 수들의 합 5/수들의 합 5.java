import java.util.*;

/**
 * @author jihye.byun
 * BOJ 2018 수들의 합 5 실버5
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
    	
    	int start = 1;
    	int end = 1;
    	int sum = 1;
    	int count = 0;
    	
    	while (end <= N) {
    		if (end - start + 1 > N) {
    			sum -= start++;
    			continue;
    		}
    		
    		if (sum == N) {
    			count++;
    			sum -= start++;
    			continue;
    		}
    		
    		if (sum < N) {
    			sum += ++end;
    			continue;
    		}
    		
    		if (sum > N) {
    			sum -= start++;
    			continue;
    		}
    	}
    	
    	System.out.println(count);
    }
}
