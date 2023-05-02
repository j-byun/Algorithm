import java.util.Scanner;

/**
 * @author jihye.byun
 * BOJ 2630 색종이 만들기 실버2 분할정복
 * 
 * 문제
 * 정사각형칸들로 이루어진 정사각형 모양의 종이
 * 각 정사각형 칸들은 하얀색or파란색으로 칠해져 있다.
 * 주어진 종이를 일정한 규칙에 따라 잘라서 다양한 크기를 가진 정사각형 모양의
 * 하얀색 또는 파란색 색종이를 만들자.
 * 
 * 전체 종이의 크기가 N*N일때...
 * 1. 전체 종이가 모두 같은 색으로 칠해져 있지 않으면 가로와 세로의 중간 부분을 잘라서
 * 		네 개의 N/2 × N/2색종이로 나눈다.
 * 2. 나누어진 종이에 대해서도 모두 같은 색으로 칠해져 있지 않으면 1번을 반복한다.
 * 3. 이 과정을 잘라진 종이가 모두 하얀색 또는 모두 파란색으로 칠해져 있거나,
 * 		하나의 정사각형 칸이 되어 더 이상 자를 수 없을 때까지 반복한다.
 * 
 * 잘라진 하얀색 색종이와 파란색 색종이의 개수를 구하자.
 * 
 * 조건
 * 종이의 크기 N (N=2^k), k는 1이상 7이하의 자연수
 * => N은 2, 4, 8, 16, 32, 64, 128 중 하나
 * 하얀색으로 칠해진 칸은 0, 파란색으로 칠해진 칸은 1로 주어진다.
 * 
 * 풀이
 * 1. 색종이를 가로세로 절반씩 분할해서 0의 개수와 1의 개수를 세어주자.
 * 1-1. 0의 개수가 1이상이고 1의 개수가 1이상이면 더 분할시키자.
 * 2. 모든 칸을 다 세었는데 둘 중 하나의 개수만 카운트 됐다면?
 * 2-1. 더 분할하지 말고 지금 세어준 색의 개수를 +1 하고 종료하자.
 * 3. 분할의 마지노선은? start + 1 == end 일 때
 */

public class Main {
	
	static int N, white, blue;
	static int[][] paper;
	
	private static void checkColor(int rowStart, int rowEnd, int colStart, int colEnd) {
		// 한 변이 start ~ end-1 까지인 색종이가 모두 하나의 색으로 이루어져 있는지 확인하는 method
		
		// 세어줄 하얀색0 과 파란색1의 개수를 저장할 변수를 만들자
		int curWhite = 0;
		int curBlue = 0;
		
		for (int row = rowStart; row < rowEnd; row++) {
			for (int col = colStart; col < colEnd; col++) {
				// 현재 칸이 0이면 하얀색 개수를 세어주고
				if (paper[row][col] == 0)
					curWhite++;
				// 현재 칸이 1이면 파란색 개수를 세어주자
				else
					curBlue++;
				
				// 현재 색종이 조각에 하얀색과 파란색이 섞여있다면?
				// 어차피 이 조각은 글렀다!
				// 4등분하고 더 확인할 필요 없으니 반환시켜버리자
				if (curWhite > 0 && curBlue > 0) {
					divide(rowStart, rowEnd, colStart, colEnd);
					return;
				}
			}
		}
		
		// 색종이의 모든 칸을 확인했는데 하나의 색으로만 이루어져 있다면?
		// 이 색종이는 더 이상 쪼개지 않아도 된다!
		// 세어준 색을 1 카운트해주자
		if (curWhite > 0)
			white++;
		else
			blue++;
		
		return;
	}
	
	private static void divide(int rowStart, int rowEnd, int colStart, int colEnd) {
		// 현재 색종이 조각이 모두 같은 색으로 이루어지지 않은 경우 색종이를 네 개의 조각으로 분리시키는 method
		
		// 기저조건
		// start + 1 == end 라면? 더 이상 쪼갤 수 없다
		if (rowStart + 1 == rowEnd && colStart + 1 == colEnd) {
			// 한 칸의 종이 색을 세어주고 반환시키자
			if (paper[rowStart][colStart] == 0)
				white++;
			else
				blue++;
			return;
		}
		
		// 더 쪼갤 수 있는 크기면 1/4로 쪼개서 각각의 종이 조각을 확인하자
		int rowMid = (rowStart + rowEnd) / 2;
		int colMid = (colStart + colEnd) / 2;
		
		checkColor(rowStart, rowMid, colStart, colMid);
		checkColor(rowStart, rowMid, colMid, colEnd);
		checkColor(rowMid, rowEnd, colStart, colMid);
		checkColor(rowMid, rowEnd, colMid, colEnd);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt(); // 색종이 크기 입력받기
		
		paper = new int[N][N]; // 하얀색과 파란색으로 이루어진 전체 색종이 공간
		
		// 색종이 정보 입력받기
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				paper[row][col] = sc.nextInt();
			}
		}
		
		// 색종이를 분할해서 확인해보기 전에 일단 초기 하얀색, 파란색 조각의 개수 0으로 초기화
		white = blue = 0;
		
		// 현재 색종이가 모두 같은 색인지 확인해보자
		checkColor(0, N, 0, N);
		
		// 조건을 만족할 때 까지 분할해서 확인한 후의 하얀색 조각 개수, 파란색 조각 개수를 출력하자
		System.out.println(white);
		System.out.println(blue);
	}

}
