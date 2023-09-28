import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 1202 보석 도둑 골드2
 *
 * 문제
 * N개의 보석
 * 각 보석은 무게 M과 가격 V를 가지고 있다.
 * K개의 가방을 가지고 있고, 각 가방에 담을 수 있는 최대 무게는 C이다.
 * 가방에는 최대 한 개의 보석만 넣을 수 있다.
 * 훔칠 수 있는 보석의 최대 가격을 구하자.
 *
 * 조건
 * 1 ≤ N, K ≤ 300,000
 * 0 ≤ Mi, Vi ≤ 1,000,000
 * 1 ≤ Ci ≤ 100,000,000
 *
 * 풀이
 * 1. 보석의 최대 가격은 long형으로 선언하자.
 * 2. 보석과 가방을 무게 오름차순으로 정렬하자.
 * 3. 가방을 차례대로 확인하며 현재 가방에 담을 수 있는 보석들을 우선순위 큐에 담고,
 * 3-1. 그 중 가격이 가장 비싼 보석 하나를 가방에 담자.
 */

public class Main {
	
	private static class Jewel {
		int weight, price;
		public Jewel(int weight, int price) {
			this.weight = weight;
			this.price = price;
		}
	}
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int N = Integer.parseInt(st.nextToken()); // 보석의 개수
    	int K = Integer.parseInt(st.nextToken()); // 가방의 개수
    	Jewel[] jewels = new Jewel[N]; // 보석
    	int[] bags = new int[K]; // 가방
    	
    	// 보석 N개 입력받기
    	for (int idx = 0; idx < N; idx++) {
    		st = new StringTokenizer(br.readLine());
    		jewels[idx] = new Jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
    	}
    	
    	Arrays.sort(jewels, new Comparator<Jewel>() {

			@Override
			public int compare(Jewel o1, Jewel o2) {
				return o1.weight - o2.weight;
			}
    		
    	}); // 보석 무게 순서대로 정렬
    	
    	// K개의 가방 무게 입력받기
    	for (int idx = 0; idx < K; idx++) {
    		st = new StringTokenizer(br.readLine());
    		bags[idx] = Integer.parseInt(st.nextToken());
    	}
    	
    	Arrays.sort(bags); // 가방 무게 순서대로 정렬

    	PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
    	long answer = 0L;
    	int jewelIdx = 0;
    	
    	// 모든 가방에 대해 확인하기
    	for (int bagIdx = 0; bagIdx < K; bagIdx++) {
    		while (jewelIdx < N && jewels[jewelIdx].weight <= bags[bagIdx]) {
    			pq.add(jewels[jewelIdx++].price);
    		}
    		
    		if (pq.isEmpty()) continue;
    		
    		answer += pq.poll();
    	}
    	
    	System.out.println(answer);
    }
}
