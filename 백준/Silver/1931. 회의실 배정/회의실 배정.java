import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author jihye.byun
 * BOJ 1931 회의실 배정 실버1
 * 
 * 문제
 * 한 개의 회의실에서 N개의 회의를 진행하자.
 * 각 회의에 대해 시작시간과 끝나는 시간이 주어져 있다.
 * 각 회의가 겹치지 않게 하면서 회의실을 사용할 수 있는 회의의 최대 개수를 찾아보자.
 * 회의는 중간에 중단될 수 없으며, 한 회의가 끝나는 것과 동시에 다음 회의가 시작될 수 있다.
 * 회의의 시작시간과 끝나는 시간이 같은 경우는 시작하자마자 끝나는 것이다.
 * 
 * 조건
 * 회의의 수 N(1 ≤ N ≤ 100,000)
 * 시작 시간과 끝나는 시간은 2^31-1보다 작거나 같은 자연수 또는 0이다.
 * 
 * 풀이
 * 1. DP를 사용해서 회의 시작 시간의 회의 개수 +1을 회의가 끝나는 시간에 저장하자.
 * => 시간초과
 * 
 * 다른 방법으로 풀어보자!
 * 1. 회의 시간이 짧은 애가 우선순위
 * 
 */

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		/*
		 * 시간초과 코드
		 * 
		int N = sc.nextInt(); // 회의의 수 입력받기
		
		int[] start = new int[N + 1]; // 각 회의의 시작시간을 저장할 공간
		int[] end = new int[N + 1]; // 각 회의의 끝나는 시간을 저장할 공간
		
		// 회의 시간을 입력받으면서 제일 마지막으로 끝나는 시간을 찾아두자
		int maxEnd = 0;
		for (int idx = 1; idx <= N; idx++) {
			start[idx] = sc.nextInt();
			end[idx] = sc.nextInt();
			
			if (maxEnd < end[idx])
				maxEnd = end[idx];
		}
		
		// 제일 마지막 회의가 끝나는 시간까지 확인해보자
		int[] time = new int[maxEnd + 1];
		
		// N개의 회의에 대해 전체 시간만큼 다 확인해보자
		for (int i = 1; i <= N; i++) {
			for (int t = 0; t <= maxEnd; t++) {
				if (t == end[i]) // 현재 회의가 끝나는 시간이라면
					// 현재 회의가 시작할 때의 최대 회의 수에 +1 해주자
					time[t] = time[start[i]] + 1;
				else if (t != 0) // 현재 회의가 끝나는 시간이 아니라면
					// 이전 회의까지만 고려했을 때 VS 현재 회의까지 고려했을 때 중 큰 값을 저장하자
					time[t] = Math.max(time[t], time[t - 1]);
			}
		}
		
		// 마지막 회의가 끝난 시간에 저장된 최대 회의 개수 출력
		System.out.println(time[maxEnd]);
		*/
		
		int N = sc.nextInt(); // 회의의 수 입력받기
		
		int[][] meeting = new int[N][2];
		
		for (int idx = 0; idx < N; idx++) {
			meeting[idx][0] = sc.nextInt();
			meeting[idx][1] = sc.nextInt();
		}
		
		Arrays.sort(meeting, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[1] == o2[1])
					return o1[0] - o2[0];
				return o1[1] - o2[1];
			}
		});
		
		
		int latestEnd = 0;
		int maxMeetingCount = 0;
		
		for (int idx = 0; idx < N; idx++) {
			if (meeting[idx][0] < latestEnd) continue;
			
			maxMeetingCount++;
			latestEnd = meeting[idx][1];
		}
		
		System.out.println(maxMeetingCount);
	}
}
