import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 4195 친구 네트워크 골드2
 *
 * 문제
 * 소셜 네트워크 사이트에서 친구를 모으자.
 * 어떤 사이트의 친구 관계가 생긴 순서대로 주어졌을 때, 두 사람의 친구 네트워크에 몇 명이 있는지 구하자.
 * 친구 네트워크란 친구 관계만으로 이동할 수 있는 사이를 말한다.
 *
 * 조건
 * 친구 관계의 수 F는 100,000을 넘지 않는다.
 * F개의 친구 관계가 생긴 순서대로 주어진다.
 * 친구 관계는 두 사용자의 아이디(알파벳 대문자 또는 소문자로만 이루저니 길이 20이하의 문자열)로 이루어져 있다.
 * 친구 관계가 생길 때마다, 두 사람의 친구 네트워크에 몇 명이 있는지 구하는 프로그램을 작성하시오.
 *
 * 풀이
 * 1. 친구의 이름이 들어오면 이를 그 친구의 인덱스 번호로 변환해서 생각해야 하니 HashMap을 활용하자.
 * 2. 친구 관계가 입력되면, union하고, root친구의 rank를 확인하여 출력하자.
 * 3. 같은 친구 관계가 중복으로 들어올 수도 있을 것 같은데? 이 경우도 고려하자.
 */

public class Main {

    static HashMap<String, Integer> map;
    static ArrayList<Integer> parent, rank;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(st.nextToken()); // 테스트케이스 개수 입력받기
        
        // 테케 개수만큼 반복
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int F = Integer.parseInt(st.nextToken()); // 친구 관계 개수 입력받기

            map = new HashMap<>(); // 친구의 이름과 인덱스 번호를 저장할 HashMap 생성
            parent = new ArrayList<>(); // 친구의 root친구 인덱스를 저장할 ArrayList (친구가 몇 명인지 몰라서 배열 X)
            rank = new ArrayList<>(); // 친구의 rank를 저장할 ArrayList (친구가 몇 명인지 몰라서 배열 X)

            // 친구 관계 개수만큼 반복
            for (int cnt = 1; cnt <= F; cnt++) {
                st = new StringTokenizer(br.readLine());
                // 두 친구의 이름 입력받아서 인덱스 번호 찾기
                int friend1 = getFriendsIndex(st.nextToken());
                int friend2 = getFriendsIndex(st.nextToken());

                // 처음 들어온 친구라면 parent와 rank 초기값 세팅
                if (parent.size() <= friend1) {
                    makeSet(friend1);
                }
                if (parent.size() <= friend2 ) {
                    makeSet(friend2);
                }

                // 두 친구의 root친구 인덱스 번호 찾기
                int p1 = find(friend1);
                int p2 = find(friend2);

                // 둘이 이미 친구라면? root친구의 rank 출력
                if (p1 == p2) {
                    bw.write(rank.get(p1) + "\n");
                    continue;
                }

                // 둘이 아직 친구가 아니라면? union하고 root친구의 rank 출력
                union(p1, p2);
                bw.write(rank.get(find(p1)) + "\n");
            }
        }

        bw.flush();
    }

    static int getFriendsIndex(String name) {
        // String으로 들어온 친구의 이름을 int index로 변환해서 반환하는 메서드
        
        // 아직 HashMap에 저장되지 않은 친구라면 새로운 인덱스 만들어서 return
        // HashMap에 이미 저장된 친구라면 (인덱스 번호를 가지고 있는 친구라면) 그 번호 return
        if (!map.containsKey(name)) {
            map.put(name, map.size());
        }

        return map.get(name);
    }

    static void makeSet(int index) {
        // 새로 들어온 친구의 parent와 rank 초기값을 세팅하는 메서드

        parent.add(index);
        rank.add(1);
    }

    static int find(int index) {
        // root 친구의 index를 반환하는 메서드

        if (parent.get(index) == index)
            return index;
        return find(parent.get(index));
    }
    
    static void union(int x, int y) {
        // 두 친구를 네트워크로 연결하는 메서드

        // 랭크가 더 높은 쪽을 부모로 설정
        if (rank.get(x) >= rank.get(y)) {
            parent.set(y, x);
            rank.set(x, rank.get(x) + rank.get(y));
        }
        if (rank.get(x) < rank.get(y)) {
            parent.set(x, y);
            rank.set(y, rank.get(y) + rank.get(x));
        }
    }
}
