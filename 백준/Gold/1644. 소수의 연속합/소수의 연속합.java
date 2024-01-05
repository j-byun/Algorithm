import java.util.*;

/**
 * @author jihye.byun
 * BOJ 1644 소수의 연속합 골드3
 *
 * 문제
 * 자연수가 주어졌을 때, 이 자연수를 연속된 소수의 합으로 나타낼 수 있는 경우의 수를 구하시오.
 *
 * 조건
 * 자연수 N (1 ≤ N ≤ 4,000,000)
 *
 * 풀이
 * 1. N 이하의 소수들을 모두 구한다.
 * 2. 투 포인터로 left = 0, right = 0에서 시작해서 합이 N보다 작으면 right 증가, 합이 N보다 크면 left 증가하며 탐색한다.
 */

public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	// 소수의 합으로 나타내야 하는 자연수 N 입력받기
    	int N = sc.nextInt();
    	
    	if (N == 1) {
    		System.out.println("0");
    		return;
    	}
    	
    	// 에라토스테네스의 체
    	// N 이하의 소수들을 판별할 배열 만들기
    	boolean[] isPrime = new boolean[N + 1];
    	
    	// 소수판별배열의 기본값을 true로 설정
    	Arrays.fill(isPrime, true);
    	
    	// 0과 1은 소수가 아니니 false로 설정
    	isPrime[0] = false;
    	isPrime[1] = false;
    	
    	// 2부터 루트N까지의 수를 확인해서 그 배수들을 false 처리
    	for (int idx = 2; idx <= Math.sqrt(N); idx++) {
    		if (!isPrime[idx]) continue;
    		
    		for (int factor = 2; idx * factor <= N; factor++) {
    			isPrime[idx * factor] = false;
    		}
    	}
    	// 소수 판별 끝
    	
    	// 포인터가 소수만 가르킬 수 있도록 소수들을 list에 저장하기
    	ArrayList<Integer> primeNums = new ArrayList<>();
    	
    	for (int idx = 0; idx <= N; idx++) {
    		if (isPrime[idx])
    			primeNums.add(idx);
    	}
    	
    	// 투 포인터로 슬라이드하며 소수의 합이 N이 되는 경우의 수 구하기
    	int count = 0;
    	int left = 0;
    	int right = 0;
    	int sum = primeNums.get(right);
    	
    	while (right < primeNums.size()) {
    		
    		if (sum == N) {
    			count++;
    			sum -= primeNums.get(left);
    			left++;
    		}
    		
    		if (sum <= N) {
    			right++;
    			if (right == primeNums.size()) break;
    			sum += primeNums.get(right);
    		}
    		
    		if (sum > N) {
    			sum -= primeNums.get(left);
    			left++;
    		}
    	}
    	
    	System.out.println(count);
    }
}
