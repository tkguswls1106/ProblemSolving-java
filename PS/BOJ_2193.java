import java.util.*;

// - 알고리즘: DP

public class BOJ_2193 {
    public static long[][] dp = new long[92][2];  // dp[i][j] = i자리일때 j(0,1)로 끝나는 이친수의 개수

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        // 점화식 찾기 :
        // dp[i][0] = dp[i-1][0 or 1] = dp[i-1][0] + dp[i-1][1]
        // dp[i][1] = dp[i-1][0]

        if(n == 1) {
            System.out.print(1);
            return;
        }

        // 초기값 정의 :
        dp[1][0] = 0;  // 맨앞자리에는 0이 올 수 없음.
        dp[1][1] = 1;

        // dp값 세팅 :
        for(int i=2; i<=n; i++) {
            dp[i][0] = dp[i-1][0] + dp[i-1][1];
            dp[i][1] = dp[i-1][0];
        }

        long answer = dp[n][0] + dp[n][1];
        System.out.print(answer);
    }
}
