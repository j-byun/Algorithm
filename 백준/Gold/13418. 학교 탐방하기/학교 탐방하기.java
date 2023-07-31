import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * @author jihye.byun
 * BOJ 13418 학교 탐방하기 골드3 MST
 *
 * 문제
 * 오르막길을 k번 오를 때, 피로도는 k^2이 된다.
 * 최아그 최선의 경로 간 피로도의 차이를 계산하자.
 *
 * 조건
 * 건물의 개수 N(1 ≤ N ≤ 1,000)
 * 도로의 개수 M(1 ≤ M ≤ N(N-1)/2)
 * A와 B 건물에 연결된 도로 : A, B(1 ≤ A, B ≤ N), C
 * C : 0오르막길 1내리막길
 * 입구는 항상 1번 건물과 연결되어 있다.
 *
 * 풀이
 */

public class Main {
	
	static int N, M, zeroOne;
	static int[] parent;
	static int[][] edge;

    private static int findset(int x) {
        if (x != parent[x])
            return parent[x] = findset(parent[x]);
        return x;
    }

    private static void union(int x, int y) {
        parent[y] = x;
    }

    private static int connectRoad() {
    	int count = 0;
    	int upCount = 0;
    	
    	// 일단 0-1도로를 연결하고 시작
    	union(0, 1);
    	count++;
    	if (zeroOne == 0) upCount++;
    	
    	for (int idx = 0; idx < M; idx++) {
    		int px = findset(edge[idx][0]);
    		int py = findset(edge[idx][1]);
    		
    		if (px == py) continue;
    		
    		union(px, py);
    		count++;
    		if (edge[idx][2] == 0) upCount++;
    		
    		if (count == N) break;
    	}
    	
    	return upCount;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken()); // 건물의 개수
        M = Integer.parseInt(st.nextToken()); // 도로의 개수

        parent = new int[N + 1];
        edge = new int[M][3];

        // makeset
        for (int idx = 0; idx <= N; idx++) {
        	parent[idx] = idx;
        }

        st = new StringTokenizer(br.readLine());
        st.nextToken();
        st.nextToken();
        
        // 0-1도로를 연결하는 길이 오르막길인지 저장해놓자
        zeroOne = Integer.parseInt(st.nextToken());
        
        // 연결 가능한 도로 정보 입력받기
        for (int idx = 0; idx < M; idx++) {
            st = new StringTokenizer(br.readLine());
            edge[idx][0] = Integer.parseInt(st.nextToken());
            edge[idx][1] = Integer.parseInt(st.nextToken());
            edge[idx][2] = Integer.parseInt(st.nextToken());
        }
        
        // 내리막길 우선으로 정렬하고 N개의 도로를 연결해보자
        Arrays.sort(edge, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				return o2[2] - o1[2];
			}
		});
        
        int good = connectRoad();
        
        // parent배열 초기화 (makeset)
        for (int idx = 0; idx <= N; idx++) {
        	parent[idx] = idx;
        }
        
        // 오르막길 우선으로 정렬하고 N개의 도로를 연결해보자
        Arrays.sort(edge, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				return o1[2] - o2[2];
			}
		});
        
        int bad = connectRoad();

        System.out.println((bad * bad) - (good * good));
    }
}
