import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 16566 카드 게임 플래티넘5
 *
 * 문제
 * 1. 1~N까지의 카드 중 M개의 카드
 * 2. 철수는 빨간색 M개의 카드를, 민수는 파란색 M개의 카드를 가진다.
 * 3. 철수와 민수는 고른 카드 중 1장을 뒤집어진 상태로 내고, 카드를 뒤집어 번호가 큰 사람이 이긴다.
 * 4. 3번 동작을 K번 해서 더 많이 이긴 사람이 최종적으로 승리한다.
 * 5. 한 번 낸 카드는 반드시 버려야 한다.
 * 
 * 철수는 마술사라서 본인이 낼 카드를 마음대로 조작할 수 있다.
 * 득, 버린 카드를 다시 들고 오거나 없는 카드를 낼 수 있다.
 * 민수는 심리학자라서 철수가 낼 카드를 알 수 있다.
 * 민수는 철수가 낼 카드보다 큰 카드가 있다면 그 카드 중 가장 작은 카드를 내기로 했다.
 * 
 * K번 동안 철수가 낼 카드가 입력으로 주어지면, 민수가 어떤 카드를 낼지 출력하라.
 * 단, 민수가 카드를 내지 못하는 경우는 없다.
 *
 * 조건
 * 전체 카드의 개수 N
 * 철수와 민수가 가진 카드의 개수 M
 * 카드 게임의 턴 수 K
 * 
 * 1 ≤ M ≤ N ≤ 4,000,000
 * 1 ≤ K ≤ min(M, 10,000)
 * 
 * 카드의 번호를 나타내는 M개의 자연수이고, 각각의 자연수들은 1이상이고 N이하이며 서로 다르다.
 *
 * 풀이
 * 1. 이분 탐색으로 철수가 낼 카드보다 큰 수 중 가장 작은 수를 출력하자.
 * 2. 1번 과정에서, 이미 낸 카드는 제외하자.
 * 3. boolean배열을 만들어서 이미 낸 카드를 제외하면 안 낸 카드를 찾을 때 까지 인덱스를 계속 조정해야하니
 * 	  ArrayList로 만들어서 낸 카드는 제외하자.
 * -> list에서 값을 제거하는 시간때문에 시간초과 발생
 * 4. 이분탐색으로 upper_bound를 찾고, 그 인덱스를 기준으로 uninon 집합을 find해서 부모 인덱스를 찾자.
 */

public class Main {
	
	static int N, M, K;
	static int[] cards, parent;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	StringBuilder sb = new StringBuilder();
    	
    	// 전체 카드 개수, 철수와 민수가 뽑을 카드의 개수, 카드 게임의 턴 수  입력받기
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	K = Integer.parseInt(st.nextToken());
    	
    	// 철수와 민수가 뽑은 카드를 저장할 배열 만들기
    	cards = new int[M];
    	
    	// 각 카드의 부모 카드 인덱스를 저장할 배열 만들기
    	parent = new int[M];
    	
    	st = new StringTokenizer(br.readLine());
    	for (int idx = 0; idx < M; idx++) {
    		// 철수와 민수가 뽑은 카드 번호 저장하기
    		cards[idx] = Integer.parseInt(st.nextToken());
    		// 부모 배열 초기화하기
    		parent[idx] = idx;
    	}
    	
    	// 철수와 민수가 뽑은 카드를 오름차순으로 정렬
    	Arrays.sort(cards);
    	
    	// K번의 카드 게임 진행하기
    	st = new StringTokenizer(br.readLine());
    	while (K-- > 0) {
    		// 이분 탐색으로 철수가 낼 카드보다 큰 수 중 가장 작은 수의 인덱스 찾기
    		int index = binarySearch(Integer.parseInt(st.nextToken()));
    		
    		// union집합을 find해서 인덱스의 부모 찾기
    		index = find(index);
    		sb.append(cards[index]).append("\n");
    		
    		// 이제 index는 사용했으니 index+1과 union해서 새로운 부모 인덱스 설정하기
    		if (index < M - 1)
    			union(index, find(index + 1));
    	}
    	
    	// 출력
    	System.out.println(sb.toString());
    }
    
    private static int binarySearch(int find) {
    	int start = 0;
    	int end = M - 1;
    	int mid;
    	
    	while (start <= end) {
    		mid = (start + end) / 2;
    		
    		if (cards[mid] <= find) {
    			start = mid + 1;
    		}
    		
    		if (cards[mid] > find) {
    			end = mid - 1;
    		}
    	}
    	
    	return start;
    }
    
    private static int find(int x) {
    	if (parent[x] == x) return x;
    	return parent[x] = find(parent[x]);
    }
    
    private static void union(int x, int y) {
    	parent[x] = y;
    }
}
