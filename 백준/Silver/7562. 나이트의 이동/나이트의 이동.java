import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author jihye.byun
 * BOJ 7562 나이트의 이동 실버1 BFS
 *
 * 문제
 * 체스판 위에 한 나이트가 놓여져 있다.
 * 나이트가 이동하려고 하는 칸이 주어진다.
 * 나이트는 몇 번 움직이면 이 칸으로 이동할 수 있을까?
 *
 * 조건
 * l(4 ≤ l ≤ 300)
 * 체스판의 크기는 l × l이다.
 *
 * 풀이
 */

public class Main {
	static int[] dr = new int[] {-2, -1, 1, 2, 2, 1, -1, -2};
	static int[] dc = new int[] {1, 2, 2, 1, -1, -2, -2, -1};
	
	static int size;
	static boolean[][] visit;
	
	private static int bfs(int startRow, int startCol, int endRow, int endCol) {
		Queue<Coo> queue = new ArrayDeque<>();
		queue.add(new Coo(startRow, startCol, 0));
		if (startRow == endRow && startCol == endCol) return 0;
		visit[startRow][startCol] = true;
		
		while (!queue.isEmpty()) {
			Coo cur = queue.poll();
			
			for (int d = 0; d < 8; d++) {
				int nr = cur.row + dr[d];
				int nc = cur.col + dc[d];
				
				if (nr < 0 || nr >= size || nc < 0 || nc >= size) continue;
				if (visit[nr][nc]) continue;
				if (nr == endRow && nc == endCol) return cur.count + 1;
				visit[nr][nc] = true;
				queue.add(new Coo(nr, nc, cur.count + 1));
			}
		}
		return 0;
	}
	
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	int T = sc.nextInt(); // 테스트케이스 개수 입력받기
    	
    	for (int tc = 1; tc <= T; tc++) { // 테스트케이스 개수만큼 반복
    		
    		size = sc.nextInt(); // 체스판의 크기 입력받기
    		visit = new boolean[size][size]; // 체스판 방문체크 배열
    		
    		System.out.println(bfs(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt()));
    	}
    	
    }
    
    private static class Coo {
    	int row, col, count;
    	public Coo(int row, int col, int count) {
    		this.row = row;
    		this.col = col;
    		this.count = count;
    	}
    }
}
