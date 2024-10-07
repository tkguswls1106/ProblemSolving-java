import java.util.*;
import java.io.*;

public class BOJ_1600 {
    public static int k, n, m;
    public static int[][] board = new int[202][202];
    public static int[][][] dist = new int[202][202][32];  // [x][y][말이동횟수] : 말이동횟수 몇번사용해서 (x,y)좌표에 도달했는지 + 거리
    public static int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};
    public static int[] kx = {-1,-1,-2,-2,1,1,2,2}, ky = {-2,2,-1,1,-2,2,-1,1};

    public static class Tuple {
        int x;
        int y;
        int k;

        public Tuple(int x, int y, int k) {
            this.x = x;
            this.y = y;
            this.k = k;
        }
    }

    public static void bfs(int x, int y) {
        Queue<Tuple> qu = new LinkedList<>();
        qu.offer(new Tuple(x,y,0));

        while(!qu.isEmpty()) {
            Tuple cur = qu.poll();
            int cntK = cur.k;

            for(int i=0; i<4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
                if(board[nx][ny] == 1 || dist[nx][ny][cntK] > 0) continue;

                qu.offer(new Tuple(nx,ny,cntK));
                dist[nx][ny][cntK] = dist[cur.x][cur.y][cntK] + 1;
            }

            if(cntK == k) continue;
            for(int i=0; i<8; i++) {
                int nx = cur.x + kx[i];
                int ny = cur.y + ky[i];

                if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
                if(board[nx][ny] == 1 || dist[nx][ny][cntK+1] > 0) continue;

                qu.offer(new Tuple(nx,ny,cntK+1));
                dist[nx][ny][cntK+1] = dist[cur.x][cur.y][cntK] + 1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt = new StringTokenizer(br.readLine());

        k = Integer.parseInt(stt.nextToken());
        stt = new StringTokenizer(br.readLine());
        m = Integer.parseInt(stt.nextToken());
        n = Integer.parseInt(stt.nextToken());

        for(int i=0; i<n; i++) {
            stt = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) {
                board[i][j] = Integer.parseInt(stt.nextToken());
            }
        }

        if(n==1 && m==1 && board[0][0]==0) {
            System.out.print(0);
            return;
        }
        bfs(0,0);

        int minDist = (int) 1e9;
        for(int i=0; i<=k; i++) {
            int distValue = dist[n-1][m-1][i];
            if(distValue != 0) minDist = Math.min(minDist, distValue);
        }
        if(minDist == (int) 1e9) minDist = -1;

        System.out.print(minDist);
    }
}