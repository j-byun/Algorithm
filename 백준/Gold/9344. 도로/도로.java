import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * @author jihye.byun
 * BOJ 9344 도로 골드3
 *
 * 문제
 * N개의 도시를 모두 연결하자.
 * 각 테스트 케이스에 대해, p-q를 지으면서 가장 짧은 도로망을 만들 수 있으면 YES를 출력한다.
 * 아니면 NO를 출력한다.
 *
 * 조건
 * 테스트 케이스의 개수 T (T ≤ 10)
 * 도로망 위에 존재하는 도시의 수 N (2 ≤ N ≤ 10,000)
 * 길의 수 M (1 ≤ M ≤ 20,000)
 * 그 사이에 도로를 지어도 되는지 판단해야 하는 두 도시 p와 q(1 ≤ p,q ≤ N)
 * 도시 u와 v를 잇는 양방향 길의 비용이 w (1 ≤ u ≤ N, 1 ≤ v ≤ N, 1 ≤ w ≤ 400,000)
 * 도로를 짓는 데 드는 비용은 모두 다르며, 두 도시를 잇는 길은 오직 하나이다.
 * 모든 도시를 잇는 도로망이 최소 한 개 이상 존재한다는 것이 보장된다.
 * 모든 입력은 정수이다.
 *
 * 풀이
 * 1. MST - 크루스칼 알고리즘을 적용하자.
 */

public class Main {

    private static int findset (int x, int[] parent) {
        if (parent[x] != x)
            return findset(parent[x], parent);
        return x;
    }

    private static void union(int x, int y, int[] parent) {
        parent[y] = x;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for (int tc = 1; tc <= T; tc++) {
            String result = "NO";

            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 도시의 수
            int M = Integer.parseInt(st.nextToken()); // 길의 수
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());

            int[] parent = new int[N + 1];
            int[][] edge = new int[M][3];

            // makeset
            for (int idx = 0; idx <= N; idx++) {
                parent[idx] = idx;
            }
            
            // 길 정보 입력받기
            for (int idx = 0; idx < M; idx++) {
                st = new StringTokenizer(br.readLine());

                edge[idx][0] = Integer.parseInt(st.nextToken());
                edge[idx][1] = Integer.parseInt(st.nextToken());
                edge[idx][2] = Integer.parseInt(st.nextToken());
            }

            // 길 정보 거리에 따라 오름차순으로 정렬하기
            Arrays.sort(edge, (o1, o2) -> o1[2] - o2[2]);

            int count = 0;

            for (int idx = 0; idx < M; idx++) {
                int px = findset(edge[idx][0], parent);
                int py = findset(edge[idx][1], parent);

                if (px == py)
                    continue;

                if ((edge[idx][0] == p && edge[idx][1] == q) || (edge[idx][0] == q && edge[idx][1] == p))
                    result = "YES";

                union(px, py, parent);
                count++;

                if (count == N - 1)
                    break;
            }

            bw.write(result);
            bw.write("\n");
        }

        bw.flush();
    }
}
