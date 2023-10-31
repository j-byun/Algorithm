import java.util.*;

/**
 * @author jihye.byun
 * BOJ 1758 알바생 강호 실버4
 *
 * 문제
 *
 * 조건
 *
 * 풀이
 * N은 100,000보다 작거나 같은 자연수
 * 팁은 100,000보다 작거나 같은 자연수이다.
 */

public class Main {
	
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	int N = sc.nextInt();
    	Integer[] tips = new Integer[N];
    	Long maxTip = 0L;
    	
    	for (int idx = 0; idx < N; idx++) {
    		tips[idx] = sc.nextInt();
    	}
    	
    	Arrays.sort(tips, Collections.reverseOrder());
    	
    	for (int idx = 0; idx < N; idx++) {
    		Long curTip = (long) (tips[idx] - idx);
    		
    		if (curTip < 0L) curTip = 0L;
    		
    		maxTip += curTip;
    	}
    	
    	
    	System.out.println(maxTip);
    }
}
