import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 15683 감시 골드4
 *
 * 문제
 * N*M 크기의 직사각형 사무실
 * 사무실에는 총 K개의 CCCTV가 설치되어 있다.
 * CCTV는 감시할 수 있는 방법에 따라 5가지 종류가 있다.
 * 1: 한쪽 방향만 감시
 * 2: 서로 반대방향인 두 방향 감시
 * 3: 서로 직각방향인 두 방향 감시
 * 4: 세 방향 감시
 * 5: 네 방향 감시
 * CCTV는 감시할 수 있는 방향에 있는 칸 전체를 감시할 수 있다.
 * CCTV는 벽을 통과할 수 없다.
 * CCTV가 감시할 수 없는 영역은 사각지대라고 한다.
 * CCTV는 90도 회전시킬 수 있다.
 * 지도에서
 * 0: 빈 칸
 * 1~5: CCTV
 * 6: 벽
 * CCTV는 CCTV를 통과할 수 있다.
 * 사무실의 크기와 상태, CCTV의 정보가 주어졌을 때, CCTV의 방향을 적절히 정해서
 * 사각 지대의 최소 크기를 구하자.
 *
 * 조건
 * 사무실의 세로크기 N, 가로크기 M (1 ≤ N, M ≤ 8)
 * CCTV의 최대 개수는 8개를 넘지 않는다.
 *
 * 풀이
 * 1번 CCTV의 종류 4개 (상, 하, 좌, 우)
 * 2번 CCTV의 종류 2개 (상하, 좌우)
 * 3번 CCTV의 종류 4개 (상우, 우하, 하좌, 좌상)
 * 4번 CCTV의 종류 4개 (상 빼고, 하 빼고, 좌 빼고, 우 빼고)
 * 5번 CCTV의 종류 1개 (무조건 전체 감시)
 * 1. CCTV의 좌표, 번호, 감시할 방향을 저장할 배열을 만들자.
 * 2. 각 CCTV에 따라 감시 방향의 조합을 구해서 시뮬레이션 해보자.
 */

public class Main {
	
	public static class Cctv {
		int row, col, type, direction;
		public Cctv(int row, int col, int type, int direction) {
			this.row = row;
			this.col = col;
			this.type = type;
			this.direction = direction;
		}
	}
	
	static int[][] map;
	static int N, M;
	static int cctvCount;
	static Cctv[] cctv;
	static int minBlindSpot;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	N = Integer.parseInt(st.nextToken()); // row크기
    	M = Integer.parseInt(st.nextToken()); // col크기
    	
    	map = new int[N][M];
    	
    	cctv = new Cctv[8]; // cctv 정보 저장할 배열
    	cctvCount = 0; // cctv 갯수 세어줄 변수
    	
    	// 사무실 정보 입력받으면서 cctv 정보 저장
    	for (int row = 0; row < N; row++) {
    		st = new StringTokenizer(br.readLine());
    		for (int col = 0; col < M; col++) {
    			map[row][col] = Integer.parseInt(st.nextToken());
    			
    			if (map[row][col] >= 1 && map[row][col] <= 5) {
    				cctv[cctvCount++] = new Cctv(row, col, map[row][col], 0);
    			}
    		}
    	}
    	
    	minBlindSpot = N * M; // 사각지대 초기값 설정
    	
    	dfs(0); // 각 cctv의 방향 조합 만들어서 시뮬레이션 해보기
    	
