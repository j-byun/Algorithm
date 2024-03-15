import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    
    int[] arr = new int[3];
    
    for (int idx = 0; idx <3; idx++) {
      arr[idx] = sc.nextInt();
    }
    
    Arrays.sort(arr);
    
    System.out.println(arr[1]);
  }
}