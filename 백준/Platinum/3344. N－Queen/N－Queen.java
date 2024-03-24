import java.util.*;

/**
 * @author jihye.byun
 * BOJ 3344 N-Queen 플래티넘2
 * 시간 제한 1초 | 메모리 제한 128 MB
 *
 * 문제
 * N이 주어졌을 때 N개의 퀸을 놓을 수 있는 한가지 경우를 출력하자.
 *
 * 조건
 * N은 8, 26, 213, 2012, 99991, 99999중 하나이다.
 *
 * 풀이
 * 1. 재귀로 안됨; 이 문제 전용 풀이가 있음;; 구글 참고
 */

public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	StringBuilder sb = new StringBuilder();
    	
    	int N = sc.nextInt();
    	
    	if (N % 6 != 2 && N % 6 != 3) {
    		for (int i = 1; i <= N; i++) {
    			if (i % 2 != 0) {
    				sb.append(i).append("\n");
    			}
    		}
    		
    		for (int i = 2; i <= N; i++) {
    			if (i % 2 == 0) {
    				sb.append(i).append("\n");
    			}
    		}
    		
    	} else if (N % 6 == 2) {
    		for (int i = 2; i <= N; i++) {
    			if (i % 2 == 0) {
    				sb.append(i).append("\n");
    			}
    		}
    		
    		sb.append(3).append("\n");
    		sb.append(1).append("\n");
    		for (int i = 7; i <= N; i++) {
    			if (i % 2 != 0) {
    				sb.append(i).append("\n");
    			}
    		}
    		sb.append(5).append("\n");
    		
    	} else if (N % 6 == 3) {
    		for (int i = 4; i <= N; i++) {
    			if (i % 2 == 0) {
    				sb.append(i).append("\n");
    			}
    		}
    		sb.append(2).append("\n");
    		
    		for (int i = 5; i <= N; i++) {
    			if (i % 2 != 0) {
    				sb.append(i).append("\n");
    			}
    		}
    		sb.append(1).append("\n");
    		sb.append(3).append("\n");
    		
    	}
    	
    	System.out.println(sb.toString());
    }
}
