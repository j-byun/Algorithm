import java.util.*;

/**
 * @author jihye.byun
 * BOJ 11536 줄 세우기 실버5
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
    	
    	int N = sc.nextInt() - 1;
    	boolean isIncrease = true;
    	boolean isDecrease = true;
    	String before = sc.next();
    	
    	while (N-- > 0) {
    		String name = sc.next();
    		
    		// 양수면 오름차순, 음수면 내림차순
    		if (before.compareTo(name) < 0) isDecrease = false;
    		if (before.compareTo(name) > 0) isIncrease = false;
    		
    		before = name;
    	}
    	
    	System.out.println((isIncrease == false && isDecrease == false) ? "NEITHER" 
    			: (isIncrease == true) ? "INCREASING" : "DECREASING");
    }
}
