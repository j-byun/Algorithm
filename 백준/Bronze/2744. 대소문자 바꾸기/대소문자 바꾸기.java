import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    
    char[] arr = sc.next().toCharArray();
    
    for (char c : arr) {
      if (Character.isUpperCase(c)) {
        System.out.print(Character.toLowerCase(c));
      } else {
        System.out.print(Character.toUpperCase(c));
      }
    }
  }
}