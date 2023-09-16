import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 10773 제로 실버4
 *
 * 문제
 * 잘못된 수를 부를 때마다 0을 외쳐서 가장 최근에 쓴 수를 지우게 시킨다.
 * 이렇게 모든 수를 받아 적은 후 그 수의 합을 구하자.
 *
 * 조건
 * 수의 개수 K(1 ≤ K ≤ 100,000)
 * K개의 수 (0에서 1,000,000 사이의 정수)
 *
 * 풀이
 * 1. 수를 부를 때 마다 스택에 저장하고, 0을 외치면 스택에서 pop한다.
 */

public class Main {
	
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
        
    	Stack<Integer> stack = new Stack<>();
    	
    	int K = sc.nextInt();
    	for (int idx = 0; idx < K; idx++) {
    		int cur = sc.nextInt();
    		
    		if (cur == 0)
    			stack.pop();
    		else
    			stack.push(cur);
    	}
    	
    	long result = 0;
    	while (!stack.isEmpty()) {
    		result += stack.pop();
    	}
    	System.out.println(result);
    }
}
