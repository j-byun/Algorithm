import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author jihye.byun
 * BOJ 14891 톱니바퀴 골드5 구현/시뮬레이션
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
 */

public class Main {
	static Scanner sc = new Scanner(System.in);
	
	private static void input(ArrayList<LinkedList<Integer>> wheel, LinkedList<Turn> list) {
		// 톱니바퀴 회전 정보를 입력받자
		
		int turnCnt = sc.nextInt(); // 회전 횟수를 입력받자
		
		// 회전 횟수만큼 회전 진행
		for (int cnt = 0; cnt < turnCnt; cnt++) {
			int curWheel = sc.nextInt() - 1; // 회전 시킬 톱니바퀴 번호
			int curDirection = sc.nextInt(); // 회전 시킬 방향 => 1 : 시계 / -1 : 반시계
					
			list.add(new Turn(curWheel, curDirection));
					
			// 일단 인접한 톱니바퀴들을 회전시켜야 할지 확인해보자
			// 양쪽 톱니바퀴를 한 번에 확인하면, 중간 톱니바퀴와 맞물린 톱니가 같아서 탐색을 종료해야 되는 경우를 고려하기 힘들다
			// 그러니 왼쪽먼저 확인하고 오른쪽을 확인하자
			int dis = 1;
			// 왼쪽 인접한 톱니바퀴가 있다면 반복 탐색
			while (curWheel - dis >= 0) {
						
				int leftWheel = curWheel - dis;
							
				// 왼쪽 톱니바퀴의 2번 톱니와 오른쪽 톱니바퀴의 6번 톱니를 비교해보자
				int left2 = wheel.get(leftWheel).get(2);
				int right6 = wheel.get(leftWheel + 1).get(6);
				
				// 두 톱니가 서로 다를 때에만 왼쪽 톱니바퀴를 회전시키자
				if (left2 != right6) {
					// 현재 톱니바퀴 기준 홀수칸만큼 이동했으면 반대방향으로 회전시키자
					if (dis % 2 == 0)
						list.add(new Turn(leftWheel, curDirection));
					else
						list.add(new Turn(leftWheel, -curDirection));
					
				} else break;
				// 두 톱니가 서로 같다면 탐색을 종료하자
				
				// 한 칸 다음 톱니를 확인하자
				dis++;
			} // 왼쪽 톱니바퀴 탐색 끝
					
			// 오른쪽 톱니바퀴를 탐색하자
			dis = 1; // 거리 초기화
			// 오른쪽 인접한 톱니바퀴가 있다면 반복 탐색
			while (curWheel + dis < 4) {
						
				int rightWheel = curWheel + dis;
				
				// 오른쪽 톱니바퀴의 6번 톱니와 왼쪽 톱니바퀴의 2번 톱니를 비교해보자
				int right6 = wheel.get(rightWheel).get(6);
				int left2 = wheel.get(rightWheel - 1).get(2);
					
				// 두 톱니가 서로 다를 때에만 오른쪽 톱니바퀴를 회전시키자
				if (right6 != left2) {
					// 현재 톱니바퀴 기준 홀수칸만큼 이동했으면 반대방향으로 회전시키자
					if (dis % 2 == 0)
						list.add(new Turn(rightWheel, curDirection));
					else
						list.add(new Turn(rightWheel, -curDirection));
					
				} else break;
				// 두 톱니가 서로 같다면 탐색을 종료하자
				
				// 한 칸 다음 톱니를 확인하자
				dis++;
			} // 오른쪽 톱니바퀴 탐색 끝
			
			// 한 번의 회전정보로 회전시킬 톱니바퀴를 모두 찾았다면
			// 톱니바퀴들을 회전시키고 다음 회전정보를 입력받자
			turn(wheel, list);
		}
	}
	
	private static void turn(ArrayList<LinkedList<Integer>> wheel, LinkedList<Turn> list) {
		// 톱니바퀴를 회전시키자
		
		// 회전시켜야될 모든 톱니바퀴를 다 회전시킬 때 까지 반복 실행
		while (!list.isEmpty()) {
			// 회전시킬 톱니바퀴 정보 하나를 꺼내오자
			Turn cur = list.pollFirst();
			
			LinkedList<Integer> curWheel = wheel.get(cur.wheel); // 회전시킬 톱니바퀴
			int curDirection = cur.direction; // 회전시킬 방향
			
			// 시계 방향 회전이라면
			// 마지막 톱니를 뽑아서 제일 앞에 넣어주자
			if (curDirection == 1) {
				curWheel.addFirst(curWheel.pollLast());
				
			} else {
				// 반시계 방향 회전이라면
				// 제일 앞 톱니를 뽑아서 마지막에 넣어주자
				curWheel.addLast(curWheel.pollFirst());
			}
		}
	}
	
	private static int calc(ArrayList<LinkedList<Integer>> wheel) {
		// 모든 톱니바퀴 회전이 끝난 뒤 점수를 계산하자
		
		int score = 0; // 점수를 저장할 공간
		
		// 네 개의 톱니바퀴를 확인해보자
		for (int wheelCnt = 0; wheelCnt < 4; wheelCnt++) {
			// 현재 톱니바퀴의 12시 방향이 S극이라면,
			if (wheel.get(wheelCnt).get(0) == 1)
				// 2의 톱니바퀴 인덱스 거듭제곱 만큼 점수를 더해주자
				score += 1 << wheelCnt;
		}
		
		// 계산이 끝났으면 합산된 점수를 반환하자
		return score;
	}
	
	public static void main(String[] args) {
		
		// 네 개의 톱니바퀴를 모아서 2차원 배열 형식으로 관리할 수 있게 ArrayList에 저장하자
		ArrayList<LinkedList<Integer>> wheel = new ArrayList<>();
		
		// 네 개의 톱니바퀴 정보를 입력받자
		for (int wheelCnt = 0; wheelCnt < 4; wheelCnt++) {
			// 한 톱니바퀴의 정보를 저장할 LinkedList 공간을 만들자
			LinkedList<Integer> newWheel = new LinkedList<>();
			String line = sc.next(); // 줄 단위로 읽어와서
			
			for (int sawtooth = 0; sawtooth < 8; sawtooth++) {
				newWheel.addLast(line.charAt(sawtooth) - '0');
				// 하나의 char씩 끊어서 숫자로 변환시켜 저장하자
			}
			
			// 한 톱니바퀴 정보 입력이 끝났다면 톱니바퀴를 ArrayList에 추가하자
			wheel.add(newWheel);
		} // 톱니바퀴 정보 입력 끝
		
		// 회전 시킬 톱니 정보를 저장해둘 공간을 만들자
		LinkedList<Turn> turnData = new LinkedList<>();
		
		// 톱니바퀴 회전 정보를 입력받자
		input(wheel, turnData);
		
		// 톱니바퀴 회전이 끝났다면 점수를 계산해서 출력하자
		System.out.println(calc(wheel));
	}
	
	// 회전시킬 톱니 정보를 나타낼 Turn 클래스
	private static class Turn {
		int wheel, direction;

		public Turn(int wheel, int direction) {
			this.wheel = wheel;
			this.direction = direction;
		}
	}
	
}
