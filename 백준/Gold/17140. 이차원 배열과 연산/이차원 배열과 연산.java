import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @author jihye.byun
 * BOJ 17140 이차원 배열과 연산 골드4
 *
 * 문제
 * 3*3크기의 배열 A
 * 배열의 인덱스는 1부터 시작한다.
 * 1초가 지날때마다 배열에 연산이 적용된다.
 * -R연산: 배열 A의 모든 행에 대해서 정렬을 수행한다. 행의 개수 >= 열의 개수인 경우에 적용된다.
 * -C연산: 배열 A의 모든 열에 대해서 정렬을 수행한다. 행의개수 < 열의 개수인 경우에 적용된다.
 * 수의 등장 횟수가 커지는 순으로, 그러한 것이 여러가지면 수가 커지는 순으로 정렬한다.
 * 그 다음 배열 A에 정렬된 결과를 다시 넣어야 한다
 * 정렬된 결과를 배열에 넣을 때는, 수와 등장 횟수를 모두 넣으며, 순서는 수가 먼저이다.
 * 
 * 행 또는 열의 크기가 100을 넘어가는 경우에는 처음 100개를 제외한 나머지는 버린다.
 * 배열 A에 들어있는 수와 r, c, k가 주어졌을 때, A[r][c]에 들어있는 값이 k가 되기 위한 최소 시간을 구해보자.
 *
 * 조건
 * (1 ≤ r, c, k ≤ 100)
 * 배열 A에 들어있는 수는 100보다 작거나 같은 자연수이다.
 * 
 * 100초가 지나도 A[r][c] = k가 되지 않으면 -1을 출력한다.
 *
 * 풀이
 * 1. 101*101 크기의 배열을 만들고, 1~100번 까지의 인덱스를 사용하자.
 * 2. 현재 최대 row, col 갯수를 나타내는 포인터를 만들자.
 * 3. 수의 등장 횟수는 map에 넣어서 카운트하자.
 * 4. map에 저장된 숫자들을 우선순위 큐에 옮겨서 조건대로 정렬하자.
 */

public class Main {
	
	public static class Pair implements Comparable<Pair>{
		int num, count;
		public Pair(int num, int count) {
			this.num = num;
			this.count = count;
		}
		@Override
		public int compareTo(Pair o) {
			// TODO Auto-generated method stub
			if (this.count == o.count)
				return this.num - o.num;
			return this.count - o.count;
		}
	}
	
	private static int[][] arr;
	private static int rowSize, colSize;
	private static int r, c, k;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	// 초기화
    	arr = new int[101][101];
    	rowSize = 0;
    	colSize = 0;
    	
    	// 문제의 조건값 r, c, k 입력받기 (arr[r][c] = k)
    	r = Integer.parseInt(st.nextToken());
    	c = Integer.parseInt(st.nextToken());
    	k = Integer.parseInt(st.nextToken());
    	
    	// 3*3 행렬의 값 입력받기
    	for (int row = 1; row <= 3; row++) {
    		st = new StringTokenizer(br.readLine());
    		for (int col = 1; col <= 3; col++) {
    			arr[row][col] = Integer.parseInt(st.nextToken());
    		}
    	}
    	rowSize = 3;
    	colSize = 3;
    	
    	// 100초 동안 진행
    	int result = -1;
    	for (int time = 0; time <= 100; time++) {
    		// arr[r][c] = k 를 만족하는지 확인
    		if (arr[r][c] == k) {
    			// 조건 만족 시 현재 시간 저장 후 탈출
    			result = time;
    			break;
    		}
    		
    		// 조건 불만족 시 연산 진행
    		// 행 >= 열 인 경우 R연산
    		if (rowSize >= colSize)
    			operationR();
    		else
    			// 행 < 열 인 경우 C연산
    			operationC();
    	}
    	
    	// 결과 출력
    	System.out.println(result);
    }
    
    private static void operationR() {
    	// R연산
    	// 배열의 모든 행에 대해서 정렬을 수행한다.
    	int curMaxColSize = colSize;
    	
    	for (int row = 1; row <= rowSize; row++) {
    		// pair를 저장할 map
    		HashMap<Integer, Integer> map = new HashMap<>();
    		for (int col = 1; col <= curMaxColSize; col++) {
    			if (arr[row][col] == 0)
    				continue;
    			
    			// 아직 저장되지 않은 수는 카운트 1로 저장
    			if (map.get(arr[row][col]) == null)
    				map.put(arr[row][col], 1);
    			// 이미 세었던 수는 카운트 +1
    			else
    				map.put(arr[row][col], map.get(arr[row][col]) + 1);
    			
    			// 숫자 센 후에는 배열의 값을 0으로 초기화
    			arr[row][col] = 0;
    		}
    		
    		// 한 행의 숫자 갯수를 다 세었다면 정렬해서 차례대로 다시 저장
    		// 우선순위 조건대로 정렬하기 위해 우선순위 큐 만들기
    		PriorityQueue<Pair> pq = new PriorityQueue<>();
    		for (Integer key : map.keySet()) {
    			pq.add(new Pair(key, map.get(key)));
    		}
    		
    		// 이제 다시 배열에 값 넣어주기
    		int curColSize = pq.size() * 2;
    		// colSize max값 업데이트
    		colSize = Math.min(Math.max(colSize, curColSize), 100);
    		
    		for (int col = 1; col <= curColSize && col <= 100; col += 2) {
    			Pair cur = pq.poll();
    			arr[row][col] = cur.num;
    			arr[row][col + 1] = cur.count;
    		}
    	}
    }
    	
    private static void operationC() {
    	// C연산
    	// 배열의 모든 열에 대해서 정렬을 수행한다.
    	int curMaxRowSize = rowSize;
    		
    	for (int col = 1; col <= colSize; col++) {
    		// pair를 저장할 map
   			HashMap<Integer, Integer> map = new HashMap<>();
   			for (int row = 1; row <= curMaxRowSize; row++) {
   				if (arr[row][col] == 0)
   					continue;
    				
    			// 아직 저장되지 않은 수는 카운트 1로 저장
    			if (map.get(arr[row][col]) == null)
    				map.put(arr[row][col], 1);
    			// 이미 세었던 수는 카운트 +1
   				else
   					map.put(arr[row][col], map.get(arr[row][col]) + 1);
    				
   				// 숫자 센 후에는 배열의 값을 0으로 초기화
    			arr[row][col] = 0;
    		}
    			
    		// 한 열의 숫자 갯수를 다 세었다면 정렬해서 차례대로 다시 저장
    		// 우선순위 조건대로 정렬하기 위해 우선순위 큐 만들기
   			PriorityQueue<Pair> pq = new PriorityQueue<>();
   			for (Integer key : map.keySet()) {
   				pq.add(new Pair(key, map.get(key)));
   			}
    			
    		// 이제 다시 배열에 값 넣어주기
    		int curRowSize = pq.size() * 2;
   			// rowSize max값 업데이트
   			rowSize = Math.min(Math.max(rowSize, curRowSize), 100);
    			
   			for (int row = 1; row <= curRowSize && row <= 100; row += 2) {
    			Pair cur = pq.poll();
    			arr[row][col] = cur.num;
   				arr[row + 1][col] = cur.count;
   			}
   		}
   	}
    		
}
