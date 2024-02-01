import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 문자열 잘라내기 골드5
 *
 * 문제
 * R*C 크기의 테이블
 * 테이블의 원소는 알파벳 소문자
 * 각 테이블의 열을 위에서 아래로 읽어서 하나의 문자열을 만들 수 있다.
 * 만약 가장 위의 행을 지워도 테이블의 열을 읽어서 문자열이 중복되지 않는다면,
 * 가장 위의 행을 지워주고, count의 개수를 1증가시키는 과정을 반복한다.
 * 만약 동일한 문자열이 발견되는 경우, 반복을 멈추고 count의 개수를 출력 후 프로그램을 종료한다.
 * 테이블이 주어질 경우 count의 값을 구해보자.
 *
 * 조건
 * 테이블의 행의 개수 R과 열의 개수 C (2 ≤ R, C ≤ 1000)
 * 가장 처음에 주어지는 테이블에는 열을 읽어서 문자열을 만들 때,
 * 동일한 문자열이 존재하지 않는 입력만 주어진다.
 *
 * 풀이
 * 1. 열의 개수만큼 행의 길이를 갖는 문자열이 만들어진다.
 * 2. count = i번째 행까지는 잘라도 중복이 없음을 나타내는 인덱스
 * 3. count < 0인 경우는 주어지지 않는다는 조건
 * 4. 열의 개수만큼 문자열을 저장할 String 배열을 만들어서 테이블의 문자열을 저장
 * 5. HashSet을 만들고, 열의 개수만큼 문자열을 순회하며 substring해서 문자열을 자르고 다시 set에 저장
 * 6. 5번 과정에서 set에 중복 값 검사 후 중복이 발견될 경우 그 전 인덱스 출력
 */

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken()); // 행의 개수 입력받기
        int C = Integer.parseInt(st.nextToken()); // 열의 개수 입력받기

        String[] words = new String[C];// 테이블의 문자를 열 기준으로 읽어서 저장할 배열 만들기

        // 테이블 정보 입력받기
        for (int row = 0; row < R; row++) {
            String line = br.readLine();
            for (int col = 0; col < C; col++) {
                if (row == 0)
                    words[col] = "";
                words[col] += line.charAt(col);
            }
        }

        // col개의 문자들을 0번부터~R-1번부터 시작하는 문자로 잘라서 set에 추가하기
        for (int row = 1; row < R; row++) {
            HashSet<String> set = new HashSet<>(); // 자른 문자열들을 중복없이 저장할 set 생성

            for (int col = 0; col < C; col++) {
                String cur = words[col].substring(row);

                // 중복된 문자가 있으면 해당 row턴 끝내지 않고 종료
                if (set.contains(cur)) {
                    System.out.println(row - 1);
                    return;
                }

                // 중복된 문자가 없으면 set에 추가
                set.add(cur);
            }
        }

        System.out.println(R - 1);
    }
}
