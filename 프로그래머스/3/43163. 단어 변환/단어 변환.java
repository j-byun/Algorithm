class Solution {
    
    static int size, answer;
    static boolean[] visit;
    
    public int solution(String begin, String target, String[] words) {
        size = words.length;
        
        // target을 못만들면 애초에 탐색 X
        boolean canMakeTarget = false;
        
        for (int idx = 0; idx < size; idx++) {
            if (words[idx].equals(target)) {
                canMakeTarget = true;
            }
        }
        
        if (!canMakeTarget) {
            return 0;
        }
        
        // target이 words안에 있으면 일단 탐색
        answer = Integer.MAX_VALUE;
        visit = new boolean[size];
        
        dfs(0, begin, target, words);
        
        // 다 탐색해도 못만들면 0 반환
        if (answer == Integer.MAX_VALUE) {
            answer = 0;
        }
        
        return answer;
    }
    
    void dfs(int count, String now, String target, String[] words) {
        if (now.equals(target)) {
            answer = Math.min(answer, count);
            return;
        }
        
        for (int idx = 0; idx < size; idx++) {
            if (visit[idx]) continue;
            if (!canChange(now, words[idx])) continue;
            visit[idx] = true;
            dfs(count + 1, words[idx], target, words);
            visit[idx] = false;
        }
    }
    
    boolean canChange(String a, String b) {
        int length = a.length();
        int count = 0;
        
        for (int idx = 0; idx < length; idx++) {
            if (a.charAt(idx) != b.charAt(idx)) {
                count++;
            }
        }
        
        return count == 1;
    }
}