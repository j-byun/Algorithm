import java.util.*;
import java.io.*;

/**
 * @author jihye.byun
 * BOJ 12100 2048 (Easy) 골드2
 * 시간 제한 1초 | 메모리 제한 512 MB
 *
 * 문제
 * 보드의 크기가 N*N인 2048게임
 * 보드의 크기와 보드판의 블록 상태가 주어졌을 때,
 * 최대 5번 이동해서 만들 수 있는 가장 큰 블록의 값을 구하자
 *
 * 조건
 * 보드의 크기 N (1 ≤ N ≤ 20)
 * 0은 빈 칸을 나타내며, 이외의 값은 모두 블록을 나타낸다.
 * 블록에 쓰여있는 수는 2 <= a <= 1024 를 만족하는 2의 제곱꼴이다.
 * 블록은 적어도 하나 주어진다.
 *
 * 풀이
 * 1. 상하좌우 델타배열을 만들어서 4방향 * 5번 이동하는 경우를 구현해보자.
 * 2. 이전 이동 상태의 영향을 받으니 dfs탐색을 하자. => 어차피 배열을 들고다녀야 되겠네... 그럼 bfs도 가능
 * 3. 시간복잡도 : 5번 이동 * 4방향 * 배열복사 20*20=400 * 블록합치기 20*20=400*한칸에서확인하는최대범위20=8000 = 64,000,000
 * 3-1. 따라서, 시간 제한 1초 안에 구현 가능
 */

public class Main {

    static int N, maxNum;
    static int[] dr = new int[] {-1, 0, 1, 0};
    static int[] dc = new int[] {0, 1, 0, -1};
    static int[] startRow, startCol, endRow, endCol;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 보드의 크기 입력받기

        // 방향별 시작점, 끝점 좌표값 설정
        startRow = new int[] {0, N-1, N-1, 0};
        startCol = new int[] {0, N-1, N-1, 0};
        endRow = new int[] {N-1, 0, 0, N-1};
        endCol = new int[] {N-1, 0, 0, N-1};

        int[][] board = new int[N][N]; // 보드판 생성

        // 보드판 정보 입력받기
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; col++) {
                board[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        maxNum = 0; // 5번 이동했을 때의 최댓값을 저장할 변수 초기화

        dfs(0, board); // dfs 탐색으로 2048게임 진행

        // 최대 5번 이동했을 때의 최댓값 출력
        System.out.println(maxNum);
    }

    static void dfs(int count, int[][] originBoard) {

        // 5번 이동했으면 최댓값 확인 후 돌아가기
        if (count == 5) {
            updateMaxNum(originBoard);
            return;
        }

        // 아직 5번 이동하지 않았으면 상하좌우 중 한 방향으로 더 이동하기
        for (int d = 0; d < 4; d++) {
            // 보드판 복사하기
            int[][] newBoard = new int[N][N];
            copyBoard(originBoard, newBoard);

            // 보드판 움직이기
            moveBoard(d, newBoard);

            // 이동시킨 보드판 가지고 다음 턴으로 넘어가기
            dfs(count + 1, newBoard);
        }
    }

    static void copyBoard(int[][] originBoard, int[][] newBoard) {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                newBoard[row][col] = originBoard[row][col];
            }
        }
    }

    static void moveBoard(int d, int[][] board) {

        int row = startRow[d];
        int col = startCol[d];
        int cnt = 0;

        while (row >= 0 && row < N && col >= 0 && col < N) {
            // 현재 칸이 0이면 다음 칸 중 숫자가 있는 칸 찾기
            if (board[row][col] == 0) {
                int nr = row - dr[d];
                int nc = col - dc[d];

                while (nr >= 0 && nr < N && nc >= 0 && nc < N) {
                    if (board[nr][nc] != 0) {
                        board[row][col] = board[nr][nc];
                        board[nr][nc] = 0;
                        break;
                    }

                    nr -= dr[d];
                    nc -= dc[d];
                }
            }
            
            // 현재 칸이 0이 아니라면? 다음 칸 중 가장 가까이 있는 칸이 같은 숫자인지 확인하고 합치기
            if (board[row][col] != 0) {
                int nr = row - dr[d];
                int nc = col - dc[d];

                while (nr >= 0 && nr < N && nc >= 0 && nc < N) {
                    if (board[nr][nc] != 0) {
                        if (board[nr][nc] == board[row][col]) {
                            board[row][col] *= 2;
                            board[nr][nc] = 0;
                        }
                        break;
                    }

                    nr -= dr[d];
                    nc -= dc[d];
                }
            }
            
            // 위의 과정 다~ 했는데도 현재 칸이 0이라면? 더 이상 할 게 없음!
            // 다음 줄로 넘어가기
            if (board[row][col] == 0) {
                cnt++;
                row = startRow[d] - (dc[d] * cnt);
                col = startCol[d] - (dr[d] * cnt);
                continue;
            }

            // 현재칸이 0이 아니라면? 다음 칸으로 넘어가기
            row -= dr[d];
            col -= dc[d];
            if (row < 0 || row >= N || col < 0 || col >= N) {
                cnt++;
                row = startRow[d] - (dc[d] * cnt);
                col = startCol[d] - (dr[d] * cnt);
            }
        }
    }

    static void updateMaxNum(int[][] board) {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                maxNum = Math.max(maxNum, board[row][col]);
            }
        }
    }
}
