import java.util.*;

class Solution {
    
    int minLength;
    
    public int solution(String s) {
        int size = s.length(); // 문자열의 길이
        // 1개 ~ size/2개 까지 진행해보자
        
        minLength = size; // 압축 시 가장 짧은 문자열 길이를 저장할 변수
        
        // 1개 ~ size/2개 단위까지 문자열 압축 진행
        for (int length = 1; length <= size / 2; length++) {
            condenseString(s, size, length);
        }
        
        // 압축 후 가장 짧은 문자열 길이 반환
        return minLength;
    }
    
    void condenseString(String s, int size, int unit) {
        Stack<String> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int index = 0;
        
        while (index < size) {
            String cur = s.substring(index, Math.min(index + unit, size));
            
            // 앞 문자열이랑 같으면|비교할 문자열이 없으면 그냥 stack에 추가
            if (stack.isEmpty() || stack.peek().equals(cur)) {
                stack.push(cur);
            }
            
            // 앞 문자열이랑 다르면 앞에꺼 먼저 빼주고 나를 stack에 추가
            if (!stack.isEmpty() && !stack.peek().equals(cur)) {
                if (stack.size() == 1) {
                    sb.append(stack.peek());
                }
                if (stack.size() > 1) {
                    sb.append(stack.size()).append(stack.peek());
                }
                stack.clear();
                stack.push(cur);
            }
            
            index += unit;
        }
        
        // 마지막에 stack에 남아있는 문자열 처리
        if (stack.size() == 1) {
            sb.append(stack.peek());
        }
        if (stack.size() > 1) {
            sb.append(stack.size()).append(stack.peek());
        }
        
        minLength = Math.min(minLength, sb.length());
    }
}