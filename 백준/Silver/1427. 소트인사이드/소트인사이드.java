import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	StringBuilder sb = new StringBuilder();
    	
    	int[] count = new int[10];
    	String s = sc.next();
    	
    	for (int idx = 0; idx < s.length(); idx++) {
    		count[s.charAt(idx) - '0']++;
    	}
    	
    	for (int cnt = 9; cnt >= 0; cnt--) {
    		while (count[cnt] > 0) {
    			sb.append(cnt);
    			count[cnt]--;
    		}
    	}
    	
    	System.out.println(sb.toString());
    }
}
