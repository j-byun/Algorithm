import java.util.Scanner;

/**
 * @author jihye.byun
 * BOJ 2502 떡 먹는 호랑이 실버1
 * 
 * 문제
 * 오늘 떡 = 어제 떡 + 그제 떡
 * 오늘 떡의 개수 K와, 떡을 준 날짜 D가 주어졌을 때,
 * 첫째 날 떡의 개수 A 와 두번째 날 떡의 개수 B를 계산하자.
 * 
 * 조건
 * 1 ≤ A ≤ B
 * 답이 되는 A, B가 하나 이상일 때에는 그 중 하나만 출력하자.
 * 할머니가 넘어온 날 D (3 ≤ D ≤ 30)
 * 그 날 호랑이에게 준 떡의 개수 K (10 ≤ K ≤ 100,000)
 * 
 * 풀이
 * 1. 피보나치 수열인데, 알아내야 하는 건 첫째 날 떡의 개수와 둘때 날 떡의 개수이니까,
 * 1-1. 피보나치 수열을 전체에 대해서가 아닌, 1일차 떡을 몇 번 사용했는지, 2일차 떡을 몇 번 사용했는지에 대해 만들자.
 * 1-3. 1일차 떡의 사용량 fb1[1] = 1, fb1[2] = 0, fb1[3] = 1, fb1[4] = 1, fb1[5] = 2, ...
 * 1-4. 2일차 떡의 사용량 fb2[1] = 0, fb2[2] = 1, fb2[3] = 1, fb2[4] = 2, fb2[5] = 3, ...
 * 2. 어차피 D가 3부터이니까, fb2 배열만 구해서 fb2[D]가 2일차 떡 사용량, fb2[D - 1]이 1일차 떡 사용량이라고 하자.
 * 3. 사용량을 구했으니, 떡1의 개수 * 1일차 사용량 + 떡2의 개수 * 2일차 사용량 = K를 만족하는 개수를 브루트포스로 찾아보자.
 * 3-1. 하나만 찾으면 되니까 작은 수 부터 탐색해서 하나라도 발견하면 바로 탐색을 종료하고 출력하자.
 */

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int D = sc.nextInt(); // 할머니가 넘어온 날
		int K = sc.nextInt(); // 그 날 호랑이에게 준 떡의 개수
		
		int[] fibo = new int[D + 1]; // 인덱스번째 날에 사용한 떡2의 개수를 저장할 배열 공간
		
		fibo[2] = 1;
		
		// D일에 사용한 떡2의 사용량을 구해보자
		for (int idx = 3; idx <= D; idx++) {
			fibo[idx] = fibo[idx - 1] + fibo[idx - 2];
		}
		
		// D일에 사용한 떡1의 사용량 : fibo[N - 1];
		// D일에 사용한 떡2의 사용량 : fibo[N];
		
		// 이제 num1 * fibo[D - 1] + num2 * fibo[D] = K를 만족하고,
		// num1 <= num2인 num1과 num2를 구해보자
		for (int num1 = 1; num1 < K; num1++) {
			if ((K - num1 * fibo[D - 1]) % fibo[D] == 0) {
				System.out.println(num1 + "\n" + ((K - num1 * fibo[D - 1]) / fibo[D]));
				break;
			}
		}
		
	}
}
