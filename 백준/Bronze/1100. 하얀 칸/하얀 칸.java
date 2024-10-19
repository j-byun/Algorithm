import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 1100 하얀 칸 브론즈2
 * 시간 제한 2초 | 메모리 제한 128 MB
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
//    	StringTokenizer st = new StringTokenizer(br.readLine());
//    	StringBuilder sb = new StringBuilder();

    	int count = 0;
    	
    	for (int row = 0; row < 8; row++) {
    		String line = br.readLine();
    		for (int col = 0; col < 8; col++) {
    			if ((row + col) % 2 == 1) continue;
    			if (line.charAt(col) == 'F') count++;
    		}
    	}
    	
    	System.out.println(count);
    }
}
