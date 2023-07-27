import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author jihye.byun
 * BOJ 2573 빙산 골드4 DFS/BFS
 *
 * 문제
 * 북극의 빙산이 녹고 있다.
 * 빙산을 2차원 배열에 표시한다고 하자.
 * 빙산의 각 부분별 높이 정보는 배열의 각 칸에 양의 정수로 저장된다.
 * 빙산 이외의 바다에 해당되는 칸에는 0이 저장된다.
 * 빙산의 높이는 바닷물에 많이 접해있는 부분에서 더 빨리 줄어들기 때문에,
 * 배열에서 빙산의 각 부분에 해당되는 칸에 있는 높이는 일년마다
 * 그 칸에 동서남북 네 방향으로 붙어있는 0이 저장된 칸의 개수만큼 줄어든다.
 * 단, 각 칸에 저장된 높이는 0보다 더 줄어들지 않는다.
 * 바닷물은 호수처럼 빙산에 둘러싸여 있을 수도 있다.
 * 2차원 배열에서 동서남북 방향으로 붙어있는 칸들은 서로 연결되어 있다.
 * 한 덩어리의 빙산이 주어질 때, 이 빙산이 두 덩어리 이상으로 분리되는 최초의 시간(년)을 구하는 프로그램을 작성하시오.
 * 만일 전부 다 녹을 때까지 두 덩어리 이상으로 분리되지 않으면 프로그램은 0을 출력한다.
 *
 * 조건
 * 이차원 배열의 행의 개수와 열의 개수를 나타내는 두 정수 N과 M (3이상 300이하)
 * 배열의 각 칸에 들어가는 값은 0이상 10이하
 * 배열의 칸의 개수는 10,000개 이하
 * 배열의 테두리는 항상 0
 *
 * 풀이
 */

public class Main {

    static int[] dr = new int[] {-1, 0, 1, 0};
    static int[] dc = new int[] {0, 1, 0, -1};

    static int N, M;
    static int[][] ice, ocean;
    static boolean[][] visit;

    private static void melt() {
        // 빙산의 각 칸과 맞닿은 바다의 개수를 확인하자
        ocean = new int[N][M];

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (ice[row][col] == 0) continue;

                int count = 0;
                for (int d = 0; d < 4; d++) {
                    int nr = row + dr[d];
                    int nc = col + dc[d];
                    
                    // 어차피 테두리는 바다니까 범위 체크 필요없음
                    if (ice[nr][nc] == 0) count++;
                }
                ocean[row][col] = count;
            }
        }
        
        // 각 칸이 얼만큼 녹아야 하는지 확인했으니, 모든 칸을 녹여주자
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                ice[row][col] -= ocean[row][col];

                // 음수면 0으로 만들자
                if (ice[row][col] < 0) ice[row][col] = 0;
            }
        }
    }

    private static void bfs(int row, int col) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {row, col});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (visit[cur[0]][cur[1]]) continue;
            visit[cur[0]][cur[1]] = true;

            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];

                if (nr < 0 || nr > N || nc < 0 || nc > M) continue;
                if (ice[nr][nc] == 0) continue;
                if (visit[nr][nc]) continue;

                queue.add(new int[] {nr, nc});
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken()); // 빙산 배열의 행 개수
        M = Integer.parseInt(st.nextToken()); // 빙산 배열의 열 개수
        
        ice = new int[N][M]; // 빙산의 높이 정보를 저장할 배열

        // 빙산의 높이 정보를 입력받자
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < M; col++) {
                ice[row][col] = Integer.parseInt(st.nextToken());
            }
        }
    
        int time = 0;

        while (true) {
            time++;
            
            // 각 칸의 빙산을 녹이자
            melt();

            // 이제 녹은 후의 빙산이 분리되었는지 확인하자
            int count = 0;
            visit = new boolean[N][M];

            for (int row = 0; row < N; row++) {
                for (int col = 0; col < M; col++) {
                    if (ice[row][col] == 0) continue;
                    if (!visit[row][col]) {
                        if (count == 1) {
                            // 이게 두번째 빙산 덩어리면 탐색하지말고 바로 시간 출력 후 종료
                            System.out.println(time);
                            return;
                        }
                        bfs(row, col);
                        count++;
                    }
                }
            }

            // 모든 빙산이 한꺼번에 녹아버렸다면 0을 출력하자
            if (count == 0) {
                System.out.println(0);
                return;
            }
        }
    }
}
