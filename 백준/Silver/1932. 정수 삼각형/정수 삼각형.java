import java.util.Scanner;

// DP
// 1932 정수 삼각형
// 실버1

// 문제
// 맨 위층에서 시작해서 아래에 있는 수 중 하나를 선택하여 아래층을 내려올 때,
// 이제까지 선택된 수의 합이 최대가 되는 경우의 합을 출력하시오

// 아래층에 있는 수는 현재 층에서 선택된 수의 바로 왼쪽, 바로 오른쪽에 있는 수 중에서만 선택 가능

// 조건
// 삼각형의 크기는 1 이상 500 이하 (층 수)
// 삼각형을 이루고 있는 각 수는 모두 정수이다
// 삼각형을 이루고 있는 각 수의 범위는 0이상 9999이하

// 풀이
// 한 층 내려갈때, 위층에서 선택된 인덱스가 k번 이었다면,
// 아래층의 k번이나 k+1번 인덱스의 값만 선택할 수 있다

// 삼각형의 k층에는 k개의 정수가 있다
// => 배열의 최대 크기는 삼각형 크기 만큼이다

// 삼각형의 각 수를 저장할 num배열과,
// 층을 내려가며 선택한 수의 합을 저장할 sum배열을 만들자

// 1층에서는 num배열의 값을 그대로 sum배열에 넣고,
// 2층부터는 윗층의 k번과 k-1번 인덱스 중 큰 값을 구해 그 값과 현재 칸 값의 합을 sum배열에 저장하자
//	=> IndexOutOfBounds에러를 막기 위해 삼각형크기+1의 크기로 배열을 생성해서 0번인덱스는 비워두자

// 삼각형의 마지막 층에서 최대값을 출력하자

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int size = sc.nextInt(); // 삼각형의 크기 입력받기
		
		int[] num = new int[size + 1]; // 삼각형의 각 수를 저장할 공간
		int[] sum = new int[size + 1]; // 지금까지 층을 내려오며 선택한 값을 저장한 공간
		
		// 배열을 1차원으로 만들어서 메모리를 아끼기위해
		// 입력받음과 동시에 최적 경로를 탐색하자
		for (int cnt = 1; cnt < size + 1; cnt++) {
			for (int idx = cnt; idx >= 1; idx--) {
				num[idx] = sc.nextInt();
				
				if (cnt == 1) { // 1층일 때는 입력받은 값 하나를 그대로 선택하자
					sum[idx] = num[idx];
					continue;
				}
				
				// 1층이 아닐 때는 위층의[idx]와 [idx-1]에 저장된 합 중 큰 값을 선택하자
				// 이 때 인덱스가 1부터 증가하는 방향이었으면 idx-1의 값이 이미 업데이트 되어 있기 때문에 원래의 값을 확인할 수 없다
				// 따라서 인덱스를 감소시키는 방향으로 진행하자
				sum[idx] = Math.max(sum[idx], sum[idx - 1]) + num[idx];
			}
		}
		sc.close(); // 입력이 끝났으니 스캐너를 닫아주자
		
		// 마지막 층 까지 값 선택이 끝났으니 저장된 sum배열에서 제일 큰 값을 출력하자
		int maxSum = Integer.MIN_VALUE;
		
		for (int idx = 1; idx < size + 1; idx++) {
			maxSum = Math.max(maxSum, sum[idx]);
		}
		
		System.out.println(maxSum); // 출력
	}

}
