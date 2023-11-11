import java.util.*;

/**
 * @author jihye.byun
 * BOJ 30394 회전하지 않는 캘리퍼스 브론즈3
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
    	int minY = Integer.MAX_VALUE;
    	int maxY = Integer.MIN_VALUE;
    	
    	while (N-- > 0) {
    		int x = sc.nextInt();
    		int y = sc.nextInt();
    		
    		minY = Math.min(minY, y);
    		maxY = Math.max(maxY, y);
    	}
    	
    	System.out.println(maxY - minY);
    }
    
}
