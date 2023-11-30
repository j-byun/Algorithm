import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 21921 블로그 실버3
 *
 * 문제
 *
 * 조건
 *
 * 풀이
 */

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int N = Integer.parseInt(st.nextToken());
    	int X = Integer.parseInt(st.nextToken());
    	
    	int[] visitors = new int[N];
    	
    	st = new StringTokenizer(br.readLine());
    	for (int idx = 0; idx < N; idx++) {
    		visitors[idx] = Integer.parseInt(st.nextToken());
    	}
    	
    	int start = 0;
    	int end = start + X - 1;
    	int curVisitor = 0;
    	
    	for (int idx = start; idx <= end; idx++) {
    		curVisitor += visitors[idx];
    	}
    	
    	int maxVisitor = curVisitor;
    	int maxCount = 1;
    	
    	while (end < N - 1) {
    		curVisitor -= visitors[start++];
    		curVisitor += visitors[++end];
    		
    		if (maxVisitor == curVisitor) {
    			maxCount++;
    		}
    		
    		if (maxVisitor < curVisitor) {
        		maxVisitor = curVisitor;
        		maxCount = 1;
        	}
    	}
    	
    	if (maxVisitor == 0) {
    		System.out.println("SAD");
    		return;
    	}
    	
    	System.out.println(maxVisitor);
    	System.out.println(maxCount);
    }
}
