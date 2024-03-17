import java.util.*;
import java.math.BigInteger;

/**
 * @author jihye.byun
 * BOJ 2338 긴자리 계산 브론즈5
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
    	
    	BigInteger a = sc.nextBigInteger();
    	BigInteger b = sc.nextBigInteger();
    	
    	System.out.println(a.add(b));
    	System.out.println(a.subtract(b));
    	System.out.println(a.multiply(b));
    }
}
