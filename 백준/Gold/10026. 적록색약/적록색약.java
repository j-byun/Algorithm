import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author jihye.byun
 * BOJ 10026 적록색약 골드5 BFS
 *
 * 문제
 * 적록색약은 빨간색과 초록색의 차이를 느끼지 못한다.
 * N*N 크기의 그리드 각 칸에 R, G, B 중 하나를 색칠한 그림이 있다.
 * 그림은 몇 개의 구역으로 나뉘어져 있고, 구역은 같은 색으로 이루어져 있다.
 * 같은 색상이 상하좌우로 인접해 있는 경우에 두 글자는 같은 구역에 속한다.
 * 색상의 차이를 거의 느끼지 못하는 경우도 같은 색상이라 한다.
 * 그림이 입력으로 주어졌을 때, 적록색약인 사람이 봤을 떄와 아닌 사람이 봤을 때 구역의 수를 구하는 프로그램을 작성하시오.
 *
 * 조건
 * 1 ≤ N ≤ 100
 * 적록색약이 아닌 사람이 봤을 때의 구역의 개수와 적록색약인 사람이 봤을 때의 구역의 수를 공백으로 구분해 출력한다.
 *
 * 풀이
 * 1. BFS 탐색을 통해 같은 색으로 이루어진 하나의 구역을 찾고, 구역의 개수를 1 증가시키자.
 * 2. 적록색약의 경우 R과 G를 동일하게 취급하자.
 * 3. map을 char형이 아닌 int형으로 만들고, R, G, B에 각각 1, 2, 3을 대입시키자.
 * 4. 적록색약인 경우, 아닌 경우로 나뉘어지기 때문에 map을 두 개 만들어서 활용하자.
 * 5. 방문처리 map을 따로 생성하지 말고 0으로 처리하자.
 * 6. BFS메서드를 하나로 재사용하기 위해 적록색약 map을 입력받을 때 부터 R과 G를 동일하게 저장하자.
 */

public class Main {
	static int[] dr = new int[] {-1, 0, 1, 0};
	static int[] dc = new int[] {0, 1, 0, -1};
	
	static final int R = 1;
	static final int G = 2;
	static final int B = 3;
	
	static int N;
	static int[][] normalMap, abnormalMap;
	
	private static void bfs(int startRow, int startCol, boolean isAbnormal) {
		int[][] map;
		if (isAbnormal)
			map = abnormalMap;
		else
			map = normalMap;
		
		int color = map[startRow][startCol];
		Queue<Coo> queue = new ArrayDeque<>();
		queue.add(new Coo(startRow, startCol));
		
		while (!queue.isEmpty()) {
			Coo cur = queue.poll();
			
			if (map[cur.row][cur.col] != color) continue;
			
			map[cur.row][cur.col] = 0;
			
			for (int d = 0; d < 4; d++) {
				int nr = cur.row + dr[d];
				int nc = cur.col + dc[d];
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
				if (map[nr][nc] != color) continue;
				queue.add(new Coo(nr, nc));
			}
		}
	}
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	// map의 크기 입력받기
    	N = Integer.parseInt(st.nextToken());
    	
    	// map 만들기
    	normalMap = new int[N][N];
    	abnormalMap = new int[N][N];
    	
    	// map 정보 입력받기
    	for (int row = 0; row < N; row++) {
    		st = new StringTokenizer(br.readLine());
    		String line = st.nextToken();
    		for (int col = 0; col < N; col++) {
    			if (line.charAt(col) == 'R') {
    				normalMap[row][col] = R;
        			abnormalMap[row][col] = R;
    			} else if (line.charAt(col) == 'G') {
    				normalMap[row][col] = G;
        			abnormalMap[row][col] = R;
    			} else {
    				normalMap[row][col] = B;
        			abnormalMap[row][col] = B;
    			}
    		}
    	}
    	
    	// 적록색약일 때, 아닐 때의 구역 수를 확인해보자
    	int normalCount = 0;
    	int abnormalCount = 0;
    	for (int row = 0; row < N; row++) {
    		for (int col = 0; col < N; col++) {
    			// 적록색약이 아닐 때
    			if (normalMap[row][col] != 0) {
    				normalCount++;
    				bfs(row, col, false);
    			}
    			
    			// 적록색약일 때
    			if (abnormalMap[row][col] != 0) {
    				abnormalCount++;
    				bfs(row, col, true);
    			}
    		}
    	}
    	
    	// 구역의 수 출력
    	System.out.println(normalCount + " " + abnormalCount);
    }
    
    private static class Coo {
    	int row, col;
    	public Coo(int row, int col) {
    		this.row = row;
    		this.col = col;
    	}
    }
}
