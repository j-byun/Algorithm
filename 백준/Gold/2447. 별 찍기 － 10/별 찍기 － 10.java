import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	// 재귀
	// 2447 별찍기 - 10
	// 골드5

	// 3의 k제곱 값을 반환하는 square 함수
	public static int square(int k) {
		// 3의 0제곱은 1
		if (k == 0)
			return 1;

		// 이전 값에 *3해서 반환
		return 3 * square(k - 1);
	}

	// map에 별찍는 함수
	// [3의 k제곱][3의 k제곱]사이즈의 map과 k를 입력받음
	public static void star(String[][] map, int k) {

		// k가 1일 때 3*3 사이즈의 map에 대해
		// 1,1만 공백이고 나머지 다 별찍기
		if (k == 1) {
			for (int row = 0; row < 3; row++) {
				for (int col = 0; col < 3; col++) {
					if (row == 1 && col == 1) {
						map[row][col] = " ";
					} else {
						map[row][col] = "*";
					}
				}
			}
			return;
		}
		
		// k != 1 일 때
		// k-1번째의 map 그대로 넣고
		star(map, k - 1);		
		
		// 빈 칸도 채우기
		for (int row = 0; row < square(k); row++) {
			for (int col = 0; col < square(k); col++) {
				// 배열 가운데 square(k - 1)*square(k - 1) 사이즈의 공백 넣기
				if (row >= square(k - 1) && row < 2 * square(k - 1)
						&& col >= square(k - 1) && col < 2 * square(k - 1)) {
					map[row][col] = " ";
				} else {
					// 그 외의 구역에 대해서는 
					// row와 col index를 n%3해서 star(map, k - 1)의 값 붙여넣기
					map[row][col] = map[row % square(k - 1)][col % square(k - 1)];
				}
			}
		}
		

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// 입력값 n
		int n = Integer.parseInt(br.readLine());
		br.close();

		// n*n 사이즈의 map 만들기
		String[][] map = new String[n][n];

//        System.out.println(square(n));

		int k = 0;

		// n이 3의 k제곱일 때 k값 구하기
		for (k = 0;; k++) {
			if (square(k) == n) {
				break;
			}
		}

//        System.out.println(k);

		// map에 별 찍기
		star(map, k);
		
//		System.out.println(Arrays.deepToString(map));

		// 별 찍힌 map 출력
		for (int row = 0; row < n; row++) {
			for (int col = 0; col < n; col++) {
				bw.write(map[row][col]);
			}
			bw.write("\n");
		}
		bw.flush();
		bw.close();

	}
}
