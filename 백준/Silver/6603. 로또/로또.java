import java.util.*;
import java.io.*;

public class Main {
	
	static StringBuilder sb;
	static int k;
	static int[] arr, answer;
	static boolean[] visit;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	sb = new StringBuilder();
    	
    	while (true) {
    		st = new StringTokenizer(br.readLine());
    		
    		k = Integer.parseInt(st.nextToken());
    		if (k == 0) break;
    		
    		arr = new int[k];
    		for (int idx = 0; idx < k; idx++) {
    			arr[idx] = Integer.parseInt(st.nextToken());
    		}
    		
    		answer = new int[6];
    		visit = new boolean[k];
    		
    		perm(0, 0);
    		
    		sb.append("\n");
    	}
    	
    	System.out.println(sb.toString());
    }
    
    static void perm(int depth, int start) {
    	if (depth == 6) {
    		for (int i : answer) {
    			sb.append(i).append(" ");
    		}
    		sb.append("\n");
    		return;
    	}
    	
    	for (int idx = start; idx < k; idx++) {
    		if (visit[idx]) continue;
    		answer[depth] = arr[idx];
    		visit[idx] = true;
    		perm(depth + 1, idx + 1);
    		visit[idx] = false;
    	}
    }
}
