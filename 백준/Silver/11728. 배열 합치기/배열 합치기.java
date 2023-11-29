import java.util.*;

/**
 * @author jihye.byun
 * BOJ 11728 배열 합치기 실버5
 *
 * 문제
 *
 * 조건
 * 1 ≤ N, M ≤ 1,000,000
 *
 * 풀이
 */

public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	int N = sc.nextInt();
    	int M = sc.nextInt();
    	
    	ArrayList<Integer> list = new ArrayList<>();
    	
    	for (int cnt = 0; cnt < N + M; cnt++) {
    		list.add(sc.nextInt());
    	}
    	
    	Collections.sort(list);
    	
    	StringBuilder sb = new StringBuilder();
    	for (int idx = 0; idx < N + M; idx++) {
    		sb.append(list.get(idx)).append(" ");
    	}
    	
    	System.out.println(sb.toString());
    }
}
