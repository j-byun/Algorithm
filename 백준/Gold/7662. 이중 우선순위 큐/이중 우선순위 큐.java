import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 7662 이중 우선순위 큐 골드4
 * 시간 제한 6초 | 메모리 제한 256 MB
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
    	
    	int T = Integer.parseInt(st.nextToken());
    	
    	while (T-- > 0) {
    		TreeMap<Integer, Integer> map = new TreeMap<>();
    		
    		st = new StringTokenizer(br.readLine());
    		int k = Integer.parseInt(st.nextToken());
    		
    		while (k-- > 0) {
    			st = new StringTokenizer(br.readLine());
    			char command = st.nextToken().charAt(0);
    			int input = Integer.parseInt(st.nextToken());
    			
    			// 삽입
    			if (command == 'I') {
    				map.put(input, map.getOrDefault(input, 0) + 1);
    			}
    			
    			// 삭제
    			if (command == 'D') {
    				if (map.isEmpty()) continue;
    				
    				if (input == 1) {
    					int key = map.lastKey();
    					if (map.get(key) == 1)
    						map.remove(key);
    					else
    						map.put(key, map.get(key) - 1);
    				}
    				if (input == -1) {
    					int key = map.firstKey();
    					if (map.get(key) == 1)
    						map.remove(key);
    					else
    						map.put(key, map.get(key) - 1);
    				}
    			}
    		}
    		
    		if (map.isEmpty()) {
    			sb.append("EMPTY\n");
    		} else {
    			sb.append(map.lastKey()).append(" ").append(map.firstKey()).append("\n");
    		}
    	}
    	
    	System.out.println(sb.toString());
    }
}
