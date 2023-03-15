import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 810158 개미
// 실버4

// 문제
// 가로길이 w, 세로길이 h인 2차원 격자 공간
// 격자 안 좌표 p,q에 놓여진 개미는 오른쪽 위 45도 방향으로 일정한 속력으로 움직이기 시작
// 경계면에 부딪치면 같은 속력으로 반사되어 움직임 (90도 반사), 코너에 부딪치면 다시 원래의 좌표로 반사

// w*h 격자 공간에서 처음에 (p,q)좌표에서 출발하는 개미의 t시간 후 위채 (x,y)를 계산하시오

// 조건
// 개미는 절대 지치지 않고 같은 속력으로 이동한다
// 2 ≤ w,h(자연수) ≤ 40,000
// 0 < p < w, 0 < q < h
// 계산할 시간 t의 범위 1 ≤ t ≤ 200,000,000

// 풀이
// 격자 공간을 시계 방향으로 90도 돌려서 왼쪽 위가 0,0이고 오른쪽 아래가 w,h인 row, col map으로 생각하자
// 오른쪽 위 45도 방향 => row+1, col+1

// 1. 아래 경계에 부딪칠 때 (row > w)
// row+1 col+1 하다가 row > w되면 row-1 col+1
//							&& col > h되면 row-1 col-1
// row+1 col-1 하다가 row > w되면 row-1 col-1
//							&& col < 0되면 row-1 col+1

// 2. 오른쪽 경계에 부딪칠 때 (col > h)
// row+1 col+1 하다가 col > h되면 row+1 col-1
//							&& row > w되면 row-1 col-1
// row-1 col+1 하다가 col > h되면 row-1 col-1
//							&& row < 0되면 row+1 col-1

// 3. 왼쪽 경계에 부딪칠 때 (col < 0)
// row+1 col-1 하다가 col < 0되면 row+1 col+1
//							&& row > w되면 row-1 col+1
// row-1 col-1하다가 col < 0되면 row-1 col+1
//							&& row < 0되면 row+1 col+1

// 4. 위쪽 경계에 부딪칠 때 (row < 0)
// row-1 col-1 하다가 row < 0되면 row+1 col-1
//							&& col <0되면 row+1 col+1
// row-1 col+1 하다가 row <0되면 row+1 col+1
//							&& col > h되면 row+1 col-1

// => 진행하다가 경계를 만나면 진행하던 방향에 -1 곱해서 진행
// row 경계만 만나면 row에 -1곱하는 방향, col 경계도 만나면 둘 다 -1 곱하는 방향
// 초기 dr, dc = 1, row나 col의 경계를 만나면 dr = -dr || dc = -dc

// => 시간초과

// 수학 문제로 접근해서 풀자!
// 


public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine()); // 한 줄 읽어와서 띄어쓰기 기준으로 분리
		
		int rowSize = Integer.parseInt(st.nextToken()); // w 입력받기
		int colSize = Integer.parseInt(st.nextToken()); // h 입력받기
		
		st = new StringTokenizer(br.readLine());
		
		int row = Integer.parseInt(st.nextToken()); // 초기 위치 p 좌표값 입력받기
		int col = Integer.parseInt(st.nextToken()); // 초기 위치 q 좌표값 입력받기
		
		int timeLimit = Integer.parseInt(br.readLine()); // 개미가 움직일 시간 t 입력받기
		
		row += timeLimit % (2 * rowSize);
		col += timeLimit % (2 * colSize);
		
//		row = (row + timeLimit) % (2 * rowSize);
//		col = (col + timeLimit) % (2 * colSize);
		
		if (row > rowSize && row <= 2 * rowSize)
			row = 2 * rowSize - row;
		
		if (row > 2 * rowSize)
			row = row - 2 * rowSize;
		
		if (col > colSize && col <= 2 * colSize)
			col = 2 * colSize - col;
		
		if (col > 2 * colSize)
			col = col - 2 * colSize;
		
		
		
		/* timeout error code
		int time = 0; // 초기 시간
		
		int dr = 1;  // 초기 진행방향 row+1, col+1 표시해줄 dr, dc
		int dc = 1;
		
		
		while (time < timeLimit) { // 주어진 조건 시간만큼 움직이기
			time++; // 시간 +1 해주고
			
			row += dr; // 현재 진행방향으로 움직이기
			col += dc;
			
			// 일단 움직이고...
			// 진행방향의 다음 좌표가 격자를 벗어날 위치면 진행방향 꺾어주기
			if (row == 0 || row == rowSize)
				dr = -dr;
			
			if (col == 0 || col == colSize)
				dc = -dc;
		}
		*/
		
		// 조건 시간만큼 움직인 후의 좌표 출력하기
		System.out.printf("%d %d", row, col);
		
	}

}

