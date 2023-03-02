import java.util.Scanner;

// 13458 시험 감독
// 브론즈2

// 문제
// N개의 시험장
// 각각의 시험장마다 Ai명의 응시자들이 있다

// 감독관 : 총감독관/부감독관
// 총감독관 : 한 시험장에서 감시할 수 있는 응시자의 수 B명
// 부감독관 : 한 시험장에서 감시할 수 있는 응시자의 수 C명
// 각 시험장에 총감독관은 오직 1명, 부감독관은 여러 명 가능
// 각 시험장의 응시생을 모두 감시해야 될 때 필요한 감독관 수의 최솟값 구하기

// 조건
// 시험장의 개수 N(1 ≤ N ≤ 1,000,000)
// 각 시험장에 있는 응시자의 수 Ai (1 ≤ Ai ≤ 1,000,000)
// (1 ≤ B, C ≤ 1,000,000)

// 풀이
// 각 시험장의 응시자 수 입력받고 다시 시험장 배열 돌면서
// (응시자수 - 총감독관 감시 학생 수) / 부감독관 감시 학생 수 를 올림처리해서 min값에 저장
// 		=> 총감독관 1명도 더해주기

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int roomSize = sc.nextInt(); // 시험장의 개수 입력받기
		
		int[] room = new int[roomSize]; // 각 시험장에 있는 응시자 수 저장할 배열
		
		for (int idx = 0; idx < roomSize; idx++) { // 각 시험장의 응시자 수 입력받기
			room[idx] = sc.nextInt();
		}
		
		int sup = sc.nextInt(); // 총감독관이 감시할 수 있는 응시자의 수
		int sub = sc.nextInt(); // 부감독관이 감시할 수 있는 응시자의 수
		
		long minSum = roomSize; // 모든 시험장의 최소 감독관 수를 저장할 공간
							// 각 시험장에 이미 총감독관 한 명 씩 있다고 가정하자
		
		for (int idx = 0; idx < roomSize; idx++) { // 모든 고사실을 확인하며
			
			if (room[idx] - sup > 0) { // 부감독관이 필요할 때만
				minSum += (long) Math.ceil((double) (room[idx] - sup) / sub); // 최소감독관 수 더하기
			}
		}
		
		System.out.println(minSum); // 출력
		
	}

}

