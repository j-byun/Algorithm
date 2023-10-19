import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 4999 아! 브론즈5	
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
    	
    	String jaehwan = sc.next();
    	String doctor = sc.next();
    	
    	System.out.println((jaehwan.length() >= doctor.length()) ? "go" : "no");
    }
}
