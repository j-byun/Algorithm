import java.util.*;

/**
 * @author jihye.byun
 * BOJ 1920 수 찾기 실버4
 *
 * 문제
 *
 * 조건
 * N(1 ≤ N ≤ 100,000)
 * M(1 ≤ M ≤ 100,000)
 *
 * 풀이
 */

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();
        int[] arr = new int[N];
        
        for (int idx = 0; idx < N; idx++) {
            arr[idx] = sc.nextInt();
        }
        
        Arrays.sort(arr);
        
        int M = sc.nextInt();
        
        for (int idx = 0; idx < M; idx++) {
            System.out.println(binarySearch(arr, sc.nextInt(), 0, N - 1));
        }
    }
    
    static int binarySearch(int[] arr, int num, int left, int right) {
        
        while (left <= right) {
            int mid = (left + right) / 2;
            
            if (num == arr[mid]) {
                return 1;
            } else  if (num < arr[mid]) {
            	right = mid - 1;
            } else if (num > arr[mid]) {
            	left = mid + 1;
            }
        }
        
        return 0;
    }
}
