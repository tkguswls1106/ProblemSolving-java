import java.util.*;
import java.io.*;
import java.awt.*;

// - 알고리즘: BFS
// - 문제 Tip:
// 모든 좌표에서 목표지점을 향해 BFS를 돌리면 시간초과가 남. => '모든 좌표에서의 목표도착지점까지의 거리를 구하는 방식' X
// 따라서 발상을 전환하여, 목표지점에서만 모든 좌표를 향해 BFS를 돌려서 풀면됨. => '목표도착지점에서 출발한 모든 좌표까지의 거리를 구하는 방식' O

public class BOJ_14940 {
    public static int n, m;
    public static int[][] board = new int[1002][1002], dist = new int[1002][1002];
    public static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};

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
        StringBuilder stb = new StringBuilder();

        StringTokenizer stt = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stt.nextToken());
        m = Integer.parseInt(stt.nextToken());

        int goalX = 0, goalY = 0;  // 만약 값초기화없이 'int goalX, goalY;'로만 선언하면 에러뜸.
        for(int i=0; i<n; i++) {
            stt = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) {
                board[i][j] = Integer.parseInt(stt.nextToken());
                if(board[i][j] == 2) {
                    goalX = i;
                    goalY = j;
                }
            }
        }

        bfs(goalX, goalY);

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(board[i][j] == 2 || board[i][j] == 0) {  // 원래 갈 수 없는 땅
                    stb.append(0);
                }
                else {
                    if(dist[i][j] == 0) {  // 원래 갈 수 있는 땅인 부분 중에서 도달할 수 없는 위치
                        stb.append(-1);
                    }
                    else {  // 도달 성공 위치
                        stb.append(dist[i][j] - 1);
                    }
                }
                stb.append(" ");
            }
            stb.append("\n");
        }
        
        System.out.print(stb.toString());
    }
}
