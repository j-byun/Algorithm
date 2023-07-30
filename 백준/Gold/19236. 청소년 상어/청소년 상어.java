import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author jihye.byun
 * BOJ 19236 청소년 상어 골드2
 *
 * 문제
 * 아기 상어가 성장해 청소년 상어가 되었다.
 * 4*4 크기의 공간이 1*1 크기의 정사각형 칸으로 나누어져 있다.
 * 간 칸은 (x, y)로 표현한다. x: 행의 번호, y: 열의 번호
 * 한 칸에는 물고기가 한 마리 존재한다.
 * 각 물고기는 번호와 방향을 가지고 있다.
 * 번호는 1보다 크거나 같고, 16보다 작거나 같은 자연수이며, 두 물고기가 같은 번호를 갖는 경우는 없다.
 * 방향은 8가지 방향(상하좌우, 대각선) 중 하나이다.
 * 
 * 청소년 상어가 이 공간에 들어가 물고기를 먹으려고 한다.
 * 청소년 상어는 (0, 0)에 있는 물고기를 먹고, (0, 0)에 들어가게 된다.
 * 상어의 방향은 (0, 0)에 있던 물고기의 방향과 같다.
 * 이후 물고기가 이동한다.
 * 
 * 물고기는 번호가 작은 물고기부터 순서대로 이동한다.
 * 물고기는 한 칸을 이동할 수 있고, 이동할 수 있는 칸은 빈 칸과 다른 물고기가 있는 칸,
 * 이동할 수 없는 칸은 상어가 있거나, 공간의 경계를 넘는 칸이다.
 * 각 물고기는 방향이 이동할 수 있는 칸을 향할 때까지 방향을 45도 반시계 회전시킨다.
 * 만약, 이동할 수 있는 칸이 없으면 이동을 하지 않는다.
 * 그 외의 경우에는 그 칸으로 이동을 한다.
 * 물고기가 다른 물고기가 있는 칸으로 이동할 때는 서로의 위치를 바꾸는 방식으로 이동한다.
 * 
 * 물고기의 이동이 모두 끝나면 상어가 이동한다.
 * 상어는 방향에 있는 칸으로 이동할 수 있는데, 한 번에 여러 개의 칸을 이동할 수 있다.
 * 상어가 물고기가 있는 칸으로 이동했다면. 그 칸에 있는 물고기를 먹고, 그 물고기의 방향을 가지게 된다.
 * 이동하는 중에 지나가는 칸에 있는 물고기는 먹지 많는다.
 * 물고기가 없는 칸으로는 이동할 수 없다.
 * 상어가 이동할 수 있는 칸이 없으면 공간에서 벗어나 집으로 간다.
 * 상어가 이동한 후에는 다시 물고기가 이동하며, 이후 이 과정이 계속해서 반복된다.
 * 
 * 상어가 먹을 수 있는 물고기 번호의 합의 최댓값을 출력한다.
 *
 * 조건
 * 물고기의 방향은 상 방향부터 반시계 방향 순서대로 1~8번으로 주어진다.
 *
 * 풀이
 * 1. 1번~16번 물고기의 위치를 저장해두고 위치가 바뀔 때 마다 업데이트 해주자.
 * 1-1. 안그러면 물고기가 이동할 때 마다 map을 16번 순회해야 한다.
 * 2. 그림5에서 상어가 먹을 수 있는 물고기는 12, 15, 8번인데, 이 중 어떤 물고기를 먹어야 먹은 물고기 번호의 합이 최대가 되는지는 모른다.
 * 2-1. 그러니 모든 경우를 다 확인해 봐야 한다.
 */

public class Main {
	
