import java.util.*;

/**
 * @author jihye.byun
 * BOJ 9655 돌 게임 실버5
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
    	
    	System.out.println((N % 2 == 0) ? "CY" : "SK");
    }
}
