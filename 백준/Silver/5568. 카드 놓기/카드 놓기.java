import java.util.*;

/**
 * @author jihye.byun
 * BOJ 5568 카드 놓기 실버4
 * 시간 제한 1초 | 메모리 제한 256 MB
 *
 * 문제
 * 카드 n장을 바닥에 나란히 놓는다.
 * 각 카드에는 1이상 99이하의 정수가 적혀져 있다.
 * 이 중에서 k장의 카드를 선택하고, 가로로 나란히 정수를 만든다.
 * 만들 수 있는 정수는 모두 몇 가지일까?
 *
 * 조건
 * 4 <= n <= 10
 * 2 <= k <= 4
 *
 * 풀이
 * 1. 카드 개수가 최대 10, 뽑을 개수가 최대 4이므로 완전 탐색 가능 (10*9*8*7 <= 10000)
 * 2. 만들 수 있는 최대 숫자는 99999999
 * 3. 배열로 확인하기엔 크니까 HashMap으로 체크하자
 */

public class Main {
	
	static int n, k, count;
	static int[] cards, pick;
	static boolean[] used;
	static HashMap<String, Integer> map;
	
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	n = sc.nextInt();
    	k = sc.nextInt();
    	
    	cards = new int[n];
    	used = new boolean[n];
    	pick = new int[k];
    	
    	for (int idx = 0; idx < n; idx++) {
    		cards[idx] = sc.nextInt();
    	}
    	
    	count = 0;
    	map = new HashMap<>();
    	
    	permutation(0);
    	
    	System.out.println(count);
    }
    
    static void permutation(int depth) {
    	if (depth == k) {
    		// 일렬로 나열한 숫자 확인
    		StringBuilder sb = new StringBuilder();
    		
    		for (int pickIdx = 0; pickIdx < k; pickIdx++) {
    			sb.append(cards[pick[pickIdx]]);
    		}
    		
    		if (map.containsKey(sb.toString())) {
    			return;
    		}
    		
    		count++;
    		map.put(sb.toString(), 0);
    		
    		return;
    	}
    	
    	for (int idx = 0; idx < n; idx++) {
    		if (used[idx]) continue;
    		
    		used[idx] = true;
    		pick[depth] = idx;
    		permutation(depth + 1);
    		used[idx] = false;
    	}
    }
}
