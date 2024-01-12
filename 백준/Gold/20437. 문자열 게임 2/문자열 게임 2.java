import java.util.*;

/**
 * @author jihye.byun
 * BOJ 20437 문자열 게임 2 골드5
 *
 * 문제
 * 알파벳 소문자로 이루어진 문자열 W
 * 양의 정수 K
 * 어떤 문자를 정확히 K개를 포함하는 가장 짧은 연속 문자열의 길이를 구한다.
 * 어떤 문자를 정확히 K개를 초함하고, 문자열의 첫 번째와 마지막 글자가 해당 문자로 같은 가장 긴 연속 문자열의 길이를 구한다.
 * 위의 방식으로 게임을 T회 진행한다.
 *
 * 조건
 * 문자열 게임의 수 T (1 ≤ T ≤ 100)
 * 문자열 W와 정수 K가 주어진다. (1 ≤ K ≤ |W| ≤ 10,000) 
 *
 * 풀이
 * 1. a~z 문자의 위치(인덱스)를 저장할 ArrayList의 배열(26개)를 만든다.
 * 2. 문자열 W를 쪼개서 현재 문자가 위치한 인덱스를 저장한다.
 * 3. 1번의 list를 돌면서 한 문자에 K개 이상의 인덱스가 존재하는지 확인한다.
 * 4. K개 이상의 인덱스가 존재하는 문자가 없다면, -1을 출력한다.
 * 5. K개 이상의 인덱스가 존재한다면, (k-1)-0, (k)-1 ... 차례대로 돌면서 최대길이와 최소길이를 업데이트한다.
 */

public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	StringBuilder sb = new StringBuilder();
    	
    	int T = sc.nextInt(); // 테케 개수
    	
    	// 게임 시작
    	while (T-- > 0) {
    		String W = sc.next(); // 문자열 입력
    		int K = sc.nextInt(); // 포함해야하는 문자의 개수 입력
    		
    		// a~z 문자의 위치를 저장할 list의 배열
    		// 게임마다 a~z의 위치 저장할 배열 초기화
    		ArrayList<Integer>[] list = new ArrayList[26];
    		for (int idx = 0; idx < 26; idx++) {
    			list[idx] = new ArrayList<>();
    		}
    		
    		// 문자열 W를 쪼개서 각 문자의 인덱스 저장
    		for (int idx = 0; idx < W.length(); idx++) {
    			list[W.charAt(idx) - 'a'].add(idx);
    		}
    		
    		boolean flag = false; // K개의 동일한 문자가 있는지 확인할 flag
    		int min = 10000; // 가장 짧은 연속 문자열의 길이 초기값
    		int max = 0; // 가장 긴 연속 문자열의 길이 초기값
    		
    		for (int alphabet = 0; alphabet < 26; alphabet++) {
    			// 동일한 문자가 K개가 안되면 확인 X
    			if (list[alphabet].size() < K) continue;
    			
    			// 동일한 문자가 K개 이상이면 확인
    			flag = true;
    			for (int end = K - 1; end < list[alphabet].size(); end++) {
    				min = Math.min(min, list[alphabet].get(end) - list[alphabet].get(end + 1 - K) + 1);
    				max = Math.max(max, list[alphabet].get(end) - list[alphabet].get(end + 1 - K) + 1);
    			}
    		}
    		
    		// a~z를 다 확인했는데 K개 이상인 문자가 없으면 -1 출력 후 다음 게임 진행
    		if (!flag) {
    			sb.append(-1).append("\n");
    			continue;
    		}
    		
    		// K개 이상인 문자가 있으면 확인한 최소, 최대 길이 출력
    		sb.append(min).append(" ").append(max).append("\n");
    	}
    	
    	System.out.println(sb.toString());
    }
}
