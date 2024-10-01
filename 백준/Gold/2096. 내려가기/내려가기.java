import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 2096 내려가기 골드5
 * 시간 제한 1초 | 메모리 제한 256 MB
 *
 * 문제
 * N줄에 0이상 9이하의 숫자가 세 개씩 적혀 있다.
 * 내려가기 게임 : 첫 줄에서 시작해서 마지막 줄에서 끝나게 되는 놀이
 * 다음 줄로 내려갈 때에는 바로 아래의 수나, 바로 아래의 수와 붙어 있는 수로만 이동할 수 있다.
 * 숫자표가 주어져 있을 때, 얻을 수 있는 최대 점수, 최소 점수를 구하는 프로그램을 작성하자.
 * 점수는 원룡이가 위치한 곳의 수의 합이다.
 *
 * 조건
 * N(1 ≤ N ≤ 100,000)
 * 숫자는 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 중의 하나가 된다.
 *
 * 풀이
 * 1. N의 수가 크므로, 완전 탐색이 아닌 DP로 풀자.
 */

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	
    	int n = Integer.parseInt(br.readLine());
    	int[][] arr = new int[n][3];
    	
    	for (int row = 0; row < n; row++) {
    		st = new StringTokenizer(br.readLine());
    		
    		for (int col = 0; col < 3; col++) {
    			arr[row][col] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	// 최대, 최소값 찾기
    	int[][] dpMax = new int[n][3];
    	int[][] dpMin = new int[n][3];
    	
    	// 1열 초기화
    	for (int col = 0; col < 3; col++) {
    		dpMax[0][col] = arr[0][col];
    		dpMin[0][col] = arr[0][col];
    	}
    	
    	for (int row = 1; row < n; row++) {
    		for (int col = 0; col < 3; col++) {
    			dpMax[row][col] = dpMax[row - 1][col] + arr[row][col];
    			dpMin[row][col] = dpMin[row - 1][col] + arr[row][col];
    			
    			if (col >= 1) {
    				dpMax[row][col] = Math.max(dpMax[row][col], dpMax[row - 1][col - 1] + arr[row][col]);
    				dpMin[row][col] = Math.min(dpMin[row][col], dpMin[row - 1][col - 1] + arr[row][col]);
    			}
    			
    			if (col <= 1) {
    				dpMax[row][col] = Math.max(dpMax[row][col], dpMax[row - 1][col + 1] + arr[row][col]);
    				dpMin[row][col] = Math.min(dpMin[row][col], dpMin[row - 1][col + 1] + arr[row][col]);
    			}
    		}
    	}
    	
    	// 최대 점수, 최소 점수 출력
    	System.out.printf("%d %d", 
    			Math.max(dpMax[n - 1][0], Math.max(dpMax[n - 1][1], dpMax[n - 1][2])), 
    			Math.min(dpMin[n - 1][0], Math.min(dpMin[n - 1][1], dpMin[n - 1][2])));
    }
}
