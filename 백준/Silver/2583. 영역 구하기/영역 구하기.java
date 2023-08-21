import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author jihye.byun
 * BOJ 2583 영역 구하기 실버1 BFS
 *
 * 문제
 * 눈금의 간격이 1인 M*N크기의 모눈종이가 있다.
 * 이 모눈종이 위에 눈금에 맞추어 K개의 직사각형을 그릴 때,
 * 이들 K개의 직사각형의 내부를 제외한 나머지 부분이 몇 개의 분리된 영역으로 나누어진다.
 * M과 N 그리고 K개의 직사각형 좌표가 주어질 때,
 * K개의 직사각형 내부를 제외한 나머지 부분이 몇 개의 분리된 영역으로 나누어지는지,
 * 그리고 분리된 각 영역의 넓이가 얼마인지를 구하여 이를 출력하는 프로그램을 작성하시오.
 *
 * 조건
 * M, N, K는 모두 100 이하의 자연수이다.
 * 입력되는 K개의 직사각형들이 모눈종이 전체를 채우는 경우는 없다.
 *
 * 풀이
 * 1. BFS 탐색하며 공간의 개수를 카운트하고,
 * 1-1. 그 공간의 넓이를 ArrayList에 저장 후 sort해서 차례대로 출력하자.
 * 2. 모눈종이의 빈 공간은 0, 이미 직사각형이 그려진 부분은 1, 빈 공간이었지만 카운트 한 경우 9로 표시하자.
 */

public class Main {
	
	static int[] dr = new int[] {-1, 0, 1, 0};
	static int[] dc = new int[] {0, 1, 0, -1};
	
	static int M, N;
	static int[][] map;
	static int count;
	static List<Integer> list;
	
	private static void bfs(int startRow, int startCol) {
		Queue<Coo> queue = new ArrayDeque<>();
		queue.add(new Coo(startRow, startCol));
		int area = 0;
		
		while (!queue.isEmpty()) {
			Coo cur = queue.poll();
			
			if (map[cur.row][cur.col] != 0) continue;
			
			map[cur.row][cur.col] = 9;
			area++;
			
			for (int d = 0; d < 4; d++) {
				int nr = cur.row + dr[d];
				int nc = cur.col + dc[d];
				
				if (nr < 0 || nr >= M || nc < 0 || nc >= N) continue;
				if (map[nr][nc] != 0) continue;
				queue.add(new Coo(nr, nc));
			}
		}
		
		list.add(area);
	}
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	
    	// map크기 초기화
    	M = Integer.parseInt(st.nextToken());
    	N = Integer.parseInt(st.nextToken());
    	map = new int[M][N];
    	
    	// 직사각형 좌표 정보 입력받기
    	int K = Integer.parseInt(st.nextToken());
    	for (int count = 0; count < K; count++) {
    		st = new StringTokenizer(br.readLine());
    		int startCol = Integer.parseInt(st.nextToken());
    		int startRow = Integer.parseInt(st.nextToken());
    		int endCol = Integer.parseInt(st.nextToken());
    		int endRow = Integer.parseInt(st.nextToken());
    		
    		// 모눈종이에 직사각형 그리기
    		for (int row = startRow; row < endRow; row++) {
    			for (int col = startCol; col < endCol; col++) {
    				map[row][col] = 1;
    			}
    		}
    	}
    	
    	// 이제 모눈종이의 빈공간을 찾아보자
    	// 빈 공간을 찾으면 공간 개수를 1 증가시키자
    	// 빈 공간에서 너비 우선 탐색을 진행해 공간의 크기를 확인하고, 확인한 크기를 리스트에 저장하자
    	count = 0;
    	list = new ArrayList<>();
    	for (int row = 0; row < M; row++) {
    		for (int col = 0; col < N; col++) {
    			if (map[row][col] == 0) {
    				count++;
    				bfs(row, col);
    			}
    		}
    	}
    	
    	bw.write(count + " \n");
    	
    	Collections.sort(list);
    	
    	for (int idx = 0; idx < list.size(); idx++) {
    		bw.write(list.get(idx) + " ");
    	}
    	
    	bw.flush();
    }
    
    private static class Coo {
    	int row, col;
    	public Coo(int row, int col) {
    		this.row = row;
    		this.col = col;
    	}
    }
}
