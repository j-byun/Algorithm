import java.util.*;

/**
 * @author jihye.byun
 * BOJ 1094 막대기 실버5
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
    	
    	int X = sc.nextInt();
    	int stick = 64;
    	int count = 0;
    	
    	while (X > 0) {
    		
    		if (X >= stick) {
    			X = X - stick;
    			count++;
    		}
    		
    		stick = stick >> 1;
    	}
    	
    	System.out.println(count);
    }
}
