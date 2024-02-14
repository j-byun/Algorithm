import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 14442 벽 부수고 이동하기 2 골드3
 * 시간 제한 2초 / 메모리 제한 512 MB
 *
 * 문제
 * N*M 맵
 * 0 : 이동할 수 있는 곳, 1 : 이동할 수 없는 벽이 있는 곳
 * (1, 1)에서 (N, M)까지 최단경로로 이동하자
 * 최단경로는 가장 적은 개수의 칸을 지나는 경로이고, 이때 시작 칸과 끝 칸도 포함해서 센다
 * 이동하는 도중에 벽을 부수고 이동하는 것이 좀 더 경로가 짧아진다면, 벽을 K개까지 부수고 이동해도 된다
 * 한 칸에서 이동할 수 있는 칸은 상하좌우로 인접한 칸이다
 * 맵이 주어졌을 때, 최단 경로룰 구하자
 *
 * 조건
 * N(1 ≤ N ≤ 1,000)
 * M(1 ≤ M ≤ 1,000)
 * K(1 ≤ K ≤ 10)
 * (1, 1)과 (N, M)은 항상 0이라고 가정하자.
 * (N, M)칸에 도착할 수 없다면 -1을 출력한다.
 *
 * 풀이
 * 1. BFS 탐색으로 (N, M) 칸에 도착하면 그 루트가 최단 경로이니 바로 탐색 종료
 * 2. 탐색하는 큐에 그 경로까지 가면서 부순 벽의 개수를 같이 저장하자
 * 3. 방문 체크 배열은...int로 선언하고 그 칸에 방문했을 때까지 부순 벽의 개수를 저장하자
 * 4. 같은 칸에 다시 방문했는데, 다른 경로로 와서 그때까지 부순 벽의 개수가 더 적다면 또 방문하자
 */

public class Main {

    static class Node {
        int row, col, dist, breakCount;

        public Node(int row, int col, int dist, int breakCount) {
            this.row = row;
            this.col = col;
            this.dist = dist;
            this.breakCount = breakCount;
        }
    }

    static int N, M, K;
    static int[][] map, visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken()); // 행의 개수 입력받기
        M = Integer.parseInt(st.nextToken()); // 열의 개수 입력받기
        K = Integer.parseInt(st.nextToken()); // 부술 수 있는 벽의 개수 입력받기

        map = new int[N][M]; // 맵 만들기

        // 맵 정보 입력받기
        for (int row = 0; row < N; row++) {
            String line = br.readLine();
            for (int col = 0; col < M; col++) {
                map[row][col] = line.charAt(col) - '0';
            }
        }

        visit = new int[N][M]; // 방문 체크 배열 만들기

        // 방문하지 않았던 칸들은 K보다 큰 값으로 초기화하기
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                visit[row][col] = 987654321;
            }
        }

        // bfs 탐색으로 찾아낸 최단경로 출력하기
        System.out.println(bfs());
    }

    static int bfs() {
        // 상하좌우 탐색용 델타 배열
        int[] dr = new int[] {-1, 0, 1, 0};
        int[] dc = new int[] {0, 1, 0, -1};

        Queue<Node> queue = new ArrayDeque<>(); // 탐색할 칸들을 차례로 저장할 큐
        
        // (0, 0)에서 시작
        queue.add(new Node(0, 0, 1, 0));
        visit[0][0] = 0;

        // 큐에 담긴 다음 칸들 탐색
        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            // (N-1, M-1)에 도착했다면 탐색 중단
            if (cur.row == N - 1 && cur.col == M - 1) {
                return cur.dist;
            }

            // 아직 도착하지 못했다면 네 방향 탐색
            for (int d = 0; d < 4; d++) {
                int nr = cur.row + dr[d];
                int nc = cur.col + dc[d];

                // 맵 범위 확인
                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;

                // 이전에 방문했던 경우와 다른 경로인지 && 벽을 더 부술 수 있는지 확인
                int newBreakCount = cur.breakCount + map[nr][nc];
                if (visit[nr][nc] <= newBreakCount) continue;
                if (newBreakCount > K) continue;

                // 방문할 수 있는 칸이면 방문
                queue.add(new Node(nr, nc, cur.dist + 1, newBreakCount));
                visit[nr][nc] = newBreakCount;
            }
        }

        // (N, M)까지 도착하지 못했다면 -1 반환
        return -1;
    }
}
