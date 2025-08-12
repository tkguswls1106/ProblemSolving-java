import java.util.*;
import java.io.*;
import java.awt.*;  // Point

// [ BFS 그림 문제 (Flood Fill) - 기초 ]
// - BFS vs 다익스트라 vs 플로이드 워셜 :
// * BFS : '단순 최단 이동거리 혹은 이동횟수를 구하는 것'을 목적으로 함.
// * 다익스트라, 플로이드 워셜 : '각 이동 간의 비용을 고려해 최소한의 힘으로 가장 빠른 길을 찾는 것'을 목적으로 함.
//   * 다익스트라 : '하나의 정점'에서 '다른 모든 정점'으로의 최단 경로를 구하는 알고리즘. 시간복잡도 O(ElogV)
//   * 플로이드 워셜 : '모든 정점'에서 '다른 모든 정점'으로의 최단 경로를 구하는 알고리즘. 시간복잡도 O(V^3)

public class BFS_BOJ_1926 {  // 주의사항: 백준 제출시엔 Main 클래스로 네이밍 변경할것.
    public static int n, m;
    public static int[][] board, visited;
    public static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};

    public static int cnt = 0, maxArea = 0;
    public static void bfs(int x, int y) {
        if(board[x][y] == 0 || visited[x][y] == 1) return;

        Queue<Point> qu = new LinkedList<>();
        qu.offer(new Point(x, y));
        visited[x][y] = 1;
        int sumArea = 1;  // 방문 성공으로, sumArea값 0->1

        while(!qu.isEmpty()) {
            Point cur = qu.poll();

            for(int i=0; i<4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
                if(board[nx][ny] == 0 || visited[nx][ny] == 1) continue;

                qu.offer(new Point(nx, ny));
                visited[nx][ny] = 1;
                sumArea++;
            }
        }

        cnt++;  // 그림개수 추가.
        maxArea = Math.max(maxArea, sumArea);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));  // bw는 안쓸만한 문제들도 있음. 상황에 맞추어 활용할것.

        // String nm = br.readLine();
        // String[] nmArr = nm.split(" ");
        // n = Integer.parseInt(nmArr[0]);
        // m = Integer.parseInt(nmArr[1]);
        StringTokenizer stt = new StringTokenizer(br.readLine());  // 또는 new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(stt.nextToken());
        m = Integer.parseInt(stt.nextToken());

        board = new int[n][m];
        visited = new int[n][m];

        for(int i=0; i<n; i++) {
            stt = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) {
                board[i][j] = Integer.parseInt(stt.nextToken());
            }
        }

        // 그림이 끊긴상태로 여러개가 존재하므로, 멀리 떨어진 다른 그림도 찾고자 BFS를 좌표(i,j)을 시작점으로 계속 바꿔주며 호출.
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                bfs(i, j); 
            }
        }

        // 사실 이 문제는, 출력량이 많지않아서 System.out.println 사용해도 상관없긴함.
        bw.write(String.format("%d\n%d", cnt, maxArea));  // 출력량이 많지않아, StringBuilder 말고 String.format 사용했음.
        bw.close();  // + bw.flush();
    }
}
