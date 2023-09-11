import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 16967 배열 복원하기 실버3
 *
 * 문제
 *
 * 조건
 *
 * 풀이
 */

public class Main {
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	
    	int H = Integer.parseInt(st.nextToken());
    	int W = Integer.parseInt(st.nextToken());
    	int X = Integer.parseInt(st.nextToken());
    	int Y = Integer.parseInt(st.nextToken());
    	
    	int[][] map = new int[H + X][W + Y];
    	
    	for (int row = 0; row < H + X; row++) {
    		st = new StringTokenizer(br.readLine());
    		for (int col = 0; col < W + Y; col++) {
    			map[row][col] = Integer.parseInt(st.nextToken());
    			
    			if (row >= X && col >= Y)
    				map[row][col] -= map[row - X][col - Y];
    			
    			if (row < H && col < W)
    				bw.write(map[row][col] + " ");
    		}
    		bw.write("\n");
    	}
    	
    	bw.flush();
    }
    
}
