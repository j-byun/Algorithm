import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 11048 이동하기 실버2
 *
 * 문제
 *
 * 조건
 * 1 ≤ N, M ≤ 1,000
 * 사탕의 개수는 0보다 크거나 같고, 100보다 작거나 같다.
 *
 * 풀이
 */

public class Main {
	static int N, M, maxCandy;
	static int[][] map;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	N = Integer.parseInt(st.nextToken()); // row 크기
    	M = Integer.parseInt(st.nextToken()); // col 크기
    	map = new int[N + 1][M + 1]; // row-1, col-1 에 접근하기 위해 1,1부터 시작하게 map 크기 지정
    	
    	// 미로에 놓여진 사탕 정보 입력받기
    	for (int row = 1; row <= N; row++) {
    		st = new StringTokenizer(br.readLine());
    		for (int col = 1; col <= M; col++) {
    			// 현재 칸 사탕 정보를 입력받고
    			map[row][col] = Integer.parseInt(st.nextToken());
    			// 왼쪽칸과 윗쪽 칸 중 더 큰 칸을 거쳐오게 하자
    			// 대각선 왼쪽 윗칸은 왼쪽과 윗쪽칸에서 이미 거쳐왔기 때문에 고려하지 않아도 됨
    			map[row][col] += Math.max(map[row - 1][col], map[row][col - 1]);
    		}
    	}
    	
    	// 사탕을 제일 많이 주울 수 있는 길로 왔을 때, 마지막 칸에서의 사탕 갯수 출력
    	System.out.println(map[N][M]);
    }
}
