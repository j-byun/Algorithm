import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 22251 빌런 호석 골드5
 *
 * 문제
 *
 * 조건
 *
 * 풀이
 * 1. 0~9까지의 숫자가 표시되는 방법을 boolean[7] 배열에 저장해 map에 담아 관리
 * 2. 10*10짜리 표를 만들어서 i -> j 가 될 때 반전시켜야 되는 숫자를 확인해서 채워넣기
 * 3. dfs탐색 -> maxDepth까지 갔는데 바뀐 갯수가 0이면 원래 수니까 pass (최소 1개, 최대 P개 반전)
 * 3-1. 1층 미만, N층 초과면 건물에 해당되지 않으니까 pass
 */

public class Main {
	
	static int N, K, P, X, count;
	static int[][] display;
	static int[] original, answer;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	N = Integer.parseInt(st.nextToken()); // 최대 층
    	K = Integer.parseInt(st.nextToken()); // 디스플레이에 보여지는 자리 수
    	P = Integer.parseInt(st.nextToken()); // 최대 반전시킬 갯수
    	X = Integer.parseInt(st.nextToken()); // 현재 엘레베이터가 멈춰있는 층
    	
    	display = new int[10][10]; // 각 숫자들의 반전 갯수를 저장할 배열
    	
    	fillDisplay(); // 각 숫자들의 반전 갯수 구해서 배열 채우기
    	
    	original = new int[K];
    	answer = new int[K];
    	
    	for (int cnt = K - 1; cnt >= 0; cnt--) {
    		original[cnt] = X % 10;
    		X /= 10;
    	}
    	
    	count = 0;
    	
    	changeFloor(0, 0);
    	
    	System.out.println(count);
    }
    
    static void changeFloor(int depth, int sum) {
    	
    	if (depth == K) {
    		if (sum == 0) return;
    		
    		StringBuilder sb = new StringBuilder();
    		
    		for (int idx = 0; idx < K; idx++) {
    			sb.append(answer[idx]);
    		}
    		
    		int floor = Integer.parseInt(sb.toString());
    		
    		if (floor < 1 || floor > N) return;
    		
    		count++;
    		return;
    	}
    	
    	for (int idx = 0; idx <= 9; idx++) {
    		if (sum + display[original[depth]][idx] > P) continue;
    		answer[depth] = idx;
    		changeFloor(depth + 1, sum + display[original[depth]][idx]);
    	}
    }
    
    static void fillDisplay() {
    	Map<Integer, String> map = new HashMap<>();
    	map.put(0, "1111110");
    	map.put(1, "0110000");
    	map.put(2, "1101101");
    	map.put(3, "1111001");
    	map.put(4, "0110011");
    	map.put(5, "1011011");
    	map.put(6, "1011111");
    	map.put(7, "1110000");
    	map.put(8, "1111111");
    	map.put(9, "1111011");
    	
    	for (int i = 0; i <= 9; i++) {
    		for (int j = i + 1; j <= 9; j++) {
    			String first = map.get(i);
    			String second = map.get(j);
    			int diff = 0;
    			
    			for (int idx = 0; idx < 7; idx++) {
    				if (first.charAt(idx) != second.charAt(idx))
    					diff++;
    			}
    			
    			display[i][j] = display[j][i] = diff;
    		}
    	}
    }
}
