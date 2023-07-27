import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @author jihye.byun
 * BOJ 1368 물대기 골드2
 *
 * 문제
 * N개의 논에 물을 대자.
 * 물을 대는 방법 1. 직접 논에 우물을 판다.
 * 물을 대는 방법 2. 이미 물을 대고 있는 다른 논으로부터 물을 끌어온다.
 *
 * 조건
 * 논의 수 N(1 ≤ N ≤ 300)
 *  i번째 논에 우물을 팔 때 드는 비용 Wi(1 ≤ Wi ≤ 100,000)
 *  i번째 논과 j번째 논을 연결하는데 드는 비용 Pi,j(1 ≤ Pi,j ≤ 100,000, Pi,j = Pj,i, Pi,i = 0)
 *
 * 풀이
 * 1. MST - 크루스칼 알고리즘을 적용하자.
 */

public class Main {

    private static int findset(int x, int[] parent) {
        if (x != parent[x])
            return parent[x] = findset(parent[x], parent);
        return x;
    }

    private static void union(int x, int y, int[] parent) {
        parent[y] = x;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 논의 수
        int[] well = new int[N + 1]; // 각 논에 직접 우물을 파는 비용을 저장할 배열 공간
        int[] parent = new int[N + 1]; // 각 논에 연결된 부모 논 번호를 저장할 배열 공간
        int[][] arr = new int[N + 1][N + 1]; // 두 논을 연결하는 데에 드는 비용을 저장할 배열 공간

        // makeset
        // 각 논에 직접 우물을 파는 비용 입력받기
        for (int idx = 1; idx <= N; idx++) {
            parent[idx] = idx;

            st = new StringTokenizer(br.readLine());
            well[idx] = Integer.parseInt(st.nextToken());
        }

        // 비용 오름차순 우선순위 큐
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);

        // arr 배열 정보 입력받기
        for (int row = 1; row <= N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 1; col <= N; col++) {
                arr[row][col] = Integer.parseInt(st.nextToken());

                if (row > col) continue;
                if (row == col)
                    pq.add(new int[] {0, col, well[row]});
                else
                    pq.add(new int[] {row, col, arr[row][col]});
            }
        }

        int sum = 0;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            int px = findset(cur[0], parent);
            int py = findset(cur[1], parent);

            if (px == py) continue;

            union(px, py, parent);
            sum += cur[2];
        }

        System.out.println(sum);
    }
}
