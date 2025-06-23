import java.util.*;
import java.io.*;

// - 알고리즘 : DFS + DP (탑다운 방식)
// - 문제 Tip :
// 만약 BFS와 유사한 DFS + 백트래킹 방식으로 푸는 경우, 이동가능한 방향이 사방인 4가지이므로,
// 시간복잡도가 최대 'O(4^(NxM)) = O(4^(500x500))'로서 2초의 시간제한을 초과하게되어 틀림.
// ==> 'DFS + 백트래킹 대신 DP' 알고리즘으로 풀이할것.

public class BOJ_1520 {
    public static int n, m;
    public static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};
    public static int[][] arr = new int[502][502];
    public static int[][] dp = new int[502][502];

    public static int dfsDp(int x, int y) {  // DFS + DP (탑다운 방식)
        if(x==n-1 && y==m-1) {
            return 1;  // 이는 만약 바텀업 방식의 DP 풀이였다면, 초기값 할당인 'dp[0][0]=1;'에 해당될것임.
        }
        if(dp[x][y] != -1) {
            return dp[x][y];  // 이미 계산된적 있는 위치라면 그대로 반환.
        }
        else {
            dp[x][y] = 0;  // 이전에 계산여부를 확인하고자 첫 -1로 초기화했으므로, 이제 실질적인 경로 계산을 위한 0으로 다시 초기화하여 시작.
        }

        int height = arr[x][y];
        for(int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx<0 || nx>=n || ny<0 || ny>=m) continue;
            int nextHeight = arr[nx][ny];
            if(!(height > nextHeight)) continue;

            dp[x][y] += dfsDp(nx, ny);
        }
        return dp[x][y];  // 계산된 후
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stt = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stt.nextToken());  // 문제상으로는 m에 해당.
        m = Integer.parseInt(stt.nextToken());  // 문제상으로는 n에 해당.

        for(int i=0; i<n; i++) {
            stt = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) {
                int height = Integer.parseInt(stt.nextToken());
                arr[i][j] = height;
            }
        }

        for(int i=0; i<n; i++) {
            Arrays.fill(dp[i], -1);  // DP 탑다운 방식에서, 아직 계산된 적 없는 위치임을 알리기 위함.
        }

        int answer = dfsDp(0, 0);
        System.out.print(answer);
    }
}
