import java.util.*;

/**
 * @author jihye.byun
 * BOJ 1406 에디터 실버2	
 *
 * 문제
 * 한 줄로 된 간단한 에디터를 구현하려고 한다.
 * 이 편집기는 영어 소문자만을 기록할 수 있는 편집기로, 최대 600,000글자까지 입력할 수 있다.
 * 커서는 문장의 맨 앞, 문장의 맨 뒤, 또는 문장 중간 임의의 곳에 위치할 수 있다.
 * 길이가 L인 문자열이 현재 편집기에 입력되어 있으면, 커서가 위치할 수 있는 곳은 L+1가지 경우가 있다.
 * 편집기에 입력되어 있는 문자열이 주어지고, 그 이후 입력한 명령어가 차례로 주어졌을 때,
 * 모든 명령어를 수행하고 난 후 편집기에 입력되어 있는 문자열을 구하자.
 * 단, 명령어가 수행되기 전에 커서는 문장의 맨 뒤에 위치하고 있다고 한다.
 * 
 * <편집기에서 지원하는 명령어>
 * L : 커서를 왼쪽으로 한 칸 옮김 (커서가 문장의 맨 앞이면 무시됨)
 * D : 커서를 오른쪽으로 한 칸 옮김 (커서가 문장의 맨 뒤이면 무시됨)
 * B : 커서 왼쪽에 있는 문자를 삭제함 (커서가 문장의 맨 앞이면 무시됨)
 * 		삭제로 인해 커서는 한 칸 왼쪽으로 이동한 것처럼 나타나지만, 실제로 커서의 오른쪽에 있던 문자는 그대로임
 * P $ : $라는 문자를 커서 왼쪽에 추가함
 *
 * 조건
 * 문자열은 길이가 N이고, 영어 소문자로만 이루어져 있으며, 길이는 100,000을 넘지 않는다.
 * 입력할 명령어의 개수를 나타내는 정수 M(1 ≤ M ≤ 500,000)
 * 명령어는 위의 네 가지 중 하나의 형태로만 주어진다.
 *
 * 풀이
 * 1. 커서 위치를 기준으로 왼쪽 문자들을 저장할 스택과 오른쪽 문자들을 저장할 스택으로 구분한다.
 * 2. 커서가 이동함에 따라 한 쪽 스택의 문자를 뽑아 다른 쪽 스택에 쌓는다.
 * 3. 명령이 끝나면, 왼쪽 스택을 모두 차례대로 오른쪽 스택에 넣고, 오른쪽 스택에서 차례로 뽑아 문자를 출력한다.
 */

public class Main {
	
	static List<Character> list;
	
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	Stack<Character> left = new Stack<>(); // 왼쪽 문자들을 저장할 스택
    	Stack<Character> right = new Stack<>(); // 오른쪽 문자들을 저장할 스택
    	
    	String input = sc.next(); // 문자열 입력받기
    	
    	// 입력받은 문자열을 차례로 왼쪽 스택에 쌓기
    	for (int idx = 0; idx < input.length(); idx++) {
    		left.push(input.charAt(idx));
    	}
    	
    	int tc = sc.nextInt();
    	
    	while (tc-- > 0) {
    		char command = sc.next().charAt(0);
    		
    		switch (command) {
    		case 'L' :
    			if (!left.isEmpty())
    				right.push(left.pop());
    			break;
    		case 'D' :
    			if (!right.isEmpty())
    				left.push(right.pop());
    			break;
    		case 'B' :
    			if (!left.isEmpty())
    				left.pop();
    			break;
    		case 'P' :
    			left.push(sc.next().charAt(0));
    			break;
    		}
    	}
    	
    	while (!left.isEmpty()) {
    		right.push(left.pop());
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	
    	while (!right.isEmpty()) {
    		sb.append(right.pop());
    	}
    	
    	System.out.println(sb);
    }
}
