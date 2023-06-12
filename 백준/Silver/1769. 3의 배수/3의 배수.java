import java.util.Scanner;

/**
 * @author jihye.byun
 * BOJ 1769 3의 배수 실버5
 * 
 * 문제
 * 주어진 양의 정수 X에 대해 각 자리의 수를 더한 수 Y를 만든다.
 * Y가 한 자리 수가 될 때 까지 위의 과정을 반복하고, 최종적으로 한 자리 수 Y가 3의 배수인지 확인하자.
 * 큰 수 X가 주어졌을 때, X->Y변환 과정을 몇 번 거쳐야 Y가 한 자리 수가 되는지 구하자.
 * 
 * 조건
 * X는 1,000,000자리 이하의 자연수
 * 수는 0으로 시작하지 않는다.
 * 
 * 풀이
 * 1. 큰 수 X를 String으로 입력받자.
 * 2. X의 길이가 1보다 크다면 X의 길이만큼 char 단위로 쪼개서 더한 수 Y를 만들자.
 * 3. 2번 과정이 한 번 진행될 때 마다 변환 횟수를 1씩 증가시키자.
 * 4. 한 번의 변환 과정이 끝나면 X = Y, Y = 0으로 초기화 하고 다시 X의 길이를 확인하자.
 */

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String X = sc.next(); // 큰 수 X를 문자열로 입력받자
		
		int count = 0; // X -> Y 변환 횟수를 세어줄 변수
		
		while (X.length() > 1) { // X가 한 자리 수가 아니라면 변환
			count++; // 변환 횟수 +1
			int Y = 0; // X의 각 자리 수를 더해준 수 Y를 저장할 공간
			
			// X문자열 길이만큼 char단위로 쪼개서 Y에 더해주자
			for (int index = 0; index < X.length(); index++)
				Y += X.charAt(index) - '0';
			
			// 변환한 수를 다시 X에 저장해주자
			X = Integer.toString(Y);
		}
		
		System.out.println(count); // 변환 횟수 출력
		// 변환된 한 자리 수가 3or6or9이면 YES, 아니면 NO 출력
		System.out.println((X.equals("3") || X.equals("6") || X.equals("9"))? "YES" : "NO");
	}
}
