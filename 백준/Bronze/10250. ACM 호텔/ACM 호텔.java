import java.util.*;

/**
 * @author jihye.byun
 * BOJ 10250 ACM 호텔 브론즈3
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
    	
    	int T = sc.nextInt();
    	
    	for (int tc = 1; tc <= T; tc++) {
    		int H = sc.nextInt();
    		int W = sc.nextInt();
    		int N = sc.nextInt();
    		
    		int room = 0;
    		
    		if (N % H == 0) {
    			room = H * 100 + (N / H);
    		}
    		
    		if (N % H != 0) {
    			room = (N % H) * 100 + (N / H) + 1;
    		}
    		
    		sb.append(room).append("\n");
    	}
    	
    	System.out.println(sb.toString());
    }
}
