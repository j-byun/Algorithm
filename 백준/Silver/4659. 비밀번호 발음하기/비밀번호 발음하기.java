import java.util.*;

/**
 * @author jihye.byun
 * BOJ 4659 비밀번호 발음하기 실버5
 *
 * 문제
 * <높은 품질을 가진 비밀번호의 조건>
 * 1. 모음(a,e,i,o,u) 하나를 반드시 포함
 * 2. 모음이 3개 혹은 자음이 3개 연속으로 오면 안 된다.
 * 3. 같은 글자가 연속적으로 두번 오면 안되나, ee 와 oo는 허용
 *
 * 조건
 * 마지막 테스트 케이스는 end
 * 패스워드는 한글자 이상 20글자 이하의 문자열
 * 패스워드는 대문자를 포함하지 않는다.
 *
 * 풀이
 */

public class Main {
	
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	StringBuilder sb = new StringBuilder();
    	
    	Set<Character> set = new HashSet<>();
    	set.add('a');
    	set.add('e');
    	set.add('i');
    	set.add('o');
    	set.add('u');
    	
    	while (true) {
    		String password = sc.next();
    		
    		if (password.equals("end")) break;
    		
    		boolean isAcceptable = true;
    		int vowelCount = 0;
    		char first = 0;
    		char second = 0;
    		char third = 0;
    		
    		for (int idx = 0; idx < password.length(); idx++) {
    			first = second;
    			second = third;
    			third = password.charAt(idx);
    			
    			if (set.contains(third))
    				vowelCount++;
    			
    			if (idx > 0 && second == third && third != 'e' && third != 'o')
    				isAcceptable = false;
    				
    			if (idx > 1 && ((set.contains(first) && set.contains(second) && set.contains(third)) || (!set.contains(first) && !set.contains(second) && !set.contains(third))))
    				isAcceptable = false;
    		}
    		
    		if (vowelCount < 1)
    			isAcceptable = false;
    		
    		sb.append("<").append(password).append("> is ").append((isAcceptable) ? "acceptable" : "not acceptable").append(".\n");
    	}
    
    	System.out.println(sb.toString());
    }
}
