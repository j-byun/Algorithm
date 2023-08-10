import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author jihye.byun
 * BOJ 12865 평범한 배낭 골드5
 *
 * 문제
 * N개의 물건
 * 각 물건은 무게 W와 가치 V를 가진다.
 * 해당 물건을 배낭에 넣어서 가면 준서가 V만큼 즐길 수 있다.
 * 배낭의 무게는 최대 K
 * 배낭에 넣을 수 있는 물건들의 가치의 최댓값을 구하자.
 *
 * 조건
 * 물품의 수 N(1 ≤ N ≤ 100)
 * 준서가 버틸 수 있는 무게 K(1 ≤ K ≤ 100,000)
 * 각 물건의 무게 W(1 ≤ W ≤ 100,000)
 * 각 물건의 가치 V(0 ≤ V ≤ 1,000)
 *
 * 풀이
 */

public class Main {
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int N = Integer.parseInt(st.nextToken()); // 물품의 수
    	int K = Integer.parseInt(st.nextToken()); // 버틸 수 있는 무게
    	
    	int[] W = new int[N + 1]; // 각 물건의 무게를 저장할 배열
    	int[] V = new int[N + 1]; // 각 물건의 가치를 저장할 배열
    	int[][] dp = new int[N + 1][K + 1];
    	
    	for (int idx = 1; idx <= N; idx++) {
    		st = new StringTokenizer(br.readLine());
    		W[idx] = Integer.parseInt(st.nextToken());
    		V[idx] = Integer.parseInt(st.nextToken());
    	}
    	
    	for (int item = 1; item <= N; item++) {
    		for (int weight = 1; weight <= K; weight++) {
    			if (W[item] > weight)
    				dp[item][weight] = dp[item - 1][weight];
    			else
    				dp[item][weight] = Math.max(dp[item - 1][weight], dp[item - 1][weight - W[item]] + V[item]);
    		}
    	}
    	
    	System.out.println(dp[N][K]);
    }
}
