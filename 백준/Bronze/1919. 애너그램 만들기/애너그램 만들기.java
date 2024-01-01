import java.util.*;

/**
 * @author jihye.byun
 * BOJ 1919 애너그램 만들기 브론즈2
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
    	
    	// 단어 입력받기
    	String a = sc.next();
    	String b = sc.next();
    	
    	// 단어 알파벳별로 분해해서 세어줄 배열 만들기
    	int[] charA = new int[26];
    	int[] charB = new int[26];
    	
    	// 단어 분해해서 알파벳 갯수 세어주기
    	checkAlphabet(a, charA);
    	checkAlphabet(b, charB);
    	
    	// 두 단어의 서로 다른 알파벳 갯수 세기
    	int diff = 0;
    	
    	for (int idx = 0; idx < 26; idx++) {
    		diff += Math.abs(charA[idx] - charB[idx]);
    	}
    	
    	System.out.println(diff);
    }
    
    private static void checkAlphabet(String word, int[] charWord) {
    	
    	for (int idx = 0; idx < word.length(); idx++) {
    		charWord[word.charAt(idx) - 'a']++;
    	}
    }
}
