import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 8980 택배 골드1
 *
 * 문제
 * 1번부터 오른쪽으로 차례대로 번호 붙여진 직선 도로 상의 마을들
 * 마을에 있는 물건을 배송하기 위한 트럭
 * 트럭이 있는 본부는 1번 마을의 왼쪽에 있다.
 * 트럭은 본부에서 출발하여 1번~마지막 마을까지 오른쪽으로 가면서 마을에 있는 물건을 배송
 * 각 마을은 배송할 물건들을 박스에 넣어 보내며,
 * 본부에서는 박스를 보내는 마을 번호, 받는 마을 번호, 박스의 개수를 알고 있다.
 * 박스들은 모두 크기가 같다.
 * 트럭에는 최대 용량이 있다.
 * 트럭 한 대로 배송할 수 있는 최대 박스 수를 구하자.
 *
 * 조건
 * 마을의 수 N (N은 2이상 2,000이하 정수)
 * 트럭의 용량 C (C는 1이상 10,000이하 정수)
 * 보내는 박스 정보의 개수 M (M은 1이상 10,000이하 정수)
 * 보내는 박수의 개수는 1이상 10,000이하 정수
 *
 * 풀이
 * 1. dp 회의실 배정과 동일한 방식으로 도착마을 기준 오름차순 -> 시작마을 기준 오름차순 정렬해서 탐색
 * 2. => 박스들 중 일부만 배송할 수 있기 때문! 먼저 끝나는게 와따다. 그래야 다음꺼 또 실을 수 있음!
 */

public class Main {
    static class Box implements Comparable<Box>{
        int from, to, amount;

        public Box(int from ,int to, int amount) {
            this.from = from;
            this.to = to;
            this.amount = amount;
        }

        @Override
        public int compareTo(Box o) {
            if (this.to == o.to) {
                return this.from - o.from;
            }
            return this.to - o.to;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken()); // 마을의 수 입력받기
        int C = Integer.parseInt(st.nextToken()); // 트럭의 용량 입력받기
        
        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken()); // 박스 정보의 개수
        
        PriorityQueue<Box> pq = new PriorityQueue<>(); // 박스 정보를 저장할 우선순위 큐

        // 박스 정보 입력받기
        for (int cnt = 1; cnt <= M; cnt++) {
            st = new StringTokenizer(br.readLine());
            pq.add(new Box(Integer.parseInt(st.nextToken())
                    , Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        
        int[] boxes = new int[N + 1]; // 각 마을을 지날 때 트럭에 실려있는 박스의 개수를 저장할 배열 생성

        // 박스 정보 하나씩 빼서 박스 운반하기
        int boxCount = 0; // 총 운반한 박스의 개수를 저장할 변수 생성

        while (!pq.isEmpty()) {
            Box cur = pq.poll();

            // 현재 박스를 실어야되는 출발지~도착지까지 확인하며 최대로 운반할 수 있는 박스 개수 파악하기
            int maxBox = cur.amount;

            for (int idx = cur.from; idx < cur.to; idx++) {
                maxBox = Math.min(maxBox, C - boxes[idx]);
            }

            // 운반할 수 있는 최대 박스만큼 운반
            boxCount += maxBox;

            // 그럼 이제 출발지~도착지까지 트럭에 박스 추가해주기
            for (int idx = cur.from; idx < cur.to; idx++) {
                boxes[idx] += maxBox;
            }
        }

        System.out.println(boxCount);
    }
}
