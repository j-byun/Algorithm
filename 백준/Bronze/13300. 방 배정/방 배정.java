import java.util.Scanner;

// 13300 방 배정
// 브론즈2

// 문제
// 1~6학년 수학여행 방 배정하기
// 남학생을 남학생끼리, 여학생은 여학생끼리 방 배정 해야함
// 한 방에는 같은 학생들만 배정해야 함
// 한 방에 배정할 수 있는 최대 인원 수 k가 주어졌을 때,
// 조건에 맞게 모든 학생을 배정하기 위해 필요한 방의 최소 개수 구하기

// 조건
// 수학여행에 참가하는 학생 수를 나타내는 정수 N(1 ≤ N ≤ 1,000)
// 한 방에 배정할 수 있는 최대 인원 수 K(1 < K ≤ 1,000)
// 성별 S는 0, 1중 하나로서 여학생인 경우에 0, 남학생인 경우에 1
// 학년 Y(1 ≤ Y ≤ 6)

// 풀이
// 학년 별, 성별로 모두 나누어서 방을 배정해야 하기 때문에...
// 1. [12]크기의 배열을 만들어서
// 2. 1학년여, 1학년남, 2학년여, 2학년남 ... , 6학년여, 6학년남 순서대로 각 학생 수를 저장하자
// 3. 12칸을 확인하면서 해당 칸의 학생 수를 한 방에 배정할 수 있는 최대 인원수 k로 나눈 몫을 올림해서 구하자

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int studentCnt = sc.nextInt(); // 전체 학생 수 입력받기
		int maxNum = sc.nextInt(); // 한 방의 최대 인원 수 입력받기
		
		int[] student = new int[12]; // 학년별, 성별로 구분해서 학생 수를 저장할 공간
		
		for (int cnt = 0; cnt < studentCnt; cnt++) { // 각 학생들의 정보를 입력받자
			int gender = sc.nextInt();
			int grade = sc.nextInt();
			
			student[(grade - 1) * 2 + gender]++; // 현재 학생의 성별, 학년에 맞게 배열에 학생을 저장하자
										// 학년 별로 0, 2, 4, 6, 8, 10번 인덱스 부터 시작하고, 남학생의 경우 인덱스+1 해주면 된다
		}
		
		int minRoom = 0; // 모든 학생들을 배정하기 위해 필요한 방의 최소 개수를 저장할 공간
		
		for (int idx = 0; idx < 12; idx++) { // 성별, 학년으로 나눠서 저장한 학생들을 배치시키자
			minRoom += (int) Math.ceil((double) student[idx] / maxNum); 
					// 학년, 성별로 분류한 학생 수를 한 방에 수용 가능한 최대 학생 수로 나누고, 해당 값을 올림한 값이 
					// 해당 분류에 속한 학생을 배치하는데에 사용되는 최소 방의 개수이다
		}
		
		System.out.println(minRoom);
	}

}

