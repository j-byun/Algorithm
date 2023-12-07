import java.util.*;

/**
 * @author jihye.byun
 * BOJ 17478 재귀함수가 뭔가요? 실버5
 *
 * 문제
 *
 * 조건
 *
 * 풀이
 */

public class Main {
	
	static final String FIRST = "어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.";
	static final String SECOND = "\"재귀함수가 뭔가요?\"";
	static final String THIRD = "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.";
	static final String FOURTH = "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.";
	static final String FIFTH = "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"";
	static final String SIXTH = "라고 답변하였지.";
	static final String ANSWER = "\"재귀함수는 자기 자신을 호출하는 함수라네\"";
	
	static StringBuilder sb;
	
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	int N = sc.nextInt();
    	
    	sb = new StringBuilder();
    	
    	sb.append(FIRST).append("\n");
    	recursion(0, N);
    	
    	System.out.println(sb.toString());
    }
    
    static void recursion(int depth, int maxDepth) {
    	
    	StringBuilder newSb = new StringBuilder();
    	
    	for (int cnt = 0; cnt < depth; cnt++) {
    		newSb.append("____");
    	}
    	
    	if (depth < maxDepth) {
    		sb.append(newSb.toString()).append(SECOND).append("\n");
    		sb.append(newSb.toString()).append(THIRD).append("\n");
    		sb.append(newSb.toString()).append(FOURTH).append("\n");
    		sb.append(newSb.toString()).append(FIFTH).append("\n");
    		recursion(depth + 1, maxDepth);
    		sb.append(newSb.toString()).append(SIXTH).append("\n");
    	}
    	
    	if (depth == maxDepth ) {
    		sb.append(newSb.toString()).append(SECOND).append("\n");
    		sb.append(newSb.toString()).append(ANSWER).append("\n");
    		sb.append(newSb.toString()).append(SIXTH).append("\n");
    	}
    	
    }
}
