import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * @author jihye.byun
 * BOJ 14621 나만 안되는 연애 골드3
 *
 * 문제
 * 1. 사심 경로는 사용자들의 사심을 만족시키기 위해 남초 대학교와 여초 대학교들을 연결하는 도로로만 이루어져 있다.
 * 2. 사용자들이 다양한 사람과 미팅할 수 있도록 어떤 대학교에서든 모든 대학교로 이동이 가능한 경로이다.
 * 3. 시간을 낭비하지 않고 미팅할 수 있도록 이 경로의 길이는 최단 거리가 되어야 한다.
 * 이때, 주어지는 거리 데이터를 이용하여 사심 경로의 길이를 구해보자.
 * 모든 학교를 연결하는 경로가 없을 경우 -1을 출력한다.
 * 
 * 조건
 * 학교의 수 N (2 ≤ N ≤ 1,000)
 * 학교를 연결하는 도로의 개수 M (1 ≤ M ≤ 10,000)
 * 두 학교 간의 거리 d (1 ≤ d ≤ 1,000)
 * 남초 대학교라면 M, 여초 대학교라면 W
 *
 * 풀이
 * 1. 최소 신장 트리 - 크루스칼 알고리즘
 * 2. 학교 간의 거리 d 순서대로 정렬한 후 n-1개의 간선으로 연결하자.
 */

public class Main {
	
	private static int findset(int x, int[] p) {
		if (p[x] != x)
			return findset(p[x], p);
		return p[x];
	}
	private static void union(int x, int y, int[] p) {
		p[y] = x;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		char[] university = new char[N + 1];
		int[] p = new int[N + 1]; // 부모 노드 정보 저장
		int[][] edges = new int[M][3];
		
		// 남/여 대학 정보 입력받기
		sc.nextLine();
		String[] line = sc.nextLine().split(" ");
		for (int idx = 1; idx <= N; idx++) {
			university[idx] = line[idx - 1].charAt(0);
		}
		
		// 간선 연결 정보 입력받기
		for (int cnt = 0; cnt < M; cnt++) {
			edges[cnt][0] = sc.nextInt();
			edges[cnt][1] = sc.nextInt();
			edges[cnt][2] = sc.nextInt();
		}
		
		// 간선을 거리 오름차순으로 정렬
		Arrays.sort(edges, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}
		});
		
		// makeset
		for (int idx = 1; idx <= N; idx++) {
			p[idx] = idx;
		}
		
		int pick = 0;
		int dist = 0;
		
		for (int cnt = 0; cnt < M; cnt++) {
			if (university[edges[cnt][0]] == university[edges[cnt][1]])
				continue;
			
			int px = findset(edges[cnt][0], p);
			int py = findset(edges[cnt][1], p);
			
			if (px == py)
				continue;
			
			union(px, py, p);
			pick++;
			dist += edges[cnt][2];
			
			if (pick == N - 1)
				break;
		}
		
		System.out.println((pick == N - 1) ? dist : -1);
	}
}
