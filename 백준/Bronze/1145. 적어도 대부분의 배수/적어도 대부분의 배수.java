import java.util.Arrays;
import java.util.Scanner;

/**
 * @author jihye.byun
 * BOJ 1145 적어도 대부분의 배수 브론즈1
 * 시간 제한 2초 | 메모리 제한 128 MB
 *
 * 문제
 *
 * 조건
 *
 * 풀이
 */

public class Main {
    public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int[] arr = new int[5];

		for (int idx = 0; idx < 5; idx++) {
			arr[idx] = sc.nextInt();
		}
		
		for (int num = 1; num < 1000000; num++) {
			int count = 0;
			
			for (int idx = 0; idx < 5; idx++) {
				if (num % arr[idx] == 0) count++;
			}
			
			if (count >= 3) {
				System.out.println(num);
				return;
			}
		}
		
    }
}
