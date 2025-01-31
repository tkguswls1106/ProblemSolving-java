import java.util.*;
import java.io.*;
import java.awt.Point;

// - 알고리즘: BFS

public class BOJ_2667 {
    public static int n;
    public static int[][] board, visited;
    public static int[] dx = {1,0,-1,0}, dy = {0,1,0,-1};
    public static int areaCnt = 0;
    public static List<Integer> areaSumList = new ArrayList<>();

    public static void bfs(int x, int y) {
        if(board[x][y] == 0 || visited[x][y] == 1) return;

        Queue<Point> qu = new LinkedList<>();
        qu.offer(new Point(x,y));
        visited[x][y] = 1;
        int areaSum = 1;

        while(!qu.isEmpty()) {
            Point cur = qu.poll();

            for(int i=0; i<4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx<0 || nx>=n || ny<0 || ny>=n) continue;
                if(board[nx][ny] == 0 || visited[nx][ny] == 1) continue;

                qu.offer(new Point(nx,ny));
                visited[nx][ny] = 1;
                areaSum++;
            }
        }

        areaCnt++;
        areaSumList.add(areaSum);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        visited = new int[n][n];
        for(int i=0; i<n; i++) {
            int col = 0;
            String str = br.readLine();
            for(char ch : str.toCharArray()) {
                board[i][col++] = ch - '0';
            }
        }

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                bfs(i, j);
            }
        }

        stb.append(areaCnt).append("\n");
        Collections.sort(areaSumList);
        for(int areaSum : areaSumList) {
            stb.append(areaSum).append("\n");
        }

        System.out.print(stb.toString());
    }
}
