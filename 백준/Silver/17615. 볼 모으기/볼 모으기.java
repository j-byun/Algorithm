import java.util.*;

/**
 * @author jihye.byun
 * BOJ 17615 볼 모으기 실버1
 *
 * 문제
 *
 * 조건
 * 1 ≤ N ≤ 500,000
 *
 * 풀이
 * 1. 제일 바깥쪽에 현재 색이 존재하면, 제일 큰 볼 무리를 제외한 나머지의 합 만큼 움직여야 한다.
 * 2. 제일 바깥쪽에 현재 색이 존재하지 않으면, 현재 색의 볼 전체 개수만큼 움직여야 한다.
 * 3. 전체가 한 종류의 색으로만 구성되어 있으면 답은 0이다.
 */

public class Main {
	
	static final int B = 0;
	static final int R = 1;
	static int N;
	static int[] balls, ballCount;
	
	
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	N = sc.nextInt();
    	balls = new int[N];
    	ballCount = new int[2];
    	
    	String input = sc.next();
    	for (int idx = 0; idx < N; idx++) {
    		char cur = input.charAt(idx);
    		
    		if (cur == 'B')
    			balls[idx] = B;
    		else
    			balls[idx] = R;
    		
    		ballCount[balls[idx]]++;
    	}
    	
//    	if (ballCount[0] == 0 || ballCount[1] == 0) {
//    		System.out.println(Math.max(ballCount[0], ballCount[1]));
//    		return;
//    	}
    	
    	int red = moveBalls(R);
    	int blue = moveBalls(B);
    	
    	System.out.println(Math.min(red, blue));
    }
    
    static int moveBalls(int color) {
    	
//    	if (balls[0] != color && balls[N - 1] != color)
//    		return ballCount[color];
    	
    	// 한쪽 끝이라도 현재 색이면 왼쪽 끝 무리를 뺀 수와 오른쪽 끝 무리르 뺀 수 중 작은 수 반환
    	int left = 0;
    	for (int idx = 0; idx < N; idx++) {
    		if (balls[idx] != color)
    			break;
    		left++;
    	}
    	
    	int right = 0;
    	for (int idx = N - 1; idx >= 0; idx--) {
    		if (balls[idx] != color)
    			break;
    		right++;
    	}
    	
    	return Math.min(ballCount[color] - left, ballCount[color] - right);
    }
}
