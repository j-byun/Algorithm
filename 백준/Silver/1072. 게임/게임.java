import java.util.*;

/**
 * @author jihye.byun
 * BOJ 1072 게임 실버3
 * 시간 제한 2초 | 메모리 제한 128 MB
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
    	
    	long X = sc.nextInt();
    	long Y = sc.nextInt();
    	long Z = 100 * Y / X;
    	
    	int answer = -1;
    	int start = 0;
    	int end = 1_000_000_000;
    	int mid = (start + end) / 2;
    	
    	while (start <= end) {
    		mid = (start + end) / 2;
    		
    		if (100 * (Y + mid) / (X + mid) > Z) {
    			answer = mid;
    			end = mid - 1;
    		} else {
    			start = mid + 1;
    		}
    	}
    	
    	System.out.println(answer);
    }
}
