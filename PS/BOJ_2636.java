import java.util.*;
import java.io.*;
import java.awt.Point;

// - 알고리즘: BFS
// - 문제 Tip: 치즈가 아닌곳을 BFS로 순회해서 visited를 적용시키면, 외곽 외에는 더이상 안쪽으로 접근할 수 없어서 테두리만 확인이 가능해짐.

public class BOJ_2636 {
    public static int n, m;
    public static int[][] board = new int[102][102];
    public static int[][] visited;
    public static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};
    public static int cheeseCnt = 0;
    public static StringBuilder answerStb = new StringBuilder();

    public static void bfs(int x, int y) {  // 치즈가 아닌곳을 BFS로 순회. (이후 테두리 한번까지는 허용.)
        Queue<Point> qu = new LinkedList<>();
        qu.offer(new Point(x, y));
        visited[x][y] = 1;

        while(!qu.isEmpty()) {
            Point cur = qu.poll();

            for(int i=0; i<4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
                if(visited[nx][ny] == 1) continue;

                if(board[nx][ny] == 0) {
                    qu.offer(new Point(nx, ny));
                }
                else {  // 치즈인 경우, 테두리 녹임 처리.
                    board[nx][ny] = 0;
                    cheeseCnt--;
                }
                visited[nx][ny] = 1;

                if(cheeseCnt == 0) return;  // 성능 누수 방지.
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stt = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stt.nextToken());
        m = Integer.parseInt(stt.nextToken());
        for(int i=0; i<n; i++) {
            stt = new StringTokenizer(br.readLine());
            if(i==0 || i==n-1) continue;  // 가장자리 제외
            for(int j=0; j<m-1; j++) {
                board[i][j] = Integer.parseInt(stt.nextToken());
                if(board[i][j] == 1) cheeseCnt++;
            }
        }

        if(cheeseCnt == 0) {
            System.out.print("0\n0");
            return;
        }

        int time = 0;
        int prevCheeseCnt = cheeseCnt;  // 녹기 한시간전의 남은 치즈개수
        while(true) {
            visited = new int[n][m];
            bfs(0, 0);
            time++;

            if(cheeseCnt == 0) {
                answerStb.append(time).append("\n").append(prevCheeseCnt);
                System.out.print(answerStb.toString());
                return;
            }
            prevCheeseCnt = cheeseCnt;  // else
        }
    }
}
