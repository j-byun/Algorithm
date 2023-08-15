import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author jihye.byun
 * BOJ 2252 줄 세우기 골드3 위상정렬
 *
 * 문제
 * N명의 학생들을 키 순서대로 줄 세우자
 * 일부 학생들에 대해, 두 학생의 키를 비교해 보았다.
 * 일부 학생들의 키를 비교한 결과가 주어졌을 때, 줄을 세우는 프로그램을 작성하시오.
 *
 * 조건
 * 학생 수 N(1 ≤ N ≤ 32,000)
 * 키를 비교한 회수 M(1 ≤ M ≤ 100,000)
 * 학생들의 번호는 1번부터 N번이다.
 *
 * 풀이
 */

public class Main {
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	
    	int N = Integer.parseInt(st.nextToken());
    	int M = Integer.parseInt(st.nextToken());
    	
    	int[] inDegree = new int[N + 1]; // 진입차수를 저장할 배열
    	List<Integer>[] adjList = new ArrayList[N + 1]; // 인접 리스트
    	
    	// 인접 리스트 생성 (초기화)
    	for (int idx = 1; idx <= N; idx++) {
    		adjList[idx] = new ArrayList<>();
    	}
    	
    	// 인접 리스트 입력받기
    	for (int cnt = 0; cnt < M; cnt++) {
    		st = new StringTokenizer(br.readLine());
    		
    		int a = Integer.parseInt(st.nextToken());
    		int b = Integer.parseInt(st.nextToken());
    		adjList[a].add(b);
    		inDegree[b]++;
    	}
    	
    	// 위상정렬 - 큐
    	Queue<Integer> queue = new ArrayDeque<>();
    	
    	// 진입차수가 0인 모든 노드 큐에 삽입
    	for (int idx = 1; idx <= N; idx++) {
    		if (inDegree[idx] == 0)
    			queue.add(idx);
    	}
    	
    	while (!queue.isEmpty()) {
    		int cur = queue.poll();
    		bw.write(cur + " ");
    		
    		for (int next : adjList[cur]) {
    			inDegree[next]--;
    			if (inDegree[next] == 0)
    				queue.add(next);
    		}
    	}
    	
    	bw.flush();
    }
}
