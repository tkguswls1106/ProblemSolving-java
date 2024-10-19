// < 성공 방법 1 >
// - 알고리즘: BFS (토마토 문제. 2차원 배열)
// - 추가 Tip: 이렇게 풀어도되고, 애초에 익지않은 토마토만 dist = -1 로 두고 시작하는 방식도 괜찮다.

import java.util.*;
import java.io.*;
import java.awt.*;

public class BOJ_7576 {
    public static int n, m;
    public static int[][] board = new int[1002][1002];
    public static int[][] dist = new int[1002][1002];
    public static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};
    public static Queue<Point> qu = new LinkedList<>();

    public static void bfs() {

        while(!qu.isEmpty()) {
            Point cur = qu.poll();

            for(int i=0; i<4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
                if(board[nx][ny] != 0 || dist[nx][ny] > 0) continue;  // board[nx][ny] != 0 : 초기에 익지않은 토마토를 넣었던 자리만 탐색하겠음.

                qu.offer(new Point(nx,ny));
                dist[nx][ny] = dist[cur.x][cur.y] + 1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt = new StringTokenizer(br.readLine());

        m = Integer.parseInt(stt.nextToken());
        n = Integer.parseInt(stt.nextToken());

        // boolean isAll1 = true;
        for(int i=0; i<n; i++) {
            stt = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) {
                board[i][j] = Integer.parseInt(stt.nextToken());
                if(board[i][j] == 1) qu.offer(new Point(i,j));
                // else if(board[i][j] == 0) isAll1 = false;
            }
        }
        // if(isAll1) {
        //     System.out.print(0);
        //     return;
        // }

        bfs();

        int maxDist = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(board[i][j] == 0 && dist[i][j] == 0) {  // 초기에 익지않은 토마토를 넣었던 자리가, 최종적으로도 그대로 안익었을경우
                    System.out.print(-1);
                    return;
                }
                else if(board[i][j] != -1) {
                    maxDist = Math.max(maxDist, dist[i][j]);
                }
            }
        }

        System.out.print(maxDist);
    }
}

/*
// < 성공 방법 2 >
// - 알고리즘: BFS (토마토 문제. 2차원 배열)

import java.util.*;
import java.io.*;
import java.awt.*;

public class BOJ_7576 {
    public static int n, m;
    public static int[][] board = new int[1002][1002];
    public static int[][] dist = new int[1002][1002];
    public static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};
    public static Queue<Point> qu = new LinkedList<>();

    public static void bfs() {

        while(!qu.isEmpty()) {
            Point cur = qu.poll();

            for(int i=0; i<4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
                if(dist[nx][ny] != -1) continue;  // 안익은토마토가 아니라면 건너뜀.

                qu.offer(new Point(nx,ny));
                dist[nx][ny] = dist[cur.x][cur.y] + 1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt = new StringTokenizer(br.readLine());

        m = Integer.parseInt(stt.nextToken());
        n = Integer.parseInt(stt.nextToken());

        for(int i=0; i<n; i++) {
            stt = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) {
                board[i][j] = Integer.parseInt(stt.nextToken());
                if(board[i][j] == 1) qu.offer(new Point(i,j));
                else if(board[i][j] == 0) dist[i][j] = -1;
            }
        }

        bfs();

        int maxDist = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(dist[i][j] == -1) {  // 초기에 익지않은 토마토를 넣었던 자리가, 최종적으로도 그대로 안익었을경우
                    System.out.print(-1);
                    return;
                }
                else {
                    maxDist = Math.max(maxDist, dist[i][j]);
                }
            }
        }

        System.out.print(maxDist);
    }
}
*/