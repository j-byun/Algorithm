import java.util.Scanner;

// 10163 색종이
// 브론즈1

// 문제
// 평면에 색종이 놓기
// 색종이는 수직, 수평으로 놓여진다
// 나중에 놓인 색종이가 이전 색종이를 덮어버리면 이전 색종이는 완전히 가려서 보이지 않게 된다

// 조건
// 색종이의 장수를 나타내는 정수 N (1 ≤ N ≤ 100)

// 풀이
// 사각형이 입력되면 해당 칸을 해당 사각형 번호로 덮어씌우기
// 입력 다 받고 다시 map 돌면서 각 사각형 번호로 채워진 칸의 개수 카운팅하기


public class Main {
	static int[][] map;
	static int confettiCnt;
	static int[] confettiArea;
	
	public static void attachConfetti(int startRow, int startCol, int dr, int dc, int confettiNum) { // map에 색종이 면적만큼 색종이 번호 입력하는 method
		
		for (int row = startRow; row < startRow + dr; row++) {
			for (int col = startCol; col < startCol + dc; col++) {
				
				map[row][col] = confettiNum;
			}
		}
		
	}
	
	public static void countingConfetti() { // map에 붙어있는 각 번호의 색종이 개수를 출력하는 method
		
		for (int row = 0; row < 1001; row++) { // map을 확인하면서
			for (int col = 0; col < 1001; col++) {
				
				if (map[row][col] == 0) // 현재 칸에 붙은 색종이가 없으면 다음 칸 확인
					continue;
				
				for (int idx = 1; idx <= confettiCnt; idx++) {
					if (map[row][col] == idx) { // 현재 칸에 제일 마지막으로 붙은 색종이 번호를 찾아서
						confettiArea[idx]++; // 해당 색종이 번호 면적을 +1 해준다
						break;
					}
				}
			}
		}
		
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		confettiCnt = sc.nextInt(); // 붙일 색종이 개수 입력받기
		
		confettiArea = new int[confettiCnt + 1]; // 각 색종이의 면적을 저장할 배열 초기화
		
		map = new int[1001][1001]; // 1001*1001크기의 map 만들기
										// 문제의 색종이를 오른쪽으로 90도 돌려서 생각하자
		
		for (int cnt = 1; cnt <= confettiCnt; cnt++) { // 붙일 색종이 수 만큼 반복해서 붙이기
			int startRow = sc.nextInt(); // 색종이 시작 지점 좌표 입력받기
			int startCol = sc.nextInt();
			
			int dr = sc.nextInt(); // 색종이 면적 입력 받기
			int dc = sc.nextInt();
			
			attachConfetti(startRow, startCol, dr, dc, cnt); // map에 색종이 붙이기
		}
		
		countingConfetti(); // 색종이를 다 붙였으면 이제 제일 위에 보이는 색종이의 번호를 카운트하자
		
		for (int idx = 1; idx <= confettiCnt; idx++) { // 색종이 번호 카운팅이 끝났으니 출력하자
			sb.append(confettiArea[idx]).append("\n");
		}
		
		
		System.out.println(sb);
		
	}

}

