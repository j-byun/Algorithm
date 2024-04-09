import java.util.*;

/**
 * @author jihye.byun
 * BOJ 2011 암호코드 골드5
 * 시간 제한 2초 | 메모리 제한 128 MB
 *
 * 문제
 * A : 1 ~ Z : 26
 * 암호가 주어졌을 때, 그 암호의 해석이 몇 가지 나올 수 있는지 구하자.
 * 정답을 1,000,000으로 나눈 나머지를 출력한다.
 * 암호가 잘못되어 암호를 해석할 수 없는 경우에는 0을 출력한다.
 *
 * 조건
 * 5000자리 이하의 암호
 * 암호는 숫자로 이루어져 있다.
 *
 * 풀이
 * 1. i번째 자리까지 해석하는 경우의 수 :
 * 		i-1번째에 지금 숫자 더하거나(i번째자리가 0이 아니면), 
 * 		i-2번째에 i-1번째랑 i번째까지 두자리 수 더하거나 (두 자리수 만들었을 때 26이하이면)
 * 2. 두 가지 경우 모두 고려했는데도 i번째 자리를 만드는 경우의 수가 0이라면 암호가 잘못되었음!
 */

public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	String password = sc.next();
    	
    	int size = password.length();
    	
    	// 암호를 각 자리수로 쪼개서 숫자로 표현하기
    	int[] code = new int[size + 1];
    	int index = 1;
    	for (char c : password.toCharArray()) {
    		code[index++] = c - '0';
    	}
    	
    	// dp로 1~size까지 자리수의 암호를 해석하는 경우의 수를 구해보자
    	int[] dp = new int[size + 1];
    	dp[0] = 1;
    	
    	for (int idx = 1; idx <= size; idx++) {
    		boolean wrongCode = true;
    		
    		// 이번꺼 한자리만 고려하는 경우
    		if (code[idx] != 0) {
    			dp[idx] += dp[idx - 1];
    			wrongCode = false;
    		}
    		
    		// 바로 앞까지 두자리 고려하는 경우
    		// 한자리가 되면 안되니까 idx-1번째가 0인 경우는 포함하지 않음
    		int num = code[idx - 1] * 10 + code[idx];
    		if (idx >= 2 && code[idx - 1] != 0 && num >= 1 && num <= 26) {
    			dp[idx] += dp[idx - 2];
    			wrongCode = false;
    		}
    		
    		if (wrongCode) {
    			System.out.println(0);
    			return;
    		}
    		
    		dp[idx] %= 1_000_000;
    	}
    	
    	System.out.println(dp[size]);
    }
}
