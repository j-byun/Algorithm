import java.util.Scanner;
import java.util.Stack;

/**
 * @author jihye.byun
 * BOJ 17298 오큰수 골드4
 *
 * 문제
 * 크기가 N인 수열 A가 있다.
 * 수열의 각 원소 Ai에 대해서 오큰수 NGE(i)를 구하려고 한다.
 * Ai의 오큰수는 오른쪽에 있으면서 Ai보다 큰 수 중에서 가장 왼쪽에 있는 수를 의미한다.
 * 그러한 수가 없는 경우에 오큰수는 -1이다.
 *
 * 조건
 * 수열 A의 크기 N (1 ≤ N ≤ 1,000,000)
 * 수열 A의 원소 A1, A2, ..., AN (1 ≤ Ai ≤ 1,000,000)
 *
 * 풀이
 * 1. 오큰수(i) : i-1번 인덱스보다 오른쪽에 있으면서 큰 수 중 가장 왼쪽에 있는 수
 */

public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	int N = sc.nextInt(); // 수열 A의 크기
    	int[] arr = new int[N]; // 수열 A
    	Stack<Integer> stack = new Stack<>(); // 수열 A의 원소들을 저장할 스택
    	
    	// 수열 A의 원소들 입력받기
    	for (int idx = 0; idx < N; idx++) {
    		arr[idx] = sc.nextInt();
    	}
    	
    	stack.add(0);
    	for (int idx = 1; idx < N; idx++) {
    		// 스택이 비어있지 않으면서 현재 원소가 스택의 맨 위 인덱스의 원소보다 큰 경우
    		// 해당 조건을 만족할 때 까지 스택의 원소를 pop하면서 해당 인덱스의 값을 현재 원소로 바꿔준다.
    		while (!stack.isEmpty() && arr[stack.peek()] < arr[idx]) {
    			arr[stack.pop()] = arr[idx];
    		}
    		
    		stack.push(idx);
    	}
    	
    	while (!stack.isEmpty()) {
    		arr[stack.pop()] = -1;
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	for (int idx = 0; idx < N; idx++) {
    		sb.append(arr[idx] + " ");
    	}
    	System.out.println(sb);
    }
}
