import java.util.*;

/**
 * @author jihye.byun
 * BOJ 2217 로프 실버4
 *
 * 문제
 *
 * 조건
 * N(1 ≤ N ≤ 100,000)
 *
 * 풀이
 */

public class Main {
	
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	int N = sc.nextInt();
    	Integer[] rope = new Integer[N];
    	
    	for (int idx = 0; idx < N; idx++) {
    		rope[idx] = sc.nextInt();
    	}
    	
    	Arrays.sort(rope, Collections.reverseOrder());
    	
    	int maxWeight = 0;
    	
    	for (int idx = 0; idx < N; idx++) {
    		maxWeight = Math.max(maxWeight, rope[idx] * (idx + 1));
    	}
    	
    	System.out.println(maxWeight);
    }
}
