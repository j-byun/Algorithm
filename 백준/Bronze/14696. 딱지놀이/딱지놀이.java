import java.util.Scanner;

// 14696 딱지놀이
// 브론즈1

// 문제
// A, B가 딱지놀이를 하는데,
// 처음에 여러 장의 딱지를 가지고 시작해서 매 라운드마다 각자 딱지를 하나씩 낸다
// 딱지에는 별(★), 동그라미(●), 네모(■), 세모(▲) 네 가지 모양 중 하나 이상의 모양이 표시되어 있다

// 두 딱지 중 어느 딱지가 더 강력한 것인지는 다음 규칙을 따른다
// 1. 우선수위1 - 별의 개수 많은 쪽이 이긴다
// 2. 우선순위2 - 동그라미 개수 많은 쪽이 이긴다
// 3. 우선순위3 - 네모 개수 많은 쪽이 이긴다
// 4. 우선순위4 - 세모 개수 많은 쪽이 이긴다
// 5. 네 도형의 개수가 모두 같으면 비긴다
// 별 : 4, 동그라미 : 3, 네모 : 2, 세모 : 1

// 조건
// 총 라운드 수 N 은 1 이상 1,000 이하
// 어린이가 내는 딱지에 나온 그림의 총 개수 a는 1이상 100이하

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int maxRound = sc.nextInt(); // 딱지놀이의 총 라운드 수 입력받기
		
		for (int round = 1; round <= maxRound; round++) { // 총 라운드 만큼 반복해서 게임 진행
			
			int aCnt = sc.nextInt(); // a가 낼 딱지의 그림 개수
			
			int[] aCard = new int[5]; // a가 낼 딱지에 그려진 각 그림의 개수 저장할 공간
			
			for (int cnt = 0; cnt < aCnt; cnt++) { // a가 내는 딱지의 그림 입력받기
				aCard[sc.nextInt()]++; // 해당 그림의 번호를 인덱스로 받아서 배열에 해당 그림 개수 저장
			}
			
			int bCnt = sc.nextInt(); // b가 낼 딱지의 그림 개수
			
			int[] bCard = new int[5]; // b가 낼 딱지에 그려진 각 그림의 개수 저장할 공간
			
			for (int cnt = 0; cnt < bCnt; cnt++) { // b가 내는 딱지의 그림 입력받기
				bCard[sc.nextInt()]++;
			}
			
			char result = 'D'; // 일단 둘이 비겼다고 가정하고 시작
			
			// 두 딱지 중 어느 딱지가 이긴건지 비교하기
			for (int idx = 4; idx > 0; idx--) {
				if (aCard[idx] > bCard[idx]) { // a의 현재 그림이 더 많으면 a가 이겼으니 탐색 종료
					result = 'A';
					break;
					
				} else if (aCard[idx] < bCard[idx]) { // b의 현재 그림이 더 많으면 b가 이겼으니 탐색 종료
					result = 'B';
					break;
				}
				// 둘의 그림 개수가 같으면 다음 그림 확인하러가기
				// 이렇게 4~1번까지 확인했는데 모든 그림의 개수가 같으면 result값 변화 없이 비겼다는 결과가 출력된다
			}
			
			System.out.println(result);
		}
		
	}

}

