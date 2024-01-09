import java.util.*;

/**
 * @author jihye.byun
 * BOJ 9527 1의 개수 세기 골드2
 *
 * 문제
 *
 * 조건
 *
 * 풀이
 */

public class Main {
	
	static long[] dp;
	
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	long A = sc.nextLong();
    	long B = sc.nextLong();
    	
    	dp = new long[55];
    	dp[0] = 1;
    	long plus = 1L;
    	
    	for (int idx = 1; idx < 55; idx++) {
    		plus = plus << 1;
    		dp[idx] = dp[idx - 1] * 2 + plus;
    	}
    	
    	System.out.println(getBitCount(B) - getBitCount(A - 1));
    }
    
    static long getBitCount(long x) {
    	long count = x & 1;
    	
    	for (int idx = 54; idx > 0; idx--) {
    		if ((x & (1L << idx)) > 0L) {
    			count += dp[idx - 1] + (x - (1L << idx) + 1);
    			x -= (1L << idx);
    		}
    	}
    	
    	return count;
    }
}
