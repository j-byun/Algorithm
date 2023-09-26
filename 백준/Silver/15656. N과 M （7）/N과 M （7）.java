import java.util.*;

/**
 * @author jihye.byun
 * BOJ 15656 N과 M (7) 실버3
 *
 * 문제
 * N개의 자연수와 자연수 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.
 * N개의 자연수는 모두 다른 수이다.
 * - N개의 자연수 중에서 M개를 고른 수열
 * - 같은 수를 여러 번 골라도 된다.
 *
 * 조건
 * 1 ≤ M ≤ N ≤ 8
 * 입력으로 주어지는 수는 10,000보다 작거나 같은 자연수이다.
 * 수열은 사전 순으로 증가하는 순서로 출력해야 한다.
 *
 * 풀이
 */

public class Main {
	
	static int N;
	static int[] nums, result;
	static StringBuilder sb;
	
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	sb = new StringBuilder();
    	
    	// N, M 입력받기
    	N = sc.nextInt();
    	int M = sc.nextInt();
    	
    	nums = new int[N]; // N개의 수를 저장할 배열 선언
    	result = new int[M]; // 완성된 수열을 저장할 배열 선언
    	
    	// N개의 수 입력받기
    	for (int idx = 0; idx < N; idx++) {
    		nums[idx] = sc.nextInt();
    	}
    	
    	Arrays.sort(nums); // N개의 수 오름차순 정렬
    	
    	getSequence(0, M);
    	
    	System.out.println(sb.toString());
    }
    
    private static void getSequence(int depth, int maxDepth) {
    	
    	if (depth == maxDepth) {
    		for (int idx = 0; idx < maxDepth; idx++) {
    			sb.append(result[idx]).append(" ");
    		}
    		sb.append("\n");
    		return;
    	}
    	
    	for (int idx = 0; idx < N; idx++) {
    		result[depth] = nums[idx];
    		getSequence(depth + 1, maxDepth);
    	}
    }
}
