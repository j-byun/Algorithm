
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      int T = sc.nextInt();
      
      for(int tc = 1; tc <= T; tc++) {
         int N = sc.nextInt();
         int M = sc.nextInt();
         int K = sc.nextInt();
         
         int[] arr = new int[N];
         
         for(int idx = 0; idx < N; idx++) {
            arr[idx] = sc.nextInt();
         }
         Arrays.sort(arr);
         
         int sec = 0;
         int fish = 0;
         int i = 0;
         
         if (arr[0] < M) fish = -1;
         
         while(fish >= 0 && sec != arr[N-1]) {
        	 sec++;
            
            if(sec % M == 0) {
               fish += K;
            }
            
            for(int idx = i; idx < N; idx++) {
               if(arr[idx] == sec) {
                  fish--;
                  i = idx;
               }
            }
         }
         if(fish < 0) {
            System.out.println("#" + tc + " Impossible");
         }
         if(fish >= 0 && sec == arr[N-1]) {
            System.out.println("#" + tc + " Possible");
         }
      }
      
   }
}