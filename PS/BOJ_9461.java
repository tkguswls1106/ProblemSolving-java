import java.util.*;
import java.io.*;

// - 알고리즘: DP

public class BOJ_9461 {
    public static long[] dp = new long[102];
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();
        int tc = Integer.parseInt(br.readLine());

        // - 점화식 찾기
        // dp[i] = dp[i-3] + dp[i-2]

        // - 초기값 정의
        dp[1] = dp[2] = dp[3] = 1;

        // - dp값 세팅
        for(int i=4; i<=100; i++) {
            dp[i] = dp[i-3] + dp[i-2];
        }

        while(tc-- > 0) {
            int n = Integer.parseInt(br.readLine());
            stb.append(dp[n]).append("\n");
        }

        System.out.print(stb.toString());
    }
}
