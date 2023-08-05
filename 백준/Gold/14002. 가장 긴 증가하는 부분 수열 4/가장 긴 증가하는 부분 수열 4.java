import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * @author jihye.byun
 * BOJ 14002 가장 긴 증가하는 부분 수열 4 골드4
 *
 * 문제
 * 수열 A가 주어졌을 때, 가장 긴 증가하는 부분 수열을 구하자.
 * 첫쨰 줄에 수열 A의 가장 긴 증가하는 부분 수열의 길이를 출력한다.
 * 둘쨰 줄에는 가장 긴 증가하는 부분 수열을 출력한다.
 * 그러한 수열이 여러가지인 경우 아무거나 출력한다.
 *
 * 조건
 * 수열 A의 크기 N (1 ≤ N ≤ 1,000)
 * 수열 A를 이루고 있는 Ai가 주어진다. (1 ≤ Ai ≤ 1,000)
 *
 * 풀이
 * 1. dp배열을 이용해서 최장증가부분수열을 찾고, 이 때 참조하는 바로 전 증가 숫자의 인덱스를 같이 저장해두자.
 * 2. dp배열이 완성되면 가장 큰 값을 찾고, 저장된 바로 전 인덱스의 값들을 순서대로 스택에 넣어서 거꾸로 출력하자.
 */

public class Main {
	
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	
    	int N = Integer.parseInt(st.nextToken()); // 수열의 크기
    	int[] arr = new int[N]; // 수열을 저장할 배열
    	int[][] dp = new int[N][2]; // 최장증가부분수열의 길이와 그 때 참조한 바로 전 수의 인덱스를 저장할 dp배열
    	
    	// 수열의 값들 입력받기
    	st = new StringTokenizer(br.readLine());
    	for (int idx = 0; idx < N; idx++) {
    		arr[idx] = Integer.parseInt(st.nextToken());
    	}
    	
    	// dp로 최장증가부분수열의 길이를 찾아보자
    	// 탐색과 동시에 최댓값과 그 때의 인덱스를 같이 확인하자
    	int maxNum = 0;
    	int maxIdx = 0;
    	for (int idx = 0; idx < N; idx++) {
    		dp[idx][0] = 1;
    		dp[idx][1] = idx;
    		
    		for (int before = 0; before < idx; before++) {
    			if (arr[idx] > arr[before] && dp[idx][0] < dp[before][0] + 1) {
    				dp[idx][0] = dp[before][0] + 1;
    				dp[idx][1] = before;
    			}
    		}
    		
    		// 최장증가부분수열 길이의 최댓값을 확인하고 그 때의 인덱스를 함께 저장하자
    		if (maxNum < dp[idx][0]) {
    			maxNum = dp[idx][0];
    			maxIdx = idx;
    		}
    	}
    	
    	// 스택을 활용해 최장증가부분수열의 값들을 거꾸로 저장하자
    	Stack<Integer> stack = new Stack<>();
    	
    	while (true) {
    		stack.add(arr[maxIdx]);
    		if (maxIdx == dp[maxIdx][1])
    			break;
    		maxIdx = dp[maxIdx][1];
    	}
    	
    	// 출력
    	bw.write(maxNum + "\n");
    	while (!stack.isEmpty()) {
    		bw.write(stack.pop() + " ");
    	}
    	bw.flush();
    }
}
