import java.util.*;
import java.io.*;

// - 알고리즘 : DP
// - 문제 Tip :
// DP 계단오르기 문제인 'DP_BOJ_2579' 문제와 유사함.
// 단, 이 문제는 마지막 요소를 꼭 포함해야한다는 조항이 없으니 주의할것!

public class BOJ_2156 {
    public static int[] arr = new int[10002];
    public static int[] dp = new int[10002];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for(int i=1; i<=n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        // 점화식 찾기
        // => OorX X = dp1 = dp[i-1] + X
        // => OorX X O = dp2 = dp[i-2] + X + arr[i]
        // => OorX X O O = dp3 = dp[i-3] + X + arr[i-1] + arr[i]
        
        // 초기값 할당
        dp[1] = arr[1];
        if(2 <= n) dp[2] = arr[1] + arr[2];
        if(3 <= n) {
            int exclude3 = dp[2];
            int include3 = Math.max(arr[1], arr[2]) + arr[3];
            
            dp[3] = Math.max(exclude3, include3);
        }

        int dp1, dp2, dp3;
        for(int i=4; i<=n; i++) {
            dp1 = dp[i-1];
            dp2 = dp[i-2] + arr[i];
            dp3 = dp[i-3] + arr[i-1] + arr[i];

            dp[i] = Math.max(Math.max(dp1, dp2), dp3);
        }

        System.out.print(dp[n]);
    }
}
