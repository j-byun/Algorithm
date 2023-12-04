import java.util.*;

/**
 * @author jihye.byun
 * BOJ 20310 타노스 실버3
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
    	
    	char[] input = sc.next().toCharArray();
    	
    	int size = input.length;
    	int zeroCount = 0;
    	int oneCount = 0;
    	
    	for (int idx = 0; idx < size; idx++) {
    		if (input[idx] == '0')
    			zeroCount++;
    		else
    			oneCount++;
    	}
    	
    	boolean[] used = new boolean[size];
    	
    	zeroCount /= 2;
    	for (int idx = 0; idx < size; idx++) {
    		if (input[idx] == '0') {
    			used[idx] = true;
    			if (--zeroCount == 0) break;
    		}
    	}
    	
    	oneCount /= 2;
    	for (int idx =  size - 1; idx >= 0; idx--) {
    		if (input[idx] == '1') {
    			used[idx] = true;
    			if (--oneCount == 0) break;
    		}
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	for (int idx = 0; idx < size; idx++) {
    		if (!used[idx]) continue;
    		
    		sb.append(input[idx]);
    	}
    	
    	System.out.println(sb.toString());
    }
}
