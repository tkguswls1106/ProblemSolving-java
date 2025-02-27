import java.util.*;
import java.io.*;

// - 알고리즘: BFS

public class BOJ_2206 {
    public static int n, m;
    public static int[][] board = new int[1002][1002];
    public static int[][][] dist = new int[1002][1002][2];  // [][][?] => 0: 벽안부수고이동, 1: 벽부수고이동
    public static int[] dx = {1,0,-1,0};
    public static int[] dy = {0,1,0,-1};

    public static class Tuple {
        public int x;
        public int y;
        public int isBreak;  // 다음좌표로 이동여부를 체크할때, 이전에 벽을 뚫은적이 있는지 큐에서 확인 가능.

        public Tuple(int x, int y, int isBreak) {
            this.x = x;
            this.y = y;
            this.isBreak = isBreak;
        }
    }
    
    public static void bfs(int x, int y) {
        Queue<Tuple> qu = new LinkedList<>();
        qu.offer(new Tuple(x, y, 0));
        dist[x][y][0] = 1;

        while(!qu.isEmpty()) {
            Tuple cur = qu.poll();
            int prevIsBreak = cur.isBreak;

            for(int i=0; i<4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx<0 || nx>=n || ny<0 || ny>=m) continue;

                if(board[nx][ny] == 0) {  // 벽 없음
                    if(dist[nx][ny][prevIsBreak] > 0) continue;  // 이미 갔던곳 => 방문 X
                    qu.offer(new Tuple(nx, ny, prevIsBreak));
                    dist[nx][ny][prevIsBreak] = dist[cur.x][cur.y][prevIsBreak] + 1;
                }
                else {  // 벽 있음
                    if(prevIsBreak == 1) continue;  // 더는 뚫을수 없음 => 방문 X
                    if(prevIsBreak == 0 && dist[nx][ny][1] > 0) continue;  // 뚫어서 진입해봤자, 이미 갔던곳 => 방문 X
                    qu.offer(new Tuple(nx, ny, 1));
                    dist[nx][ny][1] = dist[cur.x][cur.y][prevIsBreak] + 1;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stt.nextToken());
        m = Integer.parseInt(stt.nextToken());

        for(int i=0; i<n; i++) {
            String inputStr = br.readLine();
            for(int j=0; j<m; j++) {
                char inputCh = inputStr.charAt(j);
                board[i][j] = inputCh - '0';
            }
        }

        bfs(0, 0);

        int answer = -1;  // 0 0
        int dist1 = dist[n-1][m-1][0], dist2 = dist[n-1][m-1][1];
        if(dist1 != 0) {
            if(dist2 != 0) answer = Math.min(dist1, dist2);  // dist1 dist2
            else answer = dist1;  // dist1 0
        }
        else {
            if(dist2 != 0) answer = dist2;  // 0 dist2
        }

        System.out.print(answer);
    }
}
