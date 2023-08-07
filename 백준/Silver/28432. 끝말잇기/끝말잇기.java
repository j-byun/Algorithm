import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author jihye.byun
 * BOJ solved.ac Grand Arena #1 B 끝말잇기
 *
 * 문제
 *
 * 조건
 *
 * 풀이
 */

public class Main {
	
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	
    	int N = Integer.parseInt(st.nextToken());
    	List<String> word = new ArrayList<>(N);
    	
    	for (int idx = 0; idx < N; idx++) {
    		st = new StringTokenizer(br.readLine());
    		word.add(st.nextToken());
    	}
    	
    	char start = '0';
    	char end = '0';
    	for (int idx = 0; idx < N; idx++) {
    		if (word.get(idx).equals("?")) {
    			if (idx != 0)
    				start = word.get(idx - 1).charAt(word.get(idx - 1).length() - 1);
    			if (idx != N - 1)
    				end = word.get(idx + 1).charAt(0);
    		}
    	}
    	
    	st = new StringTokenizer(br.readLine());
    	int M = Integer.parseInt(st.nextToken());
    	for (int idx = 0; idx < M; idx++) {
    		st = new StringTokenizer(br.readLine());
    		String input = st.nextToken();
    		
    		if ((start == '0' || input.charAt(0) == start) && (end == '0' || input.charAt(input.length() - 1) == end) && !word.contains(input)) {
    			bw.write(input);
    		}
    	}
    	
    	bw.flush();
    }
}
