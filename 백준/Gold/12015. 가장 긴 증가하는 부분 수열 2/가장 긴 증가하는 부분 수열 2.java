import java.util.*;
import java.util.Map.Entry;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 12015 가장 긴 증가하는 부분 수열 2 골드2
 *
 * 문제
 * 수열 A가 주어졌을 때, 가장 긴 증가하는 부분 수열을 구하자.
 *
 * 조건
 * 수열 A의 크기 N (1 ≤ N ≤ 1,000,000)
 * 수열 A를 이루고 있는 Ai (1 ≤ Ai ≤ 1,000,000)
 *
 * 풀이
 */

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int N = Integer.parseInt(st.nextToken());
    	int[] arr = new int[N];
    	int[] lis = new int[N];
    	
    	int maxLength = 0;
    	
    	st = new StringTokenizer(br.readLine());
    	for (int idx = 0; idx < N; idx++) {
    		arr[idx] = Integer.parseInt(st.nextToken());
    		
    		int insertIdx = Arrays.binarySearch(lis, 0, maxLength, arr[idx]);
    		if (insertIdx < 0) insertIdx = (insertIdx + 1) * -1;
    		lis[insertIdx] = arr[idx];
    		if (insertIdx >= maxLength) maxLength++;
    	}
    	
    	System.out.println(maxLength);
    }
}
