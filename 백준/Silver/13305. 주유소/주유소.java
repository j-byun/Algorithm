import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 13305 주유소 실버3
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
    	
    	int N = sc.nextInt() - 1;
    	
    	int[][] city = new int[2][N]; // 도로의 길이/리터당 가격
    	long[] cost = new long[N];
    	
    	for (int line = 0; line < 2; line++) {
    		for (int idx = 0; idx < N; idx++) {
    			city[line][idx] = sc.nextInt();
    		}
    	}
    	sc.nextInt();
    	
    	Arrays.fill(cost, Long.MAX_VALUE);
    	
    	for (int idx = N - 1; idx >= 0; idx--) {
    		for (int before = idx; before < N; before++) {
    			long cur = city[0][before] * city[1][idx];
    			
    			if (cost[before] < cur) break;
    			cost[before] = cur;
    		}
    	}
    	
    	long sum = 0;
    	for (int idx = 0; idx < N; idx++) {
    		sum += cost[idx];
    	}
    	
    	System.out.println(sum);
    }
    
}
