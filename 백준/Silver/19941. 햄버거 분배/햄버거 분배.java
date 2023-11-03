import java.util.*;

/**
 * @author jihye.byun
 * BOJ 19941 햄버거 분배 실버3
 *
 * 문제
 * 식탁의 길이 N
 * 햄버거를 선택할 수 있는 거리 K
 *
 * 조건
 * 1 <= N <= 20,000
 * 1 <= K <= 10
 *
 * 풀이
 */

public class Main {
	
	static final int P = 0;
	static final int H = 1;
	
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	int N = sc.nextInt();
    	int K = sc.nextInt();
    	
    	int[] table = new int[N];

    	String line = sc.next();
    	for (int idx = 0; idx < N; idx++) {
    		if (line.charAt(idx) == 'H')
    			table[idx] = H;
    	}
    	
    	int count = 0;
    	boolean[] visit = new boolean[N];
    	
    	for (int idx = 0; idx < N; idx++) {
    		if (table[idx] == H) continue;
    		
    		for (int pick = idx - K; pick <= idx + K; pick++) {
    			if (pick < 0) continue;
    			if (pick >= N) break;
    			if (table[pick] == P) continue;
    			if (visit[pick]) continue;
    			visit[pick] = true;
    			count++;
    			break;
    		}
    	}
    	
    	System.out.println(count);
    }
}
