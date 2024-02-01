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
 * => 메모리 초과! 이분탐색으로 확인을 줄이자
 */

public class Main {

    static int C;
    static String[] words;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken()); // 행의 개수 입력받기
        C = Integer.parseInt(st.nextToken()); // 열의 개수 입력받기

        char[][] table = new char[R][C]; // 테이블 정보를 저장할 배열
        words = new String[C];// 테이블의 문자를 열 기준으로 읽어서 저장할 배열 만들기

        // 테이블 정보 입력받기
        for (int row = 0; row < R; row++) {
            String line = br.readLine();
            for (int col = 0; col < C; col++) {
                table[row][col] = line.charAt(col);
            }
        }

        // 테이블의 문자들을 열 기준으로 읽어서 저장하기
        for (int col = 0; col < C; col++) {
            StringBuilder sb = new StringBuilder();
            for (int row = 0; row < R; row++) {
                sb.append(table[row][col]);
            }
            words[col] = sb.toString();
        }

        // 이분 탐색으로 기준 행 정하기
        System.out.println(binarySearch(0, R - 1));
    }

    static int binarySearch(int start, int end) {

        while (start <= end) {
            int mid = (start + end) / 2;

            // 중간 행을 기준으로 중복 여부 확인
            boolean duplicate = checkDuplicate(mid);

            // 중복이 있으면? 더 위를 기준으로 확인
            if (duplicate) {
                end = mid - 1;
            }

            // 중복이 없으면? 더 아래를 기준으로 확인
            if (!duplicate) {
                start = mid + 1;
            }
        }

        return end;
    }

    static boolean checkDuplicate(int index) {
        HashSet<String> set = new HashSet<>();

        for (int col = 0; col < C; col++) {
            String cur = words[col].substring(index);

            if (set.contains(cur)) {
                return true;
            }

            set.add(cur);
        }

        return false;
    }
}
