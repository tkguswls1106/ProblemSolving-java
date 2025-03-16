import java.util.*;
import java.io.*;
import java.awt.Point;

// - 알고리즘: BFS

public class BOJ_2583 {
    public static int n, m;  // 반대 순서로 입력받을 예정.
    public static int[][] board = new int[102][102];
    public static boolean[][] visited = new boolean[102][102];
    public static int[] dx = {1, 0, -1, 0};
    public static int[] dy = {0, 1, 0, -1};
    public static List<Integer> areaList = new ArrayList<>();

    public static void bfs(int x, int y) {
        if(board[x][y] == 1 || visited[x][y] == true) return;

        Queue<Point> qu = new LinkedList<>();
        qu.offer(new Point(x, y));
        visited[x][y] = true;
        int areaSum = 1;

        while(!qu.isEmpty()) {
            Point cur = qu.poll();

            for(int i=0; i<4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
                if(board[nx][ny] == 1 || visited[nx][ny] == true) continue;

                qu.offer(new Point(nx, ny));
                visited[nx][ny] = true;
                areaSum++;
            }
        }

        areaList.add(areaSum);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();

        StringTokenizer stt = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stt.nextToken());
        m = Integer.parseInt(stt.nextToken());
        int k = Integer.parseInt(stt.nextToken());

        while(k-- > 0) {
            stt = new StringTokenizer(br.readLine());  // y1 x1 y2 x2
            int y1 = Integer.parseInt(stt.nextToken());
            int x1 = Integer.parseInt(stt.nextToken());
            int y2 = Integer.parseInt(stt.nextToken()) - 1;  // 모눈종이의 직사각형 좌표임을 주의할것.
            int x2 = Integer.parseInt(stt.nextToken()) - 1;  // 모눈종이의 직사각형 좌표임을 주의할것.
            for(int i=x1; i<=x2; i++) {
                for(int j=y1; j<=y2; j++) {
                    board[i][j] = 1;
                }
            }
        }

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                bfs(i, j);
            }
        }

        stb.append(areaList.size()).append("\n");
        Collections.sort(areaList);
        for(int area : areaList) {
            stb.append(area).append(" ");
        }

        System.out.print(stb.toString());
    }
}
