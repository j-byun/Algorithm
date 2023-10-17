import java.util.*;

/**
 * @author jihye.byun
 * BOJ 2607 비슷한 단어 실버2	
 *
 * 문제
 * 영어 알파벳 대문자로 이루어진 단어
 * 같은 종류의 문자로 이루어져 있고, 같은 문자가 같은 개수만큼 있다면 같은 구성을 갖는다.
 * 두 단어가 같은 구성을 갖거나,
 * 또는 한 단어에서 한 문자를 더하거나, 빼거나, 하나의 문자를 다른 문자로 바꾸어 나머지 한 단어와 같은 구성을 갖게 되는 경우
 * 두 단어를 서로 비슷한 단어라고 한다.
 * 첫 번째 단어와 비슷한 단어의 개수를 찾자.
 *
 * 조건
 * 모든 단어는 영문 알파벳 대문자로 이루어져 있다.
 * 단어의 개수는 100개 이하이며, 각 단어의 길이는 10 이하이다.
 *
 * 풀이
 * 1. 첫 단어를 character 별로 분해해서 개수를 세어주자.
 * 2. 두번째 단어부터는 character 별로 분해해서 개수를 센 후, 첫 단어와 비교해서 2이상 차이나면 버리고 1이하로 차이나면 비슷한 단어 개수를 하나 더 세어주자.
 */

public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	int wordCount = sc.nextInt();
    	
    	String word = sc.next();
    	wordCount--;
    	int[] wordChar = new int[26];
    	
    	for (int idx = 0; idx < word.length(); idx++) {
    		wordChar[word.charAt(idx) - 'A']++;
    	}
    	
    	int similarCount = 0;
    	
    	while (wordCount-- > 0) {
    		String newWord = sc.next();
    		int[] newWordChar = new int[26];
    		
    		if (Math.abs(word.length() - newWord.length()) > 1) continue;
    		
    		for (int idx = 0; idx < newWord.length(); idx++) {
    			newWordChar[newWord.charAt(idx) - 'A']++;
        	}
    		
    		int diffCount = 0;
    		
    		for (int idx = 0; idx < 26; idx++) {
    			diffCount += Math.abs(wordChar[idx] - newWordChar[idx]);
    		}
    		
    		if (word.length() == newWord.length()) {
    			if (diffCount <= 2) similarCount++;
    		}
    		else {
    			if (diffCount <= 1) similarCount++;
    		}
    	}
    	
    	System.out.println(similarCount);
    }
}
