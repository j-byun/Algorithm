import java.util.*;

/**
 * @author jihye.byun
 * BOJ 5522 카드 게임 브론즈5
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
    	
    	int sum = 0;
    	
    	for (int idx = 0; idx < 5; idx++) {
    		sum += sc.nextInt();
    	}
    	
    	System.out.println(sum);
    }
}
