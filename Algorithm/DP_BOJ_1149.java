import java.util.*;
import java.io.*;

// [ DP RGB 문제 ]

public class DP_BOJ_1149 {
    // - dp 테이블 정의 :
    // dp[i][k] = i번째 집까지 칠할때의 최솟값을 의미. 단 i번째집은 k색상으로 칠함.
    public static int[][] dp = new int[1002][3];  // 0: R, 1: G, 2: B

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // - 초기값 할당 :
        StringTokenizer stt = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(stt.nextToken());
        int g = Integer.parseInt(stt.nextToken());
        int b = Integer.parseInt(stt.nextToken());
        dp[1][0] = r;
        dp[1][1] = g;
        dp[1][2] = b;

        // - dp값 세팅 (with 점화식) :
        for(int i=2; i<=n; i++) {  // 초기값 이후부터.
            stt = new StringTokenizer(br.readLine());
            r = Integer.parseInt(stt.nextToken());
            g = Integer.parseInt(stt.nextToken());
            b = Integer.parseInt(stt.nextToken());

            dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + r;  // 현재: R, 이전: G or B
            dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + g;  // 현재: G, 이전: R or B
            dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + b;  // 현재: B, 이전: R or G
        }

        int answer = Math.min(dp[n][0], Math.min(dp[n][1], dp[n][2]));
        System.out.print(answer);
    }
}
