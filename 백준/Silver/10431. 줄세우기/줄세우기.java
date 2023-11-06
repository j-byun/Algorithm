import java.util.*;

/**
 * @author jihye.byun
 * BOJ 10431 줄세우기 실버5
 *
 * 문제
 *
 * 조건
 * 테스트 케이스의 수 P (1 ≤ P ≤ 1000)
 *
 * 풀이
 */

public class Main {
	
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	StringBuilder sb = new StringBuilder();
    	
    	int P = sc.nextInt();
    	int[] line;
    	
    	while (P-- > 0) {
    		sb.append(sc.nextInt()).append(" ");
    		
    		line = new int[20];
    		int count = 0;
    		
    		for (int idx = 0; idx < 20; idx++) {
    			line[idx] = sc.nextInt();
    			
    			for (int find = 0; find < idx; find++) {
    				if (line[find] > line[idx])
    					count++;
    			}
    		}
    		
    		sb.append(count).append("\n");
    	}
    	
    	System.out.println(sb.toString());
    }
}
