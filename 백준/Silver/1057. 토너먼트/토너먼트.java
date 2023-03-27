import java.util.Scanner;

/**
 * @author jihye.byun
 * BOJ 1057 토너먼트 실버4
 * 
 * 문제
 * N명의 참가자끼리 토너먼트를 진행하자.
 * 인접한 두 번호끼리 대결해서 이긴 사람은 다음 라운드 진출, 진 사람은 그 라운드에서 떨어진다.
 * 각 라운드의 참가자가 홀수명이라면, 마지막 참가자는 부전승한다
 * 라운드가 진행될 때 마다 참가자의 번호를 1번부터 다시 매긴다.
 * (이전 번호 순서를 유지하면서 번호를 매긴다.)
 * 한 명만 남을 때까지 라운드를 계속 진행한다.
 * 지민이는 한수와 몇 번째 라운드에서 대결하는지 궁금하다.
 * 둘은 서로 만나기 전까지 항상 이긴다고 가정하자.
 * 1라운드에서의 지민이와 한수의 번호가 주어질 때, 둘이 몇 라운드에서 대결할지 출력하자.
 * 
 * 조건
 * 참가자의 수 N은 2보다 크거나 같고, 100,000보다 작거나 같은 자연수
 * 지민이의 번호는 한수의 번호와 다르다
 * 
 * 풀이
 * 1. 편의를 위해 두 사람의 번호 중 작은 번호가 지민이라고 정의하자.
 * 2. 라운드를 1씩 증가시키며, 지민이의 번호가 홀수일 때, +1한 번호가 한수의 번호이면 해당 라운드에서 대결하는 것이다.
 * 3. 2번 조건에 해당하지 않는다면, 각자의 번호를 /2하면되는데, 이 때 /2하기 전 번호가 홀수라면 +1 하고 /2 해주자.
 */

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int peopleCnt = sc.nextInt();
		
		int a = sc.nextInt();
		int b = sc.nextInt();
		
		int jimin, hansu;
		if (a < b) {
			jimin = a;
			hansu = b;
		} else {
			jimin = b;
			hansu = a;
		}
		
		int round = 0;
		
		while (true) {
			round++;
			
			if (jimin % 2 == 1) {
				if (jimin + 1 == hansu)
					break;
				else
					jimin++;
			}
			
			if (hansu % 2 == 1)
				hansu++;
			
			jimin /= 2;
			hansu /= 2;
		}
		
		
		System.out.println(round);
	}
	
}
