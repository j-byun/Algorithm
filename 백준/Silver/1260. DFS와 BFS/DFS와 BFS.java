import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 그래프
// 1260 DFS와 BFS
// 실버2

// 문제
// 그래프를 DFS로 탐색한 결과와 BFS로 탐색한 결과를 출력하는 프로그램을 작성하시오
// 방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문하자
// 더 이상 방문할 수 있는 점이 없는 경우 종료한다

// 첫째 줄에 DFS 수행 결과를,
// 다음 줄에 BFS 수행 결과를 출력한다
// 탐색을 시작할 정점의 번호 V부터 방문된 점을 순서대로 출력한다

// 조건
// 정점 번호는 1번부터 N번까지이다
// 정점의 개수 N(1 ≤ N ≤ 1,000)
// 간선의 개수 M(1 ≤ M ≤ 10,000)
// 어떤 두 정점 사이에 여러 개의 간선이 있을 수 있다.
// 입력으로 주어지는 간선은 양방향이다.

// 풀이
// 1. DFS (깊이 우선 탐색)
// 시작 노드에서 부터 한 쪽 방향으로 단말노드까지 탐색
// 단말노드에 도달했다면 바로 위의 레벨로 돌아가서 다른 방향의 노드 탐색
// 이렇게 모든 노드를 확인할 때 까지 반복 진행
// => 재귀로 구현

// 2. BFS (너비 우선 탐색)
// 시작 노드(레벨 0)와 인접한 모든 노드(레벨 1) 탐색
// 레벨 1의 노드들과 인접한 모든 노트(레벨 2) 탐색
// 이렇게 한 레벨의 노드를 모드 탐색하고 그 다음 레벨의 노드를 모두 탐색는 과정을 반복 진행
// => 큐 자료구조로 구현

// *구글링 안해보고 내 생각대로 풀어본 방법
// 노드 개수+1만큼 boolean isVisited 배열을 만들어서 해당 노드를 방문했는지 확인하자
// [간선개수][2]만큼 간선 정보를 저장할 배열을 만들어서 간선으로 이어진 두 노드의 번호를 저장하자

// 1. DFS : 깊이우선탐색
// 간선 배열의 0번 인덱스에서 시작 노드 번호를 찾는다
// 동일한 값이 여러개인 경우 간선으로 이어진 노드 번호 [1]에 저장된 값이 작은 쪽을 고르자
// => 해당 노드가 isVisted = false임을 먼저 확인할 것

// 2. BFS : 너비우선탐색
// 간선 배열의 0번 인덱스에서 시작 노드 번호를 찾아서 발견된 (간선으로 이어진)모든 노드 번호를 저장한다
// 찾은 노드 번호 중 가장 작은 번호부터 큐에 넣어주자

// => 양방향 노드이기 때문에 간선 배열의 0번 인덱스에서만 노드 번호를 찾으면 안되는 문제점 발생

// *구글링을 통한 해결 방법 : 인접 행렬로 만들자
// 간선 배열을 간선개수*2의 크기가 아닌, 간선개수*간선개수 크기로 만들어서 양방향에서 모두 접근 할 수 있게 하자

// 간선 배열의 모든 칸은 노드가 연결돼 있거나, 아니거나 둘 중 하나만 표현하면 되니까 boolean배열로 만들자
// 노드i와 노드k가 연결되어 있다면 간선 배열의 [i][k]와 [k][i]를 모두 true로 만들어주자
// 탐색 시작 전 isVisited배열을 모두 false로 초기화 하고,
// 탐색을 진행하며 방문한 노드는 isVisited = true로 바꿔서 중복 탐색이 되지 않게 유의하자

public class Main {

	static StringBuilder sb = new StringBuilder();
	static int nodeCnt, edgeCnt, startIdx;
	static boolean[][] edge;
	static boolean[] isVisited;

	public static void dfs(int node) { // 깊이 우선 탐색
		// 현재 탐색할 노드 번호를 파라미터로 받자

		sb.append(node).append(" "); // 재귀 호출 전 해당 노드는 미방문 상태임을 확인했으니 출력하자
		isVisited[node] = true; // 출력했으니 이제 해당 노드의 방문 여부를 true로 바꿔주자

		// 모든 노드에 대해 현재 노드와 간선으로 연결되어 있는지 탐색해보자
		for (int idx = 1; idx <= nodeCnt; idx++) { // 확인할 노드의 번호를 골라서
			if (edge[node][idx] && !isVisited[idx])
				// 확인할 노드가 현재 노드와 간선으로 이어져있고, 확인할 노드를 방문한 적이 없으면
				// 확인할 노드를 노드 번호가 작은 순서대로 확인하러 가자
				dfs(idx);
			// 확인할 노드와 연결된 더 깊은 노드들이 있다면
			// 재귀 호출을 통해 계속 깊이를 증가시키는 방향으로 탐색이 진행된다
		}

	}

