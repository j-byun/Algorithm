import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 19637 IF문 좀 대신 써줘 실버3
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
    	StringBuilder sb = new StringBuilder();
    	
    	int N = Integer.parseInt(st.nextToken());
    	int M = Integer.parseInt(st.nextToken());
    	
    	String[] title = new String[N];
    	int[] power = new int[N];
    	
    	for (int idx = 0; idx < N; idx++) {
    		st = new StringTokenizer(br.readLine());
    		
    		title[idx] = st.nextToken();
    		power[idx] = Integer.parseInt(st.nextToken());
    	}
    	
    	for (int cnt = 0; cnt < M; cnt++) {
    		st = new StringTokenizer(br.readLine());
    		
    		int num = Integer.parseInt(st.nextToken());
    		
    		int left = 0;
    		int right = N - 1;
    		int mid;
    		
    		while (left <= right) {
    			mid = (left + right) / 2;
    			
    			if (num > power[mid])
    				left = mid + 1;
    			else
    				right = mid - 1;
    		}
    		
    		sb.append(title[left]).append("\n");
    	}
    	
    	System.out.println(sb.toString());
    }
}
