import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 10825 국영수 실버4
 *
 * 문제
 *
 * 조건
 *
 * 풀이
 */

public class Main {
	
	static class Student implements Comparable<Student> {
		String name;
		int korean, english, math;
		
		public Student(String name, int korean, int english, int math) {
			this.name = name;
			this.korean = korean;
			this.english = english;
			this.math = math;
		}

		@Override
		public int compareTo(Student o) {
			if (o.korean == this.korean) {
				if (this.english == o.english) {
					if (o.math == this.math) {
						return this.name.compareTo(o.name);
					}
					return o.math - this.math;
				}
				return this.english - o.english;
			}
			return o.korean - this.korean;
		}
	}
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int N = Integer.parseInt(st.nextToken());
    	
    	PriorityQueue<Student> pq = new PriorityQueue<>();
    	
    	while (N-- > 0) {
    		st = new StringTokenizer(br.readLine());
    		
    		pq.add(new Student(st.nextToken(), Integer.parseInt(st.nextToken()), 
    				Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	
    	while (!pq.isEmpty()) {
    		sb.append(pq.poll().name).append("\n");
    	}
    	
    	System.out.println(sb.toString());
    }
}
