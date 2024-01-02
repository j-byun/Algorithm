import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 1799 비숍 골드1
 *
 * 문제
 * 대각선 방향으로 움직일 수 있는 비숍
 * 체스판의 색칠된 칸에는 비숍이 놓일 수 없다고 하자.
 * 서로가 서로를 잡을 수 없도록 하면서 비숍을 놓자.
 * -> 대각선 경로에 존재하지 않게
 * 체스판 한 변의 크기와, 각 칸에 비숍을 놓을 수 있는지 없는지에 대한 정보가 주어질 때,
 * 서로가 서로를 잡을 수 없는 위치에 놓을 수 있는 비숍의 최대 개수를 구하는 프로그램을 작성하시오.
 *
 * 조건
 * 체스판의 크기는 10이하의 자연수이다.
 * 비숍을 놓을 수 있는 곳에는 1, 비숍을 놓을 수 없는 곳에는 0이 빈칸을 사이에 두고 주어진다.
 *
 * 풀이
 * 1. 전체 칸을 한 번에 탐색하니 메모리 초과 발생
 * 2. 따라서 체스판을 하얀칸, 검정칸 두 개로 쪼개서 탐색하자.
 */

public class Main {
	
	static int size, maxWhite, maxBlack;
	static int[][] map;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	size = Integer.parseInt(st.nextToken()); // 체스판 한 변의 크기 입력받기
    	map = new int[size][size]; // 체스판 만들기
    	maxWhite = 0; maxBlack = 0; // 체스판에 놓을 수 있는 최대 비숍 갯수 초기화
    	
    	// 체스판 정보 입력받기
    	for (int row = 0; row < size; row++) {
    		st = new StringTokenizer(br.readLine());
    		for (int col = 0; col < size; col++) {
    			map[row][col] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	// 체스판의 모든 칸에 대해 비숍을 놓는 경우, 놓지 않는 경우 깊이 탐색
    	// 체스판을 하얀색, 검정색으로 나누어서 탐색
    	setBishop(0, 0, 0, "black");
    	setBishop(0, 1, 0, "white");
    	
    	// 최대 비숍 갯수 출력
    	System.out.println(maxWhite + maxBlack);
    }
    
    private static void setBishop(int row, int col, int bishopCount, String color) {
    	
    	// 오른쪽 끝이면 다음 행으로 진행
    	if (col >= size) {
    		setBishop(row + 1, (col + 1) % 2, bishopCount, color);
    		return;
    	}
    	
    	// 모든 행 탐색이 종료되면 최대 비숍 갯수 업데이트 후 돌아가기
    	if (row >= size) {
    		if (color.equals("white")) {
    			maxWhite = Math.max(maxWhite, bishopCount);
    		}
    		if (color.equals("black")) {
    			maxBlack = Math.max(maxBlack, bishopCount);
    		}
    		return;
    	}
    	
    	// 현재 칸에 비숍을 놓지 않고 다음 칸 탐색하는 경우
    	setBishop(row, col + 2, bishopCount, color);
    	
    	// 현재 칸에 비숍을 놓을 수 있다면
    	// 현재 칸에 비숍을 놓고 다음 칸 탐색하는 경우
    	if (canBishop(row, col)) {
    		map[row][col] = 2;
    		setBishop(row, col + 2, bishopCount + 1, color);
    		map[row][col] = 1;
    	}
    }
    
    private static boolean canBishop(int row, int col) {
    	
    	// 1. 색칠된 칸이면 비숍을 놓을 수 없음
    	if (map[row][col] == 0) {
    		return false;
    	}
    	
    	// 2. 대각선 방향에 이미 비숍이 있는 경우 비숍을 놓을 수 없음
    	// 대각선 위 두 방향 탐색 델타배열
    	int[] dr = new int[] {-1, -1};
    	int[] dc = new int[] {-1, 1};
    	
    	// 두 방향에 대해 범위를 늘려가며 탐색
    	for (int d = 0; d < 2; d++) {
    		int range = 0;
    		
    		while (range++ < size) {
    			int nr = row + dr[d] * range;
    			int nc = col + dc[d] * range;
    			
    			// 체스판 벗어나면 다음 방향 탐색
    			if (nr < 0 || nr >= size || nc < 0 || nc >= size) break;
    			// 대각선 방향에 이미 비숍이 있으면 비숍을 놓을 수 없음
    			if (map[nr][nc] == 2) return false;
    		}
    	}
    	
    	// 위의 두 경우에 해당하지 않으면 비숍을 놓을 수 있음
    	return true;
    }
}
