import java.util.*;
import java.math.BigInteger;

/**
 * @author jihye.byun
 * BOJ 2407 조합 실버3
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
    	
    	int n = sc.nextInt();
    	int m = sc.nextInt();
    	
    	BigInteger answer = BigInteger.ONE;
    	
    	for (int cnt = 1; cnt <= m; cnt++) {
    		answer = answer.multiply(new BigInteger(String.valueOf(n--)));
    		answer = answer.divide(new BigInteger(String.valueOf(cnt)));
    	}
    	
    	System.out.println(answer);
    }
}
