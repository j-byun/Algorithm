import java.util.Scanner;

/**
 * @author jihye.byun
 * BOJ 1940 주몽 실버4 정렬/투포인터
 * 
 * 문제
 * 두 개의 재료로 갑옷을 만들자.
 * 두 재료의 고유한 번호를 합쳐서 M이 되면 갑옷이 만들어지게 된다.
 * 현재 재료로 만들 수 있는 갑옷의 개수를 구하자.
 * 
 * 조건
 * 재료의 개수 N(1 ≤ N ≤ 15,000)
 * 두 번째 줄에는 갑옷을 만드는데 필요한 수 M(1 ≤ M ≤ 10,000,000)
 * N개의 재료들이 가진 고유한 번호는 100,000보다 작거나 같은 자연수이다.
 * 
 * 풀이1 - 조합(재귀)
 * 1. 두 개의 재료를 뽑는 조합을 만들자.
 * 2. 두 재료의 합이 M이 되면 카운트를 세어주자.
 * 2-1. 재귀 호출 횟수를 줄이기 위해, 재료 선택시 합이 M이 넘어간다면 해당 가지는 호출하지 말자.
 * => 메모리 30792KB, 시간 1036ms
 * 
 * 풀이2 - 정렬
 * 1. 재료를 번호 순서대로 (편의상 오름차순으로) 정렬하자.
 * 2. 재료 하나를 선택하고, 다른 재료들을 모두 확인해보면서 현재 재료와의 번호 합이 M이 되는 재료가 있다면 카운트를 세어주자.
 * 2-1. 재료를 번호 순서대로 정렬했으니, 탐색 시간을 줄이기 위해 합쳐서 M이 넘어간다면 탐색을 종료하자.
 * 2-2. 같은 번호의 재료가 여러 개 일 수 있으니, 합쳐서 M이 되는 재료를 하나 찾았다고 바로 끝내지는 말자.
 * => 메모리 31528KB, 시간 500ms
 */

public class Main {
	
	/*
	 * 풀이1 - 조합(재귀)
	 *
	static int N, M, count;
	static int[] num;
	
	public static void comb(int start, int sum, int depth, int maxDepth) {
		
		if (depth == maxDepth) {
			if (sum == M)
				count++;
			return;
		}
		
		for (int idx = start; idx < N; idx++) {
			if (sum + num[idx] > M) continue;
			comb(idx + 1, sum + num[idx], depth + 1, maxDepth);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		num = new int[N];
		
		for (int idx = 0; idx < N; idx++) {
			num[idx] = sc.nextInt();
		}
		
		count = 0;
		
		comb(0, 0, 0, 2);
		
		System.out.println(count);
	}
	*/
	
	
	// 풀이2 - 정렬
	
	// 1. num배열을 정렬 메서드의 파라미터로 들고다니니까 메모리 초과 에러 발생
	// => static으로 만들어서 재귀 호출 시 메모리를 줄여주자
	// 2. tmp배열을 merge 메서드 안에서 계속 새로 생성해주니까 메모리 초과 에러 발생
	// => 어차피 필요한 부분만 인덱스 포인터로 가르킬거니까 얘도 static으로 만들어서 메모리를 줄여주자
	//		=> 1+2로 메모리 초과 에러 해결!
	static int[] num, tmp;
	
	public static void mergeSort(int start, int end) {
		
		// 더 이상 쪼갤 수 없으면 반환
		if (start >= end) {
			return;
		}
		
		// 쪼갤 수 있다면 분할정복하자!
		int mid = (start + end) / 2;
		
		mergeSort(start, mid); // 분할
		mergeSort(mid + 1, end); // 분할
		merge(start, end); // 합병
	}
	
	public static void merge(int start, int end) {
		
		int mid = (start + end) / 2;
		int left = start; // 왼쪽 포인터는 시작점부터
		int right = mid + 1; // 오른쪽 포인터는 중간 + 1번째부터
		int idx = left; // 배열에 시작점부터 채워넣자
		
		// 왼쪽 오른쪽 둘 다 각자가 확인해야 할 배열의 범위를 벗어나지 않았다면,
		while (left <= mid && right <= end) {
			if (num[left] <= num[right]) // 왼쪽 배열 값이 더 작으면 왼쪽 값으로 채워넣고
				tmp[idx++] = num[left++];
			else // 오른쪽 배열 값이 더 작으면 오른쪽 값으로 채워넣자
				tmp[idx++] = num[right++];
		}
		
		// 왼쪽 배열의 값이 남았다면, 오른쪽 배열의 수들이 더 작았던 거니까 남은 왼쪽 배열 수들을 채워넣자
		while (left <= mid) {
			tmp[idx++] = num[left++];
		}
		
		// 오른쪽 배열의 값이 남았다면, 왼쪽 배열의 수들이 더 작았던 거니까 남은 오른쪽 배열 수들을 채워넣자
		while (right <= end) {
			tmp[idx++] = num[right++];
		}
		
		// 임시배열에 저장된 정렬된 수들을 원본 배열에 복사해주자
		for (int index = start; index <= end; index++) {
			num[index] = tmp[index];
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt(); // 재료의 개수
		int M = sc.nextInt(); // 만들어야 하는 수
		
		num = new int[N]; // 각 재료의 번호를 저장할 배열 공간
		
		// 재료의 번호들을 입력받자
		for (int idx = 0; idx < N; idx++) {
			num[idx] = sc.nextInt();
		}
		
		// 재료 번호들을 저장한 num배열을 오름차순으로 정렬해주자
		tmp = new int[N]; // 정렬에 사용할 임시 배열
		mergeSort(0, N - 1);
		
		int count = 0; // 두 재료를 합쳐서 M이 되는 경우를 세어줄 변수
		
		/*
		for (int in1 = 0; in1 < N; in1++) {
			for (int in2 = in1 + 1; in2 < N; in2++) {
				// 재료는 하나씩만 존재하니까 똑같은 재료 두 개는 고려하면 안되고,
				// 1번 재료 + 2번 재료 = 2번 재료 + 1번 재료 이니까, 순열이 아닌 조합을 만들어야한다
				// 그러니 in2는 in1+1부터 시작하자
				
				// 두 재료의 합이 M이라면 count를 세어주자
				if (num[in1] + num[in2] == M) count++;
				
				// 두 재료의 합이 M을 넘었다면, 이후의 in2는 더 이상 볼 필요가 없다
				// 오름차순 정렬해줬기 때문
				// for문 하나를 벗어나서 다음 in1을 확인하자
				if (num[in1] + num[in2] > M) break;
			}
		}
		*/
		
		int left = 0;
		int right = N - 1;
		
		while (left < right) {
			int sum = num[left] + num[right];
			
			if (sum == M) {
				count++;
				left++;
			} else if (sum < M) {
				left++;
			} else {
				right--;
			}
		}
		
		// 모든 재료 조합을 확인했다면 세어준 개수를 출력하자
		System.out.println(count);
	}
}
