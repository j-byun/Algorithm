import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 2812 크게 만들기 골드3
 * 시간 제한 1초 / 메모리 제한 128 MB
 *
 * 문제
 * N자리 숫자가 주어졌을 때, 여기서 숫자 K개를 지워서 얻을 수 있는 가장 큰 수를 구하자.
 *
 * 조건
 * 1 ≤ K < N ≤ 500,000
 * N자리 숫자는 0으로 시작하지 않는다
 *
 * 풀이
 * 1. 앞에서부터 K개의 숫자 중 제일 큰 수를 찾기
 * 2. 그 앞의 숫자들은 지워버리기
 * 3. 그럼 두번째 자리부터 K개 중 남은 수 만큼의 크기 중 제일 큰수 찾기 ... 반복
 * => 시간초과
 * 
 * 1. 입력받으면서 바로바로 앞의 값들과 비교하기
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        char[] number = st.nextToken().toCharArray();

        Deque<Character> deque = new ArrayDeque<>();

        for (char c : number) {
            while (K > 0 && !deque.isEmpty() && deque.peekLast() < c) {
                deque.pollLast();
                K--;
            }

            deque.addLast(c);
        }

        while (K-- > 0) {
            deque.pollLast();
        }

        for (char c : deque) {
            sb.append(c);
        }

        System.out.println(sb.toString());
    }
}
