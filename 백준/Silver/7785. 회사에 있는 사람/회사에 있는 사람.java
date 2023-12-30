import java.util.*;

/**
 * @author jihye.byun
 * BOJ 7785 회사에 있는 사람 실버5
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
    	
    	int n = sc.nextInt();
    	
    	HashMap<String, String> map = new HashMap<>();
    	
    	while (n-- > 0) {
    		map.put(sc.next(), sc.next());
    	}
    	
    	PriorityQueue<String> pq = new PriorityQueue<>(Collections.reverseOrder());
    	
    	for (String key : map.keySet()) {
    		if (map.get(key).equals("enter"))
    			pq.add(key);
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	
    	while (!pq.isEmpty()) {
    		sb.append(pq.poll()).append("\n");
    	}
    	
    	System.out.println(sb.toString());
    }
}
