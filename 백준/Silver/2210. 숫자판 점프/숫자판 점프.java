import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 2210 숫자판 점프 실버2	
 *
 * 문제
 * 5×5 크기의 숫자판
 * 각 칸에는 숫자 0~9가 적혀 있다.
 * 임의의 위치에서 시작해서, 인접한 네 방향으로 다섯 번 이동하면서 각 칸의 숫자를 차례대로 붙이면 6자리의 수가 된다.
 * 이동할 때에는 한 번 거쳤던 칸을 다시 거쳐도 되며, 0으로 시작하는 000123과 같은 수도 만들 수 있다.
 * 숫자판이 수어졌을 때, 만들 수 있는 서로 다른 여섯자리의 수들의 개수를 구하는 프로그램을 작성하시오.
 *
 * 조건
 *
 * 풀이
 */

public class Main {
	
	static int[] dr = new int[] {-1, 0, 1, 0};
	static int[] dc = new int[] {0, 1, 0, -1};
	
	static int[][] map;
	static LinkedHashSet<Integer> answer; 
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	
    	map = new int[5][5];
    	
    	for (int row = 0; row < 5; row++) {
    		st = new StringTokenizer(br.readLine());
    		for (int col = 0; col < 5; col++) {
    			map[row][col] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	answer = new LinkedHashSet<>();
    	
    	for (int row = 0; row < 5; row++) {
    		for (int col = 0; col < 5; col++) {
    			dfs(row, col, map[row][col] * (int) Math.pow(10, 5), 1, 6);
    		}
    	}
    	
    	System.out.println(answer.size());
    }
    
    static void dfs(int row, int col, int sum, int depth, int maxDepth) {
    	
    	if (depth == maxDepth) {
    		answer.add(sum);
    		return;
    	}
    	
    	for (int d = 0; d < 4; d++) {
    		int nr = row + dr[d];
    		int nc = col + dc[d];
    		
    		if (nr < 0 || nr >= 5 || nc < 0 || nc >= 5) continue;
    		dfs(nr, nc, sum + (map[nr][nc] * (int) Math.pow(10, 5 - depth)), depth + 1, maxDepth);
    	}
    }
}
