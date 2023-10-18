import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 1138 한 줄로 서기 실버2	
 *
 * 문제
 * N명의 사람들이 
 *
 * 조건
 *
 * 풀이
 * 1. 다익스트라
 * 2. 왼쪽에 키큰 사람이 0명인 애들을 우큐에 넣자.
 * 3. 우큐에서 뽑아서 우큐 값보다 작은 애들의 왼쪽 키큰 사람 수를 -1 해준다.
 * 4. -1해주다가 0이 되는 애가 생기면 우큐에 추가하자.
 */

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	StringBuilder sb = new StringBuilder();
    	
    	int N = Integer.parseInt(st.nextToken());
    	int[] people = new int[N + 1];
    	PriorityQueue<Integer> pq = new PriorityQueue<>();
    	
    	st = new StringTokenizer(br.readLine());
    	for (int idx = 1; idx <= N; idx++) {
    		people[idx] = Integer.parseInt(st.nextToken());
    		
    		if (people[idx] == 0) pq.add(idx);
    	}
    	
    	while (!pq.isEmpty()) {
    		int cur = pq.poll();
    		sb.append(cur).append(" ");
    		
    		for (int idx = 1; idx < cur; idx++) {
    			if (--people[idx] == 0) pq.add(idx);
    		}
    	}
    	
    	System.out.println(sb);
    }
}
