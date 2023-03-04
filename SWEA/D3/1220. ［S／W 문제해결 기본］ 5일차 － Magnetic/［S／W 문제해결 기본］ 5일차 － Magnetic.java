import java.util.Scanner;

// 1220 Magnetic
// D3

// 문제
// 푸른 자성체 b는 n극에 이끌리고, 붉은 자성체a는 s극에 이끌린다
// 테이블의 위가 n극, 아래가 s극이다

// 테이블 밖으로 떨어지는 자성체를 제외하고는 a와 b가 교착 상태에 빠져 움직이지 않게 된다
// a, b의 개수가 다르더라도 각각 하나 이상 존재하면 교착상태에 빠진다
// abb의 경우에도 하나의 교착상태로 본다
// abab는 두 개의 교착 상태로 본다
// 다른 열의 자성체와는 교착되지 않는다
// 한 줄에 두 개 이상의 교착 상태가 발생할 수 있다

// 테이블 위에 남아있는 교착상태를 반환하자

// 조건
// 테이블의 크기는 100*100이다
// 자성체는 테이블 앞 뒤의 n,s극에만 반응하고 자성체끼리는 반응하지 않는다
// 1은 s극에 이끌리는 a 자성체를, 2는 n극에 이끌리는 b자성체를 나타낸다

// 풀이
// 한 열 씩 자성체를 위에서부터 아래로 읽어와서 'ab' = '12'세트의 개수를 세어보자
// ba의 경우 각자 위아래로 테이블 밖으로 떨어지게 되고
// aabb의 경우 하나의 교착 상태이고
// aab의 경우 하나의 교착상태 이기 때문에
// abab 같은 경우에만 두 개의 교착상태로 보면 된다

public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for (int tc = 1; tc<= 10; tc++) { // 테스트케이스 10개만큼 반복 실행
			
			int size = sc.nextInt(); // 테이블 한 변의 길이 입력받기
			
			int[][] table = new int[size][size]; // 100*100 테이블 만들기
			
			for (int row = 0; row < size; row++) { // 테이블에 자성체 배치 상태 입력받기
				for (int col = 0; col < size; col++) {
					table[row][col] = sc.nextInt();
				}
			}
			
			int cnt = 0; // 교착상태 개수를 저장할 공간
			
			for (int col = 0; col < size; col++) {
				StringBuilder sb = new StringBuilder(); // 한 열 단위로 초기화
				
				for (int row = 0; row < size; row++) {
					if (table[row][col] != 0) sb.append(table[row][col]); // 테이블에 놓인 자성체만 읽어온다
				}
				
				// 한 열의 자성체 정보를 읽어 왔으니 현재 읽어온 문자열에서 'ab'세트의 개수만 세어보자
				int idx = 0;
				
				while (idx < sb.length() - 1) { // 테이블의 가장 밑에 있는 a는 어차피 떨어질거니까 찾은 자성체의 길이 - 2까지만 확인한다
					
					if (sb.charAt(idx) == '1' && sb.charAt(idx + 1) == '2') cnt++; // 'ab'세트를 찾았다면 교착상태 +1 해준다
					
					idx++; // 다음 자성체를 확인하러 간다
				}
				
			}
			
			System.out.printf("#%s %d\n", tc, cnt);
			
		}
		
		
		
		
	}

}


