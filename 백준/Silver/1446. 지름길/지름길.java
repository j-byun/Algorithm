import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 1446 지름길 실버1
 *
 * 문제
 * D킬로미터 길이의 고속도로
 * 지름길들을 통해 운전하는 거리를 줄이자.
 * 운전해야 하는 거리의 최솟값을 구하자.
 *
 * 조건
 * 지름길의 개수 N은 12 이하인 양의 정수
 * 고속도로의 길이 D는 10,000보다 작거나 같은 자연수
 * 지름길의 시작 위치, 도착 위치, 길이가 주어진다.
 * 모든 위치와 길이는 10,000보다 작거나 음이 아닌 정수이다.
 * 지름길의 시작 위치는 도착 위치보다 작다
 *
 * 풀이
 * 1. dp를 활용해 어떤 지름길을 이용할 지 구하자
 */

public class Main {

    static class Shortcut {
        int start, end, distance;

        public Shortcut(int start, int end, int distance) {
            this.start = start;
            this.end = end;
            this.distance = distance;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 지름길의 개수
        int D = Integer.parseInt(st.nextToken()); // 고속도로의 길이

        Shortcut[] shortcuts = new Shortcut[N]; // 지름길들의 정보를 저장할 배열

        // N개의 지름길 정보 입력
        for (int idx = 0; idx < N; idx++) {
            st = new StringTokenizer(br.readLine());
            shortcuts[idx] = new Shortcut(Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        // 지름길 정보를 도착 위치 기준 오름차순으로 정렬
        Arrays.sort(shortcuts, (o1, o2) -> {
            return o1.end - o2.end;
        });

        int[] highway = new int[D + 1]; // 고속도로 xkm까지 갈 때의 최소 운전거리를 저장할 dp배열

        // 0~Dkm까지 고속도로 가면서 지름길 확인하며 달릴 수 있는 최소 운전거리 찾기
        int shortcutIndex = 0; // 앞에 있는 지름길의 인덱스를 나타낼 변수
        
        for (int idx = 0; idx <= D; idx++) {
            // 그냥 고속도로 달리는 경우
            if (idx > 0)
                highway[idx] = highway[idx - 1] + 1;

            // 지름길 진입 가능한 경우
            while (shortcutIndex < N && idx == shortcuts[shortcutIndex].end) {
                highway[idx] = Math.min(highway[idx], highway[shortcuts[shortcutIndex].start] + shortcuts[shortcutIndex].distance);

                shortcutIndex++;
            }
        }

        System.out.println(highway[D]);
    }
}