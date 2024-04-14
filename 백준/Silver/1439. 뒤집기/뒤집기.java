import java.util.*;

/**
 * @author jihye.byun
 * BOJ 1439 뒤집기 실버5
 * 시간 제한 2초 | 메모리 제한 128 MB
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
    	
    	int zeroCount = 0;
    	int oneCount = 0;
    	
    	for (int idx = 0; idx < input.length; idx++) {
    		if (idx > 0 && input[idx] == input[idx - 1]) continue;
    		
    		if (input[idx] == '0') zeroCount++;
    		if (input[idx] == '1') oneCount++;
    	}
    	
    	System.out.println(Math.min(zeroCount, oneCount));
    }
}