	public static void bfs() { // 너비 우선 탐색
		// 너비 우선탐색은 재귀 호출이 발생하지 않고,
		// 이미 시작 노드 번호를 static으로 만들어 놨기 때문에
		// 노드 번호를 파라미터로 받을 필요가 없다
		
		// 탐색할 노드 번호를 순서대로 저장해 둘 큐 공간 (대기열로 활용)
		// 먼저 대기열에 저장된 노드를 먼저 탐색한다 (선입선출 구조)
		Queue<Integer> search = new LinkedList<>();
		search.offer(startIdx); // 시작 노드 부터 탐색 대기열에 넣자
		isVisited[startIdx] = true; // 대기열에 넣은 노드의 방문 여부를 true로 바꿔주자
		
		while (!search.isEmpty()) { // 대기열에 대기 중인 노드가 없을 때까지 반복 탐색
			int node = search.poll(); // 대기열 제일 앞의 노드 번호를 꺼내서 탐색하자
			
			// 현재 노드가 미방문 상태임을 확인하고 대기열에 넣어줄거니까
			// 일단 현재 노드를 출력하자
			sb.append(node).append(" ");
			
			// 모든 노드에 대해 현재 노드와 간선으로 연결되어 있는지 탐색해보자
			for (int idx = 1; idx <= nodeCnt; idx++) { // 확인할 노드의 번호를 골라서
				if (edge[node][idx] && !isVisited[idx]) {
					// 확인할 노드가 현재 노드와 간선으로 이어져있고, 확인할 노드를 방문한 적이 없으면
					// 확인할 노드를 대기열에 노드 번호가 작은 순서대로 넣어주자
					search.offer(idx);
					// 대기열에 들어간 노드의 방문 여부를 true로 바꿔주자
					isVisited[idx] = true;
					// 대기열에서 해당 노드를 빼서 확인하는 과정보다
					// for문을 돌며 현재 노드와 인접한 노드를 대기열에 넣어주는 과정이 먼저 실행되기 때문에
					// dfs에서 처럼 for문 밖에서 해당 노드 방문 여부를 true로 바꿔주면
					// 같은 노드가 대기열에 중복으로 들어가게 된다
					// 따라서 bfs에서는 대기열에 노드가 들어감과 동시에 해당 노드의 방문 여부를 바꿔줘야 한다
				}
			}
			
		} // 대기열에 저장해준 모든 노드들에 대한 탐색이 끝나면 while문이 종료되고 bfs가 종료된다
		
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		nodeCnt = sc.nextInt(); // 정점의 개수 N
		edgeCnt = sc.nextInt(); // 간선의 개수 M
		startIdx = sc.nextInt(); // 탐색을 시작할 정점의 번호 V
		
		edge = new boolean[nodeCnt + 1][nodeCnt + 1]; // 두 노드가 간선으로 이어져 있는지 여부를 저장할 공간

		for (int cnt = 0; cnt < edgeCnt; cnt++) { // 간선 정보 입력받기
			// 간선으로 이어진 두 노드 번호를 입력받아서
			int node1 = sc.nextInt();
			int node2 = sc.nextInt();
			
			// 인접행렬의 두 노드가 만나는 두 점을 모두 true로 만들어주자
			edge[node1][node2] = true;
			edge[node2][node1] = true;
		}
		
		isVisited = new boolean[nodeCnt + 1]; // 해당 노드의 방문 여부를 저장할 공간
		dfs(startIdx); // 깊이 우선 탐색 실행
		sb.append("\n"); // dfs끝났으니 개행

		isVisited = new boolean[nodeCnt + 1]; // dfs로 방문했던 노드들을 다시 초기화 시켜 미방문 상태로 만들고 bfs를 시작하자
		bfs(); // 너비 우선 탐색 실행
		
		System.out.println(sb); // 모든 탐색이 끝나면 stringbuilder에 저장해놨던 문자열 출력
	}

}

