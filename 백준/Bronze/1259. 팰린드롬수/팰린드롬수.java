import java.util.Scanner;

/**
 * @author jhye.byun
 * BOJ 1259 팰린드롬수 브론즈1
 * 
 * 문제
 * 주어진 숫자가 앞에서 읽어도, 뒤에서 읽어도 똑같은 팰린드롬수인지 확인하자.
 * 팰린드롬 = 회문
 * 
 * 조건
 * 1 이상 99999 이하의 정수가 주어진다.
 * 마지막 줄에는 0이 주어지며, 이 줄은 문제에 포함되지 않는다.
 * 
 * 풀이
 * 1. 입력받아서 0이 입력되면 프로그램 종료
 * 2. 제일 앞부터 입력되는 숫자 길이의 절반만큼 탐색해서 비교하자.
 */

public class Main {
	
	private static String isPalindrome(String input) {
		
		boolean isRight = true; // 일단 팰린드롬이라고 가정하고 시작
		int size = input.length(); // 문자열 길이 절반만큼 확인하자
		
		for (int idx = 0; idx <= size / 2; idx++) {
			// 앞에서부터 센 인덱스랑 뒤에서부터 센 인덱스의 문자가 동일하지 않다면 false로 바꿔주자
			if (input.charAt(idx) != input.charAt(size - 1 - idx)) {
				isRight = false;
				break; // 하나라도 다르면 더 이상 탐색 의미 없으니까 탈출!
			}
		}
		
		// 탐색 다 했는데도 true이면 얘는 팰린드롬이다
		if (isRight)
			return "yes";
		return "no";
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			// 줄 단위로 입력받기
			String input = sc.nextLine();
			
			// 0이 입력되면 프로그램 종료
			if (input.equals("0")) break;
			
			// 입력받은 문자열(숫자)가 팰린드롬인지 확인해서 결과 출력
			System.out.println(isPalindrome(input));
		}
	}
}
