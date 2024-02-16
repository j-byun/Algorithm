import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 14719 빗물 골드5
 * 시간 제한 1초 / 메모리 제한 256 MB
 *
 * 문제
 * 2차원 세계에 쌓여있는 블록
 * 비가 오면 블록 사이에 빗물이 고인다
 * 비는 충분히 많이 온다.
 * 고이는 빗물의 총량은 얼마일까?
 *
 * 조건
 * 2차원 세계의 세로길이 H와 가로길이 W (1 ≤ H, W ≤ 500)
 * 블록 내부의 빈 공간이 생길 수 없다.
 * 2차원 세계의 바닥은 항상 막혀있다고 가정해도 좋다.
 * 한 칸의 용량은 1이다.
 * 빗물이 전혀 고이지 않을 경우 0을 출력하여라.
 *
 * 풀이
 * 1. 양쪽 옆으로 벽이 뚫려있으면 고이지 않고 빠져나감 -> 경계의 높이가 중요
 * 2. 왼쪽으로 진행하며 -> 높이를 입력받고, 다음 칸의 높이가 더 낮으면 이전 최대 높이와의 차이를 저장
 * 3. 다음 칸의 높이가 더 높으면 지금까지 저장한 차이만큼 빗물을 모아놓고 최대 높이 업데이트
 * 4. 최대 높이인 칸을 하나 찾아놓고 그 칸까지 양쪽에서 진행하자
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int H = Integer.parseInt(st.nextToken()); // 세로길이 입력받기
        int W = Integer.parseInt(st.nextToken()); // 가로길이 입력받기
        
        int[] block = new int[W]; // W개의 블록 높이를 저장할 세계
        int maxHeight = 0; // 블록의 최대 높이를 저장할 변수
        int maxIndex = 0; // 최대 높이인 블록의 인덱스를 저장할 변수
        
        // W개의 블록 높이 입력받아 저장하기
        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < W; idx++) {
            block[idx] = Integer.parseInt(st.nextToken());
            if (maxHeight < block[idx]) {
                maxHeight = block[idx];
                maxIndex = idx;
            }
        }

        int totalRain = 0;
        int tempRain = 0;

        // -> 방향으로 진행
        int before = block[0];
        for (int idx = 1; idx <= maxIndex; idx++) {
            if (block[idx] < before) {
                tempRain += before - block[idx];
            }
            if (block[idx] >= before) {
                totalRain += tempRain;
                tempRain = 0;
                before = block[idx];
            }
        }

        tempRain = 0;

        // <- 방향으로 진행
        before = block[W - 1];
        for (int idx = W - 2; idx >= maxIndex; idx--) {
            if (block[idx] < before) {
                tempRain += before - block[idx];
            }
            if (block[idx] >= before) {
                totalRain += tempRain;
                tempRain = 0;
                before = block[idx];
            }
        }

        System.out.println(totalRain);
    }
}
