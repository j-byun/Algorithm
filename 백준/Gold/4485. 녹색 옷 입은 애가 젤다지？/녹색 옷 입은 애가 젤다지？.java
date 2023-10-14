import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 4485 녹색 옷 입은 애가 젤다지? 골드4	
 *
 * 문제
 * 화폐의 단위 루피
 * 도둑루피라 불리는 검정색 루피를 획등하면 소지한 루피가 감소하게 된다.
 * 주인공 링크는 도둑루피만 가능한 N*N크기의 동굴 제일 왼쪽 위에 있다.
 * (0,0)에서 출발해서 (N-1,N-1)까지 이동해야 한다.
 * 동굴의 각 칸마다 도둑루피가 있는데, 이 칸을 지나면 해당 도둑루피의 크기만큼 소지금을 잃게 된다.
 * 잃는 금액을 최소로 하여 동굴 건너편까지 이동해야 하며, 한 번에 상하좌우 인접한 곳으로 1칸씩 이동할 수 있다.
 * 링크가 잃을 수 밖에 없는 최소 금액은 얼마일까?
 *
 * 조건
 * 2 ≤ N ≤ 125
 * N = 0인 입력이 주어지면 전체 입력이 종료된다.
 * 동굴 각 칸의 정수는 0 이상 9 이하인 한 자리 수이다.
 *
 * 풀이
 * 1. 너비 우선 탐색을 통해 링크를 움직이며 (N-1, N-1)칸까지 도착하자.
 * 2. 움직이며 각 칸까지 도착했을 때의 최소 루피 개수를 업데이트 하자.
 * 2-1. 현재 칸에 저장돼있던 루피보다 많은 루피를 획득하게 된다면, 해당 방향으로는 더 이상 탐색하지 말자.
 */

public class Main {
	
	static class Point implements Comparable<Point>{
		int row, col, rupee;
		public Point(int row, int col, int rupee) {
			this.row = row;
			this.col = col;
			this.rupee = rupee;
		}
		@Override
		public int compareTo(Point o) {
			// TODO Auto-generated method stub
			return this.rupee - o.rupee;
		}
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N;
	static int[][] cave;
	static int[][] minRupee;
	
	static int[] dr = new int[] {-1, 0, 1, 0};
	static int[] dc = new int[] {0, 1, 0, -1};
	
    public static void main(String[] args) throws IOException {
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	int gameCount = 0; // 현재 게임 수를 세어줄 변수
    	
    	while (true) {
    		st = new StringTokenizer(br.readLine());
    		N = Integer.parseInt(st.nextToken()); // 동굴 한 변의 크기
    		if (N == 0) break; // 동굴 크기가 0이면 게임 종료
    		gameCount++;
    		
    		cave = new int[N][N]; // 동굴
    		minRupee = new int[N][N]; // 각 칸에 도달하기 위한 최소 루피를 저장할 배열
    		input(); // 동굴 각 칸의 정보 입력받기
    		
    		bw.write("Problem ");
    		bw.write(gameCount + ": ");
    		bw.write(move() + "\n"); // 링크가 잃을 수 밖에 없는 최소 금액 찾기
    	}
    	bw.flush();
    }
    
    static void input() throws IOException {
    	for (int row = 0; row < N; row++) {
    		st = new StringTokenizer(br.readLine());
    		for (int col = 0; col < N; col++) {
    			cave[row][col] = Integer.parseInt(st.nextToken());
    			minRupee[row][col] = 987654321; // 최소 루피 저장 배열 초기화
    		}
    	}
    }
    
    static int move() {
    	PriorityQueue<Point> pq = new PriorityQueue<>();
    	pq.add(new Point(0, 0, cave[0][0]));
    	
    	while (!pq.isEmpty()) {
    		Point cur = pq.poll();
    		
    		for (int d = 0; d < 4; d++) {
    			int nr = cur.row + dr[d];
    			int nc = cur.col + dc[d];
    			
    			if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
    			if (minRupee[nr][nc] <= cur.rupee + cave[nr][nc]) continue;
    			minRupee[nr][nc] = cur.rupee + cave[nr][nc];
    			pq.add(new Point(nr, nc, minRupee[nr][nc]));
    		}
    	}
    	
    	return minRupee[N - 1][N - 1];
    }
}
