import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * @author jihye.byun
 * BOJ 10423 전기가 부족해 골드2
 *
 * 문제
 * N개의 도시
 * M개의 두 도시를 연결하는 케이블의 정보
 * K개의 YNY발전소가 설치된 도시
 * 세 가지의 정보가 주어지면 케이블 설치 비용을 최소로 사용하여 모든 도시에 전기가 공급할 수 있도록 해결해야 한다.
 * 케이블이 연결되어있는 도시에는 발전소가 반드시 하나만 존재해야 한다.
 * 
 * 모든 도시에 전기를 공급할 수 있도록 케이블을 설치하는 데 드는 최소비용을 출력한다.
 * 
 * 조건
 * 도시의 개수 N(1 ≤ N ≤ 1,000)
 * 설치 가능한 케이블의 수 M(1 ≤ M ≤ 100,000)
 * 발전소의 개수 K(1 ≤ K ≤ N)
 * u도시와 v도시를 연결하는 케이블을 설치할 때 드는 비용 w는 10,000보다 작거나 같은 양의 정수
 *
 * 풀이
 * 1. 크루스칼 알고리즘을 적용하자.
 * 2. 케이블 연결 정보를 가중치 오름차순으로 정렬하여 하나씩 확인하는데,
 * 2-1. px == py 면 연결 X
 * 2-2. 이미 발전소가 포함된 도시끼리는 연결 X
 * 2-3. 총 연결 케이블의 개수는 N - K개
 */

public class Main {
	
	private static int findset(int x, int[][] p) {
		if (p[x][0] != x)
			return findset(p[x][0], p);
		return x;
	}
	
	private static void union(int x, int y, int[][] p) {
		p[y][0] = x;
		
		// x나 y 중 하나가 발전소이면 연결할 떄 발전소 정보도 넘겨주자
		if (p[x][1] == 1 || p[y][1] == 1) {
			p[x][1] = 1;
			p[y][1] = 1;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt(); // 도시의 개수
		int M = sc.nextInt(); // 설치 가능한 케이블의 수
		int K = sc.nextInt(); // 발전소의 개수
		
		int[][] p = new int[N + 1][2]; // [0]에는 부모 도시의 번호, [1]에는 발전소 유무 저장
		
		// 발전소가 있는 도시 입력받기
		for (int cnt = 0; cnt < K; cnt++) {
			p[sc.nextInt()][1] = 1;
		}
		
		int[][] edge = new int[M][3]; // 연결 케이블 정보 저장
		
		// 연결 케이블 정보 입력받기
		for (int cnt = 0; cnt < M; cnt++) {
			edge[cnt][0] = sc.nextInt();
			edge[cnt][1] = sc.nextInt();
			edge[cnt][2] = sc.nextInt();
		}
		
		// 케이블 정보를 연결 비용 오름차순으로 정렬하기
		Arrays.sort(edge, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}
		});
		
		// makeset
		for (int idx = 0; idx <= N; idx++) {
			p[idx][0] = idx;
		}
		
		// 최소 연결 비용 케이블부터 확인하며 도시 연결하기
		int cost = 0;
		int count = 0;
		
		for (int idx = 0; idx < M; idx++) {
			int px = findset(edge[idx][0], p);
			int py = findset(edge[idx][1], p);
			
			// 사이클이 되면 제외
			if (px == py)
				continue;
			
			// 두 도시 다 발전소가 연결돼있으면 제외
			if (p[px][1] == 1 && p[py][1] == 1)
				continue;
			
			// 위의 두 조건에 걸리지 않으면?
			// 케이블로 연결하자!
			union(px, py, p);
			count++;
			cost += edge[idx][2];
			
			// 최대 연결 개수만큼 연결 끝났으면 시스템 종료
			if (count == N - K)
				break;
		}
		
		System.out.println(cost);
	}
}
