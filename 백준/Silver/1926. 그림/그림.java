import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author jihye.byun
 * BOJ 1926 그림 실버1
 *
 * 문제
 * 도화지에 그려진 그림의 개수와, 그 그림 중 넓이가 가장 넓은 것의 넓이를 출력하자.
 * 하나의 그림이란 가로나 세로로 1로 연결된 것이다.
 * 그림의 넓이란 그림에 포함된 1의 개수이다.
 *
 * 조건
 * 도화지의 세로 크기 n(1 ≤ n ≤ 500)
 * 도화지의 가로 크기 m(1 ≤ m ≤ 500)
 * 0은 색칠이 안된 부분, 1은 색칠이 된 부분을 의미한다.
 *
 * 풀이
 * 1. bfs로 그림을 탐색하자.
 */

public class Main {
	
	static int[] dr = new int[] {-1, 0, 1, 0};
	static int[] dc = new int[] {0, 1, 0, -1};
	
	static int n, m;
	static int[][] paper;
	static int count, maxArea;
	
	private static void bfs(int startRow, int startCol) {
		Queue<Coo> queue = new ArrayDeque<>();
		int area = 0;
		queue.add(new Coo(startRow, startCol));
		paper[startRow][startCol] = 9;
		area++;
		
		while (!queue.isEmpty()) {
			Coo cur = queue.poll();
			
//			if (paper[cur.row][cur.col] != 1)
//				continue;
			
			for (int d = 0; d < 4; d++) {
				int nr = cur.row + dr[d];
				int nc = cur.col + dc[d];
				
				if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
				if (paper[nr][nc] != 1) continue;
				queue.add(new Coo(nr, nc));
				paper[nr][nc] = 9; 
				area++;
			}
		}
		
		maxArea = Math.max(maxArea, area);
	}
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	// 도화지 크기 입력받기
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	paper = new int[n][m];
    	
    	// 변수 초기화
    	count = 0;
    	maxArea = 0;
    	
    	// 그림 정보 입력받기
    	for (int row = 0; row < n; row++) {
    		st = new StringTokenizer(br.readLine());
    		for (int col = 0; col < m; col++) {
    			if (Integer.parseInt(st.nextToken()) == 1)
    				paper[row][col] = 1;
    		}
    	}
    	
    	for (int row = 0; row < n; row++) {
    		for (int col = 0; col < m; col++) {
    			if (paper[row][col] == 1) {
    				count++;
    				bfs(row, col);
    			}
    		}
    	}
    	System.out.println(count);
    	System.out.println(maxArea);
    }
    
    private static class Coo {
    	private int row, col;
    	public Coo(int row, int col) {
    		this.row = row;
    		this.col = col;
    	}
    }
}
