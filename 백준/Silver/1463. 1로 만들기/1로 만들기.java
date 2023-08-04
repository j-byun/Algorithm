import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author jihye.byun
 * BOJ 1463 1로 만들기 실버3
 *
 * 문제
 * 정수 X에 사용할 수 있는 세 가지 연산
 * 1. X가 3으로 나누어 떨어지면, 3으로 나눈다.
 * 2. X가 2로 나누어 떨어지면, 2로 나눈다.
 * 3. 1을 뺀다.
 * 정수 N이 주어졌을 떄, 세 연산을 적절히 사용해서 1을 만들려고 한다.
 * 연산을 사용하는 횟수의 최솟값을 출력하시오.
 *
 * 조건
 * 1보다 크거나 같고, 10^6보다 작거나 같은 정수 N
 *
 * 풀이
 * 1. dp배열을 만들어 -1 일때, /2 일때, /3 일때 경우 중 가장 작은 값을 골라 +1 하자.
 * 2. /2 또는 /3 할 때에는 나머지가 없는지 확인하자.
 */

public class Main {
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int N = Integer.parseInt(st.nextToken());
    	int[] dp = new int[N + 1];
    	
    	if (N >= 2) dp[2] = 1;
    	if (N >= 3) dp[3] = 1;
    	
    	for (int idx = 4; idx <= N; idx++) {
    		int min = dp[idx - 1];
    		
    		// 2의 배수일 때
    		if (idx % 2 == 0) min = Math.min(min, dp[idx / 2]);
    		// 3의 배수일 때
    		if (idx % 3 == 0) min = Math.min(min, dp[idx / 3]);
    		
    		dp[idx] = min + 1;
    	}
    	
    	System.out.println(dp[N]);
    }
}
