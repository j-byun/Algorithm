import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * @author jihye.byun
 * BOJ 9465 스티커 실버1 DP
 *
 * 문제
 * 2행 n열로 배치된 스티커들 중, 변을 공유하지 않고 점수의 합이 최대가 되는 스티커 집합을 구하자.
 *
 * 조건
 * n (1 ≤ n ≤ 100,000)
 * 점수는 0보다 크거나 같고, 100보다 작거나 같은 정수이다. 
 *
 * 풀이
 * 1. DP 배열을 만들자.
 * 2. 2행짜리이니 같은 열의 두 스티커 중 하나만 사용 가능하고,
 * 2-1. 해당 스티커 기준으로 앞뒤 열의 같은 행이 아닌 스티커를 사용할 수 있다.
 * 2-2. 바로 앞뒤 열의 스티커가 아닌, 2칸 떨어진 칸의 다른 행 스티커를 사용할 수도 있다.
 */

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	
    	int T = Integer.parseInt(st.nextToken()); // 테스트케이스 개수
    	for (int tc = 1; tc <= T; tc++) {
    		st = new StringTokenizer(br.readLine());
    		int n = Integer.parseInt(st.nextToken()); // 행의 개수
    		int[][] score = new int[2][n]; // 각 스티커의 점수를 저장할 배열 공간
    		
    		// 스티커 점수 입력받기
    		for (int row = 0; row < 2; row++) {
    			st = new StringTokenizer(br.readLine());
    			for (int col = 0; col < n; col++) {
    				score[row][col] = Integer.parseInt(st.nextToken());
    			}
    		}
    		
    		// dp배열을 따로 만들지 말고 score배열을 재사용하자
    		// col = 1인 경우는 따로 계산해주자
    		if (n > 1) {
    			score[0][1] += score[1][0];
    			score[1][1] += score[0][0];
    		}
    		
    		for (int col = 2; col < n; col++) {
    			for (int row = 0; row < 2; row++) {
    				// 현재 row가 0이면 col-1 칸의 row 1 스티커를 사용할 수 있고,
    				// 현재 row가 1이면 col-1 칸의 row 0 스티커를 사용할 수 있다.
    				
    				// score[다른row][col-1]의 값과 score[다른row][col-2] 값 중 큰 값을 선택하자
    				score[row][col] += Math.max(score[(row + 1) % 2][col - 1], score[(row + 1) % 2][col - 2]);
    			}
    		}
    		
    		// col을 증가시키며 차례대로 점수를 더해줬으니,
    		// 최종적으로 n-1열의 두 값 중 큰 값을 출력하자.
    		bw.write(Math.max(score[0][n - 1], score[1][n - 1]) + "\n");
    	}
    	bw.flush();
    }
}
