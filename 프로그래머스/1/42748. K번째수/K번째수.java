import java.util.*;
import java.io.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        int[] arr;
        int index;
        
        for (int tc = 0; tc < commands.length; tc++) {
            arr = new int[commands[tc][1] - commands[tc][0] + 1];
            index = 0;
            
            for (int i = commands[tc][0] - 1; i < commands[tc][1]; i++) {
                arr[index++] = array[i];
            }
            
            Arrays.sort(arr);
            answer[tc] = arr[commands[tc][2] - 1];
        }
        
        return answer;
    }
}