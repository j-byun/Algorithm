import java.util.Scanner;

/**
 * @author jihye.byun
 * BOJ 2467 용액 골드5
 *
 * 문제
 * 많은 종류의 산성 용액과 알칼리성 용액
 * 각 용액에는 그 용액의 특성을 나타내는 하나의 정수가 주어져있다.
 * 산성 용액의 특성값은 1부터 1,000,000,000까지의 양의 정수로 나타내고,
 * 알칼리성 용액의 특성값은 -1부터 -1,000,000,000까지의 음의 정수로 나타낸다.
 * 같은 양의 두 용액을 혼합한 용액의 특성값은 혼합에 사용된 각 용액의 특성값의 합으로 정의한다.
 * 같은 양의 두 용액을 혼합하여 특성값이 0에 가장 가까운 용액을 만들려고 한다.
 * 두 종류의 알칼리성 용액만으로나 혹은 두 종류의 산성 용액만으로 특성값이 0에 가장 가까운 혼합 용액을 만드는 경우도 존재할 수 있다.
 * 산성 용액과 알칼리성 용액의 특성값이 정렬된 순서로 주어졌을 때,
 * 이 중 두 개의 서로 다른 용액을 혼합하여 특성값이 0에 가장 가까운 용액을 만들어내는 두 용액을 찾자.
 *
 * 조건
 * 전체 용액의 수 N은 2이상 100,000이하의 정수
 * N개의 용액들의 특성값은 모두 서로 다르고, 산성 용액만으로나 알칼리성 용액만으로 입력이 주어지는 경우도 있을 수 있다.
 *
 * 풀이
 * 1. 투 포인터를 활용하자.
 */

public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	int N = sc.nextInt(); // 용액의 개수
    	int[] arr = new int[N]; // 용액 특성 번호를 저장할 배열
    	
    	// 용액 특성 번호 입력받기
    	for (int idx = 0; idx < N; idx++) {
    		arr[idx] = sc.nextInt();
    	}
    	
    	int left = 0;
    	int right = N - 1;
    	int min = Integer.MAX_VALUE;
    	int ansLeft = 0;
    	int ansRight = 0;
    	
    	while (left < right) {
    		int sum = arr[left] + arr[right];
    		if (min > Math.abs(sum)) {
    			min = Math.abs(sum);
    			ansLeft = left;
    			ansRight = right;
    		}
    		
    		if (sum == 0)
    			break;
    		else if (sum > 0)
    			right--;
    		else
    			left++;
    	}
    	
    	System.out.println(arr[ansLeft] + " " + arr[ansRight]);
    }
}
