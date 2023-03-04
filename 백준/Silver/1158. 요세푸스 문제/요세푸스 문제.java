import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 1158 요세푸스 문제
// 실버4

// 문제
// 1~N번 까지의 사람이 원을 이루어 앉아있고,
// 순서대로 K번째 사람을 제거한다
// 한 사람이 제거되면 남은 사람들로 이루어진 원을 따라 이 과정을 반복한다
// N명의 사람들이 모두 제거될 때 까지 계속한 후,
// 사람들이 제거되는 순서를 요세푸스 순열이라고 한다

// 요세푸스 순열을 구하시오

// 풀이
// 원형으로 앉아있다 => 원형 큐에 사람들을 앉히자
// 1~k-1번 까지는 사람들을 빼서 큐의 제일 마지막에 다시 넣고
// k번째에는 사람을 제거하고 바로 출력하자
// 큐가 빌 때 까지 반복진행

// 제거된 사람들을 다시 배열에 저장하지 않고 바로 출력해서 메모리를 아끼자

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int peopleCnt = sc.nextInt(); // 전체 사람 수 입력받기
		int deleteIdx = sc.nextInt(); // 몇 번째 사람을 제거할 지 입력받기
		
		Queue<Integer> people = new LinkedList<>(); // 1~N번 까지의 사람들을 앉혀 놓을 큐 공간
		
		for (int cnt = 1; cnt <= peopleCnt; cnt++) {
			people.offer(cnt); // 큐에 1~N번 까지의 사람들을 앉히자
		}
		
		
		sb.append("<");
		while (!people.isEmpty()) { // 큐가 빌 때 까지 반복
			
			for (int idx = 1; idx < deleteIdx; idx++) { // k-1번 만큼 큐의 사람을 뽑아서 맨 뒤에 앉히고
				people.offer(people.poll());
			}
			
			sb.append(people.poll()); // k번째 사람은 바로 출력한다
			
			if (!people.isEmpty()) // 마지막으로 출력되는 사람일 때 출력 양식을 지키기 위해 조건부로 콤마 출력
				sb.append(", ");
		}
		sb.append(">");
		
		System.out.println(sb);
	}

}

