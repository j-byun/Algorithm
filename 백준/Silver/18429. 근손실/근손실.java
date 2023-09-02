import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author jihye.byun
 * BOJ 18429 근손실 실버3
 *
 * 문제
 * 현재 3대 운동 중량 500
 * 하루가 지날 때마다 중량이 K만큼 감소
 * 운동을 하지 않고 가만히 있다면 매일매일 중량이 감소
 * 서로다른 N개의 운동 키트 중 하루에 1개씩의 키드를 사용해서 중량 증가
 * 중량 증가량이 같은 운동 키트가 있을 수 있으나 서로 다른 운동 키트로 간주
 * 각각의 운동 키트는 N일 동안 한 번씩만 사용 가능
 * N일 동안 N개의 운동 키트를 사용하는 모든 경우 중에서, 운동 기간동안 항상 중량이 500 이상이 되도록 하는 경우의 수를 출력한다.
 *
 * 조건
 * 1 ≤ N ≤ 8
 * 1 ≤ K ≤ 50
 * 각 운동 키트의 중량 증가량 A (1 ≤ A ≤ 50)
 *
 * 풀이
 * 1. 브루트포스 + 백트래킹
 */

public class Main {
	
	private static final int minWeight = 500;
	
	private static int N, K, count;
	private static int[] kit;
	private static boolean[] visit;
	
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	N = Integer.parseInt(st.nextToken());
    	K = Integer.parseInt(st.nextToken());
    	count = 0;
    	
    	kit = new int[N];
    	visit = new boolean[N];
    	
    	st = new StringTokenizer(br.readLine());
    	for (int idx = 0; idx < N; idx++) {
    		kit[idx] = Integer.parseInt(st.nextToken());
    	}
    	
    	workOut(0, 500);
    	
    	System.out.println(count);
    }
    
    private static void workOut(int depth, int weight) {
    	
    	if (weight < minWeight)
    		return;
    	
    	if (depth == N) {
    		count++;
    		return;
    	}
    	
    	for (int idx = 0; idx < N; idx++) {
    		if (visit[idx]) continue;
    		visit[idx] = true;
    		workOut(depth + 1, weight - K + kit[idx]);
    		visit[idx] = false;
    	}
    }
    
}
