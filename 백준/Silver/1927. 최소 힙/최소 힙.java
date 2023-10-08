import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 1927 최소 힙 실버2
 *
 * 문제
 *
 * 조건
 * 연산의 개수 N(1 ≤ N ≤ 100,000)
 *
 * 풀이
 */

public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	StringBuilder sb = new StringBuilder();
    	
    	int N = sc.nextInt();
    	
    	PriorityQueue<Integer> pq = new PriorityQueue<>();
    	
    	for (int cnt = 0; cnt < N; cnt++) {
    		int input = sc.nextInt();
    		
    		if (input == 0) {
    			if (pq.isEmpty()) {
    				sb.append("0").append("\n");
    			} else {
    				sb.append(pq.poll()).append("\n");
    			}
    		} else {
    			pq.add(input);
    		}
    	}
    	System.out.println(sb);
    }
}
