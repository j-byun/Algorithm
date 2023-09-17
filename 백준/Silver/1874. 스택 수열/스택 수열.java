import java.util.*;

/**
 * @author jihye.byun
 * BOJ 1874 스택 수열 실버2
 *
 * 문제
 * 1부터 n까지의 수를 스택에 넣었다가 뽑아서 하나의 수열을 만들 수 있다.
 * 이때, 스택에 push하는 순서는 반드시 오름차순을 지키도록 하자.
 * 임의의 수열이 주어졌을 때 스택을 이용해 그 수열을 만들 수 있는지 없는지,
 * 있다면 어떤 순서로 push와 pop연산을 수행하는지 계산하자.
 * push연산은 +로, pop 연산은 -로 표현하도록 한다.
 * 불가능한 경우 NO를 출력한다.
 *
 * 조건
 * n (1 ≤ n ≤ 100,000)
 * 같은 정수가 두 번 나오는 일은 없다.
 *
 * 풀이
 */

public class Main {
	
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	int n = sc.nextInt();
    	int start = 0;
    	boolean flag = true;
    	
    	Stack<Integer> stack = new Stack<>();
    	StringBuilder sb = new StringBuilder();
    	
    	for (int count = 1; count <= n; count++) {
    		int cur = sc.nextInt();
    		
    		while (start + 1 <= cur) {
    			stack.push(++start);
    			sb.append("+").append("\n");
    		}
    		
    		if (stack.pop() != cur) {
    			flag = false;
    			break;
    		}
    		sb.append("-").append("\n");
    	}
    
    	System.out.println((!flag)? "NO" : sb);
    }
}
