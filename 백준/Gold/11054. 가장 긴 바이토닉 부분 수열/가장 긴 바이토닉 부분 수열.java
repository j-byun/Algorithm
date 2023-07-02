import java.util.Scanner;

/**
 * @author jihye.byun
 * BOJ 11054 가장 긴 바이토닉 부분 수열 골드4
 *
 * 문제
 * 수열 A가 주어졌을 때, 그 수열의 부분 수열 중 바이토닉 수열이면서 가장 긴 수열의 길이를 구하자.
 *
 * 조건
 * 수열 A의 크기 : 1 ≤ N ≤ 1,000
 * 수열 A 를 이루고 있는 Ai : 1 ≤ Ai ≤ 1,000
 *
 * 풀이
 * 1. dp배열을 만들어서 LIS와 LDS 배열을 만들고, LIS[index] + LDS[index]의 값이 가장 큰 경우를 확인하자.
 */

public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	int size = sc.nextInt();
    	int[] arr = new int[size];
    	int[] lis = new int[size]; // 최장 증가 부분 수열
    	int[] lds = new int[size]; // 최장 감소 부분 수열
    	
    	for (int idx = 0; idx < size; idx++) {
    		arr[idx] = sc.nextInt();
    	}
    	
    	lis[0] = 1;
    	for (int idx = 1; idx < size; idx++) {
    		lis[idx] = 1;
    		
    		for (int before = 0; before < idx; before++) {
    			if (arr[idx] > arr[before])
    				lis[idx] = Math.max(lis[idx], lis[before] + 1);
    		}
    		
    	}

    	lds[size - 1] = 1;
    	for (int idx = size - 2; idx >= 0; idx--) {
    		lds[idx] = 1;
    		
    		for (int before = size - 1; before > idx; before--) {
    			if (arr[idx] > arr[before])
    				lds[idx] = Math.max(lds[idx], lds[before] + 1);
    		}
    	}
    	
    	int max = 0;
    	
    	for (int idx = 0; idx < size; idx++) {
    			max = Math.max(max, lis[idx] + lds[idx]);
    	}
    	
    	System.out.println(max - 1);
    }
}
