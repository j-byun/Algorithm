import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 큐
		// 2164 카드2
		
		// 1~N 까지 카드
		// 앞에서부터 빠져나감
		
		// 제일 앞에꺼 버리고, 그 다음꺼는 마지막으로 다시 넣어주고
		// 위에꺼 반복하고 제일 마지막 남은 수 하나만 출력
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 카드 개수 n 입력받기
		int n = Integer.parseInt(br.readLine());
		
		// card 를 저장할 queue 공간 만들어주기
		Queue<Integer> card = new LinkedList<Integer>();
		
		// 1~n 까지 차례대로 큐에 넣기
		for (int i = 1; i <= n; i++) {
			card.offer(i);
		}
		
//		while (!card.isEmpty()) {
//			System.out.println(card.poll());
//		}
		
		// 큐 사이즈가 1보다 크다면 반복해서
		while (card.size() > 1) {
			// first in 을 빼버리고
			card.poll();
			// 그 다음 first in 을 빼서 last에 넣어줌
			card.offer(card.poll());
		} // 큐 사이즈가 1이면 while문을 벗어나서
		
		// 마지막 남은 큐 값 출력
		System.out.println(card.peek());
		

	}

}

