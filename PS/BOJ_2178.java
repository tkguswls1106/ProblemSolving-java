import java.util.*;
import java.io.*;
import java.awt.*;

// - 알고리즘: BFS (Maze)

public class BOJ_2178 {
    public static int n, m;
    public static int[][] board = new int[102][102];
    public static int[][] dist = new int[102][102];
    public static int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};

    public static void bfs(int x, int y) {
        if(board[x][y] == 0 || dist[x][y] > 0) return;

        Queue<Point> qu = new LinkedList<>();
        qu.offer(new Point(x,y));
        dist[x][y] = 1;

        while(!qu.isEmpty()) {
            Point cur = qu.poll();

            for(int i=0; i<4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
                if(board[nx][ny] == 0 || dist[nx][ny] > 0) continue;

                qu.offer(new Point(nx,ny));
                dist[nx][ny] = dist[cur.x][cur.y] + 1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt = new StringTokenizer(br.readLine());

        n = Integer.parseInt(stt.nextToken());
        m = Integer.parseInt(stt.nextToken());

        String inputStr;
        for(int i=0; i<n; i++) {
            inputStr = br.readLine();
            for(int j=0; j<m; j++) {
                board[i][j] = inputStr.charAt(j) - '0';
            }
        }

        bfs(0,0);
        System.out.print(dist[n-1][m-1]);
    }
}
