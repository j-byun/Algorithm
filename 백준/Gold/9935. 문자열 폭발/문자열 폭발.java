import java.util.Scanner;
import java.util.Stack;

/**
 * @author jihye.byun
 * BOJ 9935 문자열 폭발 골드4 스택
 * 
 * 문제
 * 문자열이 폭발 문자열을 포함하고 있는 경우, 모든 폭발 문자열이 폭발하고
 * 남은 문자열을 순서대로 이어 붙여 새로운 문자열을 만든다.
 * 새로 생긴 문자열에 폭발 문자열이 포함되어 있을 수도 있다.
 * 폭발은 폭발 문자열이 문자열에 없을 때까지 계속된다.
 * 모든 폭발이 끝난 후에 어떤 문자열이 남는지 구해보자.
 * 남아있는 문자가 없는 경우 "FRULA"를 출력하자.
 * 폭발 문자열은 같은 문자를 두 갱 ㅣ상 포함하지 않는다.
 * 
 * 조건
 * 문자열의 길이는 1보다 크거나 같고, 1,000,000보다 작거나 같다.
 * 폭발 문자열의 길이는 1보다 크거나 같고, 36보다 작거나 같다.
 * 두 문자열은 모두 알파벳 소문자와 대문자, 숫자 0~9로만 이루어져 있다.
 * 
 * 풀이
 * 1. 폭발문자열의 제일 앞부터 탐색하면 사이에 들어있을 다른 폭발문자열의 영항을 받으니
 * 1-1. 폭발문자열의 제일 뒤에서 부터 탐색하자.
 * 2. 스택을 사용하여 뒤에서 부터 탐색한 문자열이 폭발문자열과 일치하면 그대로 뒤에서 뽑아내자.
 */

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String line = sc.next(); // 원본 문자열
		String bomb = sc.next(); // 폭발 문자열
		
		// 실행 시간을 줄이기 위해 문자열의 길이를 먼저 확인해서 저장해두자
		int lineLen = line.length(); // 원본 문자열의 길이
		int bombLen = bomb.length(); // 폭발 문자열의 길이
		char lastBomb = bomb.charAt(bombLen - 1); // 폭발문자열의 마지막 문자
		
		Stack<Character> stack = new Stack<>(); // 폭발 문자열을 뺀 나머지만 저장할 스택
		
		// 원본 문자열의 길이만큼 확인
		for (int idx = 0; idx < lineLen; idx++) {
			// 일단 스택에 문자열을 집어넣고
			char curr = line.charAt(idx);
			stack.add(curr);
			
			// 인덱스 에러 방지를 위해 스택의 크기가 폭발 문자열 크기 이상일 때 폭발 문자열 포함 여부를 확인하자
			if (stack.size() >= bombLen && curr == lastBomb) {
				// 스택에 마지막으로 넣은 문자가 폭발 문자열의 마지막 문자와 일치하면,
				// 폭발 문자열인지 확인하자
				boolean isBomb = true;
				
				for (int newIdx = 2; newIdx <= bombLen; newIdx++) {
					if (stack.get(stack.size() - newIdx) != bomb.charAt(bombLen - newIdx)) {
						isBomb = false;
						break;
					}
				}
				
				// 마지막으로 들어간 문자열이 폭발 문자열이라면?
				// 폭발 문자열 길이만큼 스택에서 다시 뽑아내서 버리자
				if (isBomb) {
					for (int newIdx = 0; newIdx < bombLen; newIdx++) {
						stack.pop();
					}
				}
			}
		}
		
		// 스택이 비었다면 FRULA를 출력하고,
		if (stack.size() == 0)
			System.out.println("FRULA");
		// 스택에 남은 문자열이 있다면 순서대로 출력하자
		// 이 때, char 단위로 출력하면 시간초과가 발생하니
		// 출력할 문자들을 StringBuilder에 담아준 후 한 번에 출력하자
		else {
			StringBuilder sb = new StringBuilder();
			for (char c : stack) {
				sb.append(c);
			}
			System.out.println(sb);
		}
	}

}
