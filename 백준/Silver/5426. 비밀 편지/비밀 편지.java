import java.util.*;

/**
 * @author jihye.byun
 * BOJ 5426 비밀 편지 실버5
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
    	StringBuilder sb = new StringBuilder();
    	
    	int T = sc.nextInt();
    	
    	while (T-- > 0) {
    		String letter = sc.next();
    		
    		int size = (int) Math.sqrt(letter.length());
    		
    		char[][] map = new char[size][size];
    		
    		int index = 0;
    		
    		for (int row = 0; row < size; row++) {
    			for (int col = 0; col < size; col++) {
    				map[row][col] = letter.charAt(index++);
    			}
    		}
    		
    		for (int col = size - 1; col >= 0; col--) {
    			for (int row = 0; row < size; row++) {
    				sb.append(map[row][col]);
    			}
    		}
    		sb.append("\n");
    	}
    	
    	System.out.println(sb.toString());
    }
}
