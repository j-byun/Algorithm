import java.util.Scanner;

/**
 * @author jihye.byun 
 * BOJ 11404 플로이드 골드4 그래프
 * 
 * 문제
 * 한 도시에서 다른 도시로 이동하는 버스
 * 버스는 한 번 탈 때 비용을 지불해야 함
 * 모든 도시의 쌍에 대해 도시 A에서 B로 가는 최소 비용을 구하자
 * 
 * 조건
 * 도시의 개수 n(2 ≤ n ≤ 100)
 * 버스의 개수 m(1 ≤ m ≤ 100,000)
 * 시작 도시와 도착 도시가 같은 경우는 없다.
 * 비용은 100,000보다 작거나 같은 자연수이다.
 * 시작도시와 도착도시를 연결하는 노선은 하나가 아닐 수 있다.
 * 
 * 풀이
 * 1. 플로이드-워셜 알고리즘 적용
 * 1-1. 입접행렬의 [i][i]칸은 0, 다른 칸은 maxValue로 초기화 하자.
 * 1-2. maxValue가 21억일 경우 경유지를 거쳐가는 계산 시 int형으로 계산 불가 에러 발생
 * 1-3. 따라서 int형으로 사용할거면 최대 10억으로 제한하자.
 * 1-4. 현재 문제에서는 버스의 비용 100,000 * 도시의 개수 100 = 10,000,000보다만 큰 값이면 된다.
 * 2. 버스는 출발지에서 도착지로만 가고, 반대 방향은 다른 버스가 가니까 인접행렬의 [i][j]번째 칸만 업데이트하자.
 * 2-1. 이미 입력된 값과 비교해서 더 작은 값을 넣어주자.
 * 3. 직통 버스가 아닌 경우를 고려해보자.
 * 3-1. 예제1에서 도시2->도시4->도시5로 갈 수 있기 때문에 이 때의 비용은 도시2->도시5로 가는 비용으로 볼 수 있다.
 * 
 * <플로이드-워셜 알고리즘>
 * 다이나믹 프로그래밍 기법 사용, 인접 행렬 이용
 * 모든 노드에서 모든 노드로 가는 최소 비용을 단계적으로 갱신
 * 1. 인접행렬의 [i][i]칸 (자기 자신에서 자기 자신으로 가는 경우)는 0, 그 외의 칸은 maxValue로 초기화
 * 2. 1개의 간선을 거쳐서 각 노드로 도착하는 경우 최소 비용을 행렬에 저장 (초기값 입력받기)
 * 3. 2개 이상의 간선을 거쳐서 각 노드로 도착하는 경우는 모든 노드를 경유지로 보고 최소 비용 판단
 */

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int city = sc.nextInt(); // 도시의 개수 N 입력받기
		int bus = sc.nextInt(); // 버스의 개수 M 입력받기
		
		int[][] cost = new int[city + 1][city + 1];
		// i->j도시로 가는 버스 비용을 저장할 배열
		// 도시 번호 자체를 인덱스로 사용하기 위해 0번 인덱스는 버리자
		
		// 플로이드-워셜 알고리즘에 따라 행렬의 [i][i]칸은 0, 그 외의 칸은 maxValue로 초기화하자
		for (int row = 1; row <= city; row++) {
			for (int col = 1; col <= city; col++) {
				if (row == col) continue;
				cost[row][col] = 100000000;
			}
		}
		
		// 두 도시 사이의 직통 버스 요금을 저장하자
		int city1 = 0, city2 = 0, curCost = 0; // 변수 초기값 지정
		
		for (int cnt = 0; cnt < bus; cnt++) {
			city1 = sc.nextInt(); // 시작 도시 번호
			city2 = sc.nextInt(); // 도착 도시 번호
			curCost = sc.nextInt(); // 현재 버스의 비용
			
			// 행렬에 들어있는 값과 현재 버스 비용 중 더 작은 값을 저장하자
			cost[city1][city2] = Math.min(cost[city1][city2], curCost);
		}
		sc.close();
		
		// 다른 도시를 지나가는 경우를 생각해보자
		// 플로이드-워셜 알고리즘
		// 노드를 1개부터 city개까지 거쳐가는 경우를 고려한다
		for (int stopover = 1; stopover <= city; stopover++) {
			for (city1 = 1; city1 <= city; city1++) {
				for (city2 = 1; city2 <= city; city2++) {
					// 직통 버스와 경유 버스 중 최소 비용으로 업데이트
					cost[city1][city2] = Math.min(cost[city1][city2], cost[city1][stopover] + cost[stopover][city2]);
				}
			}
		}
		
		
		// 최소 비용 저장이 끝났다면 cost배열의 1,1~city,city까지 저장된 값을 출력하자
		for (city1 = 1; city1 <= city; city1++) {
			for (city2 = 1; city2 <= city; city2++) {
				// 초기값인 maxValue가 저장되어있는 경우,
				// 해당하는 두 도시를 연결하는 버스가 아예 없는 것이니 0을 출력하자
				if (cost[city1][city2] == 100000000)
					sb.append(0).append(" ");
				else
					sb.append(cost[city1][city2]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}
