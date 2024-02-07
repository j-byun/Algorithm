import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 11003 최솟값 찾기 플래티넘5
 *
 * 문제
 * N개의 수
 * i-L+1 ~ i 까지의 최솟값을 구하라
 *
 * 조건
 * 1 ≤ L ≤ N ≤ 5,000,000
 * -10^9 ≤ 배열의 원소 Ai ≤ 10^9
 *
 * 풀이
 * 1. 현재 수와 L번째 이전 수 사이의 L개의 값 중 최솟값을 구하자.
 * 2. 길이가 L인 슬라이딩 윈도우
 * 3. 슬라이딩 윈도우를 덱으로 구현하자
 * 4. 길이가 L인 덱에 오른쪽으로 갈 수록 큰 수를 저장하자
 * 5. 새로 들어온 값이 덱의 오른쪽에 있는 값보다 작으면 새 값보다 작을 때까지 뽑아버리고 새 값을 넣자
 * 5-1. 어차피 새로운 인덱스의 수명이 더 기니까 새 값보다 큰 값을 더 이상 쓸모가 없음!
 */

public class Main {

    static class Node {
        int index, num;

        public Node(int index, int num) {
            this.index = index;
            this.num = num;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken()); // 수의 개수
        int L = Integer.parseInt(st.nextToken()); // 슬라이딩 윈도우의 크기
        Deque<Node> deque = new ArrayDeque<>(); // 최대 L개의 숫자를 오름차순으로 저장할 덱

        // N개의 숫자 입력받기
        st = new StringTokenizer(br.readLine());
        for (int idx = 1; idx <= N; idx++) {
            int cur = Integer.parseInt(st.nextToken());

            // 덱에 값이 있고, 덱의 오른쪽 숫자가 지금 들어온 값보다 크면 숫자 다 뽑아내기
            while (!deque.isEmpty() && deque.peekLast().num > cur) {
                deque.pollLast();
            }

            // 그럼 이제 덱에 값이 없거나,
            // 덱의 오른쪽으로 값을 넣으면 오름차순으로 정렬이 되는 상황
            // 그러니 지금 들어온 값을 덱의 오른쪽에 넣자
            deque.addLast(new Node(idx, cur));

            // 그럼 이제 덱은 오름차순 정렬이 됐으니, 왼쪽에서 값을 뽑으면 최솟값인데
            // 뽑은 값의 인덱스가 슬라이딩 윈도우의 크기 L을 벗어나면 안된다
            // 그러니 기한만료된 값은 다 버리고 남은 값 중 최솟값을 뽑자!
            while (idx - deque.peekFirst().index >= L) {
                deque.pollFirst();
            }

            sb.append(deque.peekFirst().num).append(" ");
        }

        System.out.println(sb.toString());
    }
}
