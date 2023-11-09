import java.util.*;

/**
 * @author jihye.byun
 * BOJ 17266 어두운 굴다리 실버4
 *
 * 문제
 *
 * 조건
 * 굴다리의 길이 N (1 ≤ N ≤ 100,000)
 * 가로등의 개수 M (1 ≤ M ≤ N)
 * 가로등의 위치 x는 오름차순으로 입력받으며 가로등의 위치는 중복되지 않으며, 정수이다.
 *
 * 풀이
 */

public class Main {
	
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	int N = sc.nextInt();
    	int M = sc.nextInt();
    	
    	int minHeight = N;
    	int before = 0;
    	int cur, gap;
    	
    	for (int cnt = 0; cnt < M; cnt++) {
    		cur = sc.nextInt();
    		gap = cur - before;
    		before = cur;
    		
    		minHeight = Math.max(minHeight, (int) Math.ceil((double) gap / 2));
    		
    		if (cnt == 0)
    			minHeight = cur;
    		
    		if (cnt == M - 1)
    			minHeight = Math.max(minHeight, N - cur);
    	}
    	
    	System.out.println(minHeight);
    }
}
