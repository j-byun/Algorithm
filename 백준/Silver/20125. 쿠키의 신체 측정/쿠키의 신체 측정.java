import java.util.*;

/**
 * @author jihye.byun
 * BOJ 20125 쿠키의 신체 측정 실버4
 *
 * 문제
 *
 * 조건
 *
 * 풀이
 */

public class Main {
	
	static int[] dr = new int[] {0, 0, 1}; // 좌우하
	static int[] dc = new int[] {-1, 1, 0};
	
	static int N;
	static int[][] map;
	
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	N = sc.nextInt();
    	map = new int[N + 1][N + 1];
    	int heartRow = 0;
    	int heartCol = 0;
    	
    	for (int row = 1; row <= N; row++) {
    		String line = sc.next();
    		for (int col = 1; col <= N; col++) {
    			if (line.charAt(col - 1) == '*')
    				map[row][col] = 1;
    			
    			if (map[row][col] == 1 && heartRow == 0) {
    				heartRow = row + 1;
    				heartCol = col;
    			}
    		}
    	}
    	
    	System.out.println(heartRow + " " + heartCol);
    	System.out.print(measureLength(heartRow, heartCol - 1, 0, 1) + " ");
    	System.out.print(measureLength(heartRow, heartCol + 1, 1, 1) + " ");
    	int waist = measureLength(heartRow + 1, heartCol, 2, 1);
    	System.out.print(waist + " ");
    	System.out.print(measureLength(heartRow + waist + 1, heartCol - 1, 2, 1) + " ");
    	System.out.print(measureLength(heartRow + waist + 1, heartCol + 1, 2, 1) + " ");
    }
    
    static int measureLength(int row, int col, int d, int count) {
    	if (row <= 0 || row > N || col <= 0 || col > N)
    		return count - 1;
    	
    	if (map[row][col] != 1)
    		return count - 1;
    	
    	return measureLength(row + dr[d], col + dc[d], d, count + 1);
    }
    
}
