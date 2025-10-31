import java.util.*;
import java.io.*;

// - 알고리즘 : DFS + 백트래킹 (BFS 사방탐색 응용)
// - 유사 문제 : 'DFS_BackTracking_BOJ_1987' 문제

public class BOJ_1189 {
    public static int n = 0, m = 0, targetDist = 0;
    public static int endX = 0, endY = 0;  // target 좌표
    public static int[][] board = new int[7][7];
    public static boolean[][] visited = new boolean[7][7];
    public static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};
    public static int answer = 0;

    public static void dfs(int x, int y, int dist) {
        boolean isMaxDist = (dist == targetDist);
        if(x == endX && y == endY) {
            if(isMaxDist) answer++;
            return;
        }
        else if(isMaxDist) {
            return;
        }

        for(int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
            if(board[nx][ny] == 0 || visited[nx][ny] == true) continue;

            visited[nx][ny] = true;
            dfs(nx, ny, dist+1);

            visited[nx][ny] = false;  // 백트래킹
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stt.nextToken());
        m = Integer.parseInt(stt.nextToken());
        targetDist = Integer.parseInt(stt.nextToken());

        int startX = n - 1, startY = 0;  // start = {n-1, 0}
        endY = m - 1;  // end = {0, m-1}

        for(int i=0; i<n; i++) {
            String rowStr = br.readLine();
            for(int j=0; j<m; j++) {
                char ch = rowStr.charAt(j);
                if(ch == '.') board[i][j] = 1;
            }
        }

        visited[startX][startY] = true;
        dfs(startX, startY, 1);

        System.out.print(answer);
    }
}
