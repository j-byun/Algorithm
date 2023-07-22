import java.util.Scanner;

/**
 * @author jihye.byun
 * BOJ 2480 주사위 세개 브론즈4
 *
 * 문제
 * 주사위 3개를 던지자.
 * 1. 같은 눈이 3개가 나오면 10,000원+(같은 눈)*1,000원의 상금을 받게 된다.
 * 2. 같은 눈이 2개만 나오는 경우에는 1,000원+(같은 눈)*100원의 상금을 받게 된다.
 * 3. 모두 다른 눈이 나오는 경우에는 (그 중 가장 큰 눈)*100원의 상금을 받게 된다.
 * 3개의 주사위의 나온 눈이 주어질 때, 상금을 계산하는 프로그랭을 작성하시오.
 *
 * 조건
 *
 * 풀이
 */

public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	int a = sc.nextInt();
    	int b = sc.nextInt();
    	int c = sc.nextInt();
    	
    	// 1. 모두 같은 눈일 때
    	if (a == b && b == c) {
    		System.out.println(10_000 + a * 1_000);
    	} else if (a != b && b != c && a != c) {
    		// 2. 모두 다른 눈일 때
    		int max = Math.max(a, Math.max(b, c));
    		System.out.println(max * 100);
    	} else {
    		if (a == b || a == c)
    			System.out.println(1_000 + a * 100);
    		else
    			System.out.println(1_000 + b * 100);
    	}
    }
}
