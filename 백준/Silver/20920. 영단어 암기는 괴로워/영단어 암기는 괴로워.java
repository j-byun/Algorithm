import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 20920 영단어 암기는 괴로워 실버3
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
    	
    	int N = Integer.parseInt(st.nextToken()); // 단어의 개수
    	int M = Integer.parseInt(st.nextToken()); // 외울 단어의 길이 기준
    	
    	Map<String, Integer> map = new HashMap<>();
    	
    	for (int cnt = 0; cnt < N; cnt++) {
    		st = new StringTokenizer(br.readLine());
    		String word = st.nextToken();
    		
    		if (word.length() < M) continue;
    		
    		if (map.containsKey(word))
    			map.put(word, map.get(word) + 1);
    		
    		if (!map.containsKey(word))
    			map.put(word, 1);
    	}
    	
    	List<String> words = new ArrayList<>(map.keySet());
    	
    	Collections.sort(words, (o1, o2) -> {
    		if (map.get(o2) == map.get(o1)) {
    			if (o2.length() == o1.length()) {
    				return o1.compareTo(o2);
    			}
    			return o2.length() - o1.length();
    		}
    		return map.get(o2) - map.get(o1);
    	});
    	
    	StringBuilder sb = new StringBuilder();
    	for (int idx = 0; idx < words.size(); idx++) {
    		sb.append(words.get(idx)).append("\n");
    	}
    	
    	System.out.println(sb.toString());
    }
}
