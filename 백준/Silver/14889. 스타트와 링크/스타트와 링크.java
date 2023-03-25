import java.util.Arrays;
import java.util.Scanner;

/**
 * @author jihye.byun
 * BOJ 14889 스타트와 링크 실버2 브루트포스(순열/조합)
 * 
 * 문제
 * 짝수 N명의 사람을 N/2명씩 두 그룹으로 나누자.
 * 한 그룹에 i사람과 j사람이 같이 포함되면 Sij와 Sji의 능력치가 더해진다.
 * 두 그룹의 능력치 차이를 최소로 할 때, 능력치 차이의 최솟값을 출력하자.
 * 
 * 조건
 * 사람 수 N(4 ≤ N ≤ 20, N은 짝수)
 * Sii는 항상 0이고, 나머지 Sij는 1보다 크거나 같고, 100보다 작거나 같은 정수
 * 
 * 풀이
 * 1. N/2명의 모든 조합을 만들어보자.
 * 2. 뽑은 팀을 true팀, 뽑히지 않은 팀을 false팀으로 나눠서 생각하자.
 * 2-1. 4명일 떄, 1,2를 뽑은 것과 3,4를 뽑은 것은 동일한 경우이지만, 조건을 막을 수 있을까?
 * 2-2. 모르겠는데.... 그냥 중복으로 확인해버리자. 시간 좀 낭비하지 뭐
 * 3. 각 팀에서 2명을 뽑는 순열을 만들어보자.
 * 3-1. 순열로 뽑힌 두 사람의 능력치를 구하고, 합에 저장하자.
 * 3-2. 현재 팀 조합의 능력치를 모두 구한 후 그 차를 구해서 최솟값으로 업데이트 하자.
 * 3-3. 두 팀의 현재 능력치 합과 능력치 차이의 최솟값은 파라미터로 들고다니면 재귀 종료될 때 가져갈 수 없으니 static으로 만들자.
 */

public class Main {
	
	static int N, sumTeam1, sumTeam2, minGap;
	static int[][] stats;
	static boolean[] used;
	static int[] team1, team2, pair1, pair2;
	
	private static void comb(int start, int depth, int maxDepth) {
		// N/2명을 뽑는 조합을 구하자
		
		// 기저조건
		if (depth == maxDepth) {
			// N/2명을 true팀으로 다 영입했다면
			// true팀과 false팀으로 나눠서 각 팀의 능력치 점수를 확인해보자
			team1 = new int[N / 2];
			team2 = new int[N / 2];
			
			int team1Idx = 0;
			int team2Idx = 0;
			
			// 뽑힌 사람은 팀1, 뽑히지 않은 사람은 팀2로 분류하자
			for (int idx = 0; idx < N; idx++) {
				if (used[idx])
					team1[team1Idx++] = idx;
				else
					team2[team2Idx++] = idx;
			}
			sumTeam1 = 0; // 팀1의 능력치 점수를 저장할 공간
			sumTeam2 = 0; // 팀2의 능력치 점수를 저장할 공간
			
			// 각 팀에서 뽑은 두 명의 사람을 순서대로 저장할 배열 공간을 만들자
			pair1 = new int[2];
			pair2 = new int[2];
			
			// 각 팀에서 두 명의 선수를 뽑아서 능력치 점수를 확인하자
			// Sij와 Sji는 다르니, 두 명의 선수를 뽑는 순열을 만들자
			perm(0, 2);
			
			// 두 팀의 능력치 차이 최솟값을 업데이트 하자
			minGap = Math.min(minGap, Math.abs(sumTeam1 - sumTeam2));
			return;
		}
		
		// 다음 사람은 무조건 현재 사람 + 1번 사람부터 확인할거니까
		// 현재 idx번째 사람은 무조건 지금 처음 확인하게 된다
		// 따라서 이미 true로 뽑혔는지 확인할 필요가 없다
		for (int idx = start; idx < N; idx++) {
			used[idx] = true; // 현재 사람을 true팀으로 뽑자
			comb(idx + 1, depth + 1, maxDepth); // 다음 사람 확인하러 가자
			used[idx] = false; // 현재 사람 다시 영입 취소
		}
		
	}
	
	private static void perm(int depth, int maxDepth) {
		// 순열로 각 팀에서 2명식 페어를 구해서 페어의 능력치 점수 합을 구하자
		
		// 기저조건
		if (depth == maxDepth) {
			// 두 명을 뽑는 순열을 구했다면
			// 뽑힌 사람 순서대로 Sij 페어의 능력치 점수를 확인하자
			sumTeam1 += stats[pair1[0]][pair1[1]];
			sumTeam2 += stats[pair2[0]][pair2[1]];
			return;
		}
		
		// 중복 순열로 만들자
		// 어차피 Sii는 0이니까 더해져도 상관없다
		// 실행 시간은 늘어나지만 used 배열공간을 아낄 수 있음
		for (int idx = 0; idx < N / 2; idx++) {
			pair1[depth] = team1[idx];
			pair2[depth] = team2[idx];
			perm(depth + 1, maxDepth);
		}
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt(); // 전체 사람 수 입력받기
		
		stats = new int[N][N];
		// 각 사람들의 조합으로 얻을 수 있는 능력치를 표현할 스탯 표
		
		// 스탯 표 정보 입력받기
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				stats[row][col] = sc.nextInt();
			}
		}
		
		used = new boolean[N]; // N/2명의 사람 조합을 나타낼 boolean 배열
		
		minGap = Integer.MAX_VALUE; // 두 팀의 능력치 차이 최솟값을 저장할 공간
		
		comb(0, 0, N / 2); // N/2명을 뽑는 조합을 만들어보자
		
		// 두 팀의 능력치 차이 최솟값을 구했다면 그 값을 출력하자
		System.out.println(minGap);
	}
	
}
