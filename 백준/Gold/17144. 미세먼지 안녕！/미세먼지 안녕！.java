import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author jihye.byun
 * BOJ 17144 미세먼지 안녕! 골드4
 *
 * 문제
 * <1초 동안 아래 적힌 일이 순서대로 일어난다.>
 * 1. 미세먼지가 확산된다. 확산은 미세먼지가 있는 모든 칸에서 동시에 일어난다.
 * - (r,c)에 있는 미세먼지는 인접한 네 방향으로 확산된다.
 * - 인접한 방향에 공기청정기가 있거나, 칸이 없으면 그 방향으로는 확산이 일어나지 않는다.
 * - 확산되는 양은 Ar,c / 5이고 소수점은 버린다.
 * - (r,c)에 남은 미세먼지의 양은 Ar,c - (Ar,c / 5)*확산된 방향의 개수 이다.
 * 2. 공기청정기가 작동한다.
 * - 공기청정기에서는 바람이 나온다.
 * - 위쪽 공기청정기의 바람은 반시계 방향으로 순환하고, 아래쪽 공기청정기의 바람은 시계방향으로 순환한다.
 * - 바람이 불면 미세먼지가 바람의 방향대로 모두 한 칸씩 이동한다.
 * - 공기청정기에서 부는 바람은 미세먼지가 없는 바람이고, 공기청정기로 들어간 미세먼지는 모두 정화된다.
 * 
 * 방의 정보가 주어졌을 때, T초가 지난 후 방에 남아있는 미세먼지의 양을 구해보자.
 * 
 * 조건
 * 6 ≤ R, C ≤ 50
 * 1 ≤ T ≤ 1,000
 * Ar,c (-1 ≤ Ar,c ≤ 1,000)
 * 공기청정기가 설치된 곳은 Ar,c가 -1, 나머지 값은 미세먼지의 양
 * -1은 2번 위아래로 붙어져 있고, 가장 윗 행, 아랫 행과 두 칸이상 떨어져 있다.
 *
 * 풀이
 * 1. 방 전체 칸을 확인하며 미세먼지가 있는 칸의 좌표와 미세먼지 양을 큐에 저장하자.
 * 2. 미세먼지가 있는 모든 칸을 큐에 담았다면 네 방향으로 bfs 확산을 진행하자.
 * 3. 미세먼지 확산이 끝났다면 공기청정기 위치에서부터 while문을 사용해 시계방향/시계반대방향으로 청소하자.
 */

public class Main {
	
	static int R, C, T;
	static int[][] map;
	
	static List<int[]> cleaner;
	
	// 상우하좌 시계방향 델타배열
	static int[] dr = new int[] {-1, 0, 1, 0};
	static int[] dc = new int[] {0, 1, 0, -1};
	
	private static void dustDiffusion() {
		
		Queue<Dust> queue = new ArrayDeque<>();
		
		for (int row = 0; row < R; row++) {
    		for (int col = 0; col < C; col++) {
    			if (map[row][col] > 0)
    				queue.add(new Dust(row, col, map[row][col]));
    		}
    	}
		
		while (!queue.isEmpty()) {
			Dust cur = queue.poll();
			
			int diffusion = 0;
			
			for (int d = 0; d < 4; d++) {
				int nr = cur.row + dr[d];
				int nc = cur.col + dc[d];
				
				if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
				if (map[nr][nc] == -1) continue;
				diffusion++;
				map[nr][nc] += cur.dust / 5;
			}
			
			map[cur.row][cur.col] -= (cur.dust / 5) * diffusion;
		}
	}
	
	private static void airClean() {
		
		int cr, cc, nr, nc, curDust, newDust;
		
		// 윗쪽 청소기
		cr = cleaner.get(0)[0];
		cc = cleaner.get(0)[1];
		curDust = 0;
		
		for (int d = 0; d < 4; d++) {
			
			while (true) {
				nr = cr + dr[(5 - d) % 4];
				nc = cc + dc[(5 - d) % 4];
				
				if (nr < 0 || nr >= R || nc < 0 || nc >= C) break;
				if (map[nr][nc] == -1) break;
				newDust = map[nr][nc];
				map[nr][nc] = curDust;
				curDust = newDust;
				cr = nr;
				cc = nc;
			}
		}
		
		// 아랫쪽 청소기
		cr = cleaner.get(1)[0];
		cc = cleaner.get(1)[1];
		curDust = 0;
		
		for (int d = 0; d < 4; d++) {
			
			while (true) {
				nr = cr + dr[(d + 1) % 4];
				nc = cc + dc[(d + 1) % 4];
				
				if (nr < 0 || nr >= R || nc < 0 || nc >= C) break;
				if (map[nr][nc] == -1) break;
				newDust = map[nr][nc];
				map[nr][nc] = curDust;
				curDust = newDust;
				cr = nr;
				cc = nc;
			}
		}
	}
	
	private static int sumLeft() {
		
		int sum = 0;
		
		for (int row = 0; row < R; row++) {
    		for (int col = 0; col < C; col++) {
    			if (map[row][col] != -1)
    				sum += map[row][col];
    		}
    	}
		
		return sum;
	}
	
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	R = sc.nextInt();
    	C = sc.nextInt();
    	T = sc.nextInt();
    	
    	map = new int[R][C];
    	cleaner = new ArrayList<int[]>();
    	
    	// 방 전체의 미세먼지 정보 입력받기
    	for (int row = 0; row < R; row++) {
    		for (int col = 0; col < C; col++) {
    			map[row][col] = sc.nextInt();
    			
    			if (map[row][col] == -1)
    				cleaner.add(new int[] {row, col});
    		}
    	}
    	
    	int time = 0;
    	
    	while (time < T) {
    		// 미세먼지 확산
    		dustDiffusion();
    		// 공기청정 시작
    		airClean();
    		
    		time++;
    	} // time == T가 되면 while문 탈출
    	
    	// time == T일 때 map 전체를 확인해서 남은 미세먼지 양 확인
    	System.out.println(sumLeft());
    }
    
    private static class Dust {
    	int row, col, dust;
    	public Dust(int row, int col, int dust) {
    		this.row = row;
    		this.col = col;
    		this.dust = dust;
    	}
    }
}
