import java.util.*;

/**
 * @author jihye.byun
 * BOJ 1484 다이어트 골드5
 *
 * 문제
 * 성원이가 더 찐 G킬로그램 = 성원이의 현재 몸무게의 제곱 - 성원이가 기억하고 있던 몸무게의 제곱
 * 성원이의 현재 몸무게로 가능한 것을 모두 출력하자.
 *
 * 조건
 * G는 100,000보다 작거나 같은 자연수
 * 
 * 가능한 성원이의 현재 몸무게를 오름차순으로 출력한다.
 * 가능한 몸무게가 없을 때는 -1을 출력한다.
 * 현재 몸무게는 자연수로 떨어지지 않을 수도 있는데, 이런 경우는 제외해야 한다.
 *
 * 풀이
 * 1. 투 포인터
 */

public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	StringBuilder sb = new StringBuilder();
    	
    	int G = sc.nextInt(); // 성원이가 더 찐 무게 입력받기
    	
    	int now = 1; // 성원이의 현재 몸무게 초기값 설정
    	int before = 1; // 성원이의 예전 몸무게 초기값 설정
    	
    	while (now <= 100_000) {
    		long diff = (now * now) - (before * before);
    		
    		if (diff == G) {
    			sb.append(now++).append("\n");
    			before++;
    		}
    		
    		if (diff < G) {
    			now++;
    		}
    		
    		if (diff > G) {
    			before++;
    		}
    	}
    	
    	System.out.println((sb.length() == 0) ? "-1" : sb.toString());
    }
}
