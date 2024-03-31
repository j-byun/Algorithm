import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 10816 숫자 카드 2 실버4
 * 시간 제한 1초 | 메모리 제한 256 MB
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
    	
    	Map<Integer,Integer> map = new HashMap<>();
    	
    	int N = Integer.parseInt(st.nextToken());
    	
    	st = new StringTokenizer(br.readLine());
    	while (N-- > 0) {
    		int input = Integer.parseInt(st.nextToken());
    		
    		map.put(input, map.getOrDefault(input, 0) + 1);
    	}
    	
    	st = new StringTokenizer(br.readLine());
    	int M = Integer.parseInt(st.nextToken());
    	
    	st = new StringTokenizer(br.readLine());
    	while (M-- > 0) {
    		sb.append(map.getOrDefault(Integer.parseInt(st.nextToken()), 0)).append(" ");
    	}
    	
    	System.out.println(sb.toString());
    }
}
