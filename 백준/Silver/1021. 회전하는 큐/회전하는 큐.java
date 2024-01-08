import java.util.*;

/**
 * @author jihye.byun
 * BOJ 1021 회전하는 큐 실버3
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
    	int M = sc.nextInt();
    	
    	ArrayDeque<Integer> deque = new ArrayDeque<>();
    	
    	for (int idx = 1; idx <= N; idx++) {
    		deque.add(idx);
    	}
    	
    	int answer = 0;
    	
    	while (M-- > 0) {
    		int num = sc.nextInt();
    		int count = 0;
    		
    		int first = deque.pollFirst();
    		
    		while (first != num) {
    			deque.add(first);
    			count++;
    			
    			first = deque.pollFirst();
    		}
    		
    		answer += Math.min(count, deque.size() + 1 - count);
    	}
    	
    	System.out.println(answer);
    }
}
