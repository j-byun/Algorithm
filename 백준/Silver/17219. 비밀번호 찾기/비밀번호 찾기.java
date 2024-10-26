import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        Map<String, String> map = new HashMap<>();
        
        for (int idx = 0; idx < N; idx++) {
            st = new StringTokenizer(br.readLine());
            map.put(st.nextToken(), st.nextToken());
        }
        
        for (int idx = 0; idx < M; idx++) {
            st = new StringTokenizer(br.readLine());
            sb.append(map.get(st.nextToken())).append("\n");
        }
        
        System.out.println(sb.toString());
    }
}