import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 12919 A와 B 2 골드5
 * 시간 제한 2초 / 메모리 제한 512 MB
 *
 * 문제
 * A와 B로만 이루어진 영어 단어
 * 두 문자열 S와 T가 주어졌을 때, S를 T로 바꾸자
 * 
 * 문자열을 바꾸는 두 가지 연산
 * 1. 문자열의 뒤에 A를 추가한다
 * 2. 문자열의 뒤에 B를 추가하고 문자열을 뒤집는다
 * 
 * 주어진 조건을 이용해서 S를 T로 만들 수 있는지 없는지 알아내는 프로그램을 작성하시오
 *
 * 조건
 * 1 ≤ S의 길이 ≤ 49
 * 2 ≤ T의 길이 ≤ 50
 * S의 길이 < T의 길이
 *
 * 풀이
 * 1. T에서 시작해서 제일 끝이 A이면 제거 || 제일 처음이 B라면 제거하고 뒤집기
 * 2. 1을 반복해서 dfs탐색하며 S의 길이와 같아졌을 때 문자열이 일치하는지 확인
 * 3. 근데 시간복잡도가 제한을 넘을거같은데..?
 */

public class Main {
    
    static String S;
    static int flag;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        S = st.nextToken(); // 원본 문자열 입력받기

        st = new StringTokenizer(br.readLine());
        String T = st.nextToken(); // 만들어야 하는 문자열 입력받기
        
        flag = 0; // S를 T로 만들 수 있는지 표시할 변수

        editString(T);

        System.out.println(flag);
    }

    static void editString(String s) {
        if (flag == 1) {
            return;
        }

        if (s.length() == S.length()) {
            if (s.equals(S)) {
                flag = 1;
            }
            return;
        }

        if (s.charAt(s.length() - 1) == 'A') {
            editString(s.substring(0, s.length() - 1));
        }

        if (s.charAt(0) == 'B') {
            StringBuilder sb = new StringBuilder();
            sb.append(s.substring(1));
            sb.reverse();
            editString(sb.toString());
        }
    }
}
