import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author jihye.byun 
 * BOJ 13335 트럭 실버1 구현
 * 
 * 문제
 * 1차선 다리를 n개의 트럭이 줄지어 지나간다.
 * 트럭의 순서는 바꿀 수 없으며, 트럭의 무게는 서로 같지 않을 수 있다.
 * 다리 위에는 다리 길이 만큼의 트럭만 동시에 올라갈 수 있다.
 * => 트럭의 길이는 1이다.
 * 각 트럭들은 1초에 1만큼 이동할 수 있다.
 * (편의를 위해 단위길이를 1, 단위시간을 1초로 보자.)
 * 동시에 다리 위에 올라가 있는 트럭들의 무게의 합은 다리의 최대하중 L보다 작거나 같아야 한다.
 * 다리의 길이와 다리의 최대하중, 다리를 건너려는 트럭들의 무게가 순서대로 주어졌을 때,
 * 노든 트럭이 다리를 건너는 최단시간을 구하는 프로그램을 작성하자.
 * 
 * 조건
 * 다리를 건너는 트럭의 수 n (1 ≤ n ≤ 1,000)
 * 다리의 길이 w (1 ≤ w ≤ 100)
 * 다리의 최대하중 L (10 ≤ L ≤ 1,000)
 * i번째 트럭의 무게 ai (1 ≤ ai ≤ 10)
 * 
 * 풀이
 * 1. 트럭의 순서는 바꿀 수 없으니, 선입선출 구조의 큐에 트럭 무게들을 입력받자.
 * 2. 다리에 올라가기 전 대기중인 트럭들의 큐와, 다리 위에 올라가 있는 트럭들의 큐 두 개를 만들자.
 * 3. 트럭을 다리에 올릴 때, 다리에 올라간 시간을 같이 저장하자.
 * 4. 단위시간을 1초씩 증가시키면서, (다리 위 트럭 중 제일 앞 트럭이 다리에 올라간 시간 + 다리의 길이) == 현재 시간 이 되면
 * 4-1. 제일 앞 트럭이 다리를 다 건넌거니까 큐에서 뽑아서 버리자.
 * 5. 다리 위에 올라가 있는 트럭들의 무게 합 + 현재 1번으로 대기중인 트럭의 무게가 최대하중보다 작거나 같다면,
 * 5-1. 대기 1번 트럭도 다리 위에 올리자.
 * 5-2. 무게의 합이 최대하중보다 크다면 그냥 단위시간을 증가시키며 다리 위 트럭만 이동시키자.
 * 6. 트럭을 다리 위 큐에 넣고 뺄 때, 현재 큐에 있는 트럭들의 무게를 저장할 변수를 계속 업데이트 시켜주자.
 * 6-1. 매번 큐의 모든 인덱스 값을 확인하려면 실행 시간이 길어지고, LinkedList 대신 ArrayDeque을 사용하는 이유가 없기 때문이다.
 */

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int truckCnt = sc.nextInt(); // 트럭의 수 n 입력받기
		int length = sc.nextInt(); // 다리의 길이 w 입력받기
		int maxWeight = sc.nextInt(); // 다리의 최대하중 L 입력받기
		
		Queue<Integer> waiting = new ArrayDeque<Integer>();
		// 대기 중인 트럭들을 저장할 큐 대기열 공간
		Queue<Truck> bridge = new ArrayDeque<>();
		// 다리 위의 트럭들을 저장할 큐 공간
		// 해당 트럭이 다리에 진입한 시간도 함께 표현하기 위해 Truck 클래스를 저장하는 큐로 만들자
		
		// 대기 중인 트럭의 무게를 차례대로 대기열 큐에 저장하자
		for (int cnt = 0; cnt < truckCnt; cnt++) {
			waiting.offer(sc.nextInt());
		}
		sc.close(); // 입력 끝
		
		// bridge 큐가 비어있으면 while문이 실행되지 않으니까,
		// 모든 변수의 초기 상태는 첫 번째 트럭이 다리 위에 올라간 1초에서의 상태로 만들자.
		int time = 1; // 현재 단위시간을 나타낼 변수
		int curWait = waiting.poll(); // 대기 1번 트럭을 뽑아서
		bridge.offer(new Truck(curWait, time));
		// 1초에 대기 1번 트럭을 다리에 진입시키자
		int sumWeight = curWait;
		// 다리 위 트럭들의 무게에 1번 트럭의 무게를 저장하자
		
		while (!bridge.isEmpty()) { 
			// 다리가 비어있는 경우는 모든 트럭이 다리를 통과한 경우이니,
			// 다리가 비어있지 않는 동안 while문을 반복 실행하자.
			
			time++; // 단위시간 1초 증가
			
			// 다리 위의 제일 앞 트럭을 확인해보자
			Truck curOnBridge = bridge.peek();
			// 해당 트럭이 다리를 다 지나갈 시간이 됐다면,
			if (curOnBridge.enterTime + length == time) {
				bridge.poll(); // 다리 위 큐에서 뽑아서 버리자
				sumWeight -= curOnBridge.weight;
				// 현재 다리 하중에서 통과한 트럭 무게를 빼주자
			}
			
			// 대기열 제일 앞 트럭을 확인해보자
			// 만약 대기열이 비었다면 그냥 다음 단위시간으로 이동해서 다리 위 트럭들만 이동시키자
			if (waiting.isEmpty()) continue;
			
			curWait = waiting.peek(); // 대기 1번 트럭을 확인해보자
			if (sumWeight + curWait > maxWeight) continue;
			// 현재 다리 위 트럭들의 무게 + 대기 1번 트럭의 무게가 다리의 최대 하중을 넘는다면
			// 대기 1번 트럭을 다리에 진입시키지 말고 그냥 다음 단위시간으로 이동하자
			
			// 무게의 합이 다리의 최대 하중을 넘지 않는다면,
			// 현재 단위시간에 대기 1번 트럭을 다리에 진입시켜주자
			curWait = waiting.poll();
			bridge.offer(new Truck(curWait, time));
			sumWeight += curWait;
			// 다리 하중에 새로 진입한 트럭의 무게를 더해주자
		}
		
		// 다리가 비어서 while문을 탈출했다는 것은, 모든 트럭이 다리를 통과했다는 것이다.
		// 이 때의 단위시간을 출력하자
		System.out.println(time);
	}
	
	public static class Truck {
		// 현재 다리 위에 있는 트럭의 무게와, 다리에 진입한 시간을 표현할 Truck 클래스
		int weight, enterTime;

		public Truck(int weight, int enterTime) {
			this.weight = weight;
			this.enterTime = enterTime;
		}
	}

}
