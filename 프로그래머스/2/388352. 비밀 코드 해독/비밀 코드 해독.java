import java.util.*;

class Solution {
    
    final int SIZE = 5;
    int answer = 0;
    int[] arr = new int[SIZE];
    
    public int solution(int n, int[][] q, int[] ans) {
        
        comb(0, 1, n, q, ans);
        
        return answer;
    }
    
    void comb(int depth, int start, int n, int[][] q, int[] ans) {
        if (depth == SIZE) {
            if (check(q, ans)) {
                answer++;
            }
            return;
        }
        
        for (int i = start; i <= n; i++) {
            arr[depth] = i;
            comb(depth + 1, i + 1, n, q, ans);
        }
    }
    
    boolean check(int[][] q, int[] ans) {
        int m = q.length;
        
        for (int i = 0; i < m; i++) {
            int count = 0;
            
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    if (arr[k] == q[i][j]) {
                        count++;
                        break;
                    }
                }
            }
            
            if (count != ans[i]) {
                return false;
            }
        }
        
        return true;
    }
}