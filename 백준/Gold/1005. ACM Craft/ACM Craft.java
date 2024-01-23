import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 1005 ACM Craft 골드3
 *
 * 문제
 *
 * 조건
 *
 * 풀이
 * 1. 위상정렬 + 비용계산
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken()); // 테크트케이스 개수 입력

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken()); // 건물의 개수
            int K = Integer.parseInt(st.nextToken()); // 건물간의 건설순서 규칙의 총 개수
            
            int[] cost = new int[N + 1]; // 각 건물당 건설에 걸리는 시간을 저장할 배열 생성

            // 건물 건설에 걸리는 시간 입력
            st = new StringTokenizer(br.readLine());
            for (int idx = 1; idx <= N; idx++) {
                cost[idx] = Integer.parseInt(st.nextToken());
            }

            ArrayList<Integer>[] adjList = new ArrayList[N + 1]; // 건설순서 규칙을 저장할 인접리스트 생성

            // 인접리스트 초기화
            for (int idx = 1; idx <= N; idx++) {
                adjList[idx] = new ArrayList<>();
            }

            int[] inDegree = new int[N + 1]; // 각 건물의 진입차수를 저장할 배열 생성

            // 건설순서 규칙 입력
            for (int cnt = 0; cnt < K; cnt++) {
                st = new StringTokenizer(br.readLine());

                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());

                adjList[start].add(end);
                inDegree[end]++;
            }

            int[] time = new int[N + 1]; // 각 건물을 짓는 최소시간을 저장할 배열 생성
            Queue<Integer> queue = new ArrayDeque<>(); // 지을 수 있는 건물들을 차례로 저장할 큐 생성

            // 먼저 지어야하는 건물이 없는 건물들 먼저 짓기
            for (int idx = 1; idx <= N; idx++) {
                if (inDegree[idx] > 0) continue;
                time[idx] = cost[idx];
                queue.add(idx);
            }

            // 앞의 건물을 지었으면 지을 수 있는 다음 건물 짓기
            while (!queue.isEmpty()) {
                int cur = queue.poll();

                for (int next : adjList[cur]) {
                    inDegree[next]--;
                    // 다음 건물 짓는 시간 최대값으로 업데이트
                    time[next] = Math.max(time[next], time[cur] + cost[next]);

                    if (inDegree[next] == 0) {
                        queue.add(next);
                    }
                }
            }

            st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken()); // 건설해야할 건물의 번호
            sb.append(time[W]).append("\n"); // W번 건물 건설에 걸리는 최소 시간 출력
        }

        System.out.println(sb.toString());
    }
}
