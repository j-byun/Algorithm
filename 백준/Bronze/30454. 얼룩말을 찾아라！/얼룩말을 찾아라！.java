import java.util.*;

/**
 * @author jihye.byun
 * BOJ 30454 얼룩말을 찾아라! 브론즈3
 * 시간 제한 0.5초 | 메모리 제한 1024 MB
 *
 * 문제
 * 얼룩말의 몸통에 검은 줄이 많을수록 아름답다.
 * 0 : 흰 털, 1 : 검은 털
 * 검은 줄의 개수 : 얀속하는 검은 부분의 개수
 * 가장 아름다운 얼룩말이 몇 개의 줄을 가졌고, 그런 얼룩말이 총 몇 마리 있는지 구하자.
 *
 * 조건
 * 얼룩말의 개체 수 N
 * 얼룩말의 몸통 길이를 나타내는 정수 L
 * 1 <= N, L <= 1,000
 *
 * 풀이
 */

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 얼룩말의 개체 수
        int L = sc.nextInt(); // 얼룩말의 몸통 길이
        int[] blackCount = new int[L + 1]; // 검은 줄이 i개인 얼룩말의 마리 수를 저장할 카운트 배열

        for (int cnt = 0; cnt < N; cnt++) {
            String zebra = sc.next();

            // 얼룩말의 검은 줄 개수 세기
            blackCount[countBlackLine(zebra, L)]++;
        }

        for (int idx = L; idx >= 0; idx--) {
            if (blackCount[idx] > 0) {
                System.out.println(idx + " " + blackCount[idx]);
                return;
            }
        }
    }

    private static int countBlackLine(String zebra, int size) {
        int count = 0;
        char before = '0';

        for (int idx = 0; idx < size; idx++) {
            char cur = zebra.charAt(idx);

            if (before == '0' && cur == '1') {
                count++;
            }

            before = cur;
        }

        return count;
    }
}
