import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 16919 봄버맨 2 골드3
 *
 * 문제
 * R*C 크기의 격자판 위의 봄버맨
 * 격자의 각 칸은 비어있거나 폭탄이 들어있다.
 * 폭탄이 있는 칸은 3초가 지난 후에 폭발하고,
 * 폭발한 이후에는 폭탄이 있던 칸이 파괴되어 빈 칸이 되며,
 * 인접한 네 칸도 함께 파괴된다.
 * 인접한 폭탄은 폭발없이 파괴된다.
 * 봄버맨은 폭탄에 면역력을 가지고 있어서, 격자판의 모든 칸을 자유롭게 이동할 수 있다.
 * 
 * 0초: 일부 칸에 폭탄 설치
 * 1초: 아무것도 하지 않는다.
 * 2초: 폭탄이 설치되어 있지 않은 모든 칸에 폭탄을 설치한다.
 * 3초: 3초 전에 설치된 폭탄이 모두 폭발한다.
 * 4초: 2초 반복
 * 5초: 3초 반복
 * 
 * 폭탄을 설치해놓은 초기 상태가 주어졌을 때, N초가 흐른 후의 격자판 상태를 구하자.
 *
 * 조건
 * R, C, N (1 ≤ R, C ≤ 200, 1 ≤ N ≤ 10^9)
 *
 * 풀이
 * 1. 1초에 1억번 연산 수행, 10^9=10억이면 10초를 1/5로 줄이자.
 * 2. 2초 이후부터는 짝수번째 초에 폭탄 설치, 홀수번째 초에 폭발이 일어난다.
 * 3. 홀수번째 초만 탐색하며 폭발되는 칸 + 인접 네칸에 해당 초+4 시간의 폭탄을 설치하자.
 * 4. 출력할 결과가 짝수번째라면, 무조건 모든 칸에 폭탄이 존재한다.
 * 4-1. 출력할 결과가 홀수번째라면, 현재시간보다 큰 수가 저장된 칸만 폭탄이 존재한다.
 */

public class Main {
	
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int R = Integer.parseInt(st.nextToken()); // 행의 개수
    	int C = Integer.parseInt(st.nextToken()); // 열의 개수
    	int N = Integer.parseInt(st.nextToken()); // 흐른 시간
    	
    	int[][] map = new int[R][C]; // 폭탄을 설치할 격자판
    	
    	for (int row = 0; row < R; row++) {
    		st = new StringTokenizer(br.readLine());
    		String line = st.nextToken();
    		for (int col = 0; col < C; col++) {
    			if (line.charAt(col) == 'O')
    				map[row][col] = 3;
    			else {
    				if (N > 1)
    					map[row][col] = 5;
    			}
    		}
    	}
    	
    	if (N == 1) {
    		printMap(N, R, C, map); // 격자 상태 출력
    	} else if (N % 2 == 0) {
    		// 짝수가 입력되는 경우 모든 칸에 폭탄이 있는 상태로 출력
    		printBombs(R, C, map);
    	} else {
    		// 홀수가 입력되면 시뮬레이션 진행 후 출력
    		playBombs(N % 4 + 4, R, C, map);
    		printMap(N % 4 + 4, R, C, map); // 격자 상태 출력
    	}
    	
    }
    
    private static void playBombs(int maxTime, int R, int C, int[][] map) {
    	// 폭탄 설치, 폭발 시뮬레이션을 진행하는 메서드
    	
    	for (int time = 3; time <= maxTime; time += 2) {
    		for(int row = 0; row < R; row++) {
    			for (int col = 0; col < C; col++) {
    				explodeBombs(time, row, col, R, C, map);
    			}
    		}
    	}
    }
    
    private static void explodeBombs(int time, int row, int col, int R, int C, int[][] map) {
    	// 현재 시간보다 이전 값인 칸과 인접한 네 개의 칸을 폭발시키고, 현재시간+4인 폭탄으로 재설치하자
    	int[] dr = new int[] {-1, 0, 1, 0};
    	int[] dc = new int[] {0, 1, 0, -1};
    	
    	if (map[row][col] > time) return;
    	
    	map[row][col] = time + 4;
    	
    	for (int d = 0; d < 4; d++) {
    		int nr = row + dr[d];
    		int nc = col + dc[d];
    		
    		if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
    		
    		if (map[nr][nc] <= time)
    			explodeBombs(time, nr, nc, R, C, map);
    		else
    			map[nr][nc] = time + 4;
    	}
    	
    }
    
    private static void printMap(int time, int R, int C, int[][] map) throws IOException{
    	// 격자 상태를 출력하는 메서드 (홀수시간일 때)
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	
    	for (int row = 0; row < R; row++) {
    		for (int col = 0; col < C; col++) {
    			if (map[row][col] > time && map[row][col] < time + 4)
    				bw.write('O');
    			else
    				bw.write('.');
//    			bw.write(map[row][col] + " ");
    		}
    		bw.write("\n");
    	}
    	bw.flush();
    }
    
    private static void printBombs(int R, int C, int[][] map) throws IOException{
    	// 모든 칸이 폭탄으로 채워진 격자를 출력하는 메서드
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	
    	for (int row = 0; row < R; row++) {
    		for (int col = 0; col < C; col++) {
    			bw.write('O');
    		}
    		bw.write("\n");
    	}
    	bw.flush();
    }
    
}
