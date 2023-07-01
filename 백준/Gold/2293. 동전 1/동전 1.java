import java.util.Scanner;

/**
 * @author jihye.byun
 * BOJ 2293 동전 1 골드5
 *
 * 문제
 * 각각 다른 가치의 n가지 동전을 적당히 사용해서,
 * 그 가치희 합이 k원이 되도록 만드는 경우의 수를 구하시오.
 * 각각의 동전은 몇 개라도 사용할 수 있다.
 * 사용한 동전의 구성이 같은데, 순서만 다른 것은 같은 경우이다.
 *
 * 조건
 * 1 ≤ n ≤ 100
 * 1 ≤ k ≤ 10,000
 * 동전의 가치는 100,000보다 작거나 같은 자연수
 *
 * 풀이
 * 1. dp를 활용하자.
 * 2. dp[0] = 1, dp[현재금액] += dp[현재금액 - 현재 고려할 동전의 가치]
 */

public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	int n = sc.nextInt();
    	int k = sc.nextInt();
    	
    	int[] coins = new int[n];
    	int[] dp = new int[k + 1];
    	
    	for (int idx = 0; idx < n; idx++) {
    		coins[idx] = sc.nextInt();
    	}
    	
    	dp[0] = 1;
    	for (int idx = 0; idx < n ; idx++) {
    		for (int amount = 1; amount <= k; amount++) {
    			if (amount - coins[idx] >= 0)
    				dp[amount] += dp[amount - coins[idx]];
    		}
    	}
    	System.out.println(dp[k]);
    }
}
