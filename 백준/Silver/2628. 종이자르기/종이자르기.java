import java.util.*;

/**
 * @author jihye.byun
 * BOJ 2628 종이자르기 실버5
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
    	
    	int C = sc.nextInt();
    	int R = sc.nextInt();
    	
    	boolean[][] paper = new boolean[2][Math.max(R, C)];
    	
    	int cut = sc.nextInt();
    	
    	for (int cnt = 0; cnt < cut; cnt++) {
    		paper[sc.nextInt()][sc.nextInt()] = true;
    	}
    	
    	int maxRow = 0;
    	int curRow = 0;
    	
    	for (int idx = 0; idx < R; idx++) {
    		if (paper[0][idx]) {
    			curRow = 1;
    		} else {
    			curRow++;
    		}
    		
    		maxRow = Math.max(maxRow, curRow);
    	}
    	
    	int maxCol = 0;
    	int curCol = 0;
    	
    	for (int idx = 0; idx < C; idx++) {
    		if (paper[1][idx]) {
    			curCol = 1;
    		} else {
    			curCol++;
    		}
    		
    		maxCol = Math.max(maxCol, curCol);
    	}
    	
    	System.out.println(maxRow * maxCol);
    }
}
