import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author jihye.byun
 * BOJ 2468 안전 영역 실버1 DFS/BFS
 *
 * 문제
 * 장마철에 내리는 비의 양에 따라 일정한 높이 이하의 모든 지점은 물에 잠긴다.
 * 물에 잠기지 않는 안전한 영역 :
 *  물에 잠기지 않는 지점들이 위, 아래, 오른쪽, 왼쪽으로 인접해 있으며 그 크기가 최대인 영역
 * 어떤 지역의 높이 정보가 주어졌을 때, 장마철에 물에 잠기지 않는 안전한 영역의 최대 개수를 계산하자.
 *
 * 조건
 * 어떤 지역의 행과 열의 개수 N : 2이상 100이하의 정수
 * 각 칸의 높이는 1이상 100이하의 정수
 *
 * 풀이
 * 1. 지역 정보를 입력받을 때 최저높이와 최고높이를 찾아서 저장하자.
 * 2. 최저높이만큼 비가 왔을 때 ~ 최고높이만큼 비가 왔을 때의 경우를 확인하자.
 * 3. 각 경우에 지역들을 돌면서 안전영역을 표시하고 개수를 세어주자.
 * 4. 최대 개수만을 저장해서 남기고 출력하자. 이 때, 모든 칸의 높이가 동일하다면 기본값은 1로 설정하자.
 */

public class Main {
    static int[] dr = new int[] {-1, 0, 1, 0};
    static int[] dc = new int[] {0, 1, 0, -1};
    static int N, count;
    static int[][] map;
    static boolean[][] visit;
    
    private static void check(int level) {
        
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                // 안전한 칸이면 인접칸이랑 같이 안전지역으로 묶자
                // 이미 다른 안전지역에 포함된 칸인지 확인
                if (map[row][col] > level && !visit[row][col]) {
                    bfs(row, col, level);
                    count++;
                }
            }
        }
    }

    private static void bfs(int row, int col, int level) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {row, col});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (visit[cur[0]][cur[1]]) continue;
            visit[cur[0]][cur[1]] = true;

            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                if (map[nr][nc] <= level) continue;
                if (visit[nr][nc]) continue;
                queue.add(new int[] {nr, nc});
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken()); // 행렬의 크기
        map = new int[N][N]; // 안전지역을 구해야 하는 지역

        // 현재 지역의 최저높이와 최고높이를 찾아두자
        int minLevel = 101;
        int maxLevel = 0;
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());

                minLevel = Math.min(minLevel, map[row][col]);
                maxLevel = Math.max(maxLevel, map[row][col]);
            }
        }

        // 최저높이~최고높이만큼 비가 올 때의 경우들을 확인하자
        int maxSafe = 1;
        for (int level = minLevel; level < maxLevel; level++) {
            count = 0;
            visit = new boolean[N][N];
            check(level); // map의 모든 칸을 확인해보자
            maxSafe = Math.max(maxSafe, count);
        }

        System.out.println(maxSafe);
    }
}
