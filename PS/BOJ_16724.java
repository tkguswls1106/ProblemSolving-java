import java.util.*;
import java.io.*;

// - 알고리즘 : DFS
// - 문제 Tip :
// 만약 새로 이동한곳이 이전의 싸이클이었다면, 함께 편입되는 경로로 간주할 수 있기에 safezoneCnt를 증가시키면 안됨.
// ==> 즉, 방문여부뿐만 아니라 싸이클여부도 확인해주어야함을 의미.

public class BOJ_16724 {
    public static int n, m;
    public static int[][] board = new int[1002][1002];
    public static int[][] visited = new int[1002][1002];  // 0: 미방문, 1: 방문O 싸이클X, 2: 방문O 사이클O
    public static int[] dx = { 1, 0, -1, 0 };  // D R U L
    public static int[] dy = { 0, 1, 0, -1 };
    public static int safezoneCnt = 0;

    public static void dfs(int x, int y) {
        int moveDir = board[x][y];
        int nx = x + dx[moveDir];
        int ny = y + dy[moveDir];

        if(visited[nx][ny] == 0) {  // 다음 위치도 미방문상태이므로, 계속 이동.
            visited[nx][ny] = 1;
            dfs(nx, ny);
        }
        else if(visited[nx][ny] == 1) {  // 다음 위치에서 현재 경로가 맞닿는 새로운 싸이클을 발견한것이므로, safezoneCnt 증가.
            safezoneCnt++;
        }

        // 이 코드지점은 DFS 경로 탐색이 끝난 시점임. 따라서 싸이클 여부를 승인해줘도 무관함.
        // (어찌되었든 최종적으로 현재 위치는 기존 싸이클에 편입되거나 새로운 싸이클이 될 것이므로.)
        visited[x][y] = 2;  // 주의: 현재 위치를 기준으로 싸이클여부 승인. visited[nx][ny]에 할당하면 틀림.
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stt.nextToken());
        m = Integer.parseInt(stt.nextToken());

        for(int i=0; i<n; i++) {
            String rowStr = br.readLine();
            for(int j=0; j<m; j++) {
                char colCh = rowStr.charAt(j);
                int moveDir;
                if(colCh == 'D') moveDir = 0;
                else if(colCh == 'R') moveDir = 1;
                else if(colCh == 'U') moveDir = 2;
                else moveDir = 3;  // 'L'
                board[i][j] = moveDir;
            }
        }

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(visited[i][j] != 0) continue;
                visited[i][j] = 1;
                dfs(i, j);
            }
        }

        System.out.print(safezoneCnt);
    }
}