    	System.out.println(minBlindSpot); // 최소 사각지대 수 출력
    }
    
    static void dfs(int depth) {
    	// 각 cctv가 볼 수 있는 방향 조합 만들기
    	
    	if (depth == cctvCount) {
    		simulation();
    		return;
    	}
    	
    	int limitDir = 0;
    	if (cctv[depth].type == 1) limitDir = 4;
    	else if (cctv[depth].type == 2) limitDir = 2;
    	else if (cctv[depth].type == 3) limitDir = 4;
    	else if (cctv[depth].type == 4) limitDir = 4;
    	else if (cctv[depth].type == 5) limitDir = 1;
    	
    	for (int dir = 1; dir <= limitDir; dir++) {
    		cctv[depth].direction = dir;
    		dfs(depth + 1);
    	}
    }
    
    static void simulation() {
    	// cctv 방향 조합대로 감시하는 시뮬레이션 진행하기
    	
    	int[][] copyMap = new int[N][M];
    	
    	for (int row = 0; row < N; row++) {
    		for (int col = 0; col < M; col++) {
    			copyMap[row][col] = map[row][col];
    		}
    	}
    	
    	for (int count = 0; count < cctvCount; count++) {
    		if (cctv[count].type == 1) {
    			if (cctv[count].direction == 1)
    				goRight(cctv[count].row, cctv[count].col, copyMap);
    			else if (cctv[count].direction == 2)
    				goLeft(cctv[count].row, cctv[count].col, copyMap);
    			else if (cctv[count].direction == 3)
    				goUp(cctv[count].row, cctv[count].col, copyMap);
    			else if (cctv[count].direction == 4)
    				goDown(cctv[count].row, cctv[count].col, copyMap);
    		} else if (cctv[count].type == 2) {
    			if (cctv[count].direction == 1) {
    				goRight(cctv[count].row, cctv[count].col, copyMap);
        			goLeft(cctv[count].row, cctv[count].col, copyMap);
    			} else if (cctv[count].direction == 2) {
    				goUp(cctv[count].row, cctv[count].col, copyMap);
        			goDown(cctv[count].row, cctv[count].col, copyMap);
    			}
    		} else if (cctv[count].type == 3) {
    			if (cctv[count].direction == 1) {
    				goUp(cctv[count].row, cctv[count].col, copyMap);
    				goRight(cctv[count].row, cctv[count].col, copyMap);
    			} else if (cctv[count].direction == 2) {
    				goRight(cctv[count].row, cctv[count].col, copyMap);
    				goDown(cctv[count].row, cctv[count].col, copyMap);
    			} else if (cctv[count].direction == 3) {
    				goDown(cctv[count].row, cctv[count].col, copyMap);
    				goLeft(cctv[count].row, cctv[count].col, copyMap);
    			} else if (cctv[count].direction == 4) {
    				goLeft(cctv[count].row, cctv[count].col, copyMap);
    				goUp(cctv[count].row, cctv[count].col, copyMap);
    			} 
    		} else if (cctv[count].type == 4) {
    			if (cctv[count].direction == 1) {
        			goLeft(cctv[count].row, cctv[count].col, copyMap);
        			goUp(cctv[count].row, cctv[count].col, copyMap);
        			goDown(cctv[count].row, cctv[count].col, copyMap);
    			} else if (cctv[count].direction == 2) {
    				goRight(cctv[count].row, cctv[count].col, copyMap);
        			goUp(cctv[count].row, cctv[count].col, copyMap);
        			goDown(cctv[count].row, cctv[count].col, copyMap);
    			} else if (cctv[count].direction == 3) {
    				goRight(cctv[count].row, cctv[count].col, copyMap);
        			goLeft(cctv[count].row, cctv[count].col, copyMap);
        			goDown(cctv[count].row, cctv[count].col, copyMap);
    			} else if (cctv[count].direction == 4) {
    				goRight(cctv[count].row, cctv[count].col, copyMap);
        			goLeft(cctv[count].row, cctv[count].col, copyMap);
        			goUp(cctv[count].row, cctv[count].col, copyMap);
    			} 
    		} else if (cctv[count].type == 5) {
    			goRight(cctv[count].row, cctv[count].col, copyMap);
    			goLeft(cctv[count].row, cctv[count].col, copyMap);
    			goUp(cctv[count].row, cctv[count].col, copyMap);
    			goDown(cctv[count].row, cctv[count].col, copyMap);
    		}
    	}
    	
    	// 시뮬레이션 후 사각지대 갯수 세서 최소 사각지대 업데이트하기
    	int blidSpot = 0;
    	
    	for (int row = 0; row < N; row++) {
    		for (int col = 0; col < M; col++) {
    			if (copyMap[row][col] == 0)
    				blidSpot++;
    		}
    	}
    	
    	minBlindSpot = Math.min(minBlindSpot, blidSpot);
    }
    
    static void goRight(int startRow, int startCol, int[][] map) {
    	// 오른쪽 감시하기
    	
    	for (int col = startCol; col < M; col++) {
    		if (map[startRow][col] == 0)
    			map[startRow][col] = 7;
    		else if (map[startRow][col] == 6)
    			break;
    	}
    }
    
    static void goLeft(int startRow, int startCol, int[][] map) {
    	// 왼쪽 감시하기
    	
    	for (int col = startCol; col >= 0; col--) {
    		if (map[startRow][col] == 0)
    			map[startRow][col] = 7;
    		else if (map[startRow][col] == 6)
    			break;
    	}
    }
    
    static void goUp(int startRow, int startCol, int[][] map) {
    	// 왼쪽 감시하기
    	
    	for (int row = startRow; row >= 0; row--) {
    		if (map[row][startCol] == 0)
    			map[row][startCol] = 7;
    		else if (map[row][startCol] == 6)
    			break;
    	}
    }
    
    static void goDown(int startRow, int startCol, int[][] map) {
    	// 왼쪽 감시하기
    	
    	for (int row = startRow; row < N; row++) {
    		if (map[row][startCol] == 0)
    			map[row][startCol] = 7;
    		else if (map[row][startCol] == 6)
    			break;
    	}
    }
}
