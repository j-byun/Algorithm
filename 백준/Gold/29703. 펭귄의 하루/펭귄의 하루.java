import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 29703 펭귄의 하루 골드4
 *
 * 문제
 * N*M 크기의 펭귄 마을
 * S : 펭귄의 현재 위치
 * H : 펭귄의 집
 * E : 천적이 없어 펭귄이 이동해도 괜찮은 안전 구역
 * D : 펭귄의 천적인 바다 표범이 살고 있어 펭귄이 이동할 수 없는 위험 구역
 * F : 펭귄이 먹이를 구할 수 있는 물고기 서식지
 * 물고기를 사냥해 최대한 빠르게 펭귄의 집에 도달하는 데 걸리는 시간을 구하자
 *
 * 조건
 * 펭귄 마을의 세로 길이 N (1<=N<=1,000)
 * 펭귄 마을의 가로 길이 M (1<=M<=1,000)
 * 만약, 펭귄이 물고기 서식지를 들러 집에 도착할 수 없다면 -1을 출력한다.
 *
 * 풀이
 * 1. BFS 탐색으로 최소 도착 시간을 확인하자.
 * 2. 펭귄의 현재 위치에서부터 각 물고기 위치까지 도달하는 시간을 저장하자.
 * 3. 펭귄의 집에서부터 각 물고기 위치까지 도달하는 시간을 저장하자.
 * 4. 둘의 합 중 가장 작은 값을 찾자.
 */

public class Main {
	
	public static class Point {
		int row, col, time;
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
		public Point(int row, int col, int time) {
			this.row = row;
			this.col = col;
			this.time = time;
		}
	}
	
	private static int[] dr = new int[] {-1, 0, 1, 0};
	private static int[] dc = new int[] {0, 1, 0, -1};
	
	private static char[][] village;
	
	private static int N, M;
	
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	N = sc.nextInt(); // row 길이 입력
    	M = sc.nextInt(); // col 길이 입력
    	
    	village = new char[N][M]; // 펭귄 마을 정보를 저장할 배열
    	int[][] fromPenguin = new int[N][M]; // 펭귄 -> 물고기로 이동하는 시간을 저장할 배열
    	int[][] fromHome = new int[N][M]; // 집 -> 물고기로 이동하는 시간을 저장할 배열
    	
    	Point penguin = new Point(-1, -1); // 현재 펭귄 위치를 저장할 변수
    	Point home = new Point(-1, -1); // 집 위치를 저장할 변수
    	
    	// 펭귄 마을 지도 입력받기
    	for (int row = 0; row < N; row++) {
    		String line = sc.next();
    		for (int col = 0; col < M; col++) {
    			village[row][col] = line.charAt(col);
    			
    			if (village[row][col] == 'S') {
    				penguin = new Point(row, col);
    			}
    			if (village[row][col] == 'H') {
    				home = new Point(row, col);
    			}
    		}
    	}
    	
    	// 현재 펭귄 위치에서부터 각 물고기 위치까지 도달하는 시간 탐색
    	bfs(penguin.row, penguin.col, fromPenguin);
    	
    	// 집에서부터 각 물고기 위치까지 도달하는 시간 탐색
    	bfs(home.row, home.col, fromHome);
    	
    	// 펭귄 -> 물고기 -> 집 까지 가는 최소 시간 확인
    	int minTime = Integer.MAX_VALUE;
    	
    	for (int row = 0; row < N; row++) {
    		for (int col = 0; col < M; col++) {
    			if (village[row][col] != 'F') continue;
    			if (fromPenguin[row][col] == 0 || fromHome[row][col] == 0) continue;
    			minTime = Math.min(minTime, fromPenguin[row][col] + fromHome[row][col]);
    		}
    	}
    	
    	// 물고기 잡아서 집 가는 방법이 없으면 -1 출력
    	System.out.println((minTime == Integer.MAX_VALUE) ? -1 : minTime);
    }
    
    private static void bfs(int startRow, int startCol, int[][] arr) {
    	Queue<Point> queue = new ArrayDeque<>();
    	boolean[][] visit = new boolean[N][M];
    	
    	queue.add(new Point(startRow, startCol, 0));
    	visit[startRow][startCol] = true;
    	
    	while (!queue.isEmpty()) {
    		Point cur = queue.poll();
    		
    		for (int d = 0; d < 4; d++) {
    			int nr = cur.row + dr[d];
    			int nc = cur.col + dc[d];
    			
    			if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
    			if (village[nr][nc] == 'D') continue;
    			if (visit[nr][nc]) continue;
    			if (village[nr][nc] == 'F') {
    				arr[nr][nc] = cur.time + 1;
    			}
    			queue.add(new Point(nr, nc, cur.time + 1));
    			visit[nr][nc] = true;
    		}
    	}
    }
}
