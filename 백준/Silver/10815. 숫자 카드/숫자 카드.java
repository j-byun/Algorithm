import java.util.*;

/**
 * @author jihye.byun
 * BOJ 10815 숫자 카드 실버5
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
    	HashSet<Integer> set = new HashSet<>();
    	
    	for (int idx = 0; idx < N; idx++) {
    		set.add(sc.nextInt());
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	int M = sc.nextInt();
    	
    	for (int idx = 0; idx < M; idx++) {
    		sb.append((set.contains(sc.nextInt()) ? 1 : 0)).append(" ");
    	}
    	
    	System.out.println(sb.toString());
    }
    
}
