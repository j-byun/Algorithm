import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 1976 여행 가자 골드4
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
    	int M = sc.nextInt();

    	int[] arr = new int[N + 1];
    	for (int idx = 0; idx <= N; idx++) {
    		arr[idx] = idx;
    	}
    	
    	for (int cnt = 0; cnt < M; cnt++) {
    		int start = sc.nextInt();
    		int end = sc.nextInt();
    		
    		for (int idx = start; idx <= (start + end) / 2; idx++) {
    			int tmp = arr[idx];
    			arr[idx] = arr[start + end - idx];
    			arr[start + end - idx] = tmp;
    		}
    	}
    	
    	for (int idx = 1; idx <= N; idx++) {
    		System.out.print(arr[idx] + " ");
    	}
    	  
    }
}
