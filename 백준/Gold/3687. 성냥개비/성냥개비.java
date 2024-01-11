import java.util.*;

/**
 * @author jihye.byun
 * BOJ 3687 성냥개비
 *
 * 문제
 * 성냥개비의 개수가 주어졌을 때, 성냥개비를 모두 사용해서 만들 수 있는 가장 작은 수와 큰 수를 찾자.
 *
 * 조건
 * 성냥개비의 개수 n (2 ≤ n ≤ 100)
 * 성냥개비로 만드는 수는 양수여야하고, 0으로 시작할 수 없다.
 *
 * 풀이
 * 1 2 3 4 5 6 7 8 9 0 을 만드는 데에 필요한 성냥개비의 개수 2 5 5 4 5 6 3 7 6 6
 */

public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	// 각 수를 만들 떄 필요한 성냥개비의 개수를 저장할 map 만들기
    	Map<Integer, Integer> map = new HashMap<>();
    	map.put(1, 2);
    	map.put(2, 5);
    	map.put(3, 5);
    	map.put(4, 4);
    	map.put(5, 5);
    	map.put(6, 6);
    	map.put(7, 3);
    	map.put(8, 7);
    	map.put(9, 6);
    	map.put(0, 6);
    	
    	// 성냥개비의 개수가 n개일 때 만들 수 있는 최대/최소 수를 저장할 dp배열 만들기
    	String[] dpMax = new String[101];
    	String[] dpMin = new String[101];
    	
    	// n은 2부터 시작이니 2부터 초기화
    	dpMax[2] = "1";
    	dpMax[3] = "7";
    	dpMax[4] = "11";
    	dpMax[5] = "71";
    	dpMax[6] = "111";
    	dpMax[7] = "711";
    	
    	dpMin[2] = "1";
    	dpMin[3] = "7";
    	dpMin[4] = "4";
    	dpMin[5] = "2";
    	dpMin[6] = "6";
    	dpMin[7] = "8";
    	
    	// 8부터 100까지 dp배열 채우기
    	for (int idx = 8; idx <= 100; idx++) {
    		// 최댓값
    		// idx가 짝수일 때 111...또는 idx가 홀수일 때 711...
    		dpMax[idx] = dpMax[idx - 2] + "1";
    		
    		// 최솟값s
    		// 0~9까지의 경우 모두 탐색
    		String min = dpMin[idx - 6] + "0";
    		
    		for (int add = 1; add <= 9; add++) {
    			if (idx - map.get(add) < 2) continue;
    			
    			min = String.valueOf(Math.min(Long.parseLong(min), Long.parseLong(dpMin[idx - map.get(add)] + add)));
    		}
    		
    		dpMin[idx] = min;
    	}
    	
    	// 테스트케이스 입력받기
    	int tc = sc.nextInt();
    	
    	while (tc-- > 0) {
    		int n = sc.nextInt();
    		
    		System.out.println(dpMin[n] + " " + dpMax[n]);
    	}
    }
}
