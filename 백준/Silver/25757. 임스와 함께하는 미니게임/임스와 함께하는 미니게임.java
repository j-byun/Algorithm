import java.util.*;

/**
 * @author jihye.byun
 * BOJ 25757 임스와 함께하는 미니게임 실버5
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
    	String game = sc.next();
    	int player = 0;
    	
    	switch (game.charAt(0)) {
    	case 'Y' :
    		player = 1;
    		break;
    	case 'F' :
    		player = 2;
    		break;
    	case 'O' : 
    		player = 3;
    		break;
    	}
    	
    	Set<String> set = new HashSet<>();
    	
    	while (N-- > 0) {
    		set.add(sc.next());
    	}
    	
    	System.out.println(set.size() / player);
    }
}
