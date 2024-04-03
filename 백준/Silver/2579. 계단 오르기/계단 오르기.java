import java.util.Scanner;

// DP
// 2579 계단 오르기
// 실버3

// 문제
// 계단을 밟으면 해당 계단에 쓰여 있는 점수를 획득하며 꼭대기까지 도착하기

// 계단 오르기 규칙
// 1. 계단은 한 번에 한 계단씩 또는 두 계단씩 오를 수 있다.
// 2. 연속된 세 개의 계단을 모두 밟아서는 안 된다.
//		(시작점은 계단에 포함되지 않는다.)
// 3. 마지막 도착 계단은 반드시 밟아야 한다.

// 조건
// 계단의 개수는 300이하의 자연수
// 계단에 쓰여 있는 점수는 10,000이하의 자연수

// 풀이
// 300*10,000 => int범위 내에서 해결 가능

// k번째 계단을 밟을 때의 경우를 생각해보자
// 1. k-1번째 계단을 밟고 k-2번은 밟지 않고 k-3번째 계단을 밟거나
// 2. k-1번째 계단은 밟지 않고 k-2번째 계단을 밟는 경우가 있다

// 그럼 다시 k-1번째 계단에서
// k-2번째 계단을 밟고 k-4번째 계단을 밟거나
// k-3번째 계단을 밟는 경우가 있다

// => 여태까지 밟은 계단의 점수 합을 dp배열에 저장해놓는다고 했을 때,
// dp[k] = Math.max(arr[k-1] + dp[k-3], dp[k-2]) + arr[k] 로 표현할 수 있다

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int stairsCnt = sc.nextInt(); // 계단의 개수 입력받기
		int[] stairs = new int[stairsCnt + 1]; // 각 계단에 쓰여진 점수를 저장할 배열 공간
		int[] dp = new int[stairsCnt + 1]; // 현재까지 오른 계단의 점수 합을 저장할 배열 공간

		for (int cnt = 1; cnt < stairsCnt + 1; cnt++) {
			stairs[cnt] = sc.nextInt(); // 각 계단의 점수 입력받기
		}
		sc.close(); // 입력이 끝났으니 스캐너를 닫아주자
		
		// 첫 번째 계단은 이전 계단을 고려할 게 없으니 일단 밟자
		dp[1] = stairs[1];
		// 두 번째 계단은 첫 번째 계단과 두 번째 계단을 연속으로 오를 수 있으니 두 계단을 다 밟자
		// 입력된 계단 개수가 1개일 경우 IndexOutOfBounds 에러가 발생하니 if문으로 막아주자
		if (stairsCnt >= 2)
			dp[2] = stairs[1] + stairs[2];
		
		// 세 번째 계단 부터는 k-1 + k-3을 밟을 지, 아니먄 k-2를 밟을 지 고려해주자
		for (int idx = 3; idx < stairsCnt + 1; idx++) {
			dp[idx] = Math.max(stairs[idx - 1] + dp[idx - 3], dp[idx- 2]) + stairs[idx];
		}
		
		// 마지막 계단까지 다 확인했으면 dp의 마지막 인덱스에 저장된 점수가 최대 점수이다
		System.out.println(dp[stairsCnt]);
	}

}
