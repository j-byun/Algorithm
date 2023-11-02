import java.util.*;

/**
 * @author jihye.byun
 * BOJ 12852 1로 만들기 2 실버1
 *
 * 문제
 * 정수 X에 사용할 수 있는 연산 세 가지
 * 1. X가 3으로 나누어 떨어지면, 3으로 나눈다.
 * 2. X가 2로 나누어 떨어지면, 2로 나눈다.
 * 3. 1을 뺀다.
 * 정수 N이 주어졌을 때, 위와 같은 연산 세 개를 적절히 사용해서 1을 만들려고 한다.
 * 연산을 사용하는 횟수의 최솟값을 출력하시오.
 * 
 * 첫째 줄에 연산을 하는 횟수의 최솟값을 출력한다.
 * 둘째 줄에는 N을 1로 만드는 방법에 포함되어 있는 수를 공백으로 구분해서 순서대로 출력한다.
 * 정답이 여러 가지인 경우에는 아무거나 출력한다.
 *
 * 조건
 * 1 <= N <= 10^6
 *
 * 풀이
 * 1. 최소 연산 횟수는 세 가지의 연산 종류 중 가장 작은 수를 선택
 * 2. 이전 인덱스를 확인할 방법은? 배열을 하나 더 만들어서 저장해주자
 */

public class Main {
	
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	int N = sc.nextInt();
    	int[] count = new int[N + 1];
    	int[] before = new int[N + 1];
    	// 0번, 1번 인덱스는 0으로 활용
    	// 2의 배수, 3의 배수일 때 0번 인덱스의 값 0 활용을 위해 1~N+1까지의 인덱스 사용
    	
    	for (int idx = 2; idx <= N; idx++) {
    		count[idx] = count[idx - 1] + 1;
    		before[idx] = idx - 1;
    		
    		if (idx % 3 == 0 && count[idx] > count[idx / 3] + 1) {
    			count[idx] = count[idx / 3] + 1;
    			before[idx] = idx / 3;
    		}
    		
    		if (idx % 2 == 0 && count[idx] > count[idx / 2] + 1) {
    			count[idx] = count[idx / 2] + 1;
    			before[idx] = idx / 2;
    		}
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	
    	// 최소 연산 횟수 출력
    	sb.append(count[N]).append("\n");
    	
    	// N에서부터 내려가며 연산에 사용된 숫자 출력
    	int start = N;
    	
    	while (start > 0) {
    		sb.append(start).append(" ");
    		start = before[start];
    	}
    	
    	System.out.println(sb.toString());
    }
}
