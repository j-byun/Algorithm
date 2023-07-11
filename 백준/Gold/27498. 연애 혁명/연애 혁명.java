import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author jihye.byun
 * BOJ 27498 연애 혁명 골드3
 *
 * 문제
 * 사랑 관계 중 일부만 남기자.
 * - 이미 성사된 사랑 관계는 포기하도록 하지 않는다.
 * - 사랑 관계가 K각 관계를 이루지 않도록 한다.
 * - 포기하도록 만들 수 있는 경우가 여러가지일 경우 포기하도록 만든 애정도의 합을 최소화한다.
 * 조건을 만족하도록 사랑관계를 포기하게 했을 때, 포기하도록 만든 애정도의 합의 최솟값을 구해보자.
 *
 * 조건
 * K >= 3
 * 학생의 수 N (3 <= N <= 10,000)
 * 사랑 관계의 수 M (N <= M  <= 100,000)
 * 사랑관계를 표현하는 세 개의 정수 ai, bi, ci
 * 학생 ai와 bi, 애정도 ci (1 <= ai < bi <= N; 1 <= ci <= 10,000)
 * 성사 여부 di(공백) : 1인 경우 이미 성사된 사랑 관계, 0인 경우 그렇지 않음
 * 동일한 두 학생 간의 사랑 관계가 여러 번 주어지지 않는다.
 * 성사된 사랑 관계만으로 이루어진 K각 관계는 존재하지 않는다.
 *
 * 풀이
 * 1. K각 관계를 이루지 않는다는 말이 뭘까..? 사이클을 만들어야 한다?
 * 1-1. 그렇다면 크루스칼을 적용해서 N-1개의 간선을 선택하자.
 */

public class Main {
	
	private static int findset(int x, int[] p) {
		if (p[x] != x)
			return findset(p[x], p);
		return x;
	}
	
	private static void union(int x, int y, int[] p) {
		p[y] = x;
	}
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 학생의 수
        int M = Integer.parseInt(st.nextToken()); // 사랑 관계의 수
        
        int[] p = new int[N + 1];
        
        // makeset
        for (int idx = 1; idx <= N; idx++) {
        	p[idx] = idx;
        }
        
        Queue<Node> couple = new ArrayDeque<>();
        PriorityQueue<Node> solo = new PriorityQueue<>();
        
        int start, end, degree, isCouple;
        for (int idx = 0; idx < M; idx++) {
            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
            degree = Integer.parseInt(st.nextToken());
            isCouple = Integer.parseInt(st.nextToken());
            
            if (isCouple == 1)
                couple.add(new Node(start, end, degree));
            else
                solo.add(new Node(start, end, degree));
        }
        
        int sum = 0;
        int pick = 0;
        
        while (!couple.isEmpty()) {
            Node cur = couple.poll();
            
            int px = findset(cur.start, p);
            int py = findset(cur.end, p);
            
            union(px, py, p);
            pick++;
        }
        
        while (!solo.isEmpty()) {
            Node cur = solo.poll();
            
            if (pick >= N - 1) {
            	sum += cur.degree;
            	continue;
            }
            
            int px = findset(cur.start, p);
            int py = findset(cur.end, p);
            
            if (px == py) {
            	sum += cur.degree;
            	continue;
            }
            
            union(px, py, p);
            pick++;
        }
        
        System.out.println(sum);
    }
    
    private static class Node implements Comparable<Node> {
        int start, end, degree;
        public Node(int start, int end, int degree) {
            this.start = start;
            this.end = end;
            this.degree = degree;
        }
        @Override
        public int compareTo(Node o) {
            return o.degree - this.degree;
        }
    }
}