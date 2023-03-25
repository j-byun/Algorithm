import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author jihye.byun
 * BOJ 14891 톱니바퀴 골드5 구현/시뮬레이션 _ 수정 후 코드
 * 
 * 문제
 * 8개의 톱니를 가지고 있는 4개의 톱니바퀴가 일렬로 놓여져 있다.
 * 각 톱니는 N극과 S극 중 하나를 나타내고 있다.
 * 톱니바퀴를 총 K번 회전시키는데, 1회전당 시계 방향 또는 반시계 방향으로 톱니 한 칸 만큼 회전할 수 있다.
 * 톱니바퀴가 회전할 때, 맞닿은 톱니바퀴의 극이 다르다면 옆 톱니바퀴는 반대방향으로 회전하게 된다.
 * 톱니바퀴의 초기 상태와 톱니바퀴를 회전시킨 방법이 주어졌을 때, 최종 톱니바퀴의 상태를 구하자.
 * 
 * 조건
 * 톱니바퀴의 상태는 8개의 정수로 이루어져 있고, 12시방향부터 시계방향 순서대로 주어진다.
 * N극은 0, S극은 1로 나타나있다.
 * 회전 횟수 K(1 ≤ K ≤ 100)
 * 방향이 1인 경우는 시계 방향이고, -1인 경우는 반시계 방향이다.
 * 
 * 점수 계산 방법
 * 1번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 1점
 * 2번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 2점
 * 3번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 4점
 * 4번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 8점
 * 
 * 풀이
 * 1. 톱니바퀴를 시계방향 또는 반시계 방향으로 회전시키기 위해 덱의 기능을 사용하자.
 * 2. 다른 톱니바퀴와 맞닿아 있는 2번, 6번 인덱스의 값을 확인해야 하니 LinkedList로 구현하자.
 * 3. 리스트에 각 톱니바퀴의 12시방향부터 시계방향 순서대로 정보를 입력하자.
 * 4. 톱니바퀴가 시계방향으로 움직이면 해당 톱니의 제일 마지막 값을 뽑아서 제일 앞에 넣어주고,
 * 4-1. 반시계방향으로 움직이면 제일 앞의 값을 뽑아서 제일 마지막에 넣어주면 된다.
 * 5. 이 때, 인접한 톱니바퀴의 회전도 고려해주자.
 * 5-2. 양쪽 방향의 톱니바퀴를 확인하며, 왼쪽 톱니바퀴의 2번 인덱스와 오른쪽 톱니바퀴의 6번인덱스를 비교하자.
 * 6. 움직일 톱니바퀴의 번호를 입력받을 때 -1 해서 사용하자 (인덱스는 0부터)
 * 7. 모든 톱니바퀴 정보를 받아놓고 실행하면 이미 움직인 톱니바퀴로 인해 오차가 발생한다.
 * 7-1. 그러니 톱니바퀴 회전 정보 하나를 입력받을 때 마다 회전시켜주자.
 * 
 * => 보완점
 * 회전 정보가 들어올 때마다 양쪽 톱니바퀴를 확인하고 재귀 호출하자
 */

public class Main {
	static Scanner sc = new Scanner(System.in);
	
	private static void operation(ArrayList<LinkedList<Integer>> wheels, int wheelNum, int dir, boolean[] visit) {
		// 현재 톱니바퀴를 회전시키고 인접한 양쪽 톱니바퀴를 확인하자
		
		// 톱니바퀴를 회전시키기 전에 일단 현재 톱니바퀴의 2번, 6번 톱니 정보를 확인하자
		LinkedList<Integer> wheel = wheels.get(wheelNum);
		int cur2 = wheel.get(2);
		int cur6 = wheel.get(6);
		
		rotate(wheel, dir); // 현재 톱니바퀴를 회전시키고
		visit[wheelNum] = true; // 회전 여부를 true로 표시해주자
		
		// 인접한 톱니바퀴를 확인해서 회전시켜야 할 톱니바퀴라면 재귀호출하자
		// 왼쪽 톱니바퀴 확인
		if (wheelNum - 1 >= 0 && !visit[wheelNum - 1] && cur6 != wheels.get(wheelNum - 1).get(2))
			operation(wheels, wheelNum - 1, -dir, visit);
		// 오른쪽 톱니바퀴 확인
		if (wheelNum + 1 <= 3 && !visit[wheelNum + 1] && cur2 != wheels.get(wheelNum + 1).get(6))
			operation(wheels, wheelNum + 1, -dir, visit);
	}
	
	private static void rotate(LinkedList<Integer> wheel, int dir) {
		// 톱니바퀴를 회전시키자
		
		// 시계 방향 회전이라면
		// 마지막 톱니를 뽑아서 제일 앞에 넣어주자
		if (dir == 1) {
			wheel.addFirst(wheel.pollLast());
			
		} else {
			// 반시계 방향 회전이라면
			// 제일 앞 톱니를 뽑아서 마지막에 넣어주자
			wheel.addLast(wheel.pollFirst());
		}
	}
	
	private static int calc(ArrayList<LinkedList<Integer>> wheels) {
		// 모든 톱니바퀴 회전이 끝난 뒤 점수를 계산하자
		
		int score = 0; // 점수를 저장할 공간
		
		// 네 개의 톱니바퀴를 확인해보자
		for (int cnt = 0; cnt < 4; cnt++) {
			// 현재 톱니바퀴의 12시 방향이 S극이라면,
			if (wheels.get(cnt).get(0) == 1)
				// 2의 톱니바퀴 인덱스 거듭제곱 만큼 점수를 더해주자
				score += 1 << cnt;
		}
		
		// 계산이 끝났으면 합산된 점수를 반환하자
		return score;
	}
	
	public static void main(String[] args) {
		
		// 네 개의 톱니바퀴를 모아서 2차원 배열 형식으로 관리할 수 있게 ArrayList에 저장하자
		ArrayList<LinkedList<Integer>> wheels = new ArrayList<>();
		
		// 네 개의 톱니바퀴 정보를 입력받자
		for (int cnt = 0; cnt < 4; cnt++) {
			// 한 톱니바퀴의 정보를 저장할 LinkedList 공간을 만들자
			LinkedList<Integer> wheel = new LinkedList<>();
			String line = sc.next(); // 줄 단위로 읽어와서
			
			for (int tooth = 0; tooth < 8; tooth++) {
				wheel.addLast(line.charAt(tooth) - '0');
				// 하나의 char씩 끊어서 숫자로 변환시켜 저장하자
			}
			
			// 한 톱니바퀴 정보 입력이 끝났다면 톱니바퀴를 ArrayList에 추가하자
			wheels.add(wheel);
		} // 톱니바퀴 정보 입력 끝
		
		// 톱니바퀴 회전 정보를 입력받자
		int rotateCnt = sc.nextInt(); // 회전 횟수를 입력받자
		
		// 회전 횟수만큼 회전 진행
		for (int cnt = 0; cnt < rotateCnt; cnt++) {
			int wheel = sc.nextInt() - 1; // 회전 시킬 톱니바퀴 번호
			int dir = sc.nextInt(); // 회전 시킬 방향 => 1 : 시계 / -1 : 반시계
			
			// 현재 바퀴를 회전시키고 인접 바퀴를 확인하자
			// 현재 회전 정보로 영향받은 톱니를 체크하기 위해 새로운 boolean배열을 만들자
			operation(wheels, wheel, dir, new boolean[4]);
		}		
		
		// 톱니바퀴 회전이 끝났다면 점수를 계산해서 출력하자
		System.out.println(calc(wheels));
	}
	
}
