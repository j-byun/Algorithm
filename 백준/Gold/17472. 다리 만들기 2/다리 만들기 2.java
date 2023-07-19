import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author jihye.byun
 * BOJ 17472 다리 만들기 2 골드1
 *
 * 문제
 * N*M 나라 안의 모든 섬을 다리로 연결하자.
 * 섬은 연결된 땅이 상하좌우로 붙어있는 덩어리를 말한다.
 * 다리는 바다에만 건설할 수 있고, 다리의 길이는 다리가 격자에서 차지하는 칸의 수이다.
 * 다리를 연결해서 모든 섬을 연결하자.
 * 다리의 양 끝은 섬과 인접한 바다 위에 있어야 하고, 한 다리의 방향이 중간에 바뀌면 안된다.
 * 또, 다리의 길이는 2 이상이어야 한다.
 * 모든 섬을 연결하는 것이 불가능하면 -1을 출력한다.
 *
 * 조건
 * 1 <= 지도의 세로 크기 N, 지도의 가로 크기 M <= 10
 * 3 <= N*M <= 100
 * 2 <= 섬의 개수 <= 6
 *
 * 풀이
 * 1. MST - 크루스칼 알고리즘을 적용하자.
 * 2. BFS로 섬의 개수를 파악하고, 섬의 개수 - 1개 만큼의 다리를 연결하자.
 * 3. 섬이 있는 칸에서 상하좌우 네 방향으로 탐색하여 길이가 2이상인 다리를 연결할 수 있는 경우 길이 오름차순으로 우선순위 큐에 저장하자.
 */

public class Main {
    // 상우하좌 델타배열
    static int[] dr = new int[] {-1, 0, 1, 0};
    static int[] dc = new int[] {0, 1, 0, -1};

    static int N, M;
    static int[][] map;
    static boolean[][] visit;

    private static void bfs(int row, int col, int index) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {row, col});
        visit[row][col] = true;
        map[row][col] = index;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                if (map[nr][nc] != 1) continue;
                if (visit[nr][nc]) continue;
                queue.add(new int[] {nr, nc});
                visit[nr][nc] = true;
                map[nr][nc] = index;
            }
        }
    }
    
    private static int findset(int x, int[] parent) {
    	if (parent[x] != x)
    		return parent[x] = findset(parent[x], parent);
    	return x;
    }
    
    private static void union(int x, int y, int[] parent) {
    	parent[y] = x;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken()); // 지도의 세로 크기
        M = Integer.parseInt(st.nextToken()); // 지도의 가로 크기
        map = new int[N][M]; // 지도 공간
        visit = new boolean[N][M]; // 방문했던 칸인지 확인할 배열

        // 지도 정보 입력받기
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < M; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        // 섬의 개수를 세어서 각 섬의 위치에 번호를 입력해주자
        int island = 0;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (map[row][col] == 1 && !visit[row][col]) {
                	island++;
                    bfs(row, col, island);
                }
            }
        }
        
        // 각 섬의 부모 섬 번호를 저장할 배열 공간을 만들자
        int[] parent = new int[island + 1];
        
        // makeset
        for (int idx = 0; idx <= island; idx++) {
        	parent[idx] = idx;
        }
        
        // 섬이 있는 자리에서 4방향으로 탐색하며 길이가 2 이상인 다리를 연결할 수 있는지 확인하자
        // 다리로 연결할 두 섬의 번호와 다리의 길이를 저장할 우선순위 큐를 만들자
        // 우선순위 큐를 다리 길이 오름차순으로 정렬하자
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
        
        for (int row = 0; row < N; row++) {
        	for (int col = 0; col < M; col++) {
        		int cur = map[row][col];
        		
        		if (cur == 0) continue;
        		
        		for (int d = 0; d < 4; d++) {
        			int length = 1;
        			
        			while (true) {
        				int nr = row + (dr[d] * length);
        				int nc = col + (dc[d] * length);
        				
        				if (nr < 0 || nr >= N || nc < 0 || nc >= M) break;
        				if (map[nr][nc] == cur) break;
        				if (map[nr][nc] == 0) {
        					length++;
        					continue;
        				}
        				if (length - 1 < 2) break;
        				pq.add(new int[] {cur, map[nr][nc], length - 1});
        				break;
        			}
        		}
        	}
        }
        
        // 이제 섬의 개수 - 1개 만큼의 다리를 연결해주자
        int count = 0;
        int sum = 0;
        while (!pq.isEmpty()) {
        	int[] cur = pq.poll();
        	
        	int px = findset(cur[0], parent);
        	int py = findset(cur[1], parent);
        	
        	if (px == py) continue;
        	
        	union(px, py, parent);
        	count++;
        	sum += cur[2];
        	if (count == island - 1) break;
        }

        System.out.println((count == island - 1)? sum : -1);
    }
}