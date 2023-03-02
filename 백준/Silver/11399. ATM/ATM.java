import java.util.Arrays;
import java.util.Scanner;

// 11399 ATM
// 실버4

// 문제
// ATM 1대 뿐
// N명의 사람들이 줄 서 있고, 각자가 돈을 인출하는데 걸리는 시간은 다 다름
// 각자가 돈 인출 하는데 필요한 시간 합의 최솟값을 구하시오

// 풀이
// 오름차순 정렬하고
// 누적합 배열을 만들면서 sum에 각 배열의 값을 더해준다


public class Main {
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int peopleCnt = sc.nextInt(); // 줄 선 사람 수 입력받기
		
		int[] time = new int[peopleCnt]; // 줄 선 사람들이 인출하는데 걸리는 시간을 저장할 공간
		
		for (int idx = 0; idx < peopleCnt; idx++) { // 각 사람이 인출하는데 걸리는 시간 입력받기
			time[idx] = sc.nextInt();
		}
		
		Arrays.sort(time);
		
		int sum = 0; // 모든 사람이 돈을 인출하는데 걸리는 시간의 합을 저장할 공간
		
		for (int idx = 0; idx < peopleCnt; idx++) { // time배열을 누적합 배열로 만들면서 각 배열의 값을 sum에 더하기
			if (idx > 0) {
				time[idx] += time[idx - 1]; // 누적합 배열로 바꾸고
			}
			
			sum += time[idx]; // 합 시간에 현재 사람이 걸리는 시간 더해주기
		}
		
		System.out.println(sum);
		
	}

}

