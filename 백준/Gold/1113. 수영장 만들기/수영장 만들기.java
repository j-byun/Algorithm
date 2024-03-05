import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 1113 수영장 만들기 골드1
 * 시간 제한 2초 | 메모리 제한 128 MB
 *
 * 문제
 * N*M 크기의 수영장을 만들자.
 * 각 칸의 높이는 다르다.
 * 상하좌우 (대각선 미포함) 칸으로 막혀있어야 물을 가둬서 수영장을 만들 수 있다.
 * 물은 항상 높이가 더 낮은 곳으로만 흐르고,
 * 직육면체의 위의 표면에는 물이 없다.
 * 땅의 높이는 0이고, 땅은 물을 무한대로 흡수한다.
 * 땅의 모양이 주어질 때, 물이 얼마만큼 있을 수 있는지 구하자.
 *
 * 조건
 * N과 M은 50보다 작거나 같은 자연수이다.
 * 땅의 높이는 1보다 크거나 같고, 9보다 작거나 같은 자연수이다.
 *
 * 풀이
 * 1. N과 M, 수영장의 최대 높이가 매우 작기 때문에 물을 1만큼 채우는 경우~9만큼 채우는 경우까지 모두 확인 가능
 * 2. 물이 고일 수 있는 한 덩어리는 너비 우선 탐색으로 찾아서 확인
 * 3. 시간복잡도 : O(N*M*9)
 */

public class Main {

    static class Node {
        int row, col;
        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static int[] dr = new int[] {-1, 0, 1, 0};
    static int[] dc = new int[] {0, 1 ,0, -1};

    static int N, M;
    static int[][] pool, water, tempWater;
    static boolean[][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 수영장 크기 입력받아 수영장과 물이 채워진 수영장 만들기
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        pool = new int[N][M];
        water = new int[N][M];

        // 수영장 정보 입력받기
        for (int row = 0; row < N; row++) {
            String line = br.readLine();
            for (int col = 0; col < M; col++) {
                pool[row][col] = line.charAt(col) - '0';
            }
        }

        // 모든 칸에 대해 물을 9~1만큼 채웠을 때 고일 수 있는지 확인하자
        for (int water = 9; water >= 1; water--) {
            visit = new boolean[N][M]; // 물 높이가 변하면 방문 배열 초기화

            for (int row = 0; row < N; row++) {
                for (int col = 0; col < M; col++) {
                    // 아직 방문하지 않은 칸이고 현재 water보다 낮아 물이 고일 수 있다면 물을 채울 수 있는지 확인
                    if (!visit[row][col] && pool[row][col] < water && pool[row][col] != 0 && checkGroup(row, col, water)) {
                        // 현재 칸부터 만들 수 있는 한 덩어리가 현재 water만큼 물을 채울 수 있는지 확인하고
                        // 물을 채울 수 있다면 해당 덩어리의 최대 물 높이를 현재 water로 채워주자
                        pourWater();
                    }
                }
            }
        }

        // 9~1까지 모든 물 높이를 확인해봤으니 각 칸에 고일 수 있는 물 양의 합을 구해 출력하자
        int totalWater = 0;

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (water[row][col] != 0) {
                    totalWater += water[row][col] - pool[row][col];
                }
            }
        }

        System.out.println(totalWater);
    }

    static boolean checkGroup(int row, int col, int water) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(row, col));

        // 현재 덩어리에 포함되는 칸들을 저장해둘 임시 배열 생성
        tempWater = new int[N][M];
        tempWater[row][col] = water;
        visit[row][col] = true;
        
        // 수영장 범위 밖으로 나가서 물이 넘치거나 빈 땅(0)을 만나서 물이 빠져나가는지 표시할 플래그
        // 물이 채워질 수 있으면 true, 물이 빠져나가면 false
        boolean flag = true;
        
        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nr = cur.row + dr[d];
                int nc = cur.col + dc[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
                    flag = false;
                    continue;
                }

                if (pool[nr][nc] >= water) {
                    continue;
                }

                if (visit[nr][nc]) {
                    continue;
                }

                if (pool[nr][nc] == 0) {
                    flag = false;
                }

                queue.add(new Node(nr, nc));
                visit[nr][nc] = true;
                tempWater[nr][nc] = water;
            }
        }

        return flag;
    }

    static void pourWater() {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                water[row][col] = Math.max(water[row][col], tempWater[row][col]);
            }
        }
    }
}
