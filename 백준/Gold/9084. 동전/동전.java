import java.util.Scanner;

/**
 * @author jihye.byun
 * BOJ 9084 동전 골드5
 *
 * 문제
 * 동전의 종류가 주어질 때에 주어진 금액을 만드는 모든 방법을 세는 프로그램을 작성하시오.
 *
 * 조건
 * 동전의 가지 수 N(1 ≤ N ≤ 20)
 * 각 금액은 정수로서 1원부터 10000원까지
 * 주어진 N가지 동전으로 만들어야 할 금액 M(1 ≤ M ≤ 10000)
 * 방법의 수는 2^31 - 1 보다 작고, 같은 동전이 여러 번 주어지는 경우는 없다.
 *
 * 풀이
 * 1. dp릃 적용하자.
 * 2. 범위는 int 내에서 끝난다.
 */

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
    	
        int T = sc.nextInt();
        
        for (int tc = 1; tc <= T; tc++) {
        	
        	int N = sc.nextInt();
        	
        	int[] coins = new int[N];
        	
        	for(int idx = 0; idx < N; idx++) {
        		coins[idx] = sc.nextInt();
        	}
        	
        	int fee = sc.nextInt();
        	
        	int[] dp = new int[fee + 1];
        	dp[0] = 1;
        	
        	for (int idx = 0; idx < N; idx++) {
        		for (int amount = coins[idx]; amount <= fee; amount++) {
        			dp[amount] += dp[amount - coins[idx]];
        		}
        	}
        	
        	System.out.println(dp[fee]);
        }
    }
}
