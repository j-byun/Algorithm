import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 2512 예산 실버2
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
    	
    	int N = sc.nextInt();
    	int[] arr = new int[N];
    	int left = 0;
    	int right = 0;
    	
    	for (int idx = 0; idx < N; idx++) {
    		arr[idx] = sc.nextInt();
    		right = Math.max(right, arr[idx]);
    	}
    	
    	Arrays.sort(arr);
    	
    	int M = sc.nextInt();
    	
    	while (left <= right) {
    		int mid = (left + right) / 2;
    		int sum = 0;
    		
    		int index = Arrays.binarySearch(arr, mid);
    		if (index < 0) index = (index + 1) * -1;
    		
    		for (int idx = 0; idx < index; idx++) {
    			sum += arr[idx];
    		}
    		sum += mid * (N - index);
    		
    		if (sum <= M) left = mid + 1;
    		else right = mid - 1;
    	}
    	
    	System.out.println(right);
    }
    
}
