import java.util.*;

/**
 * @author jihye.byun
 * BOJ 5585 거스름돈 브론즈2
 * 시간 제한 1초 | 메모리 제한 128 MB
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
    	
    	int cost = sc.nextInt();
    	int left = 1000 - cost;
    	
    	int answer = 0;
    	
    	answer += left / 500;
    	left %= 500;
    	
    	answer += left / 100;
    	left %= 100;
    	
    	answer += left / 50;
    	left %= 50;
    	
    	answer += left / 10;
    	left %= 10;
    	
    	answer += left / 5;
    	left %= 5;
    	
    	answer += left;
    	
    	System.out.println(answer);
    }
}
