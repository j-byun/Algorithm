import java.util.Scanner;

/**
 * @author jihye.byun
 * BOJ 20055 컨베이어 벨트 위의 로봇 골드5 구현/시뮬레이션
 * 
 * 문제
 * 길이가 2N인 컨베이어벨트
 * 	윗줄 : 1~N
 * 	아랫줄 : 2N~N+1
 * 컨베이어 벨트는 인덱스 번호가 증가하는 방향으로 움직이고, 2N번째 칸은 1번칸으로 움직인다.
 * i번 칸의 내구도는 Ai이다.
 * 로봇을 1번 칸에 올리고 N번 칸에서 내리자.
 * 로봇을 1번 칸에 올리거나 로봇이 어떤 칸으로 이동하면 해당 칸의 내구도는 1 감소한다.
 * 로봇을 다음 과정으로 이동시키고, 종료되었을 때 몇 번쨰 단계까지 진행 중이었는지 구해보자.
 * 
 * 로봇 옮기는 과정
 * 1. 벨트가 각 칸 위의 로봇과 함께 회전한다.
 * 2. 가장 먼저 벨트에 올라간 로봇부터, 벨트가 이동하는 방향으로 한 칸 이동할 수 있다면 이동한다.
 * 2-1. 이동할 수 없다면 가만히 있는다.
 * 2-2. 로봇이 이동하기 위해서는 이동하려는 칸에 로봇이 없으며, 그 칸의 내구도가 1이상 남아 있어야 한다.
 * 3. 1번 칸의 내구도가 0이 아니면 1번 칸에 로봇을 올린다.
 * 4. 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다.
 * 4-1. 그렇지 않으면 1번으로 돌아간다.
 * 
 * 조건
 * 2 ≤ N ≤ 100
 * 1 ≤ K ≤ 2N
 * 1 ≤ Ai ≤ 1,000
 * 
 * 풀이
 * 1. 컨베이어 벨트의 내구도를 저장할 길이 2N의 배열과, 로봇의 위치를 나타낼 길이 N의 배열을 만들자.
 * 2. 컨베이어 벨트가 이동할 때에는 내구도 배열과 로봇 배열을 같이 움직이고,
 * 2-1. 로봇이 이동할 때는 로봇 배열만 움직이자.
 * 2-2. N-1번칸의 로봇은 한 칸 이동하면 N번 칸이니까 버리면 된다.
 * 3. 로봇 옮기는 과정을 순서대로 진행하며 0이 되는 칸의 개수를 세어주고,
 * 3-1. 0이 되는 칸의 개수가 K개 이상이 되면 해당 턴의 번호를 출력하고 종료하자.
 */

public class Main {
	
	private static int play(int[] belt, int[] robot, int conditionCount, int size) {
		// 로봇 옮기는 과정을 반복 진행하자
		
		// 현재 몇 번째 과정이 진행 중인지 나타낼 변수를 만들자
		int passCount = 0;
		
		// 내구도가 0인 칸의 초기 값을 0으로 선언하자.
		// 문제 조건에 의해 처음 시작 시에는 모든 칸의 내구도가 1 이상이기 때문
		int zeroCount = 0;
		
		// 내구도가 0인 칸이 K개 이상이 되면 프로그램을 종료하자
		while(zeroCount < conditionCount) {
			passCount++;
			
			// 과정 1
			// 벨트 회전
			int temp = belt[2 * size - 1];
			for (int idx = 2 * size - 1; idx > 0; idx--) {
				belt[idx] = belt[idx - 1];
			}
			belt[0] = temp;
			
			// 로봇도 같이 회전
			for (int idx = size - 1; idx > 0; idx--) {
				robot[idx] = robot[idx - 1];
			}
			robot[0] = 0;
			robot[size - 1] = 0; // N번 칸에 도착한 로봇은 내려버리자
			
			// 과정 1에서는 내구도가 감소하지 않는다
			
			// 과정 2
			// 로봇 이동
			for (int idx = size - 1; idx > 0; idx--) {
				// 이동조건
				// 1. 이전 칸에 로봇이 있고
				// 2. 다음 칸에 로봇이 없고
				// 3. 다음 칸의 내구도가 1이상
				if (robot[idx - 1] == 1 && robot[idx] == 0 && belt[idx] > 0) {
					robot[idx] = robot[idx - 1];
					robot[idx - 1] = 0;
					
					// 이동시킨 칸의 내구도를 1 감소시키고
					// 내구도가 0이 됐다면 카운트하자
					belt[idx]--;
					if (belt[idx] == 0)
						zeroCount++;
				}
			}
			robot[size - 1] = 0; // N번 칸에 도착한 로봇은 내려버리자
			
			// 과정 3
			// 1번 칸 (인덱스 0)의 내구도가 0이 아니면 로봇을 올린다
			if (belt[0] > 0) {
				robot[0] = 1;
				
				// 로봇을 올렸다면 1번 칸의 내구도를 1 감소시키고
				// 내구도가 0이 됐다면 카운트하자
				belt[0]--;
				if (belt[0] == 0)
					zeroCount++;
			}
		}
		
		// 프로그램이 종료 됐을 때 위의 과정이 몇 번 진행됐는지 반환하자
		return passCount;
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int size = sc.nextInt(); // 컨베이어 벨트의 길이 2N
		int conditionCount = sc.nextInt(); // 프로그램 종료 조건 (내구도 0인 칸의 개수)
		
		int[] belt = new int[2 * size]; // 컨베이어 벨트의 내구도를 저장할 공간
		int[] robot = new int[size]; // 로봇의 위치를 저장할 공간
		
		// 컨베이어 벨트 각 칸의 내구도를 입력받자
		for (int idx = 0; idx < 2 * size; idx++) {
			belt[idx] = sc.nextInt();
		}
		
		// 로봇이 이동하는 과정을 반복 실행하고,
		// 내구도가 0인 칸이 K개 이상이 되면 진행 중이던 과정의 번호를 출력하자
		System.out.println(play(belt, robot, conditionCount, size));
	}
	
}
