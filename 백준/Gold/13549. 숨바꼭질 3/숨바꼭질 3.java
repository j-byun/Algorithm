import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author jihye.byun 
 * BOJ 13549 숨바꼭질 3 골드5 그래프
 * 
 * 문제
 * 점N에 있는 수빈이와 점K에 있는 동생이 숨바꼭질을 하고 있다.
 * 수빈이는 걷거나 순간이동을 할 수 있는데, 만약 수빈이의 위치가 X일 때
 * 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다.
 * 순간이동을 한다면 0초 후에 2*X의 위치로 이동하게 된다.
 * 수빈이와 동생의 위치가 주어졌을 때,
 * 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 구하자.
 * 
 * 조건
 * 수빈이가 있는 점 N(0 ≤ N ≤ 100,000)
 * 동생이 있는 점 K(0 ≤ K ≤ 100,000)
 * 
 * 풀이
 * 1. 한 점에서 세 종류의 이동을 진행하자.
 * 1-1. 1초 후 X-1로 이동 || 1초 후 X+1로 이동 || 0초 후 2*X로 이동
 * 2. 재귀 호출을 할 경우 기저조건이 명확하지 않다.
 * 2-1. 수빈이의 현 위치가 동생보다 큰지 작은지 알 수 없기 때문에
 * 3. 따라서 너비 우선 탐색으로 진행하자.
 * 4. boolean 배열의 크기를 지정할 수 없으니 만들지 말자.
 * 4-1. 수빈이가 음수의 점으로 이동하거나, 100,000을 초과하는 점으로 이동할 수 없다는 조건이 없기 때문에
 * 4-2. 방문했던 점을 재방문하게 놔두자
 * 4-3. 어차피 너비 우선 탐색은 제일 먼저 발견되는 경우가 최솟값이니 신경쓰지 말자.
 * 5. 제일 먼저 발견될 때가 가장 빠른 시간이어야 하니,
 * 5-1. 0초 후 2*X로 이동하는 경우를 제일 먼저 고려해주자.
 * 6. 이동할 수 있는 점의 최대 범위를 150,000까지로 제한해주자.
 * 6-1. 어차피 그 이상은 아무리 -해서 돌아와도 최솟값이 될 수 없기 때문이다. 
 * => 시간초과
 * 
 * 7. boolean 배열을 만들어보자.
 * 7-1. 0~150,000정도 크기이면 되지 않을까..?
 */

public class Main {
	
	static int subin, brother;
	static boolean[] isVisited = new boolean[150000];
	
	public static int bfs() {
		// 너비 우선 탐색으로 수빈이를 이동시킬 method
		
		Queue<Node> queue = new ArrayDeque<>();
		// 수빈이가 이동할 수 있는 위치와 그 때의 이동 시간을 저장할 대기열 큐
		
		queue.offer(new Node(subin, 0)); // 수빈이의 초기 위치와 0초를 대기열에 넣어주자
		isVisited[subin] = true; // 현재 위치를 방문했다고 표시해주자
		
		while (!queue.isEmpty()) {
			Node cur = queue.poll(); // 대기 1번 노드를 꺼내서 확인하자
			
			int coo = cur.coo; // 대기 1번의 위치
			int time = cur.time; // 대기 1번의 시간
			
			if (coo == brother) return time;
			// 대기 1번의 위치가 동생의 위치와 동일하다면,
			// 이 때의 시간을 반환하고 메소드를 강제 종료한다.
			
			// 대기 1번의 위치가 동생의 위치와 다르다면...
			// 세 종류의 이동을 진행하자
			
			// 1. 0초 후 X*2로 이동
			// 위치가 150000을 넘어가면 아무리 되돌아와도 최소 시간이 될 수 없으니 조건으로 막아버리자
			if (coo * 2 < 150000 && !isVisited[coo * 2]) {
				queue.offer(new Node(coo * 2, time));
				isVisited[coo * 2] = true;
			}
			
			// 2. 1초 후 X-1로 이동
			if (coo - 1 >= 0 && !isVisited[coo - 1]) {
				queue.offer(new Node(coo - 1, time + 1));
				isVisited[coo - 1] = true;
			}
			// 3. 1초 후 X-1로 이동
			if (coo + 1 < 150000 && !isVisited[coo + 1]) {
				queue.offer(new Node(coo + 1, time + 1));
				isVisited[coo + 1] = true;
			}
			
			// 세 종류의 이동을 고려했으면, 다음 대기 1번을 확인하러 가자
		}
		
		// 어차피 while문의 조건에 걸려 return되기 때문에 여기까지 내려오지 않는다
		return Integer.MIN_VALUE; // 반환값 필수
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		subin = sc.nextInt(); // 현재 수빈이의 위치 N 입력받기
		brother = sc.nextInt(); // 현재 동생의 위치 K 입력받기
		
		sc.close(); // 입력 끝
		
		System.out.println(bfs()); // 너비 우선 탐색 결과 출력
	}
	
	public static class Node {
		int coo, time; // 현 위치의 좌표와 이동 시간을 표현할 Node 클래스

		public Node(int coo, int time) {
			this.coo = coo;
			this.time = time;
		}
	}
	
}
