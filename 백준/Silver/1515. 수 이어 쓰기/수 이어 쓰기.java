import java.util.*;

/**
 * @author jihye.byun
 * BOJ 1515 수 이어 쓰기 실버3
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
    	
    	String line = sc.next();
    	int index = 0;
    	int n = 0;
    	
    	while (true) {
    		n++;
    		String num = Integer.toString(n);
    		
    		for (int idx = 0; idx < num.length(); idx++) {
    			if (num.charAt(idx) == line.charAt(index))
    				index++;
    			
    			if (index == line.length()) {
    				System.out.println(n);
    				return;
    			}
    		}
    	}
    	
    }
}
