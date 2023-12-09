import java.util.*;

/**
 * @author jihye.byun
 * BOJ 10655 마라톤 1 실버3
 *
 * 문제
 *
 * 조건
 *
 * 풀이
 */

public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	int N = sc.nextInt();
    	int[][] checkPoint = new int[N][2];
    	int maxDistance = 0;
    	
    	for (int idx = 0; idx < N; idx++) {
    		checkPoint[idx][0] = sc.nextInt();
    		checkPoint[idx][1] = sc.nextInt();
    		
    		if (idx == 0) continue;
    		
    		maxDistance += Math.abs(checkPoint[idx][0] - checkPoint[idx - 1][0]) + Math.abs(checkPoint[idx][1] - checkPoint[idx - 1][1]);
    	}
    	
    	int minDistance = Integer.MAX_VALUE;
    	
    	for (int idx = 1; idx <= N - 2; idx++) {
    		int curDistance = maxDistance - Math.abs(checkPoint[idx][0] - checkPoint[idx - 1][0]) - Math.abs(checkPoint[idx][1] - checkPoint[idx - 1][1])
    				- Math.abs(checkPoint[idx][0] - checkPoint[idx + 1][0]) - Math.abs(checkPoint[idx][1] - checkPoint[idx + 1][1])
    				+ Math.abs(checkPoint[idx - 1][0] - checkPoint[idx + 1][0]) + Math.abs(checkPoint[idx - 1][1] - checkPoint[idx + 1][1]);
    		
    		minDistance = Math.min(minDistance, curDistance);
    	}
    	
    	System.out.println(minDistance);
    }
}
