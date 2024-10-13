import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 2563 색종이 실버5
 * 시간 제한 1초 | 메모리 제한 128 MB
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
    	StringBuilder sb = new StringBuilder();
    	
    	int count = Integer.parseInt(st.nextToken());
    	boolean[][] paper = new boolean[100][100];
    	
    	for (int cnt = 0; cnt < count; cnt++) {
    		st = new StringTokenizer(br.readLine());
    		int r = Integer.parseInt(st.nextToken());
    		int c = Integer.parseInt(st.nextToken());
    		
    		for (int row = r; row < r + 10; row++) {
    			for (int col = c; col < c + 10; col++) {
    				paper[row][col] = true;
    			}
    		}
    	}
    	
    	int answer = 0;
    	
    	for (int row = 0; row < 100; row++) {
    		for (int col = 0; col < 100; col++) {
    			if (paper[row][col]) answer++;
    		}
    	}
    	
    	System.out.println(answer);
    }
}
