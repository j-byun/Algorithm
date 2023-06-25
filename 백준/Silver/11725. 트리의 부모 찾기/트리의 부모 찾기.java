import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author jihye.byun
 * BOJ 11725 트리의 부모 찾기 실버2
 * 
 * 문제
 * 트리의 루트를 1이라고 정했을 때, 각 노드의 부모를 구하는 프로그램을 작성하시오.
 * 
 * 조건
 * 노드의 개수 N (2 ≤ N ≤ 100,000)
 * 
 * 풀이
 */

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		ArrayList<Integer>[] list = new ArrayList[N + 1];
		
		// 트리 초기화
		for (int idx = 1; idx <= N; idx++) {
			list[idx] = new ArrayList<>();
		}
		
		// 연결 간선 정보 입력받기
		for (int idx = 1; idx < N; idx++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			list[a].add(b);
			list[b].add(a);
		}
		
		// 루트 노드 1번부터 bfs실행
		boolean[] visit = new boolean[N + 1];
		int[] parent = new int[N + 1];
		
		Queue<Integer> queue = new ArrayDeque<Integer>();
		queue.add(1);
		visit[1] = true;
		
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			
			for (int i : list[cur]) {
				if (visit[i]) continue;
				parent[i] = cur;
				queue.add(i);
				visit[i] = true;
			}
		}
		
		// 결과 출력
		for (int idx = 2; idx <= N; idx++) {
			System.out.println(parent[idx]);
		}
		
	}
}
