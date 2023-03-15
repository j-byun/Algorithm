import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

// 1918 후위 표기식
// 골드2

// 중위표기식 -> 후위표기식
// 후위표기식 출력

// 숫자대신 알파벳 대문자로 주어진다

public class Main {

	// 연산자의 우선순위를 저장할 hashMap
	static HashMap<Character, Integer> opPriority = new HashMap<Character, Integer>() {
		{
			put('+', 1);
			put('-', 1);
			put('*', 2);
			put('/', 2);
			put('(', 3);
		}
	};

	static String infix; // 중위계산식

	static Queue<Character> postfix; // 후위계산식
	static Stack<Character> opsStack; // 연산자 스택

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		// 중위계산식 입력받기
		infix = sc.next();
		
		// 중위계산식 -> 후위계산식으로 변환
		infixToPostfix();
		
		// postfix 큐를 출력하기
		while (!postfix.isEmpty()) {
			sb.append(postfix.poll());
		}
		System.out.println(sb);
		
	}

	// 중위계산식 -> 후위계산식으로 변환
	private static void infixToPostfix() {
		postfix = new LinkedList<Character>();
		opsStack = new Stack<>();

		char current; // 현재 char를 저장할 공간
		char before; // 이전 char를 저장할 공간

		for (int idx = 0; idx < infix.length(); idx++) { // 계산식 길이만큼 반복 확인
			current = infix.charAt(idx); // 지금 확인할 char

			if (current >= 'A' && current <= 'Z') { // 현재 char이 알파벳대문자이면
				postfix.offer(current); // 바로 후위계산식에 넣기
				continue;

			} else { // 현재 char이 연산자면
				if (opsStack.isEmpty()) { // 연산자 스택이 비어있을 경우 바로 넣어주기
					opsStack.push(current);
					continue;
				}

				if (current == ')') { // 연산자 스택이 비어있지 않고, 현재 연산자가 닫는 괄호 ')' 라면
					before = opsStack.peek();

					while (before != '(') { // 여는 괄호 '('가 나오기 전까지
						before = opsStack.pop();
						postfix.offer(before); // 연산자 스택에 쌓인 연산자 차례대로 꺼내서 후위계산식에 넣기
						before = opsStack.peek();

					}

					opsStack.pop(); // 여는 괄호 전까지의 연산자 다 꺼냈으면 여는 괄호 꺼내서 버리기
					continue;

				} else { // 연산자 스택이 비어있지 않고, 현재 연산자가 닫는 괄호가 아니라면
					before = opsStack.peek();

					while (opPriority.get(current) <= opPriority.get(before) && before != '(') { // 내가 우선순위가 제일 높아질 때 까지
						before = opsStack.pop();
						postfix.offer(before); // 연산자 스택에 쌓인 연산자 차례대로 꺼내서 후위계산식에 넣기

						if (opsStack.isEmpty())
							break; // 연산자 스택에서 더 이상 꺼낼 연산자가 없으면 바로 나를 넣으면 되니까 break

						before = opsStack.peek();
					}

					opsStack.push(current); // 이제 내 우선순위가 제일 높으니까 연산자 스택에 넣기
					continue;

				}
			}
		}

		while (!opsStack.isEmpty()) { // 계산식 확인이 끝났으면 연산자 스택에 남은 연산자를 차례대로 후위계산식에 넣기
			current = opsStack.pop();
			postfix.offer(current);
		}

	}

}
