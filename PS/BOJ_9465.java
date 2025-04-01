import java.util.*;
import java.io.*;

// - 알고리즘: DP

public class BOJ_9465 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();
        int tc = Integer.parseInt(br.readLine());

        while(tc-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[][] arr = new int[2][n];
            int[][] dp = new int[2][n];  // 해당 가로줄 선택기준

            for(int i=0; i<2; i++) {
                StringTokenizer stt = new StringTokenizer(br.readLine());
                for(int j=0; j<n; j++) {
                    int num = Integer.parseInt(stt.nextToken());
                    arr[i][j] = num;
                }
            }

            // 점화식 찾기 (참고로 dp 배열은 가로줄 기준이기에 다른줄을 선택해야함.)
            // X: 선택불가, S: 현재선택, O: S선택시 가능한 이전의 자리
            // - 윗줄 선택시 :
            // ? X S
            // O O X
            // => dp[0][j] = max(dp[1][j-2], dp[1][j-1]) + arr[0][j]
            // - 아랫줄 선택시 :
            // O O X
            // ? X S
            // => dp[1][j] = max(dp[0][j-2], dp[0][j-1]) + arr[1][j]

            // 초기값 설정
            dp[0][0] = arr[0][0];
            dp[1][0] = arr[1][0];
            if(n >= 2) {
                dp[0][1] = dp[1][0] + arr[0][1];
                dp[1][1] = dp[0][0] + arr[1][1];
            }

            // DP
            for(int j=2; j<n; j++) {
                dp[0][j] = Math.max(dp[1][j-2], dp[1][j-1]) + arr[0][j];
                dp[1][j] = Math.max(dp[0][j-2], dp[0][j-1]) + arr[1][j];
            }
            stb.append(Math.max(dp[0][n-1], dp[1][n-1])).append("\n");
        }

        System.out.print(stb.toString());
    }
}
