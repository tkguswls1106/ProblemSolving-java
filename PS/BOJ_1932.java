import java.util.*;
import java.io.*;

// - 알고리즘: DP

public class BOJ_1932 {
    public static int n;
    public static int[][] board, dp;
    public static int[] dx = {1,1}, dy = {0,1};  // 밑으로 한칸 or 밑오른쪽대각선으로 한칸

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new int[n+1][n+1];
        dp = new int[n+1][n+1];

        StringTokenizer stt;
        for(int i=1; i<=n; i++) {
            stt = new StringTokenizer(br.readLine());
            for(int j=1; j<=i; j++) {
                int inputNum = Integer.parseInt(stt.nextToken());
                board[i][j] = inputNum;
            }
        }
        
        if(n == 1) {
            System.out.print(board[1][1]);
            return;
        }
        
        dp[1][1] = board[1][1];
        for(int i=1; i<=n-1; i++) {
            for(int j=1; j<=i; j++) {
                for(int k=0; k<2; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    dp[nx][ny] = Math.max(dp[nx][ny], dp[i][j] + board[nx][ny]);
                }
            }
        }

        int maxSum = -1;
        for(int col=1; col<=n; col++) {
            maxSum = Math.max(maxSum, dp[n][col]);
        }

        System.out.print(maxSum);
    }
}
