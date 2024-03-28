import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 2470 두 용액 골드5
 * 시간 제한 1초 | 메모리 제한 128 MB
 *
 * 문제
 * 산성 용액 : 1 ~ 10억
 * 알칼리성 용액 : -1 ~ -10억
 * 
 * 서로 다른 용액을 혼합하여 특성값이 0에 가장 가까운 용액을 만들자.
 *
 * 조건
 * 용액의 수 N (N은 2 이상 100,000 이하)
 * N개의 용액들의 특성값은 모두 다르고, 산성 용액만으로나 알칼리성 용액만으로 입력이 주어지는 경우도 있을 수 있다.
 *
 * 풀이
 * 1. 두 개만 혼합하면 되니까 두포인터로 찾자.
 */

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int N = Integer.parseInt(st.nextToken());
    	int[] solution = new int[N];
    	
    	st = new StringTokenizer(br.readLine());
    	for (int idx = 0; idx < N; idx++) {
    		solution[idx] = Integer.parseInt(st.nextToken());
    	}
    	
    	Arrays.sort(solution);
    	
    	int left = 0;
    	int right = N - 1;
    	int minGap = Integer.MAX_VALUE;
    	int answerLeft = 0;
    	int answerRight = N - 1;
    	
    	while (right < N && left < right) {
    		
    		if (minGap > Math.abs(solution[right] + solution[left])) {
    			minGap = Math.abs(solution[right] + solution[left]);
    			answerLeft = left;
    			answerRight = right;
    		}
    		
    		if (Math.abs(solution[right - 1] + solution[left]) > Math.abs(solution[right] + solution[left + 1])) {
    			left++;
    		} else {
    			right--;
    		}
    	}
    	
    	System.out.println(solution[answerLeft] + " " + solution[answerRight]);
    }
}
