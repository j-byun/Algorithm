import java.util.*;

/**
 * @author jihye.byun
 * BOJ 5648 역원소 정렬 실버5
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
    	PriorityQueue<Long> pq = new PriorityQueue<>();
    	
    	while (N-- > 0) {
    		String value = sc.next();
    		
    		StringBuffer sb = new StringBuffer(value);
    		
    		pq.add(Long.parseLong(sb.reverse().toString()));
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	
    	while (!pq.isEmpty()) {
    		sb.append(pq.poll()).append("\n");
    	}
    	
    	System.out.println(sb.toString());
    }
}
