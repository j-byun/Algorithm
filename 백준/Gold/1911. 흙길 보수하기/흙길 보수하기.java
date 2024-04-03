import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 1911 흙길 보수하기 골드5
 * 시간 제한 2초 | 메모리 제한 128 MB
 *
 * 문제
 * N개의 물웅덩이
 * 길이가 L인 널빤지로 물웅덩이를 덮자
 * 모든 물웅덩이를 덮기 위해 필요한 널빤지들의 최소 개수를 구하자.
 *
 * 조건
 * 물웅덩이의 개수 N(1 ≤ N ≤ 10,000)
 * 널빤지의 길이 L(1 ≤ L ≤ 1,000,000)
 * 웅덩이의 위치는 0 이상 1,000,000,000 이하의 정수
 * 입력으로 주어지는 웅덩이는 겹치지 않는다.
 *
 * 풀이
 * 1. 웅덩이를 오름차순으로 정렬해서 차례대로 확인해보자.
 * 2. 이전 널빤지로 덮이지 않는 웅덩이면 널빤지로 덮고, 널빤지 길이만큼 뒤의 위치를 확인하자.
 */

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int N = Integer.parseInt(st.nextToken()); // 물웅덩이 개수
    	int L = Integer.parseInt(st.nextToken()); // 널빤지 길이
    	
    	PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]); // 오름차순 정렬 우선순위 큐
    	
    	// 물웅덩이 정보 입력받기
    	for (int cnt = 0; cnt < N; cnt++) {
    		st = new StringTokenizer(br.readLine());
    		int start = Integer.parseInt(st.nextToken());
    		int end = Integer.parseInt(st.nextToken()) - 1;
    		
    		pq.add(new int[] {start, end});
    	}
    	
    	// 널빤지 개수와, 마지막으로 널빤지가 덮여진 위치를 표현할 변수
    	int boardCount = 0;
    	int lastBoardIndex = -1;
    	
    	while (!pq.isEmpty()) {
    		int[] pool = pq.poll();
    		
    		// 이미 널빤지로 덮여있으면 다음 물웅덩이 확인
    		if (pool[1] <= lastBoardIndex) {
    			continue;
    		}
    		
    		// 널빤지로 덮이지 않은 물웅덩이면 덮자!
    		lastBoardIndex = Math.max(lastBoardIndex, pool[0] - 1);
    		
    		int curBoardCount = (pool[1] - lastBoardIndex) / L;
    		if ((pool[1] - lastBoardIndex) % L != 0) {
    			curBoardCount++;
    		}
    		
    		lastBoardIndex += L * curBoardCount;
    		boardCount += curBoardCount;
    	}
    	
    	System.out.println(boardCount);
    }
}
