class Solution {
    public int solution(int n, int w, int num) {
        int answer = 1;
        
        int[][] map = new int[n / w + 1][w];
        int start = 0;
        int row = 0;
        int col = 0;
        int numRow = 0;
        int numCol = 0;
        
        while (++start <= n) {
            map[row][col] = start;
            
            if (start == num) {
                numRow = row;
                numCol = col;
            }
            
            if (row % 2 == 0) {
                if (col == w - 1) {
                    row++;
                    continue;
                }
                col++;
                
            } else {
                if (col == 0) {
                    row++;
                    continue;
                }
                col--;
            }
        }
        
        while (++numRow < map.length && map[numRow][numCol] != 0) {
            answer++;
        }
        
        return answer;
    }
}