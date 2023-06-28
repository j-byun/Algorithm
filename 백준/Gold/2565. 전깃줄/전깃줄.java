import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * @author jihye.byun
 * BOJ 2565 전깃줄 골드5
 * 
 * 문제
 * 
 * 조건
 * 
 * 풀이
 * 1. dp LDS적용
 */

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int size = sc.nextInt();
		
		int[][] line = new int[size][2]; // 전깃줄 정보를 저장할 리스트 공간
		int[] dp = new int[size];
		
		// 전깃줄 갯수만큼 전깃줄 연결 정보 입력받기
		for (int cnt = 0; cnt < size; cnt++) {
			line[cnt][0] = sc.nextInt();
			line[cnt][1] = sc.nextInt();
		}
		
		// A번호 기준 오름차순 정렬
		Arrays.sort(line, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		
		int max = 1;
		for (int i = 0; i < size; i++) {
			dp[i] = 1;
			
			for (int j = 0; j < i; j++) {
				if (line[i][1] > line[j][1]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
			
			max = Math.max(max, dp[i]);
		}
		
		System.out.println(size - max);
	}
}
