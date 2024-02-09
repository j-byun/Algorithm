import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 2531 회전 초밥 실버1
 * 시간 제한 1초 | 메모리 제한 256 MB
 *
 * 문제
 * 회전하는 벨트 위의 초밥
 * 벨트 위에는 같은 종류의 초밥이 둘 이상 있을 수 있다
 * 
 * 1. 벨트 위의 임의의 한 위치부터 k개의 접시를 연속해서 먹을 경우 할인된 정액 가격으로 제공한다
 * 2. 1번 행사에 참가할 경우 i번 초밥 하나를 무료로 제공한다. i번 초밥이 벨트에 없으면 새로 만들어 준다
 * 
 * 위 행사에 참가해서 가능한 한 다양한 종류의 초밥을 먹자
 *
 * 조건
 * 회전 초밥 벨트에 놓인 접시의 수 N (2 ≤ N ≤ 30,000)
 * 초밥의 가짓수 d (2 ≤ d ≤ 3,000)
 * 연속해서 먹는 접시의 수 k (2 ≤ k ≤ 3,000 (k ≤ N))
 * 쿠폰 번호 c (1 ≤ c ≤ d)
 *
 * 풀이
 * 1. 길이가 k인 슬라이딩 윈도우
 * 2. 벨트의 처음과 끝은 연결되어있으니 인덱스 처리 %N에 유의하자
 * 3. 윈도우를 이동시켜가며 그 때 추가되는 초밥과 제거되는 초밥을 카운트 배열에 적용해주고,
 * 4. 카운트 배열에 포함되어 있는 초밥의 개수를 세어서 MAX값을 업데이트 시키자
 * 5. 이렇게 완전 탐색하는 경우 시간 복잡도는 N*k = 9천만 이므로 시간 제한 1초 내에 확인 가능하다
 */

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int N = Integer.parseInt(st.nextToken()); // 벨트에 놓인 접시의 수
    	int d = Integer.parseInt(st.nextToken()); // 초밥의 가짓수
    	int k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수
    	int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호
    	
    	int[] belt = new int[N]; // 초밥이 올라가있는 벨트
    	int[] sushi = new int[d + 1]; // 각 초밥을 몇 개 먹을 수 있는지 저장할 카운트 배열
    	sushi[c]++; // c번 초밥은 쿠폰이 있어서 무조건 하나 먹을 수 있으니까 표시해두기
    	
    	// 벨트 위에 올라간 초밥 입력받기
    	for (int idx = 0; idx < N; idx++) {
    		st = new StringTokenizer(br.readLine());
    		
    		belt[idx] = Integer.parseInt(st.nextToken());
    	}
    	
    	// 길이가 k인 슬라이딩 윈도우 실행
    	// 0번 자리에서 시작할 때의 상태를 초기로 지정해주기
    	int count = 1; // 현재 자리에서 k개를 먹었을 때의 초밥 개수를 저장할 변수
    	int maxCount = 1; // 여태까지 최대로 먹을 수 있는 초밥 개수를 저장할 변수
    	// c번 초밥은 무조건 먹으니까 1로 초기화
    	
    	for (int idx = 0; idx < k; idx++) {
    		sushi[belt[idx]]++;
    		
    		// 새로운 초밥을 먹었으면 초밥 개수 세어주기
    		if (sushi[belt[idx]] == 1) {
    			count++;
    		}
    	}
    	
    	// 최대 초밥 개수 업데이트
    	maxCount = Math.max(maxCount, count);
    	
    	// 길이가 k인 윈도우를 슬라이딩 시키자
    	// 어디까지? 시작점이 N보다 작을 때 까지
    	int start = 0; // 시작점의 인덱스
    	while (++start < N) {
    		// 시작점이 한 칸 밀렸으니 그 전 초밥 제거
    		sushi[belt[start - 1]]--;
    		
    		if (sushi[belt[start - 1]] == 0) {
    			count--;
    		}
    		
    		// 종료지점의 초밥 추가해주기
    		// 인덱스 설정 유의 (회전하는 벨트니까 %N)
    		sushi[belt[(start + k - 1) % N]]++;
    		
    		if (sushi[belt[(start + k - 1) % N]] == 1) {
    			count++;
    		}
    		
    		// 최대로 먹을 수 있는 초밥 종류 업데이트
    		maxCount = Math.max(maxCount, count);
    	}

    	// 슬라이딩 윈도우로 찾아낸 최대 초밥 가짓수 출력
    	System.out.println(maxCount);
    }
}
