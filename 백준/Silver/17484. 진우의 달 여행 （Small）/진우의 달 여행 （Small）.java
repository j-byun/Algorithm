import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 17484 진우의 달 여행 (Small) 실버3
 *
 * 문제
 * 달에 도달하기 위해 필요한 연료의 최소값을 계산해 주자.
 * 1. 지구 -> 달로 가는 경우 우주선이 움직일 수 있는 방향은 좌하, 하, 우하 세 방향이다.
 * 2. 우주선은 전에 움직인 방향으로 움직일 수 없다. 즉, 같은 방향으로 두번 연속으로 움직일 수 없다.
 * 진우의 목표는 연료를 최대한 아끼며 지구의 어느위치에서든 출발하여 달의 어느위치든 착륙하는 것이다.
 *
 * 조건
 * 지구와 달 사이 공간을 나타내는 행렬의 크기를 나타내는 N, M (2≤ N, M ≤ 6)
 * 각 행렬의 원소값은 100 이하의 자연수이다.
 *
 * 풀이
 * 1. 0번 행을 지구, N+1번 행을 달이라고 하자.
 * 2. dfs탐색을 통해 우주선을 움직이고, 이전 방향을 체크해주자.
 */

public class Main {
	
	static int[] dr = new int[] {1, 1, 1};
	static int[] dc = new int[] {-1, 0, 1};
	
	static int N, M, minFuel;
	static int[][] map;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	N = Integer.parseInt(st.nextToken()) + 2;
    	M = Integer.parseInt(st.nextToken());
    	
    	map = new int[N][M];
    	
    	for (int row = 1; row < N - 1; row++) {
    		st = new StringTokenizer(br.readLine());
    		for (int col = 0; col < M; col++) {
    			map[row][col] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	minFuel = 987654321;
    	for (int col = 0; col < M; col++) {
    		travel(0, col, -1, 0);
    	}
    	
    	System.out.println(minFuel);
    }
    
    private static void travel(int row, int col, int dir, int sum) {
    	
    	if (row == N - 1) {
    		minFuel = Math.min(minFuel, sum);
    		return;
    	}
    	
    	for (int d = 0; d < 3; d++) {
    		if (dir == d) continue;
    		
    		int nr = row + dr[d];
    		int nc = col + dc[d];
    		
    		if (nc < 0 || nc >= M) continue;
    		
    		travel(nr, nc, d, sum + map[nr][nc]);
    	}
    }
}
