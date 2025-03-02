import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	int N = Integer.parseInt(br.readLine());
    	
    	int count = 0;
    	int[] lastIndex;
    	
    	for (int tc = 1; tc <= N; tc++) {
    		String line = br.readLine();
    		
    		lastIndex = new int[26];
    		Arrays.fill(lastIndex, -1);
    		
    		for (int idx = 0; idx < line.length(); idx++) {
    			int charIdx = line.charAt(idx) - 'a';
    			
    			if (lastIndex[charIdx] == -1 || lastIndex[charIdx] == idx - 1) {
    				lastIndex[charIdx] = idx;
    				
    				if (idx == line.length() - 1) count++;
    				
    			} else {
    				break;
    			}
    		}
    	}
    	
    	System.out.println(count);
    }
}
