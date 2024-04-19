import java.util.*;

/**
 * @author jihye.byun
 * BOJ 1654 랜선 자르기 실버2
 * 시간 제한 2초 | 메모리 제한 128 MB
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
    	
    	int K = sc.nextInt(); // 가지고 있는 랜선의 개수
    	int N = sc.nextInt(); // 필요한 랜선의 개수
    	
    	int[] lan = new int[K];
    	long max = 0L;
    	
    	for (int idx = 0; idx < K; idx++) {
    		lan[idx] = sc.nextInt();
    		
    		max = Math.max(max, lan[idx]);
    	}
    	
    	System.out.println(binarySearch(0L, (long) max + 1, K, N, lan));
    }
    
    static long binarySearch(long start, long end, int K, int N, int[] lan) {
    	
    	long mid = 0;
    	int count = 0;
    	
    	while (start < end) {
    		mid = (start + end) / 2;
    		
    		count = 0;
    		for (int idx = 0; idx < K; idx++) {
    			count += lan[idx] / mid;
    		}
    		
    		if (count < N) {
    			end = mid;
    		} else {
    			start = mid + 1;
    		}
    	}
    	
    	return start - 1;
    }
}
