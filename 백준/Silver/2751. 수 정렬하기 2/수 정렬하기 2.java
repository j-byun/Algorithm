import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

/**
 * @author jihye.byun
 * BOJ 2751 수 정렬하기 2 실버5
 * 
 * 문제
 * 
 * 조건
 * 
 * 풀이
 * 1. 병합정렬을 구현해보자.
 */

public class Main {
	
	static int N;
	static int[] nums, tmp;
	
	// 분할
	public static void merge(int start, int end) {
		
		int mid = (start + end) / 2;
		
		if (start >= mid)
			return;
		
		merge(start, mid);
		merge(mid, end);
		sort(start, end);
	}
	
	// 정렬
	public static void sort(int start, int end) {
		
		int index = start;
		int left = start;
		int mid = (start + end) / 2;
		int right = mid;
		
		while (left < mid && right < end) {
			if (nums[left] <= nums[right]) {
				tmp[index++] = nums[left++];
			} else {
				tmp[index++] = nums[right++];
			}
		}
		
		while (left < mid) {
			tmp[index++] = nums[left++];
		}
		
		while (right < end) {
			tmp[index++] = nums[right++];
		}
		
		for (int idx = start; idx < end; idx++) {
			nums[idx] = tmp[idx];
		}
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine().trim());
		
		nums = new int[N];
		tmp = new int[N];
		
		// 수 입력받기
		for (int idx = 0; idx < N; idx++) {
			nums[idx] = Integer.parseInt(br.readLine().trim());
		}
		
		// 정렬
		merge(0, N);
		
		// 출력
		for (int idx = 0; idx < N; idx++) {
			bw.write(nums[idx] + "\n");
		}
		bw.flush();
	}
}
