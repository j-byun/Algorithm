import java.util.*;

/**
 * @author jihye.byun
 * BOJ 9506 약수들의 합 브론즈1
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
    	
    	while (true) {
    		int n = sc.nextInt();
    		
    		if (n == -1) return;

    		StringBuilder sb = new StringBuilder();
    		sb.append(n).append(" = ");
    		int sum = 0;
    		
    		for (int idx = 1; idx < n; idx++) {
    			if (n % idx == 0) {
    				sum += idx;
    				if (idx == 1)
    					sb.append(idx).append(" ");
    				else
    					sb.append("+ ").append(idx).append(" ");
    			}
    		}
    		
    		if (sum == n)
    			System.out.println(sb.toString());
    		else
    			System.out.println(n + " is NOT perfect.");
    		
    	}
    	
    }
}
