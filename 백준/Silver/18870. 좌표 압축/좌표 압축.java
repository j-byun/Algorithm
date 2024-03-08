import java.util.*;
import java.io.*;
import java.lang.reflect.Array;

/**
 * @author jihye.byun
 * BOJ 18870 좌표 압축 실버2
 * 시간 제한 2초 | 메모리 제한 512 MB
 *
 * 문제
 * 수직선 위의 N개의 좌표를 압축하자.
 * i번 좌쵸를 압축한 값은 i번 좌표보다 작은 서로 다른 좌표의 개수와 같아야 한다.
 * N개의 좌표를 압축한 결과를 출력하자.
 *
 * 조건
 * 1 ≤ N ≤ 1,000,000
 * -10^9 ≤ Xi ≤ 10^9
 *
 * 풀이
 * 1. 나보다 작은 수의 갯수를 세자 -> 오름차순 정렬 후 나의 인덱스
 * 2. 그렇다면 원래의 좌표와 정렬된 좌표를 가지고, 원래 좌표의 값이 정렬된 좌표에서 몇번째에 위치하고 있는지 이분탐색으로 찾자.
 * 3. 근데 서로 다른 좌표라서 중복값은 안된다....? -> 배열 복사를 바로 배열에 하지 말고 HashSet에 복사한 후 옮겨서 정렬해야겠다!
 */

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	StringBuilder sb = new StringBuilder();
    	
    	int N = Integer.parseInt(st.nextToken()); // 좌표의 개수 입력받기
    	
    	int[] coo = new int[N]; // N개의 좌표를 저장할 배열 생성
    	HashSet<Integer> set = new HashSet<>(); // 중복값없는 좌표를 저장할 HashSet
    	
    	// N개의 좌표 정보 입력받기
    	st = new StringTokenizer(br.readLine());
    	for (int idx = 0; idx < N; idx++) {
    		coo[idx] = Integer.parseInt(st.nextToken());
    		
    		set.add(coo[idx]); // 중복값없이 배열 복사
    	}
    	
    	// set에 저장된 중복없는 좌표를 배열로 옮기기 (set -> Integer배열 -> int배열)
    	int[] sortedCoo = set.stream().mapToInt(Integer::intValue).toArray();
    	
    	// 복사한 배열 정렬
    	// Arrays.sort : 듀얼피봇퀵소트..
    	// 는 모르겠고 MergeSort로 구현해보자 -> 정렬 연습용
//    	mergeSort(sortedCoo, 0, sortedCoo.length);
    	Arrays.sort(sortedCoo);
    	
    	// 이제 현재 좌표가 정렬된 배열에서 몇번째에 위치하는지 이분탐색으로 찾아서 출력하자
    	for (int idx = 0; idx < N; idx++) {
//    		sb.append(binarySearch(sortedCoo, coo[idx])).append(" ");
    		sb.append(Arrays.binarySearch(sortedCoo, coo[idx])).append(" ");
    	}
    	
    	System.out.println(sb.toString());
    }
    
    static void mergeSort(int[] arr, int start, int end) {
    	// 이번 범위가 잘못된거
    	if (start >= end) {
    		return;
    	}
    	
    	// 이건 원소가 하나밖에 없어서 정렬할 필요가 없는거
    	if (start + 1 == end) {
    		return;
    	}
    	
    	int mid = (start + end) / 2;
    	mergeSort(arr, start, mid);
    	mergeSort(arr, mid, end);
    	merge(arr, start, end);
    }
    
    static void merge(int[] arr, int start, int end) {
    	
    	int[] tmp = new int[end];
    	int mid = (start + end) / 2;
    	int left = start;
    	int right = mid;
    	int index = start;
    	
    	while (left < mid && right < end) {
    		if (arr[left] <= arr[right])
    			tmp[index++] = arr[left++];
    		if (arr[left] > arr[right])
    			tmp[index++] = arr[right++];
    	}
    	
    	while (left < mid) {
    		tmp[index++] = arr[left++];
    	}
    	
    	while (right < end) {
    		tmp[index++] = arr[right++];
    	}
    	
    	for (int idx = start; idx < end; idx++) {
    		arr[idx] = tmp[idx];
    	}
    }
    
    static int binarySearch(int[] arr, int num) {
    	int start = 0;
    	int end = arr.length - 1;
    	int mid;
    	
    	while (start <= end) {
    		mid = (start + end) / 2;
    		
    		if (arr[mid] == num) {
    			return mid;
    		}
    		
    		if (arr[mid] < num) {
    			start = mid + 1;
    		}
    		
    		if (arr[mid] > num) {
    			end = mid - 1;
    		}
    	}
    	
    	return -1;
    }
}
