import java.util.*;
import java.io.*;

public class Main {
	
	static StringBuilder sb;
	static int N, M;
	static int[] arr;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	sb = new StringBuilder();
    	
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	
    	arr = new int[M];
    	
    	dfs(0);
    	
    	System.out.println(sb);
    }
    
    static void dfs(int depth) {
    	if (depth == M) {
    		for (int i : arr) {
    			sb.append(i).append(" ");
    		}
    		sb.append("\n");
    		return;
    	}
    	
    	for (int num = 1; num <= N; num++) {
    		arr[depth] = num;
    		dfs(depth + 1);
    	}
    }
}
