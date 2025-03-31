import java.util.*;
import java.io.*;
import java.awt.Point;

// - 알고리즘: BFS + 조합(백트래킹)

public class BOJ_14502 {
    public static List<Point> list = new ArrayList<>();
    public static List<Point> selected = new ArrayList<>();
    public static void comb(int start, int cnt) {  // 벽세울 빈칸 위치로 조합 실시.
        if(cnt == 3) {  // 벽 3개 세우기.
            bfs();
            return;
        }

        for(int i=start; i<list.size(); i++) {
            selected.add(list.get(i));
            comb(i+1, cnt+1);
            
            selected.remove(selected.size()-1);
        }
    }

    public static int n, m;
    public static int[][] board = new int[10][10];
    public static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};
    public static int maxAreaSum = 0;
    public static void bfs() {
        // 원본 board 배열 복사
        int[][] tempBoard = new int[10][10];
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                tempBoard[i][j] = board[i][j];
            }
        }

        // 벽 세우기
        for(Point selectCur : selected) {
            tempBoard[selectCur.x][selectCur.y] = 1;
        }

        // 바이러스 위치를 큐에 넣기
        Queue<Point> qu = new LinkedList<>();
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if(tempBoard[i][j] == 2) qu.offer(new Point(i, j));
            }
        }
        
        // BFS
        while(!qu.isEmpty()) {
            Point cur = qu.poll();

            for(int i=0; i<4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
                if(tempBoard[nx][ny] != 0) continue;  // 빈칸이 아니면 패스

                qu.offer(new Point(nx, ny));
                tempBoard[nx][ny] = 2;  // 바이러스 확산
            }
        }

        // 안전 영역 크기 계산
        int areaSum = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(tempBoard[i][j] == 0) areaSum++;
            }
        }

        maxAreaSum = Math.max(maxAreaSum, areaSum);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stt.nextToken());
        m = Integer.parseInt(stt.nextToken());

        for(int i=0; i<n; i++) {
            stt = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) {
                board[i][j] = Integer.parseInt(stt.nextToken());
                if(board[i][j] == 0) list.add(new Point(i, j));  // 빈칸 위치 저장
            }
        }
        
        comb(0, 0);

        System.out.print(maxAreaSum);
    }
}
