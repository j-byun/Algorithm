import java.util.Scanner;

// SWEA
// 5356 의석이의 세로로 말해요
// D3

// 문제
// A~Z, a~z, 0~9
// 글자를 수평 (가로방향으로) 일렬로 나열해서 단어를 만든다 *5줄
// 예)
// A A B C D D
// a f z z
// 0 9 1 2 1
// a 8 E W g 6
// P 5 h 3 k x

// 글자를 세로로 읽자
// 세로로 읽을 때 해당 칸에 글자가 없으면 그 다음 글자를 읽는다
// 예) 세로5번째 줄 : D1gk

// 세로로 읽은 순서대로 글자들을 공백 없이 한 줄로 출력하시오

// 조건
// 다섯 개의 단어들의 글자 개수는 서로 다를 수 있다
// 각 줄의 문자열 길이 1<= <=15

// 풀이
// 5*15 2차원 배열 만들어서 집어넣고
// row col 뒤집어서 출력
// if (map[row][col] != null) 일 때만 출력
// => char배열의 초기값은 '0'이니까 초기값이 null인 string배열로 선언해주기

public class Solution {


	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		// 테스트케이스 수 t 입력받기
		int t = sc.nextInt();
		
		// 단어를 저장할 2차원 배열 map만들기
		String[][] map;
		
		// 테스트케이스 개수만큼 반복 실행
		for (int testCase = 1; testCase <= t; testCase++) {
			// 출력양식
			sb.append("#").append(testCase).append(" ");
			
			// map을 default null값인 5*15사이즈 크기로 지정
			 map = new String[5][15];
			
			// 단어 입력받기
			for (int row = 0; row < 5; row++) {
				// 단어 한 줄 읽어오기
				String read = sc.next();
				
				for (int col = 0; col < 15; col++) {
					// 단어 길이 만큼
					if (col < read.length()) {
						// 단어의 col번째 char를 string으로 변환해서 map배열에 저장하기
						map[row][col] = Character.toString(read.charAt(col));
					}
				}
			}
			
			// row col 순서 바꿔서 출력
			// 배열의 해당 칸이 null이 아닐때만 출력
			for (int col = 0; col < 15; col++) {
				for (int row = 0; row < 5; row++) {
					if (map[row][col] != null) {
						sb.append(map[row][col]);
					}
				}
			}
			
			// 테스트케이스 한 개 출력 끝나면 개행하기
			sb.append("\n");
			
		}
		
		// 출력
		System.out.println(sb);
	}

}


