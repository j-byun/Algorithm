import java.util.*;

/**
 * @author jihye.byun
 * BOJ 10814 나이순 정렬 실버5
 *
 * 문제
 *
 * 조건
 *
 * 풀이
 */

public class Main {
	
	static class Member implements Comparable<Member> {
		int age;
		String name;
		
		public Member(int age, String name) {
			this.age = age;
			this.name = name;
		}

		@Override
		public int compareTo(Member o) {
			// TODO Auto-generated method stub
			return this.age - o.age;
		}
	}
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	int N = sc.nextInt();
    	
    	Member[] members = new Member[N];
    	
    	for (int idx = 0; idx < N; idx++) {
    		members[idx] = new Member(sc.nextInt(), sc.next());
    	}
    	
    	Arrays.sort(members);
    	
    	StringBuilder sb = new StringBuilder();
    	
    	for (int idx = 0; idx < N; idx++) {
    		sb.append(members[idx].age).append(" ").append(members[idx].name).append("\n");
    	}
    	
    	System.out.println(sb.toString());
    }
}
