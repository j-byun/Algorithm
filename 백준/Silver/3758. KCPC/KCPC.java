import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 3758 KCPC 실버2
 *
 * 문제
 *
 * 조건
 *
 * 풀이
 */

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int T = Integer.parseInt(st.nextToken());
    	
    	while (T-- > 0) {
    		
    		st = new StringTokenizer(br.readLine());
    		
    		int n = Integer.parseInt(st.nextToken()); // 팀의 개수
    		int k = Integer.parseInt(st.nextToken()); // 문제의 개수
    		int t = Integer.parseInt(st.nextToken()); // 내 팀의 ID
    		int m = Integer.parseInt(st.nextToken()); // 로그 엔트리의 개수
    		
    		// 총 점수
    		// 풀이 제출 횟수
    		// 마지막 제출 시간
    		int[][] log = new int[n + 1][3];
    		
    		// 각 문제별 점수
    		int[][] score = new int[n + 1][k + 1];
    		
    		for (int cnt = 0; cnt < m; cnt++) {
    			st = new StringTokenizer(br.readLine());
    			
    			int team = Integer.parseInt(st.nextToken());
    			int problem = Integer.parseInt(st.nextToken());
    			int point = Integer.parseInt(st.nextToken());
    			
    			// 총 점수
    			if (score[team][problem] < point) {
    				log[team][0] -= score[team][problem];
    				score[team][problem] = point;
    				log[team][0] += score[team][problem];
    			}
    			
    			// 풀이 제출 횟수
    			log[team][1]++;
    			
    			// 마지막 제출 시간
    			log[team][2] = cnt;
    		}
    		
    		int rank = 1;
    		
    		for (int team = 1; team <= n; team++) {
    			if (team == t) continue;
    			
    			// 최종점수 비교
    			if (log[team][0] > log[t][0]) {
    				rank++;
    				continue;
    			}
    			
    			if (log[team][0] < log[t][0]) {
    				continue;
    			}
    			
    			// 풀이 제출 횟수 비교
    			if (log[team][1] < log[t][1]) {
    				rank++;
    				continue;
    			}
    			
    			if (log[team][1] > log[t][1]) {
    				continue;
    			}
    			
    			// 마지막 제출 시간 비교
    			if (log[team][2] < log[t][2]) {
    				rank++;
    			}
    		}
    		
    		System.out.println(rank);
    	}
    }
}
