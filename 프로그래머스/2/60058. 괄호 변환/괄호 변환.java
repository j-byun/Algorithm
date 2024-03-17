import java.util.*;

class Solution {
    public String solution(String p) {
        return separate(p);
    }
    
    static String separate(String word) {
        if (word.length() == 0) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder(word);
        
        int left = 0;
        int right = 0;
        
        for (int idx = 0; idx < word.length(); idx++) {
            if (word.charAt(idx) == '(') {
                left++;
            }
            if (word.charAt(idx) == ')') {
                right++;
            }
            
            if (left == right) {
                boolean isRight = isRightString(sb.substring(0, idx + 1));
                StringBuilder newSb = new StringBuilder();
                
                if (isRight) {
                    newSb.append(sb.substring(0, idx + 1)).append(separate(sb.substring(idx + 1)));
                    return newSb.toString();
                }
                
                if (!isRight) {
                    newSb.append("(").append(separate(sb.substring(idx + 1))).append(")");
                    
                    for (int uIdx = 1; uIdx < idx; uIdx++) {
                        if (word.charAt(uIdx) == '(') {
                            newSb.append(")");
                        }
                        if (word.charAt(uIdx) == ')') {
                            newSb.append("(");
                        }
                    }
                    
                    return newSb.toString();
                }
                
                break;
            }
        }
        
        return "";
    }
    
    static boolean isRightString(String word) {
        Stack<Character> stack = new Stack<>();
        
        for (int idx = 0; idx < word.length(); idx++) {
            if (word.charAt(idx) == '(') {
                stack.push('(');
            }
            if (word.charAt(idx) == ')') {
                if (stack.isEmpty() || stack.peek() != '(') {
                    return false;
                }
                stack.pop();
            }
        }
        
        return stack.size() == 0;
    }
}