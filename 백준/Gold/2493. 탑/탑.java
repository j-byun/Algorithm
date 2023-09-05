import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 2493 탑 골드5 스택
 *
 * 문제
 * 일직선 위에 N개의 높이가 서로 다른 탑을 수평 직선의 왼쪽부터 오른쪽 방향으로 차례로 세운다.
 * 각 탑의 꼭대기에 레이저 송신기를 설치한다.
 * 모든 탑의 레이저 송신기는 레이저 신호를 지표면과 평행하게 수평 직선의 왼쪽 방향으로 발사하고,
 * 탑의 기둥 모두에는 레이저 신호를 수신하는 장치가 설치되어 있다.
 * 하나의 탑에서 발사된 레이저 신호는 가장 먼저 만나는 단 하나의 탑에서만 수신이 가능하다.
 * 탑들의 개수 N과 탑들의 높이가 주어질 때, 각각의 탑에서 발사한 레이저 신호를 어느 탑에서 수신하는지를 알아내자.
 *
 * 조건
 * 탑의 수를 나타내는 정수 N은 1이상 500,000이하
 * 탑들의 높이는 1이상 100,000,000이하의 정수
 *
 * 풀이
 * 1. 스택이 비어있다면 0을 출력하고 현재 탑을 스택에 push하자.
 * 2. 스택이 비어있지 않다면 두 경우를 확인하자.
 * 2-1. 스택에서 peek한 탑의 높이가 현재 탑의 높이보다 크다면 peek한 탑의 인덱스를 출력하고 현재 탑을 스택에 push하자.
 * 2-2. 스택에서 peek한 탑의 높이가 현재 탑의 높이보다 작다면 pop해서 버린 후, 다시 스택을 확인하자. 
 */

public class Main {
	
	private static class Tower {
		int index, height;
		public Tower(int index, int height) {
			this.index = index;
			this.height = height;
		}
	}
	
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int N = Integer.parseInt(st.nextToken()); // 탑의 개수
    	Stack<Tower> stack = new Stack<>();
    	StringBuilder sb = new StringBuilder();
    	
    	st = new StringTokenizer(br.readLine());
    	for (int idx = 1; idx <= N; idx++) {
    		int height = Integer.parseInt(st.nextToken());
    		
    		while (!stack.isEmpty()) { // 스택이 비어있지 않은 경우
    			if (stack.peek().height >= height) { // 스택에서 peek한 탑의 높이가 현재 탑보다 높거나 같을 때
    				sb.append(stack.peek().index + " ");
    				stack.push(new Tower(idx, height));
    				break;
    			} else { // 스택에서 peek한 탑의 높이가 현재 탑보다 낮을 때
    				stack.pop();
    			}
    		}
    		
    		if (stack.isEmpty()) { // 스택이 비어있을 경우
    			sb.append(0 + " ");
    			stack.push(new Tower(idx, height));
    		}
    	}
    	
    	System.out.println(sb);
    }
}
