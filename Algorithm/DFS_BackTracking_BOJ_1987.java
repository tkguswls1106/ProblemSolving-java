// < 성공 방법 >
// - 알고리즘 : DFS + 백트래킹 + 전역 visited
// ('BOJ_1987_Fail' 실패 알고리즘 : BFS + 다중 HashSet)

import java.util.*;
import java.io.*;

// [ DFS with 백트래킹 ]
// - 코드 특징 :
// 이 문제는 얼핏보면 BFS로 보이지만, DFS를 사용해야함. (자세한 이유는 'BOJ_1987_Fail' 실패 코드를 참고.)
// 이처럼 DFS를 사용해야하지만 BFS의 탐색 특징도 필요할 경우,
// DFS에 백트래킹을 함께 활용하면, 마치 BFS의 Queue에서 현재 좌표(cur)를 기준으로 사방을 탐색하듯,
// DFS에서도 이전 좌표로 돌아가 다른 방향 경로도 마저 탐색해볼 수 있음.

public class DFS_BackTracking_BOJ_1987 {
    public static int n, m;
    public static char[][] board = new char[22][22];
    public static boolean[] chVisited = new boolean[26];  // A~Z 26가지. (이는 전역 HashSet로도 대체 가능하나, 성능이 저하됨.)
    public static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};
    public static int maxCnt = 0;

    public static int getChIdx(int x, int y) {
        return board[x][y] - 'A';
    }

    public static void dfs(int x, int y, int cnt) {
        maxCnt = Math.max(maxCnt, cnt);  // '이 코드줄' 대신 '주석 코드 2줄'을 사용해도됨.

        for(int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
            if(chVisited[getChIdx(nx, ny)] == true) continue;

            chVisited[getChIdx(nx, ny)] = true;
            // maxCnt = Math.max(maxCnt, cnt+1);
            dfs(nx, ny, cnt+1);  // DFS 재귀호출 (순열의 방식과 약간 유사함.)

            chVisited[getChIdx(nx, ny)] = false;  // 백트래킹 (방문여부를 다시 되돌려, 마치 BFS의 Queue cur처럼 이전 좌표에서의 다른 방향 경로도 마저 탐색해봄.)
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
                board[i][j] = inputCh;
            }
        }

        chVisited[getChIdx(0, 0)] = true;
        // maxCnt = 1;
        dfs(0, 0, 1);
        
        System.out.print(maxCnt);
    }
}
