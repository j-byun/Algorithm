import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 30017 치즈버거 만들기 브론즈4
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
    	
    	int a = sc.nextInt();
    	int b = sc.nextInt();
    	
    	if (a > b) {
    		System.out.println(2 * b + 1);
    	} else {
    		System.out.println(2 * a - 1);
    	}
    	
    	
    }
    
}
