import java.util.*;

/**
 * @author jihye.byun
 * BOJ 1522 문자열 교환 실버1
 *
 * 문제
 * a와 b로만 이루어진 문자열
 * a를 모두 연속으로 만들기 위해서 필요한 교환의 회수를 최소로 하자
 * 문자열은 원형이기 때문에, 처음과 끝은 서로 인접해있다.
 *
 * 조건
 * 문자열의 길이는 최대 1,000이다
 *
 * 풀이
 * 1. abababababababa -> b를 바로 옆이 아니더라도 범위 밖의 a와 교환하면 됨
 * 2. 슬라이딩 윈도우 적용
 * 3. a의 갯수만큼 범위 설정해서 해당 범위 안에 b의 최소 갯수를 찾자.
 * 		-> 범위 안의 b를 범위 밖의 a랑 교환하면 해당 범위 안에 모든 a가 위치할 수 있음
 */

public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	char[] string = sc.next().toCharArray(); // a와 b로 이루어진 문자열 입력
    	
    	int start = 0;
    	int end = -1;
    	
    	// 문자열에 포함된 a의 갯수 세기
    	for (char c : string) {
    		if (c == 'a')
    			end++;
    	}
    	
    	// a가 0개이거나 1개면 교환할 필요가 없으니 0 출력하고 시스템 종료
    	if (end <= 0) {
    		System.out.println(0);
    		return;
    	}
    	
    	// a가 2개 이상이면 슬라이딩 윈도우로 a의 범위 안에 포함된 최소 b의 갯수를 찾자
    	// 일단 처음 범위에 들어있는 b의 갯수 세기
    	int curB = 0;
    	
    	for (int idx = start; idx <= end; idx++) {
    		if (string[idx] == 'b')
    			curB++;
    	}
    	
    	int minB = curB;
    	
    	// 이제 start가 string의 마지막 문자를 가르킬 때 까지 오른쪽으로 슬라이딩하며 b의 갯수를 세자
    	while (start < string.length - 1) {
    		
    		if (string[start] == 'b')
    			curB--;
    		
    		start++;
    		
    		end++;
    		
    		if (end >= string.length)
    			end %= string.length;
    		
    		if (string[end] == 'b')
    			curB++;
    		
    		minB = Math.min(minB, curB);
    		
    		if (minB == 0)
    			break;
    	}
    	
    	System.out.println(minB);
    }
}
