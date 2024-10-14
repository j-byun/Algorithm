import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 1620 나는야 포켓몬 마스터 이다솜 실버4
 * 시간 제한 2초 | 메모리 제한 256 MB
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
    	
    	int N = Integer.parseInt(st.nextToken());
    	int M = Integer.parseInt(st.nextToken());
    	
    	String[] arr = new String[N + 1];
    	HashMap<String, Integer> map = new HashMap<>();
    	
    	for (int idx = 1; idx <= N; idx++) {
    		String name = br.readLine();
    		arr[idx] = name;
    		map.put(name, idx);
    	}
    	
    	for (int cnt = 1; cnt <= M; cnt++) {
    		String line = br.readLine();
    		
    		try {
    			sb.append(arr[Integer.parseInt(line)]).append("\n");
    		} catch (NumberFormatException e) {
    			sb.append(map.get(line)).append("\n");
    		}
    	}
    	
    	System.out.println(sb.toString());
    }
}
