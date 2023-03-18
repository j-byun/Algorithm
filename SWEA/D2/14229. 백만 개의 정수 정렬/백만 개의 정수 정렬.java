import java.util.Scanner;

/**
 * @author jhye.byun SWEA 14229 백만 개의 정수 정렬 D2 분할정복 (병합정렬/퀵정렬)
 * 
 *         문제 공백으로 구분된 백만개의 정수가 주어진다. 오름차순으로 정렬한 후 배열 A에 저장하고 A[500000]을 출력하라.
 *
 *         조건 1<=A[i]<=1000000
 * 
 *         풀이 1. 병합정렬로 정렬하자.
 */

public class Solution {

	static int[] num, tmp;

	private static void mergeSort(int start, int end) {

		if (start >= end)
			return;

		int mid = (start + end) / 2;

		// 재귀 호출
		mergeSort(start, mid);
		mergeSort(mid + 1, end);

		int p = start;
		int q = mid + 1;
		int idx = p;

		while (p <= mid || q <= end) {
			if (q > end || (p <= mid && num[p] < num[q]))
				tmp[idx++] = num[p++];
			else
				tmp[idx++] = num[q++];
		}

		for (int index = start; index <= end; index++) {
			num[index] = tmp[index];
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int start = 0;
		int end = 1000000;
		int mid = (start + end) / 2;

		num = new int[end];
		tmp = new int[end];

		for (int idx = 0; idx < end; idx++) {
			num[idx] = sc.nextInt();
		}

		mergeSort(start, end - 1); // 병합정렬

		System.out.println(num[mid]); // 출력
	}

}
