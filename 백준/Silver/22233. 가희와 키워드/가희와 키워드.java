import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 22233 가희와 키워드 실버2
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
    	
    	int N = Integer.parseInt(st.nextToken());
    	int M = Integer.parseInt(st.nextToken());
    	
    	Set<String> set = new HashSet<>();
    	
    	// N개의 단어 입력받기
    	for (int cnt = 0; cnt < N; cnt++) {
    		st = new StringTokenizer(br.readLine());
    		set.add(st.nextToken());
    	}
    	
    	// M개의 글 확인하기
    	for (int cnt = 0; cnt < M; cnt++) {
    		st = new StringTokenizer(br.readLine(), ",");
    		
    		while (st.hasMoreTokens()) {
    			set.remove(st.nextToken());
    		}
    		
    		System.out.println(set.size());
    	}
    }
}
