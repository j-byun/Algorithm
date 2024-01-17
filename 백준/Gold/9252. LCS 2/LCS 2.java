import java.util.*;

/**
 * @author jihye.byun
 * BOJ 9252 LCS 2 골드4
 *
 * 문제
 *
 * 조건
 * 문자열은 알파벳 대문자로만 이루어져 있으며
 * 최대 1000글자로 이루어져 있다.
 *
 * 풀이
 * 1. dp LCS
 */

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 두 문자열 입력받기
        String stringA = sc.next();
        String stringB = sc.next();
        
        // 두 문자열의 길이 확인
        int sizeA = stringA.length() + 1;
        int sizeB = stringB.length() + 1;

        // 두 문자열을 char 배열로 변환
        char[] a = new char[sizeA];
        char[] b = new char[sizeB];
        
        for (int idx = 1; idx < sizeA; idx++) {
            a[idx] = stringA.charAt(idx - 1);
        }
        
        for (int idx = 1; idx < sizeB; idx++) {
            b[idx] = stringB.charAt(idx - 1);
        }
        
        // LCS값을 저장할 dp배열 생성
        int[][] dp = new int[sizeA][sizeB];
        
        // 두 문자열을 확인하며 LCS 탐색
        for (int indexA = 1; indexA < sizeA; indexA++) {
            for (int indexB = 1; indexB < sizeB; indexB++) {
                if (a[indexA] == b[indexB])
                    dp[indexA][indexB] = dp[indexA - 1][indexB - 1] + 1;
                else
                    dp[indexA][indexB] = Math.max(dp[indexA - 1][indexB], dp[indexA][indexB - 1]);
            }
        }

        // 공통 부분 문자열이 없다면 0출력 후 종료
        if (dp[sizeA - 1][sizeB - 1] == 0) {
            System.out.println(0);
            return;
        }

        // 공통 부분 문자열이 있으면 해당 문자열 찾기
        StringBuilder sb = new StringBuilder();

        int indexA = sizeA - 1;
        int indexB = sizeB - 1;

        while (indexA > 0 && indexB > 0) {
            if (dp[indexA][indexB] == dp[indexA - 1][indexB]) {
                indexA--;
                continue;
            }

            if (dp[indexA][indexB] == dp[indexA][indexB - 1]) {
                indexB--;
                continue;
            }

            sb.append(a[indexA]);
            indexA--;
            indexB--;
        }

        sb.reverse();
        System.out.println(dp[sizeA - 1][sizeB - 1]);
        System.out.println(sb.toString());
    }
}
