import java.util.*;

/**
 * @author jihye.byun
 * BOJ 10799 쇠막대기 실버2
 * 시간 제한 1초 | 메모리 제한 256 MB
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
    	
    	char[] poll = sc.next().toCharArray();
    	int size = poll.length;
    	int count = 0;
    	
    	Stack<Character> stack = new Stack<>();
    	
    	for (int idx = 0; idx < size; idx++) {
    		if (poll[idx] == '(') {
    			if (idx < size - 1 && poll[idx + 1] == ')') {
    				count += stack.size();
    				idx++;
    			} else {
    				stack.push('(');
    			}
    		} else {
    			count++;
    			stack.pop();
    		}
    	}
    	
    	System.out.println(count);
    }
}
