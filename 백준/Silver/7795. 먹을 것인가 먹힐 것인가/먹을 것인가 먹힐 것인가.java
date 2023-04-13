import java.util.Arrays;
import java.util.Scanner;

/**
 * @author jihye.byun
 * BOJ 7795 먹을 것인가 먹힐 것인가 실버3
 * 
 * 문제
 * 심해에 사는 두 종류의 생명체 A와 B
 * A는 B를 먹는다.
 * A는 자기보다 크기가 작은 먹이만 먹을 수 있다.
 * 두 생명체 A와 B의 크기가 주어졌을 때, A의 크기가 B보다 큰 쌍이 몇 개나 있는지 구하자.
 * 
 * 조건
 * 크기는 양의 정수이다. (1 ≤ N, M ≤ 20,000) 
 * 
 * 풀이 - 투포인터
 * 1. A와 B를 각각 오름차순으로 정렬하자.
 * 2. A와 B를 작은  수 부터 확인하면서, 모든 A에 대해 확인해보자.
 * 2-1. 첫번째 A에 대해, B가 A보다 커지면 그 때의 B 포인터가 현재 A가 먹을 수 있는 먹이 개수이다.
 * 2-2. 다음 A를 확인하면, 이전 A보다 크기가 커졌으니가 B포인터를 2-1에서 멈춘 위치부터만 확인해주면 된다.
 */

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt(); // 테스트케이스 개수
		
		for (int tc = 1; tc <= T; tc++) { // 테케 개수만큼 반복
			
			int N = sc.nextInt(); // A의 수
			int M = sc.nextInt(); // B의 수
			
			int[] a = new int[N]; // A의 수들을 저장할 배열 공간
			int[] b = new int[M]; // B의 수들을 저장할 배열 공간
			
			// A의 수들을 입력받자
			for (int idx = 0; idx < N; idx++) {
				a[idx] = sc.nextInt();
			}
			
			// B의 수들을 입력받자
			for (int idx = 0; idx < M; idx++) {
				b[idx] = sc.nextInt();
			}
			
			// A와 B를 오름차순 정렬하자
			Arrays.sort(a);
			Arrays.sort(b);
			
			// 작은 수 부터 확인하며, 현재 A가 먹을 수 있는 B의 개수를 세어주자
			int sum = 0; // 먹을 수 있는 전체 먹이 개수를 저장할 변수
			int pointB = 0; // 초기 B포인터를 0으로 지정해주자
			for (int pointA = 0; pointA < N; pointA++) {
				// A가 B를 못먹게 될 때 까지 B포인터를 증가시켜주자
				while(pointB < M && a[pointA] > b[pointB]) {
					pointB++;
				}
				// while문을 탈출했다면, 이제 현재 B포인터부터는 현재 A가 못먹는 먹이이다
				// 그러니 B포인터 이전까지의 개수를 결과에 더해주자
				sum += pointB;
			}

			// 최종 결과 출력
			System.out.println(sum);
		}
	}
}
