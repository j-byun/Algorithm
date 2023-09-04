import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 1806 부분합 골드4 누적합/두포인터
 *
 * 문제
 * 10,000이하의 자연수로 이루어진 길이 N짜리 수열
 * 이 수열에서 연속된 수들의 부분합 중에 그 합이 S이상이 되는 것 중,
 * 가장 짧은 것의 길이를 구하는 프로그램을 작성하시오.
 *
 * 조건
 * N (10 ≤ N < 100,000)
 * S (0 < S ≤ 100,000,000)
 * 수열의 각 원소는 10,000이하의 자연수
 * 부분합이 S 이상이 되는 경우가 불가능하다면 0을 출력한다.
 *
 * 풀이
 * 1. 인덱스 0번부터 시작해서 1개~N개의 누적합, 인덱스 1번부터 시작해서 1개~N-1개의 누적합을 구하자.
 * 2. 각 시작 인덱스 별 누적합이 S 이상이 되면 최소 길이를 업데이트하고 다음 인덱스를 탐색하자.
 * 3. 수열의 원소들을 입력받을 때 모든 원소들의 합을 저장해두고, S보다 작다면 바로 0을 출력하자.
 * => 시간초과
 * 
 * [두 포인터 활용 풀이 방법]
 * 1. start, end 포인터를 만들어서 인덱스 0부터 시작한다.
 * 2. start~end까지의 부분합이 S 이상이 될 때 까지 end 포인터를 증가시킨다.
 * 3. start~end까지의 부분합이 S 이상이라면 부분합이 S 미만이 될 때까지 start 포인터를 증가시킨다.
 * 4. 3번 과정을 진행하며 부분합의 최소 길이를 업데이트한다.
 * 5. while문 탐색 시 인덱스 범위 조정을 위해 배열의 길이를 1 늘려서 만들자.
 */

public class Main {
	
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	int N = sc.nextInt(); // 수열의 길이
    	int S = sc.nextInt(); // 구하려는 부분합의 최소값
    	
    	int[] arr = new int[N + 1]; // 길이 N짜리 수열
    	
    	// 수열의 원소들 입력받기
    	int sum = 0; // 수열의 전체 합을 구하자
    	for (int idx = 0; idx< N; idx++) {
    		arr[idx] = sc.nextInt();
    		sum += arr[idx];
    	}
    	
    	// 수열의 전체 합이 S 미만이라면 0 출력 후 시스템 종료
    	if (sum < S)
    		System.out.println(0);
    	// 수열의 전체 합이 S 이상이라면 최소 부분 수열의 길이를 구하자
    	else
    		System.out.println(calcPartialSum(arr, N, S));
    	
    }
    
    private static int calcPartialSum(int[] arr, int size, int minSum) {
    	// 누적합이 S 이상일 때의 최소 부분 수열 길이를 업데이트하는 메서드
    	
    	int minLength = size; // 부분 합이 S 이상일 때의 최소 부분 수열 길이를 저장할 변수
    	int start = 0; // 시작 포인터
    	int end = 0; // 종료 포인터
    	int sum = 0; // start~end포인터까지의 누적 합
    	
    	while (start <= size && end <= size) {
    		if (sum < minSum) {
    			sum += arr[end++];
    		} else {
    			minLength = Math.min(minLength, end - start);
    			sum -= arr[start++];
    		}
    	}
    	
    	return minLength;
    }
    
}

