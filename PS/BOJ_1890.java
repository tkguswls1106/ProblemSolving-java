import java.util.*;
import java.io.*;

// - 알고리즘: DP

public class BOJ_1890 {
    public static int[][] arr = new int[102][102];
    public static long[][] dp = new long[102][102];  // 해당 위치에 도착하는 경로의 개수 ('경로의 개수는 263-1보다 작거나 같다.' 조건을 고려해 long타입 설정.)
    public static int[] dx = { 1, 0 };
    public static int[] dy = { 0, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer stt;
        for(int i=0; i<n; i++) {
            stt = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                int dist = Integer.parseInt(stt.nextToken());
                arr[i][j] = dist;
            }
        }

        dp[0][0] = 1;  // 첫좌표는 본인이 본인 하나의 경로로 가능하므로, 1로 초기화.

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                int dist = arr[i][j];
                if(dist == 0) continue;  // 도착지일때 다시 오른쪽이랑 아래 이동하면, 잘못 2회가 추가되어 틀림.

                // next
                for(int k=0; k<2; k++) {
                    int nx = i + (dx[k] * dist);
                    int ny = j + (dy[k] * dist);
                    if(nx<0 || nx>=n || ny<0 || ny>=n) continue;
                    dp[nx][ny] += dp[i][j];
                }
            }
        }

        System.out.print(dp[n-1][n-1]);
    }
}
