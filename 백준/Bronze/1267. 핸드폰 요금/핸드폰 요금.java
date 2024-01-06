import java.util.*;

/**
 * @author jihye.byun
 * BOJ 1267 핸드폰 요금 브론즈3
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
    	int Y = 0;
    	int M = 0;
    	
    	while (N-- > 0) {
    		int time = sc.nextInt();
    		
    		Y += (time / 30 + 1) * 10;
    		M += (time / 60 + 1) * 15;
    	}
    	
    	if (Y < M) {
    		System.out.println("Y " + Y);
    	} else if (Y > M) {
    		System.out.println("M " + M);
    	} else if (Y == M) {
    		System.out.println("Y M " + Y);
    	}
    }
}
