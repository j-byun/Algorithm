import java.util.*;

/**
 * @author jihye.byun
 * BOJ 2669 직사각형 네개의 합집합의 면적 구하기 실버5
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
    	
    	int[][] map = new int[101][101];
    	int sum = 0;
    	
    	for (int cnt = 0; cnt < 4; cnt++) {
    		int startRow = sc.nextInt();
    		int startCol = sc.nextInt();
    		int endRow = sc.nextInt();
    		int endCol = sc.nextInt();
    		
    		for (int row = startRow; row < endRow; row++) {
    			for (int col = startCol; col < endCol; col++) {
    				if (map[row][col] == 0) {
    					map[row][col] = 1;
    					sum++;
    				}
    			}
    		}
    	}
    	
    	System.out.println(sum);
    }
}
