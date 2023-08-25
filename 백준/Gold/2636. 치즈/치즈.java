import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author jihye.byun
 * BOJ 2636 치즈 골드4 BFS
 *
 * 문제
 * 정사각형 칸들로 이루어진 사각형 모양의 판 위에 얇은 치즈가 놓여 있다.
 * 판의 가장자리에는 치즈가 놓이지 않으며, 치즈에는 하나 이상의 구멍이 있을 수 있다.
 * 치즈를 공기 중에 놓으면 공기와 접촉된 칸은 한 시간 후 녹아 없어진다.
 * 치즈의 구멍 속에는 공기가 없지만 구멍을 둘러싼 치즈가 녹아서 구멍이 열리면 구멍 속으로 공기가 들어가게 된다.
 *
 * 조건
 * 사각형 모양 판의 세로와 가로의 길이는 최대 100이다.
 * 치즈가 없는 칸은 0, 치즈가 있는 칸은 1로 주어진다.
 *
 * 풀이
 * 1. 가장자리에는 치즈가 없으니 가장자리에서 BFS 탐색을 시작하자.
 * 2. 처음 치즈와 만나면 해당 칸을 녹이고 더 이상 전파하지 않는다.
 * 3. 치즈를 녹인 후에는 남은 치즈 칸의 갯수를 세어서 리스트에 저장하자.
 * 4. 치즈 칸의 갯수가 0이 되면 그 때의 시간과 바로 전 칸의 갯수를 출력하자.
 */

public class Main {
	
	static int[] dr = new int[] {-1, 0, 1, 0};
	static int[] dc = new int[] {0, 1, 0, -1};
	
	static int R, C;
	static int[][] map;
	static boolean[][] visit;
	
	static List<Integer> list;
	
	private static boolean countCheese() {
		// 남은 치즈 덩어리의 갯수를 세는 메서드
		int count = 0;
		
		for (int row = 0; row < R; row++) {
			for (int col = 0; col < C; col++) {
				if (map[row][col] == 1)
					count++;
			}
		}
		
		list.add(count);
		if (count == 0) return false;
		return true;
	}
	
	private static void melting() {
		Queue<Coo> queue = new ArrayDeque<>();
		queue.add(new Coo(0, 0));
		
		visit = new boolean[R][C];
		visit[0][0] = true;
		
		while (!queue.isEmpty()) {
			Coo cur = queue.poll();
			
			for (int d = 0; d < 4; d++) {
				int nr = cur.row + dr[d];
				int nc = cur.col + dc[d];
				
				if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
				if (visit[nr][nc]) continue;
				visit[nr][nc] = true;
				if (map[nr][nc] == 0) { // 치즈가 없는 칸이면 더 전파
					queue.add(new Coo(nr, nc));
					continue;
				}
				map[nr][nc] = 0; // 치즈가 있는 칸이면 녹이고 전파 중지
			}
		}
	}
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	R = Integer.parseInt(st.nextToken()); // map의 가로 길이 입력
    	C = Integer.parseInt(st.nextToken()); // map의 세로 길이 입력
    	
    	map = new int[R][C]; // map 공간 만들기
    	list = new ArrayList<>(); // 매 시간 남은 치즈 덩어리의 갯수를 저장할 리스트 공간 만들기
    	
    	// map 각 칸의 정보 입력
    	for (int row = 0; row < R; row++) {
    		st = new StringTokenizer(br.readLine());
    		for (int col = 0; col < C; col++) {
    			map[row][col] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	// 치즈 녹이기 + 남은 치즈 덩어리 갯수 세기 반복
    	// 치즈를 녹이기 전에 일단 치즈 덩어리의 갯수 먼저 세기
    	// 0시간일 때 치즈가 없을 수도 있다.
    	int time = 0;
    	while (countCheese()) {
    		melting(); // 치즈를 녹이자
    		time++;
    	}
    	
    	// 치즈가 모두 녹아서 없어지는 데 걸리는 시간 출력
    	System.out.println(time);
    	
    	// 치즈가 녹기 한 시간 전에 남아있는 치즈 칸의 개수
    	// 처음부터 치즈가 하나도 없었던 경우를 고려하자
    	 if (list.size() == 1)
    		 System.out.println("0");
    	 else
    		 System.out.println(list.get(list.size() - 2));
    	
    }
    
    private static class Coo {
    	int row, col;
    	public Coo(int row, int col) {
    		this.row = row;
    		this.col = col;
    	}
    }
}
