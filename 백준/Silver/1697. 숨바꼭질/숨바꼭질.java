import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author jihye.byun
 * BOJ 1697 숨바꼭질 실버1
 *
 * 문제
 * 수빈이는 현재 점 N에 있고, 동생은 점 K에 있다.
 * 수빈이는 걷거나 순간이동을 할 수 있다.
 * 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다.
 * 순간이동을 하는 경우에는 1초 후에 2*X로 이동하게 된다.
 * 수빈이와 동생의 위치가 주어졌을 때, 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 구하자.
 *
 * 조건
 * N(0 ≤ N ≤ 100,000)
 * K(0 ≤ K ≤ 100,000)
 * N과 K는 정수이다.
 *
 * 풀이
 * 1. BFS 탐색을 진행한다.
 * 2. 순간이동, +1, -1 순으로 탐색하고, 큐에서 뽑은 값이 지금까지 찾은 최소시간보다 크면 확인하지 말고 다음 값을 뽑자.
 */

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken()); // 수빈이의 현재 위치
        int K = Integer.parseInt(st.nextToken()); // 동생의 현재 위치
        
        boolean[] visit = new boolean[200_001];
        
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {N, 0});
        
        while (!queue.isEmpty()) {
        	int[] cur = queue.poll();
        	
        	if (visit[cur[0]]) continue;
        	visit[cur[0]] = true;
        	
        	if (cur[0] == K) {
        		System.out.println(cur[1]);
        		break;
        	}
        	
        	if (2 * cur[0] <= 200_000)
        		queue.add(new int[] {2 * cur[0], cur[1] + 1});
        	if (cur[0] + 1 <= 200_000)
        		queue.add(new int[] {cur[0] + 1, cur[1] + 1});
        	if (cur[0] - 1 >= 0)
        		queue.add(new int[] {cur[0] - 1, cur[1] + 1});
        }
    }
}