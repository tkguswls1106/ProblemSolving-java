import java.util.*;
import java.io.*;

// - 알고리즘: DP 누적합

public class BOJ_11441 {
    public static int[] dp = new int[100002];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        StringTokenizer stt = new StringTokenizer(br.readLine());
        dp[1] = Integer.parseInt(stt.nextToken());
        for(int i=2; i<=n; i++) {
            dp[i] = dp[i-1] + Integer.parseInt(stt.nextToken());
        }

        n = Integer.parseInt(br.readLine());  // 문제상으로는 m
        int i, j, sum;
        while(n-- > 0) {
            stt = new StringTokenizer(br.readLine());
            i = Integer.parseInt(stt.nextToken());
            j = Integer.parseInt(stt.nextToken());
            sum = dp[j] - dp[i-1];
            stb.append(sum).append("\n");
        }

        System.out.print(stb.toString());
    }
}
