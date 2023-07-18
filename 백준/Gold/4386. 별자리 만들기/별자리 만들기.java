import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @author jihye.byun
 * BOJ 4386 별자리 만들기 골드3
 *
 * 문제
 * n개의 별들을 모두 이어 별자리를 만드는 최소 비용을 구하시오.
 * 선을 하나 이을 때마다 두 별 사이의 거리만큼의 비용이 든다.
 *
 * 조건
 * 별의 개수 n (1 <= n <= 100)
 * 각 별의 x, y좌표는 실수 형태로 주어지며, 최대 소수점 둘째자리까지 주어진다.
 * 좌표는 1000을 넘지 않는 양의 실수이다.
 * 절대/상대 오차는 10^-2까지 허용한다.
 *
 * 풀이
 * 1. MST - 크루스칼 알고리즘을 적용하자.
 * 2. n * n-1 개 만큼의 선을 고려해보자.
 */

public class Main {

    private static double calcDist(double[] a, double[] b) {
        double squareX = Math.pow(a[0] - b[0], 2);
        double squareY = Math.pow(a[1] - b[1], 2);

        return Math.sqrt(squareX + squareY);
    }
    
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

        int n = Integer.parseInt(st.nextToken()); // 별의 개수
        double[][] stars = new double[n][2]; // 각 별의 x, y좌표를 저장할 배열 공간
        int[] parent = new int[n]; // 각 별의 부모 별 번호를 저장할 공간
        
        // n개의 별의 좌표를 입력받자
        for (int idx = 0; idx < n; idx++) {
            st = new StringTokenizer(br.readLine());
            stars[idx][0] = Double.parseDouble(st.nextToken());
            stars[idx][1] = Double.parseDouble(st.nextToken());
        }

        // 각 별들을 이어주는 모든 선들을 고려해보자
        // 비용(거리) 오름차순으로 정렬하자
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        for (int a = 0; a < n; a++) {
            for (int b = a + 1; b < n; b++) {
                pq.add(new Edge(a, b, calcDist(stars[a], stars[b])));
            }
        }

        // makeset
        for (int idx = 0; idx < n; idx++) {
            parent[idx] = idx;
        }
        
        double sum = 0; // 별자리를 만드는 데에 드는 비용의 합을 저장할 공간
        int count = 0; // 연결한 선의 개수를 세어줄 변수
        int size = pq.size(); // 고려해야할 선의 개수
        
        // 비용 오름차순으로 정렬된 선 중에서 n-1개만큼을 뽑아서 연결하자
        for (int idx = 0; idx < size; idx++) {
            Edge cur = pq.poll();

            int px = findset(cur.start, parent);
            int py = findset(cur.end, parent);

            // 선으로 연결된 두 별이 이미 연결된 상태라면 continue
            if (px == py) continue;
            // 두 별이 연결되지 않은 상태라면 연결해주자
            union(px, py, parent);
            // 두 별 사이의 거리를 비용 합에 더해주자
            sum += cur.dist;
            // 연결한 선의 개수를 +1 해주자
            count++;
            // n-1개 만큼의 선을 연결했다면 탐색을 종료하자
            if (count == n - 1) break;
        }
        
        // n-1개의 선을 연결할 때 드는 최소 비용의 합을 출력하자
        System.out.printf("%.2f", sum);
    }
    private static class Edge implements Comparable<Edge>{
        int start, end;
        double dist;
        public Edge(int start, int end, double dist) {
            this.start = start;
            this.end = end;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.dist, o.dist);
        }
    }
}
