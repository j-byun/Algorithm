import java.util.Scanner;

/**
 * @author jihye.byun
 * BOJ 1940 주몽 실버4 
 * 
 * 문제
 * 
 * 조건
 * 
 * 풀이
 */

public class Main {
	
	static int N, M, count;
	static int[] num;
	
	public static void comb(int start, int sum, int depth, int maxDepth) {
		
		if (depth == maxDepth) {
			if (sum == M)
				count++;
			return;
		}
		
		for (int idx = start; idx < N; idx++) {
			if (sum + num[idx] > M) continue;
			comb(idx + 1, sum + num[idx], depth + 1, maxDepth);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		num = new int[N];
		
		for (int idx = 0; idx < N; idx++) {
			num[idx] = sc.nextInt();
		}
		
		count = 0;
		
		comb(0, 0, 0, 2);
		
		System.out.println(count);
	}
}
