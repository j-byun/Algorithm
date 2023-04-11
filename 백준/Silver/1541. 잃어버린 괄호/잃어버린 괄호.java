import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author jihye.byun
 * BOJ 1541 잃어버린 괄호 실버2 문자열파싱/그리디
 * 
 * 문제
 * 양수와 +, -로 만들어진 식에 괄호를 적절히 쳐서 식의 결과값을 최소로 만들자.
 * 
 * 조건
 * 식은 0~9와 +, -로만 이루어져 있다.
 * 식의 가장 처음과 마지막 문자는 숫자이다.
 * 연속해서 두 개 이상의 연산자가 나타나지 않는다.
 * 5자리보다 많이 연속되는 숫자는 없다.
 * 수는 0으로 시작할 수 있다.
 * 입력으로 주어지는 식의 길이는 50보다 작거나 같다.
 * 
 * 풀이
 * 1. 한 줄을 String으로 읽어와서 +나 - 연산자가 나오기 바로 전 문자까지를 integer로 parse하자.
 * 2. 결과가 최소가 되려면? 빼주는 숫자를 최대한 크게 만들자!
 * 2-1. -가 없을 때 : 괄호가 필요없다! 그냥 있는 그대로 다 더해주자
 * 2-2. -가 하나일 때 : - 연산자 뒤가 통채로 하나의 괄호라고 생각하자! 뒤의 수 다 더해서 앞의 수에서 빼줘!
 * 2-3. -가 여러 개일 때 : 하나의 - 연산자 다음부터 다음 - 연산자 전까지를 괄호라고 생각하자!
 * 
 * => 보완점
 * 1. 문자열 파싱할 때 그냥 연산자 기준으로 split 메소드를 활용하자.
 * 2. - 연산자 기준으로 나눈 후에, 그 안에서 다시 + 연산자 기준으로 나눠서 수들을 합하고, 앞의 - 연산자 기준으로 나눈 수에서 빼주자.
 */

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		/*
		// 그냥 string으로 입력받으면 두 자리 이상의 숫자를 for문 돌려서 합쳐줘야되니까 귀찮다
		// StringBuilder의 substring 메서드를 활용하기 위해 입력받은 문자열을 그대로 스트링빌더에 넣어주자
		StringBuilder sb = new StringBuilder();
		sb.append(sc.next());
		
		int size = sb.length(); // 입력받은 문자열의 길이만큼 확인하기 위해 길이를 저장해두고 사용하자
		
		// 숫자가 두 자리 이상일 경우 숫자의 시작과 끝을 나타내기 위해 인덱스를 두 개 사용하자
		int idx1 = 0; // 숫자의 처음 시작을 나타낼 인덱스
		int idx2 = 0; // 숫자의 마지막 (숫자 다음의 연산자 or 문자열의 끝)을 나타낼 인덱스
		
		// 입력받으면서 계산하면 복잡하니까 일단 입력받아서 다 저장해놓고 생각하자!
		Queue<Integer> operand = new ArrayDeque<Integer>();
		Queue<Character> operator = new ArrayDeque<>();
		
		// 문자열 길이만큼 확인
		while (idx2 <= size) {
			// 문자열의 끝까지 확인한 경우는 따로 빼주자
			// 안그럼 sb.charAt()에서 IndexOutOfBounds 에러 발생
			// 문자열의 끝까지 확인했으면 그 바로 전 까지가 하나의 숫자이다
			// => 숫자 큐에 넣자
			if (idx2 == size) {
				operand.add(Integer.parseInt(sb.substring(idx1, idx2)));
				break;
			}
			
			// 문자열이 끝나지 않았다면 확인하자
			char curr = sb.charAt(idx2); // 현재 확인할 문자 가져오기
			
			// 연산자를 만났다면 그 바로 전 까지가 하나의 숫자이다
			// => 숫자 큐에 넣자
			if (curr == '+' || curr == '-') {
				operand.add(Integer.parseInt(sb.substring(idx1, idx2)));
				// 현재 문자는 연산자이니까 걔는 연산자 큐에 넣자
				operator.add(sb.charAt(idx2));
				// 다음 숫자의 시작 인덱스를 업데이트 해주자
				idx1 = idx2 + 1;
			}
			
			// 현재 문자가 숫자이면? 숫자 아닌애 나올 때 까지 다음 인덱스를 확인하자
			idx2++;
		}
		
		// 숫자와 연산자를 다 구분했다면 이제 연산해보자
		int result = operand.poll();
		
		while (!operator.isEmpty()) {
			// 일단 - 연산자가 나오기 전까지는 다 더해주자
			if (operator.poll() == '+')
				result += operand.poll();
			
			// - 연산자가 나왔다면 다음 - 연산자가 나오거나 연산자가 없을 때 까지 다 더해서 빼주자
			else {
				int sum = operand.poll();
				
				while (!operator.isEmpty()) {
					if (operator.peek() == '+') {
						operator.poll();
						sum += operand.poll();
					} else break;
				}
				
				result -= sum;
			}
		}
		
		System.out.println(result);
		*/
		
		// 보완점을 적용해서 다시 풀어보자!
		
		// - 연산자 기준으로 나눠서 입력받자
		// 어차피 계산할 때만 Integer로 파싱해주면 되니까 그냥 String 그대로 입력받자
		String[] input = sc.next().split("-");
		
		// - 연산자로 나눠진 애들을 확인해서 계산하자
		int result = 0;
		for (int idx = 0; idx < input.length; idx++) {
			int sum = 0; // - () - 에서 괄호 안의 숫자들을 다 더해서 저장할 공간
			String[] num = input[idx].split("\\+"); // - 연산자 기준으로 분리된 애들을 다시 + 연산자 기준으로 분리해주자
			// + 문자 기준으로 split 할 때 'java.util.regex.PatternSyntaxException: Dangling meta character '+' near index 0' 에러가 발생함
			// => ||를 붙여줘서 해결
			for (int idx2 = 0; idx2 < num.length; idx2++) {
				sum += Integer.parseInt(num[idx2]);
			}
			// - 연산자 기준으로 분리한 하나의 괄호 안의 수들을 다 더해줬다면 결과값에서 빼주자
			// 이 때, 이번 괄호가 첫 괄호이면 앞에 - 연산자가 없었던 애니까 결과값에 더해주자
			if (idx == 0)
				result += sum;
			else
				result -= sum;
		}
		
		System.out.println(result);
	}
	
}
