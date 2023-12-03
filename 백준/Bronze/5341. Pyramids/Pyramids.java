import java.util.*;

/**
 * @author jihye.byun
 * BOJ 5341 Pyramids 브론즈5
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
    	
    	while (true) {
    		
    		int input = sc.nextInt();
    		
    		if (input == 0) return;
    		
    		Long answer = 0L;
    		
    		for (int idx = 1; idx <= input; idx++) {
    			answer += idx;
    		}
    		
    		System.out.println(answer);
    	}
    	
    }
}
