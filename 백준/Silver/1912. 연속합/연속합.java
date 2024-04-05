import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 1912 연속합 실버2
 * 시간 제한 1초 | 메모리 제한 128 MB
 *
 * 문제
 *
 * 조건
 *
 * 풀이
 * 1. 누적합 배열로 바꿔서 생각하자.
 */

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int n = Integer.parseInt(st.nextToken());
    	
    	int[] arr = new int[n];
    	
    	st = new StringTokenizer(br.readLine());
    	for (int idx = 0; idx < n; idx++) {
    		arr[idx] = Integer.parseInt(st.nextToken());
    		
    		// 누적합
    		if (idx != 0) {
    			arr[idx] += arr[idx - 1];
    		}
    	}
    	
    	int maxSum = arr[0];
    	int minSum = Math.min(arr[0], 0);
    	
    	for (int idx = 1; idx < n; idx++) {
    		maxSum = Math.max(maxSum, arr[idx] - minSum);
    		minSum = Math.min(minSum, arr[idx]);
    	}
    	
    	System.out.println(maxSum);
    }
}
