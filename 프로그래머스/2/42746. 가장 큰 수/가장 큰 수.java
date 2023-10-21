import java.util.*;

class Solution {
    public String solution(int[] numbers) {
         String[] arr = new String[numbers.length];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = String.valueOf(numbers[i]);
        }

        Arrays.sort(arr, (o1, o2) -> Integer.parseInt(o2 + o1) - Integer.parseInt(o1 + o2));
        
        if (arr[0].equals("0")) {
           return "0";
        }

        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < arr.length; i++) {
            answer.append(arr[i]);
        }


        return answer.toString();
    }
}