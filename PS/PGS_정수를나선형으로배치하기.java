class Solution {
    public int[][] solution(int n) {
        int[][] board = new int[n][n];
        int[][] visited = new int[n][n];
        int[] dx = {0,1,0,-1};
        int[] dy = {1,0,-1,0};
        
        board[0][0] = 1;
        visited[0][0] = 1;
        int num = 1, x = 0, y = 0;
        int visitedCnt = 0;
        for(int i=0; i<4;) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx<0 || nx>=n || ny<0 || ny>=n || visited[nx][ny]==1) {
                i++;
                if(i >= 4) i = 0;
                visitedCnt++;
                if(visitedCnt == 2) break;
                continue;
            }

            board[nx][ny] = ++num;
            x = nx;
            y = ny;
            visited[nx][ny] = 1;
            visitedCnt = 0;
        }
        
        return board;
    }
}

public class PGS_정수를나선형으로배치하기 {
    public static void main(String[] args) {
        // 메인함수 코드 생략
        return;
    }
}
