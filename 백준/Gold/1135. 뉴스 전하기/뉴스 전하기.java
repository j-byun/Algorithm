import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 1135 뉴스 전하기 골드2
 *
 * 문제
 * 회사의 매니저 민식이
 * 회사의 중요한 뉴스를 모든 직원에게 빠르게 전달하자
 * 민식이의 회사는 트리 구조 이다
 * 민식이가 트리의 루트 노드이다
 * 
 * 민식이는 직속 부하에게 한 번에 한 사람씩 전화,
 * 각 부하는 뉴스를 들은 후 그의 직속 부하에게 한 번에 한 사람씩 전화
 * 모든 직원이 뉴스를 들을 때 까지 진행
 * 모든 사람은 자신의 직속 부하에게만 전화 가능, 전화는 1분 걸린다
 * 모든 직원이 소식을 듣는데 걸리는 시간의 최솟값을 구하자.
 * 
 * 민식이의 사원 번호는 0번, 다른 사원의 번호는 1부터 시작
 *
 * 조건
 * 직원의 수 N (50보다 작거나 같은 자연수)
 *
 * 풀이
 * 1. 상사의 번호가 작고, 내 밑에 부하가 많은 순서대로 전화를 받아야 함 (간접 부하도 포함)
 * 2. 우선순위 큐에 담고, 하나씩 뽑아서 확인
 * 3. 현재 상사의 시간을 저장해놓고, +1해서 부하한테 전화, 상사의 시간도 +1
 * 4. 전화받은 부하의 시간 중 최댓값 출력
 * => 틀림
 * 
 * 1. 내 부하들을 다 탐색해서 depth랑 부하 직원 수를 따져봐야함
 */

public class Main {
    static class Staff implements Comparable<Staff>{
        int num, time;

        public Staff(int num, int time) {
            this.num = num;
            this.time = time;
        }

        @Override
        public int compareTo(Staff o) {
            return o.time - this.time;
        }
    }

    static List<Staff>[] list;
    static int[] time;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 직원 수 입력받기

        list = new ArrayList[N]; // i번 직원 밑의 부하들을 저장할 리스트 생성

        for (int idx = 0; idx < N; idx++) {
            list[idx] = new ArrayList<>();
        }

        // i번 밑의 부하들 저장
        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < N; idx++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent == -1) continue;

            list[parent].add(new Staff(idx, 0));
        }

        System.out.println(dfs(0));
    }

    static int dfs(int index) {
        int maxTime = 0;
        int callCount = 0;

        for (Staff child : list[index]) {
            child.time = dfs(child.num);
        }

        Collections.sort(list[index]);

        for (Staff child : list[index]) {
            maxTime = Math.max(maxTime, child.time + (++callCount));
        }

        return maxTime;
    }
}
