class Solution {
    public int solution(int storey) {
        int answer = 0;
        
        while (storey > 0) {
            int rest = storey % 10;
            storey /= 10;
            
            if ((rest < 5) || (rest == 5 && storey % 10 < 5)) {
                answer += rest;
            }
            
            if ((rest > 5) || (rest == 5 && storey % 10 >= 5)) {
                answer += 10 - rest;
                storey += 1;
            }
        }
        
        return answer;
    }
}