import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 3197 백조의 호수 플래티넘5
 * 시간 제한 1초 | 메모리 제한 256 MB
 *
 * 문제
 * 두 마리의 백조가 호수에 살고 있다.
 * 하지만 두 마리는 호수를 덮고 있는 빙판으로 만나지 못한다.
 * 호수는 R*C인 직사각형 모양이다.
 * 어떤 칸은 얼음으로 덮여있다.
 * 호수는 차례로 녹는다.
 * 매일 물과 접촉한 모든 빙판 공간은 녹는다.
 * 두 개의 공간이 접촉하려면 가로나 세로로 닿아 있는 것만 생각한다.
 * 백조는 오직 물 공간에서 가로나 세로로만 움직일 수 있다.
 * 며칠이 지나야 백조들이 만날 수 있는 지 계산하자.
 *
 * 조건
 * 1 ≤ R, C ≤ 1500
 * '.'은 물 공간, 'X'는 빙판 공간, 'L'은 백조가 있는 공간
 *
 * 풀이
 * 1. 0일~계속 증가시키며 빙판을 녹이자.
 *  -> 하루마다 녹일 칸 다 찾아서 boolean배열에 표시해두고 한 번에 녹여야 함
 *  -> 한 칸 녹이고 다음 칸에서 인접 칸 찾게되면 원래 녹아야되는 칸보다 더 깊게 녹을 수도 있음
 * 2. 빙판을 녹였으면 bfs로 두 백조가 만날 수 있는지 매일매일 확인하자.
 * 3. 그렇다면 두 백조가 있는 칸은 물로 표시하고 백조의 좌표를 따로 저장해두자.
 * 4. 시간복잡도 : 전체 칸 1500*1500 * 인접네방향탐색 4 * 최대 날짜 1500 * bfs... -> 시간초과
 * 5. 그렇다면... 물 덩어리들을 하나의 집합으로 묶어서 생각하자 -> union-find
 * 6. 인접 네방향이 다 물인 칸은 다음에 다시 탐색하지 않게 표시해두자.
 */

public class Main {

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class MeltNode {
        int x, y, originX, originY;

        public MeltNode(int x, int y, int originX, int originY) {
            this.x = x;
            this.y = y;
            this.originX = originX;
            this.originY = originY;
        }
    }

    static int[] dr = new int[] {-1, 0, 1, 0};
    static int[] dc = new int[] {0, 1, 0, -1};

    static int R, C;
    static char[][] lake;
    static final char WATER = '.';
    static final char ICE = 'X';
    static final char SWAN = 'L';
    static Node[][] parent;
    static Node swan1, swan2;
    static boolean[][] isPool;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 배열의 크기 입력받기
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        // 호수 생성
        lake = new char[R][C];
        // 부모 노드를 저장할 parent 배열 생성
        parent = new Node[R][C];
        // 웅덩이 안쪽을 두 번 탐색하지 않게 하기위한 boolean 배열 생성
        isPool = new boolean[R][C];

        // 호수 정보 입력받기
        for (int row = 0; row < R; row++) {
            String line = br.readLine();
            for (int col = 0; col < C; col++) {
                char input = line.charAt(col);
                lake[row][col] = input;

                if (input != SWAN) continue;

                // 백조는 위치만 저장해두고 물로 채우기
                if (swan1 == null) {
                    swan1 = new Node(row, col);
                }
                if (swan1 != null) {
                    swan2 = new Node(row, col);
                }
                lake[row][col] = WATER;
            }
        }

        // make-set : parent배열 초기화
        for (int row = 0; row < R; row++) {
            for (int col = 0; col < C; col++) {
                parent[row][col] = new Node(row, col);
            }
        }

        // 초기 호수 상태에서 물 웅덩이들을 union하고, 웅덩이 안쪽 칸은 옆의 얼음을 녹일 필요가 없으니 미리 표시해주자
        for (int row = 0; row < R; row++) {
            for (int col = 0; col < C; col++) {
                if (lake[row][col] == ICE) continue;

                Node originParent = findset(row, col);
                boolean isEdge = false;
                for (int d = 0; d < 4; d++) {
                    int nr = row + dr[d];
                    int nc = col + dc[d];

                    if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
                    if (lake[nr][nc] == ICE) isEdge = true;

                    Node newParent = findset(nr, nc);
                    if (lake[nr][nc] == WATER && originParent != newParent) {
                        union(originParent, newParent);
                    }
                }

                if (!isEdge) isPool[row][col] = true;
            }
        }

        // 이제 얼음을 녹이며 백조가 만날 수 있는지 확인해보자
        int time = 0;

        while (time <= 1501) {
            // 초기 상태에서 백조가 만날 수 있는지 먼저 확인
            if (isSwansMeet()) break;

            // 백조가 못만나면 얼음 녹이기
            time++;
            meltIce();
        }

        System.out.println(time);
    }

    static void meltIce() {
        Queue<MeltNode> queue = new ArrayDeque<>();

        for (int row = 0; row < R; row++) {
            for (int col = 0; col < C; col++) {
                if (lake[row][col] == ICE) continue;
                if (isPool[row][col]) continue;

                for (int d = 0; d < 4; d++) {
                    int nr = row + dr[d];
                    int nc = col + dc[d];

                    if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
                    queue.add(new MeltNode(nr, nc, row, col));
                }

                isPool[row][col] = true;
            }
        }

        while (!queue.isEmpty()) {
            MeltNode cur = queue.poll();

            lake[cur.x][cur.y] = WATER;

            Node newParent = findset(cur.x, cur.y);
            Node originParent = findset(cur.originX, cur.originY);

            if (newParent != originParent) {
                union(newParent, originParent);
            }

            for (int d = 0; d < 4; d++) {
                int nr = cur.x + dr[d];
                int nc = cur.y + dc[d];

                if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
                if (lake[nr][nc] == ICE) continue;

                newParent = findset(cur.x, cur.y);
                Node besidesParent = findset(nr, nc);
                if (newParent != besidesParent) {
                    union(newParent, besidesParent);
                }
            }
        }
    }

    static boolean isSwansMeet() {
        Node parent1 = findset(swan1.x, swan1.y);
        Node parent2 = findset(swan2.x, swan2.y);

        if (parent1 == parent2) return true;
        return false;
    }

    static Node findset(int row, int col) {
        if (parent[row][col].x == row && parent[row][col].y == col) return parent[row][col];
        return parent[row][col] = findset(parent[row][col].x, parent[row][col].y);
    }

    static void union(Node a, Node b) {
        if (a.x > b.x) {
            parent[a.x][a.y] = b;
        }
        if (a.x < b.x) {
            parent[b.x][b.y] = a;
        }
        if (a.x == b.x) {
            if (a.y > b.y) {
                parent[a.x][a.y] = b;
            }
            if (a.y < b.y) {
                parent[b.x][b.y] = a;
            }
        }
    }
}
