import java.util.Scanner;

/**
 * @author jihye.byun
 * BOJ 1992 쿼드트리 실버1
 * 
 * 문제
 * 쿼드 트리(Quad Tree) : 흑백 영상을 압축하여 표현하는 데이터 구조
 * 흰 점 : 0 / 검은 점 : 1
 * 2차원 배열(영상)이 모두 0이면 압출 결과 0, 모두 1이면 압축 결과 1
 * 0과 1이 섞여있으면 왼쪽 위, 오른쪽 위, 왼쪽 아래, 오른쪽 아래 4개의 영상으로 나누어 압축
 * 압축한 결과를 차례대로 괄호 안에 묶어서 표현
 * N ×N 크기의 영상이 주어질 때, 이 영상을 압축한 결과를 출력하자.
 * 
 * 조건
 * N 은 언제나 2의 제곱수 (1 ≤ N ≤ 64)
 * 
 * 풀이
 * 1. 영상을 4등분해서 0과 1의 개수를 세어주자.
 * 1-1. 현재 조각에 0과 1이 모두 존재한다면 다시 4등분 하자.
 * 2. 4등분으로 분할을 진행할 때 처음과 마지막에 괄호()를 추가해주자.
 */

public class Main {
	
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[][] video;
	
	private static void checkColor(int startRow, int endRow, int startCol, int endCol) {
		// startRow~endRow * startCol~endCol 까지의 영상 조각이 0 또는 1로만 이루어졌는지 확인하고,
		// 아니라면 4개의 영상 조각으로 분할하자
		
		// 시작점의 색을 비교 대상으로 지정하자
		int color = video[startRow][startCol];
		
		// 현재 비디오 조각의 각 칸을 확인하며 시작점의 색과 다른 조각이 발견되면 4분할하자
		for (int row = startRow; row < endRow; row++) {
			for (int col = startCol; col < endCol; col++) {
				// 0과 1이 섞여있는 경우에는? 4분할하자!
				if (video[row][col] != color) {
					sb.append('(');
					checkColor(startRow, (startRow + endRow) / 2, startCol, (startCol + endCol) / 2);
					checkColor(startRow, (startRow + endRow) / 2, (startCol + endCol) / 2, endCol);
					checkColor((startRow + endRow) / 2, endRow, startCol, (startCol + endCol) / 2);
					checkColor((startRow + endRow) / 2, endRow, (startCol + endCol) / 2, endCol);
					sb.append(')');
					return;
				}
			}
		}
		
		// 시작점 색으로만 이루어진 조각이라면?
		// 현재 조각의 색을 출력 문자열에 더해주자
		sb.append(color);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt(); // 영상의 한 변의 길이
		
		video = new int[N][N]; // 영상 정보를 저장할 공간
		
		// 영상 정보 입력받기
		for (int row = 0; row < N; row++) {
			String line = sc.next();
			for (int col = 0; col < N; col++) {
				video[row][col] = line.charAt(col) - '0';
			}
		}
		
		// 영상이 0 또는 1로만 이루어졌는지 확인하고, 아니라면 4등분하자
		checkColor(0, N, 0, N);

		// 분할해서 모든 영상 조각을 확인했으니, 압축 결과물을 출력하자
		System.out.println(sb);
	}
}
