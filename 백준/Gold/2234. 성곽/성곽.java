import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 2234 성곽 골드3
 *
 * 문제
 * 굵은 선은 벽을 나타내고, 점선은 벽이 없어서 지나다닐 수 있는 통로를 나타낸다.
 * 이러한 형태의 성의 지도를 입력받아서 다음을 계산하는 프로그램을 작성하시오.
 * 1. 이 성에 있는 방의 개수
 * 2. 가장 넓은 방의 넓이
 * 3. 하나의 벽을 제거하여 얻을 수 있는 가장 넓은 방의 크기
 *
 * 조건
 * 성의 크기 (1 ≤ M, N ≤ 50)
 * 성에는 최소 두 개의 방이 있어서, 항상 하나의 벽을 제거하여 두 방을 합치는 경우가 있다.
 *
 * 풀이
 * 1. 벽 정보, 방문 정보, 방 번호를 저장하는 클래스의 배열을 만들자.
 * 2. 방문하지 않은 모든 방을 방문하며 bfs탐색으로 한 방의 크기를 확인하자.
 * 3. 벽이 있는 쪽 방이 이미 방문했던 방이라면 인접 방의  최대 크기를 저장해두고 현재 방 크기 측정이 끝나면 더해서 최대 합을 업데이트하자.
 */

public class Main {
	static class Room {
		int wall, roomNum;
		boolean visit;
		
		public Room(int wall, boolean visit, int roomNum) {
			this.wall = wall;
			this.visit = visit;
			this.roomNum = roomNum;
		}
	}
	
	static class Point {
		int row, col;
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	// 서북동남 순서
	static int[] dr = new int[] {0, -1, 0, 1};
	static int[] dc = new int[] {-1, 0, 1, 0};
	
	static Room[][] map;
	static int N, M, maxRoomSize, maxUnionRoomSize;
	static List<Integer> list;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	
    	// map 크기 입력받기
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	
    	map = new Room[M][N]; // M*N map 만들기
    	
    	// map의 벽 정보 입력받기
    	for (int row = 0; row < M; row++) {
    		st = new StringTokenizer(br.readLine());
    		for (int col = 0; col < N; col++) {
    			map[row][col] = new Room(Integer.parseInt(st.nextToken()), false, 0);
    		}
    	}
    	
    	int curRoomNum = 0; // 전체 방의 개수를 세어줄 변수
    	maxRoomSize = 0; // 최대 방 크기를 저장할 변수
    	maxUnionRoomSize = 0; // 벽 하나를 제거해서 만들 수 있는 최대 방 크기를 저장할 변수
    	
    	list = new ArrayList<>(); // 각 방의 크기를 저장할 리스트
    	list.add(0); // 0번 인덱스 초기화
    	
    	// map 탐색하기
    	for (int row = 0; row < M; row++) {
    		for (int col = 0; col < N; col++) {
    			if (!map[row][col].visit)
    				bfs(row, col, ++curRoomNum);
    		}
    	}
    	
    	bw.write(curRoomNum + "\n"); // 방의 개수
    	bw.write(maxRoomSize + "\n"); // 가장 넓은 방의 넓이
    	bw.write(maxUnionRoomSize + "\n"); // 벽 하나를 제거해서 만들 수 있는 최대 넓이
    	bw.flush();
    }
    
    public static void bfs(int row, int col, int roomNum) {
    	Queue<Point> queue = new ArrayDeque<>();
    	queue.add(new Point(row, col));
    	map[row][col].visit = true;
    	map[row][col].roomNum = roomNum;
    	int curRoomSize = 1;
    	int maxNextRoomSize = 0;
    	
    	while (!queue.isEmpty()) {
    		Point cur = queue.poll();
    		int curWall = map[cur.row][cur.col].wall;
    		
    		int dir = 1;
    		for (int d = 0; d < 4; d++) {
    			int nr = cur.row + dr[d];
    			int nc = cur.col + dc[d];
    			
    			if (nr < 0 || nr >= M || nc < 0 || nc >= N) continue;
    			if (((curWall & (dir << d)) == (dir << d)) && map[nr][nc].visit && (map[nr][nc].roomNum != roomNum)) { // 벽이 있는 방향이고, 벽 너머의 칸을 방문했었다면?
    				maxNextRoomSize = Math.max(maxNextRoomSize, list.get(map[nr][nc].roomNum)); // 옆 방의 최대 크기 업데이트
    			}
    			if (((curWall & (dir << d)) != (dir << d)) && !map[nr][nc].visit)  { // 벽이 없는 방향이고 아직 방문하지 않은 칸이라면?
    				queue.add(new Point(nr, nc)); // 방문하자
    				map[nr][nc].visit = true;
    				map[nr][nc].roomNum = roomNum;
    				curRoomSize++;
    			}
    		}
    	}
    	
    	list.add(curRoomSize); // 현재 방 넓이 저장
    	maxRoomSize = Math.max(maxRoomSize, curRoomSize); // 최대 방 넓이 업데이트
    	maxUnionRoomSize = Math.max(maxUnionRoomSize, curRoomSize + maxNextRoomSize); // 벽 부셨을 때 최대 방 넓이 업데이트
    }
}
