import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 1205 등수 구하기 실버4
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
    	int newScore = sc.nextInt();
    	int P = sc.nextInt();
    	
    	int[] scores = new int[N];
    	
    	for (int idx = 0; idx < N; idx++) {
    		scores[idx] = sc.nextInt();
    	}
    	
    	if (N == P && newScore <= scores[N - 1]) {
    		System.out.println(-1);
    		return;
    	}
    	
    	int left = 0;
    	int right = N - 1;
    	int mid;
    	
    	while (left <= right) {
    		mid = (left + right) / 2;
    		
    		if (scores[mid] <= newScore)
    			right = mid - 1;
    		else
    			left = mid + 1;
    	}
    	
    	System.out.println((++left > P) ? -1 : left);
    	
    }
}
