import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 11650 좌표 정렬하기 실버5
 * 시간 제한 1초 | 메모리 제한 256 MB
 *
 * 문제
 * 2차원 평면 위의 점 N개
 * 좌표를 x좌표가 증가하는 순으로, x좌표가 같으면 y좌표가 증가하는 순으로 정렬하자
 *
 * 조건
 * 점의 개수 N (1 ≤ N ≤ 100,000)
 * -100,000 ≤ xi, yi ≤ 100,000
 * 좌표는 항상 정수이고, 위치가 같은 두 점은 없다
 *
 * 풀이
 * 1. 우선순위 큐에 정렬 조건을 만들어 넣고 하나씩 뽑아 출력하자
 */

public class Main {

    public static class Node {
        int x, y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken()); // 점의 개수 입력받기

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.x == o2.x) {
                return o1.y - o2.y;
            }
            return o1.x - o2.x;
        });

        // N개의 점 좌표 입력받아 차례로 정렬하기
        for (int cnt = 1; cnt <= N; cnt++) {
            st = new StringTokenizer(br.readLine());

            pq.add(new Node(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
        }

        // 정렬된 N개의 점 차례로 뽑아 출력하기
        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            sb.append(cur.x).append(" ").append(cur.y).append("\n");
        }

        System.out.println(sb.toString());
    }
}
