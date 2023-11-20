import java.util.*;

/**
 * @author jihye.byun
 * BOJ 2738 행렬 덧셈 브론즈5
 *
 * 문제
 *
 * 조건
 *
 * 풀이
 */

public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	int N = sc.nextInt();
    	int M = sc.nextInt();
    	
    	int[][] arr = new int[N][M];
    	
    	for (int row = 0; row < N; row++) {
    		for (int col = 0; col < M; col++) {
    			arr[row][col] = sc.nextInt();
    		}
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	for (int row = 0; row < N; row++) {
    		for (int col = 0; col < M; col++) {
    			sb.append(arr[row][col] + sc.nextInt()).append(" ");
    		}
    		sb.append("\n");
    	}
    	
    	System.out.println(sb);
    }
}
