import java.util.*;

/**
 * @author jihye.byun
 * BOJ 1181 단어 정렬 실버5
 *
 * 문제
 *
 * 조건
 *
 * 풀이
 */

public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	int N = sc.nextInt();
    	
    	String[] words = new String[N];
    	
    	for (int idx = 0; idx < N; idx++) {
    		words[idx] = sc.next();
    	}
    	
    	Arrays.sort(words, (o1, o2) -> {
    		if (o1.length() == o2.length()) {
    			return o1.compareTo(o2);
    		}
    		return o1.length() - o2.length();
    	});
    	
    	StringBuilder sb = new StringBuilder();
    	String before = "";
    	
    	for (int idx = 0; idx < N; idx++) {
    		if (before.equals(words[idx])) continue;
    		
    		sb.append(words[idx]).append("\n");
    		before = words[idx];
    	}
    	
    	System.out.println(sb.toString());
    }
}
