import java.util.Arrays;
import java.util.Scanner;

/**
 * @author jihye.byun
 * BOJ 15666 N과 M (12) 실버2
 * 
 * 문제
 * N개의 자연수 중 길이가 M인 수열을 구하자.
 * 1. 같은 수를 여러 번 골라도 된다.
 * 2. 고른 수열은 오름차순이어야 한다.
 * 
 * 조건
 * 1 ≤ M ≤ N ≤ 8
 * N개의 수는 10,000보다 작거나 같은 자연수이다.
 * 
 * 풀이
 * 1. 입력받은 N개의 숫자를 오름차순으로 정렬하자.
 * 2. 오름차순 정렬된 배열에서 M개의 중복조합을 만들자.
 * 2-1. 앞에서 고른 인덱스보다 작은 인덱스는 조합에서 제외시키자.
 */

public class Main {
	
	static int N, M;
	static int[] num, ans;
	static StringBuilder sb = new StringBuilder();
	
	private static void dfs(int start, int depth) {
		
		if (depth == M) {
			for (int i = 0; i < M; i++) {
				sb.append(ans[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		for (int idx = start; idx < N; idx++) {
			if (idx != 0 && num[idx] == num[idx - 1]) continue;
			ans[depth] = num[idx];
			dfs(idx, depth + 1);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		num = new int[N];
		ans = new int[M];
		
		sc.nextLine();
		String[] line = sc.nextLine().split(" ");
		for (int idx = 0; idx < N; idx++) {
			num[idx] = Integer.parseInt(line[idx]);
		}
		
		Arrays.sort(num);
		
		dfs(0, 0);
		
		System.out.println(sb);
	}
}
