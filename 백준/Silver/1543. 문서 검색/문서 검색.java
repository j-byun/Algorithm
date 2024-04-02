import java.util.*;

/**
 * @author jihye.byun
 * BOJ 1543 문서 검색 실버5
 * 시간 제한 2초 | 메모리 제한 128 MB
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
    	
    	String doc = sc.nextLine();
    	String word = sc.nextLine();
    	int count = 0;
    	
    	for (int idx = 0; idx <= doc.length() - word.length(); idx++) {
    		if (contain(doc, word, idx)) {
    			count++;
    			idx += word.length() - 1;
    		}
    	}
    	
    	System.out.println(count);
    }
    
    static boolean contain(String doc, String word, int index) {
    	
    	for (int idx = 0; idx < word.length(); idx++) {
    		if (word.charAt(idx) != doc.charAt(index + idx)) {
    			return false;
    		}
    	}
    	
    	return true;
    }
}
