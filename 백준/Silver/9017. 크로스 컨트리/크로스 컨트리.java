import java.util.*;

/**
 * @author jihye.byun
 * BOJ 9017 크로스 컨트리 실버3
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
    	
    	int T = sc.nextInt();
    	
    	while (T-- > 0) {
    		int N = sc.nextInt();
    		int[] rank = new int[N + 1];
    		int maxTeam = 0;
    		
    		for (int idx = 1; idx <= N; idx++) {
    			rank[idx] = sc.nextInt();
    			maxTeam = Math.max(maxTeam, rank[idx]);
    		}
    		
    		int[] team = new int[maxTeam + 1];
    		
    		for (int idx = 1; idx <= N; idx++) {
    			team[rank[idx]]++;
    		}
    		
    		int[][] score = new int[maxTeam + 1][7];
    		int curScore = 0;
    		
    		for (int idx = 1; idx <= N; idx++) {
    			if (team[rank[idx]] < 6) continue;
    			
    			for (int scoreIdx = 1; scoreIdx <= 6; scoreIdx++) {
    				if (score[rank[idx]][scoreIdx] != 0) continue;
    				
    				score[rank[idx]][scoreIdx] = ++curScore;
    				break;
    			}
    		}
    		
    		int minScore = 987654321;
    		int minTeam = 0;
    		int minFifthScore = 987654321;
    		
    		for (int idx = 1; idx <= maxTeam; idx++) {
    			if (score[idx][1] == 0) continue;
    			
    			int curTeamScore = score[idx][1] + score[idx][2] + score[idx][3] + score[idx][4];
    			
    			if (curTeamScore == minScore) {
    				if (minFifthScore > curTeamScore + score[idx][5]) {
    					minFifthScore = curTeamScore + score[idx][5];
    					minTeam = idx;
    				}
    			} else if (curTeamScore < minScore) {
    				minScore = curTeamScore;
    				minTeam = idx;
    				minFifthScore = curTeamScore + score[idx][5];
    			}
    		}
    		
    		System.out.println(minTeam);
    	}
    	
    }
}
