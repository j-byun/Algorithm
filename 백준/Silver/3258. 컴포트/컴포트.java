import java.util.*;

/**
 * @author jihye.byun
 * BOJ 3258 컴포트 실버3
 *
 * 문제
 *
 * 조건
 * 필드의 수 N(2 ≤ N ≤ 1000)
 * 도착해야 하는 필드의 번호 Z(2 ≤ Z)
 * 장애물이 있는 필드의 개수 M(0 ≤ M ≤ N-2)
 *
 * 풀이
 */

public class Main {
	
	static int N, Z;
	static boolean[] trap;
	static boolean[] visit;
	static boolean flag;
	
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	N = sc.nextInt(); // 필드의 수
    	Z = sc.nextInt(); // 도착해야 하는 필드의 번호
    	int M = sc.nextInt(); // 장애물이 있는 필드의 개수
    	trap = new boolean[N + 1]; // 필드의 장애물 여부를 나타낼 배열
    	
    	// 장애물 정보 입력받기
    	for (int cnt = 0; cnt < M; cnt++) {
    		trap[sc.nextInt()] = true;
    	}
    	
    	visit = new boolean[N + 1];
    	
    	int jump = 1;
    	
    	while (true) {
    		flag = true;
    		dfs(1, jump);
    		
    		if (flag)
    			break;
    		
    		jump++;
    	}
    	
    	System.out.println(jump);
    }
    
    static void dfs(int start, int jump) {
    	
    	if (trap[start] || visit[start]) {
    		flag = false;
    		return;
    	}
    	
    	if (start == Z) {
    		return;
    	}
    	
    	visit[start] = true;
    	int newStart = (start + jump) % N;
    	if (newStart == 0)
    		newStart = N;
    	dfs(newStart, jump);
    	visit[start] = false;
    }
}
