import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * @author jihye.byun
 * BOJ 1922 네트워크 연결 골드4 크루스칼
 *
 * 문제
 * 컴퓨터를 연결하는 비용을 최소로 해서 모든 컴퓨터를 연결하자.
 * 모든 컴퓨터를 연결할 수 없는 경우는 없다.
 *
 * 조건
 * 컴퓨터의 수 N (1 ≤ N ≤ 1000)
 * 연결할 수 있는 선의 수 M (1 ≤ M ≤ 100,000)
 *  a컴퓨터와 b컴퓨터를 연결하는데 비용 c (1 ≤ c ≤ 10,000)
 *  a와 b는 같을 수도 있다.
 *
 * 풀이
 * <크루스칼 알고리즘>
 * 1. 입력받은 간선 정보를 비용 기준 오름차순으로 정렬하자.
 * 2. 비용이 가장 적은 간선부터 확인하며 사이클이 발생하지 않는다면 간선으로 연결하자.
 */

public class Main {
	
	static int[] p;
	
	public static int findset(int a) {
		if (a != p[a])
			p[a] = findset(p[a]);
		return p[a];
	}
	
	public static void union(int a, int b) {
		p[b] = a;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt(); // 컴퓨터의 수
		int M = sc.nextInt(); // 연결할 수 있는 선의 수
		
		int[][] edge = new int[M][3]; // 컴퓨터1 컴퓨터2 비용
		
		for (int cnt = 0; cnt < M; cnt++) {
			edge[cnt][0] = sc.nextInt();
			edge[cnt][1] = sc.nextInt();
			edge[cnt][2] = sc.nextInt();
		}
		
		// 비용 기준 오름차순 정렬
		Arrays.sort(edge, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				return o1[2] - o2[2];
			}
		});
		
		// makeset
		p = new int[N + 1];
		
		for (int idx = 0; idx <= N; idx++) {
			p[idx] = idx;
		}
		
		// N-1개 만큼의 간선을 연결하자
		int count = 0;
		int sum = 0;
		
		for (int idx = 0; idx < M; idx++) {
			int px = findset(edge[idx][0]);
			int py = findset(edge[idx][1]);
			
			if (px != py) {
				union(px, py);
				count++;
				sum += edge[idx][2];
			}
			
			if (count == N - 1)
				break;
		}
		
		System.out.println(sum);
	}
}
