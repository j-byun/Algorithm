import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 5073 삼각형과 세 번 브론즈3
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
    	StringBuilder sb = new StringBuilder();
    	
    	while (true) {
    		
    		int[] triangle = new int[3];
    		
    		for (int idx = 0; idx < 3; idx++) {
    			triangle[idx] = sc.nextInt();
    		}
    		
    		if (triangle[0] == 0) break;
    		
    		Arrays.sort(triangle);
    		
    		if (triangle[0] + triangle[1] <= triangle[2]) {
    			sb.append("Invalid\n");
    			continue;
    		}
    		
    		if (triangle[0] == triangle[1] && triangle[1] == triangle[2]) {
    			sb.append("Equilateral\n");
    			continue;
    		}
    		
    		if (triangle[0] == triangle[1] || triangle[1] == triangle[2] || triangle[0] == triangle[1] && triangle[0] == triangle[2]) {
    			sb.append("Isosceles\n");
    			continue;
    		}
    		
    		sb.append("Scalene\n");
			continue;
    	}
    	
    	System.out.println(sb.toString());
    }
}
