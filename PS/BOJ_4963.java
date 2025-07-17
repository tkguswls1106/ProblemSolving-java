import java.util.*;
import java.io.*;
import java.awt.Point;

// - 알고리즘: BFS

public class BOJ_4963 {
    public static int n, m;
    public static int[][] board;
    public static boolean[][] visited;
    public static int[] dx = {1, 0, -1, 0, 1, 1, -1, -1};  // 상하좌우 및 대각선까지 이동 가능.
    public static int[] dy = {0, 1, 0, -1, 1, -1, 1, -1};

    public static int bfs(int x, int y) {
        if(board[x][y] == 0 || visited[x][y] == true) return 0;

        Queue<Point> qu = new LinkedList<>();
        qu.offer(new Point(x, y));
        visited[x][y] = true;

        while(!qu.isEmpty()) {
            Point cur = qu.poll();

            for(int i=0; i<8; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
                if(board[nx][ny] == 0 || visited[nx][ny] == true) continue;

                qu.offer(new Point(nx, ny));
                visited[nx][ny] = true;
            }
        }

        return 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();

        StringTokenizer stt;
        while(true) {
            String inputStr = br.readLine();
            if(inputStr.equals("0 0")) {
                System.out.print(stb.toString());
                return;
            }

            stt = new StringTokenizer(inputStr);
            m = Integer.parseInt(stt.nextToken());
            n = Integer.parseInt(stt.nextToken());
            board = new int[n][m];
            visited = new boolean[n][m];
            for(int i=0; i<n; i++) {
                stt = new StringTokenizer(br.readLine());
                for(int j=0; j<m; j++) {
                    board[i][j] = Integer.parseInt(stt.nextToken());
                }
            }

            int cnt = 0;
            for(int i=0; i<n; i++) {
                for(int j=0; j<m; j++) {
                    cnt += bfs(i, j);
                }
            }
            stb.append(cnt).append("\n");
        }
    }
}