	static int[] dr = new int[] {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = new int[] {0, -1, -1, -1, 0, 1, 1, 1};
	
	static int maxSum = 0;
    
	private static void moveFish(int sum, Fish[][] map, Coo[] fishList) {
		
		for (int idx = 1; idx <= 16; idx++) {
			int row = fishList[idx].x;
			int col = fishList[idx].y;
			
			// 이미 먹힌 물고기면 패스
			if (row == -1) continue;
			
			// 8방향 다 확인
			for (int d = 0; d < 8; d++) {
				int nr = row + dr[map[row][col].direction];
				int nc = col + dc[map[row][col].direction];
				
				// 상어가 있거나 공간을 벗어나면 반시계 방향으로 회전
				if (nr < 0 || nr >= 4 || nc < 0 || nc >= 4 || map[nr][nc].index == 0) {
					map[row][col].direction = (map[row][col].direction + 1) % 8;
					continue;
				}
				
				// 물고기가 있는 칸이면 둘이 위치 바꾸기
				Fish cur = map[row][col];
				map[row][col] = map[nr][nc];
				map[nr][nc] = cur;
				fishList[idx] = new Coo(nr, nc);
				if (map[row][col].index != -1)
					fishList[map[row][col].index] = new Coo(row, col);
				break;
			}
		}
		
		// 16마리 다 이동했으면 상어 이동시키기
		// 이동할 수 있는 방향에 먹을 수 있는 물고기가 있는지 확인하자
		List<Integer> canEatList = canEat(fishList[0].x, fishList[0].y, map);
		// 먹을 수 있는 물고기가 없으면 sum의 최대값 저장하고 반환
		if (canEatList.size() == 0) {
			maxSum = Math.max(maxSum, sum);
			return;
		}
		// 먹을 수 있는 물고기가 있으면 모든 경우를 고려해보자
		for (int idx = 0; idx < canEatList.size(); idx++) {
			Fish[][] copyMap = new Fish[4][4];
			for (int row = 0; row < 4; row++) {
				for (int col = 0; col < 4; col++) {
					copyMap[row][col] = new Fish(map[row][col].index, map[row][col].direction);
				}
			}
			
			Coo[] copyFishList = new Coo[17];
			for (int cnt = 0; cnt <= 16; cnt++) {
				copyFishList[cnt] = new Coo(fishList[cnt].x, fishList[cnt].y);
			}
			moveShark(canEatList.get(idx), sum, copyMap, copyFishList);
		}
	}
	
	private static List<Integer> canEat(int row, int col, Fish[][] map) {
		List<Integer> canEatList = new ArrayList<>();
		
		int dir = map[row][col].direction;
		
		for (int d = 1; d < 4; d++) {
			int nr = row + dr[dir] * d;
			int nc = col + dc[dir] * d;
			
			if (nr < 0 || nr >= 4 || nc < 0 || nc >= 4 || map[nr][nc].index == -1) continue;
			canEatList.add(map[nr][nc].index);
		}
		
		// 먹을 수 있는 물고기들의 번호리스트를 반환하자
		return canEatList;
	}
	
	private static void moveShark(int fishNum, int sum, Fish[][] map, Coo[] fishList) {
		int fishRow = fishList[fishNum].x;
		int fishCol = fishList[fishNum].y;
		
		int sharkRow = fishList[0].x;
		int sharkCol = fishList[0].y;
		
		fishList[0] = new Coo(fishRow, fishCol);
		fishList[fishNum] = new Coo(-1, -1);
		sum += fishNum;
		map[fishRow][fishCol].index = 0;
		map[sharkRow][sharkCol].index = -1;
		
		moveFish(sum, map, fishList);
	}
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	
    	Fish[][] map = new Fish[4][4]; // 공간 정보
    	Coo[] fishList = new Coo[17]; // 생선들의 좌표를 저장할 공간 (0번은 상어 좌표)
    	
    	// 공간의 물고기들 정보 입력받기
    	for (int row = 0; row < 4; row++) {
    		st = new StringTokenizer(br.readLine());
    		for (int col = 0; col < 4; col++) {
    			int index = Integer.parseInt(st.nextToken());
    			// 물고기 방향을 입력받을 때 -1해서 받자
    			int direction = Integer.parseInt(st.nextToken()) - 1;
    			
    			map[row][col] = new Fish(index, direction);
    			fishList[index] = new Coo(row, col);
    		}
    	}
    	
    	// (0, 0)의 물고기를 먹고 공간에 상어를 넣자
    	int sum = 0;
    	int eatedFish = map[0][0].index; // 잡아먹힐 물고기 번호
    	fishList[0] = new Coo(0, 0);
    	fishList[eatedFish] = new Coo(-1,  -1); // 물고기 먹었으니까 해당 물고기 좌표를 -1로 처리해주자
    	sum += eatedFish; // 먹은 물고기 번호 합에 더해주고
    	map[0][0].index = 0; // 이제 (0, 0)에는 상어가 있다
    	
    	// 이제 물고기 이동 + 상어 먹기 반복 진행
    	moveFish(sum, map, fishList);
    	
    	// 먹힌 물고기의 최대 번호 합 출력
    	System.out.println(maxSum);
    }
    
    public static class Fish {
    	int index, direction;
    	
    	public Fish(int index, int direction) {
    		this.index = index;
    		this.direction = direction;
    	}
    }
    
    public static class Coo {
    	int x, y;
    	
    	public Coo(int x, int y) {
    		this.x = x;
    		this.y = y;
    	}
    }
}
