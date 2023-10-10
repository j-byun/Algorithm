import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 2075 N번째 큰 수 실버2
 *
 * 문제
 *
 * 조건
 *
 * 풀이
 */

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int N = Integer.parseInt(st.nextToken());
    	PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
    	
    	for (int row = 0; row < N; row++) {
    		st = new StringTokenizer(br.readLine());
    		for (int col = 0; col < N; col++) {
    			pq.add(Integer.parseInt(st.nextToken()));
    		}
    	}
    	
    	for (int idx = 1; idx < N; idx++) {
    		pq.poll();
    	}
    	
    	System.out.println(pq.poll());
    }
}
